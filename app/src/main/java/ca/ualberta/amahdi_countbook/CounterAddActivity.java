package ca.ualberta.amahdi_countbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 *
 * The  controller class for adding new counters
 * @see Counter
 */
public class CounterAddActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private EditText nameText;
    private EditText initialNumberText;
    private EditText counterCommentText;

    private ListView oldCountersList;

    private ArrayList<Counter> Counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;


    /**
     * Called when activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);


        nameText = (EditText) findViewById(R.id.name);
        initialNumberText = (EditText) findViewById(R.id.initialNumber);
        counterCommentText = (EditText) findViewById(R.id.counterComment);

        Button saveButton = (Button) findViewById(R.id.save);
        Button clearButton = (Button) findViewById(R.id.clear);
        oldCountersList = (ListView) findViewById(R.id.oldCountersList);


        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Counters.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
                nameText.setText("");
                initialNumberText.setText("");
                counterCommentText.setText("");

            }

        });


        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = nameText.getText().toString();
                String initNum = initialNumberText.getText().toString();
                String comment = counterCommentText.getText().toString();

                Counters.add(new Counter((text),(initNum),(comment)));
                adapter.notifyDataSetChanged();

                saveInFile();

                nameText.setText("");
                initialNumberText.setText("");
                counterCommentText.setText("");

            }
        });

    }

    /**
     * Called when activity starts.
     */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this,
                R.layout.list_item, Counters);
        oldCountersList.setAdapter(adapter);
        oldCountersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    /**
     * Reads Counter content from an external Gson file.
     *
     * @throws RuntimeException
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>() {
            }.getType();
            Counters = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Counters = new ArrayList<Counter>();
        }
    }

    /**
     * Saves Counter content to an external file in Gson format.
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(Counters, writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}