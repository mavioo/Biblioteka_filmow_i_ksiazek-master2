package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Kora on 2018-01-07.
 */

public class ksiazkaszczegoly extends AppCompatActivity {
    databasehelper mDatabaseHelper;

    private TextView tytul1, autor, iloscstron, rok1, gatunek1, opis1;
    int idbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ksiazkaszczegoly);
        Intent intent = getIntent();
        idbook = intent.getIntExtra("id",0);
        tytul1 = (TextView) findViewById(R.id.tytulF1);
        autor = (TextView) findViewById(R.id.rezyserF1);
        iloscstron = (TextView) findViewById(R.id.CzasF1);
        rok1 = (TextView) findViewById(R.id.rokF1);
        gatunek1 = (TextView) findViewById(R.id.gatunekF1);
        opis1 = (TextView) findViewById(R.id.opisF1);
        mDatabaseHelper = new databasehelper(this);

        getbookDetails();
    }

    private void getbookDetails() {
        Cursor data = mDatabaseHelper.getbook(idbook);

        while(data.moveToNext()){

            tytul1.setText(data.getString(1));
            autor.setText(data.getString(2));
            iloscstron.setText(data.getString(3));
            rok1.setText(data.getString(4));
            gatunek1.setText(data.getString(5));
            opis1.setText(data.getString(6));
        }
    }

}
