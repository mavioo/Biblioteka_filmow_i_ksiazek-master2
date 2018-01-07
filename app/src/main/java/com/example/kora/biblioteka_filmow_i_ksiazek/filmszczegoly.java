package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by asus on 06.01.2018.
 */

public class filmszczegoly extends AppCompatActivity {
    databasehelper mDatabaseHelper;

    private TextView tytul, rezyser, czas, rok, gatunek, opis;
    int idFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmszczegoly);
        Intent intent = getIntent();
        idFilm = intent.getIntExtra("id",0);
        tytul = (TextView) findViewById(R.id.tytulF);
        rezyser = (TextView) findViewById(R.id.rezyserF);
        czas = (TextView) findViewById(R.id.CzasF);
        rok = (TextView) findViewById(R.id.rokF);
        gatunek = (TextView) findViewById(R.id.gatunekF);
        opis = (TextView) findViewById(R.id.opisF);
        mDatabaseHelper = new databasehelper(this);

        getFilmDetails();
    }

    private void getFilmDetails() {
        Cursor data = mDatabaseHelper.getFilm(idFilm);

        while(data.moveToNext()){

            tytul.setText(data.getString(1));
            rezyser.setText(data.getString(2));
            czas.setText(data.getString(3));
            rok.setText(data.getString(4));
            gatunek.setText(data.getString(5));
            opis.setText(data.getString(6));
        }
    }

}
