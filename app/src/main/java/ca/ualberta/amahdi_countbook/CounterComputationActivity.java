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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 *
 * The controller class of the actual counter
 * @see Counter
 */
public class CounterComputationActivity extends Activity {

    private static final String FILENAME = "file.sav";

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
        setContentView(R.layout.counter_display);


        Button addButton = (Button) findViewById(R.id.increment);
        Button minusButton = (Button) findViewById(R.id.decrement);
        Button resetButton = (Button) findViewById(R.id.resetCounter);
        Button deleteButton = (Button) findViewById(R.id.deleteCounter);
        oldCountersList = (ListView) findViewById(R.id.oldCountersList);

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