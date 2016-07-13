package com.example.erhuso.dictionaryapp20;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //For the simplecursor adaptor to match user dictionary columns to layout items

    private static final String[] COLUMNS_TO_BE_BOUND = new String[] {
            UserDictionary.Words.WORD,
            UserDictionary.Words.FREQUENCY};

    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[] {
            android.R.id.text1,
            android.R.id.text2
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //start app

        //create List
        ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view);

        //get content resolver which will send a message to the content provider

        ContentResolver resolver = getContentResolver();

        //get a cursor containing all of the rows in the words table

        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null,null);

        Log.e("Test", UserDictionary.Words.CONTENT_URI.toString());

        //set cursor adaptor to fill standard two_line_list_item with layout from the cursor
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, COLUMNS_TO_BE_BOUND, LAYOUT_ITEMS_TO_FILL,0);

        //attach the adaptor to the list view
        dictListView.setAdapter(cursorAdapter);

//        try {
//
//            //get the index of the columns using dictionary.words
//
//            int idColumn = cursor.getColumnIndex(UserDictionary.Words._ID);
//            int frequencyColumn = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY) ;
//            int wordColumn = cursor.getColumnIndex(UserDictionary.Words.WORD);
//
//
//
//            dictListView.setText("The UserDictionary contains " + cursor.getCount() + " words.\n");
//            dictListView.append("Columns: " + UserDictionary.Words._ID + " - " + UserDictionary.Words.FREQUENCY + " - " + UserDictionary.Words.WORD);
//
//
//
//
//            while (cursor.moveToNext())
//            {
//                //get data from columns
//                int id = cursor.getInt(idColumn);
//                int frequency = cursor.getInt(frequencyColumn);
//                String word = cursor.getString(wordColumn);
//
//                //append table
//                dictListView.append("\n" + id + " - " + frequency + " - " + word);
//            }
//
//
//        } finally {
//
//            //close the cursor
//            cursor.close();
//
//        }

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
