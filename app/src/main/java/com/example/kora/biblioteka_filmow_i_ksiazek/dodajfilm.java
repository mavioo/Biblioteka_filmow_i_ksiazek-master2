package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Kora on 2017-12-29.
 */

public class dodajfilm extends AppCompatActivity {

    databasehelper mDatabaseHelper;
    private Button btnAdd,btnViewData2;
    private EditText tytul, rezyser, czas, rok, gatunek, opis;
    int idBiblioteki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodajfilm);
        btnViewData2 = (Button) findViewById(R.id.lista2);
        Intent intent = getIntent();
        idBiblioteki = intent.getIntExtra("id",0);
        tytul = (EditText) findViewById(R.id.tytul);
        rezyser = (EditText) findViewById(R.id.rezyser);
        czas = (EditText) findViewById(R.id.Czas);
        rok = (EditText) findViewById(R.id.rok);
        gatunek = (EditText) findViewById(R.id.gatunek);
        opis = (EditText) findViewById(R.id.opisF);
        btnAdd = (Button) findViewById(R.id.dodajdof);
        mDatabaseHelper = new databasehelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = tytul.getText().toString();
                String r = rezyser.getText().toString();
                String c = czas.getText().toString();
                String ro = rok.getText().toString();
                String g = gatunek.getText().toString();
                String o = opis.getText().toString();
                if (t.length() != 0 && r.length() != 0 && c.length() != 0 && ro.length() != 0 && g.length() != 0 && o.length() != 0) {
                    AddData(t,r,c,ro,g,o);
                    tytul.setText("");
                    rezyser.setText("");
                    czas.setText("");
                    rok.setText("");
                    gatunek.setText("");
                    opis.setText("");
                } else {
                    toastMessage("To pole nie może być puste!");
                }

            }
        });
        btnViewData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dodajfilm.this, index.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String tytul,String rezyser,String czas,String rok,String gatunek,String opis) {
        boolean insertData = mDatabaseHelper.addDataFilm(tytul,rezyser,czas,rok,gatunek,opis,idBiblioteki);

        if (insertData) {
            toastMessage("Film został dodany");
        } else {
            toastMessage("Coś poszło nie tak");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        {
        }
    }
}