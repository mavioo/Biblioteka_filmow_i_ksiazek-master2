package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Kora on 2017-12-29.
 */

public class index extends AppCompatActivity {
    int idBiblioteki;
    private ListView mListViewFilm;
    private ListView mListViewBook;
    databasehelper mDatabaseHelper;
    Map<Integer, Integer> mapFilm = new HashMap<>();
    Map<Integer, Integer> mapBook = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        idBiblioteki = intent.getIntExtra("id",0);
        setContentView(R.layout.index);
        Button buttonActivity4 = (Button)findViewById(R.id.dodajf);
        buttonActivity4.setOnClickListener(new index.StartNewActivity4());

        Button buttonActivity5 = (Button)findViewById(R.id.dodajk);
        buttonActivity5.setOnClickListener(new index.StartNewActivity5());

        mListViewFilm = (ListView) findViewById(R.id.filmList);
        mListViewBook = (ListView) findViewById(R.id.bookList);
        mDatabaseHelper = new databasehelper(this);

        populateListViewFilm();
        populateListViewBook();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        populateListViewFilm();
        populateListViewBook();

    }

    private void populateListViewFilm() {
        Cursor data = mDatabaseHelper.getFilmsFromLibrary(idBiblioteki);
        ArrayList<String> listData = new ArrayList<>();
        int i = 0;
        while(data.moveToNext()){
            mapFilm.put(i,data.getInt(0));
            listData.add(data.getString(1));
            i++;
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListViewFilm.setAdapter(adapter);

        mListViewFilm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = mapFilm.get(i) != null ? mapFilm.get(i) : -1;

                if(id > -1){
                    Intent editScreenIntent = new Intent(index.this, filmszczegoly.class);
                    editScreenIntent.putExtra("id",id);
                    startActivity(editScreenIntent);
                }
            }
        });
    }

    private void populateListViewBook() {
        Cursor data2 = mDatabaseHelper.getbookFromLibrary(idBiblioteki);
        ArrayList<String> listData = new ArrayList<>();
        int ii = 0;
        while(data2.moveToNext()){
            mapBook.put(ii,data2.getInt(0));
            listData.add(data2.getString(1));
            ii++;
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListViewBook.setAdapter(adapter);


        mListViewBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = mapBook.get(i) != null ? mapBook.get(i) : -1;

                if(id > -1){
                    Intent editScreenIntent = new Intent(index.this, ksiazkaszczegoly.class);
                    editScreenIntent.putExtra("id",id);
                    startActivity(editScreenIntent);
                }
            }
        });
    }


    private class StartNewActivity4 implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(index.this, dodajfilm.class);
            intent.putExtra("id",idBiblioteki);
            startActivity(intent);
        }
    }
    private class StartNewActivity5 implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(index.this, dodajksiazke.class);
            intent.putExtra("id",idBiblioteki);
            startActivity(intent);
        }
    }
}