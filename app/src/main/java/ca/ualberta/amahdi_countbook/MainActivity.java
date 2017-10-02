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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 *
 * The main controller class of CountBook
 * @author team X
 * @since 1.0
 * @version 1.5
 * @see Counter
 */
public class MainActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private EditText counterNameText;
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
        setContentView(R.layout.main);


        counterNameText = (EditText) findViewById(R.id.counterName);
        Button addCounterButton = (Button) findViewById(R.id.addCounter);
        oldCountersList = (ListView) findViewById(R.id.oldCountersList);

        /**
         * Takes user to a new window that allows adding new counters
         */
        addCounterButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CounterAddActivity.class);
                startActivity(intent);
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
             throw new RuntimeException(e);
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
