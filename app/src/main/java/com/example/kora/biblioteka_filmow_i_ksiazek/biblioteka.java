package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kora on 2017-12-28.
 */

public class biblioteka extends AppCompatActivity {
    private static final String TAG = "ListDataActivity";

    databasehelper mDatabaseHelper;

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biblioteka);
        Button buttonActivity3 = (Button)findViewById(R.id.usun);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new databasehelper(this);

        buttonActivity3.setOnClickListener(new biblioteka.StartNewActivity3());
        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");


        Cursor data = mDatabaseHelper.getDataLibrary();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){

            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemIDLibrary(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(biblioteka.this, index.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("Nie ma takiejnazwy");
                }
            }
        });
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private class StartNewActivity3 implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(biblioteka.this, menu.class);
            startActivity(intent);
        }
    }
}
