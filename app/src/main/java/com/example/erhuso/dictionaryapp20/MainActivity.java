package com.example.erhuso.dictionaryapp20;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //start app

        //create textview
        TextView dictTextView = (TextView) findViewById(R.id.dictionary_text_view);

        //get content resolver which will send a message to the content provider

        ContentResolver resolver = getContentResolver();

        //get a cursor containing all of the rows in the words table

        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null,null);

        Log.e("Test", UserDictionary.Words.CONTENT_URI.toString());

        try {

            dictTextView.setText("The UserDictionary contains " + cursor.getCount() + " words.\n");
            dictTextView.append("Columns: " + UserDictionary.Words._ID + " - " + UserDictionary.Words.FREQUENCY + " - " + UserDictionary.Words.WORD);


            //get the index of the columns using dictionary.words

            int idColumn = cursor.getColumnIndex(UserDictionary.Words._ID);
            int frequencyColumn = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY) ;
            int wordColumn = cursor.getColumnIndex(UserDictionary.Words.WORD);

            while (cursor.moveToNext())
            {
                //get data from columns
                int id = cursor.getInt(idColumn);
                int frequency = cursor.getInt(frequencyColumn);
                String word = cursor.getString(wordColumn);

                //append table
                dictTextView.append("\n" + id + " - " + frequency + " - " + word);
            }


        } finally {

            //close the cursor
            cursor.close();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
