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

public class dodajksiazke extends AppCompatActivity {

    databasehelper mDatabaseHelper;
    private Button btnAdd,btnViewData2;
    private EditText tytul1, autor, iloscstron, rok1, gatunek1, opis1;
    int idBiblioteki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodajksiazke);
        btnViewData2 = (Button) findViewById(R.id.lista2);
        Intent intent = getIntent();
        idBiblioteki = intent.getIntExtra("id",0);
        tytul1 = (EditText) findViewById(R.id.tytul2);
        autor = (EditText) findViewById(R.id.rezyser2);
        iloscstron = (EditText) findViewById(R.id.Czas2);
        rok1 = (EditText) findViewById(R.id.rok2);
        gatunek1 = (EditText) findViewById(R.id.gatunek2);
        opis1 = (EditText) findViewById(R.id.opisF2);
        btnAdd = (Button) findViewById(R.id.dodajdof2);
        mDatabaseHelper = new databasehelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = tytul1.getText().toString();
                String r = autor.getText().toString();
                String c = iloscstron.getText().toString();
                String ro = rok1.getText().toString();
                String g = gatunek1.getText().toString();
                String o = opis1.getText().toString();
                if (t.length() != 0 && r.length() != 0 && c.length() != 0 && ro.length() != 0 && g.length() != 0 && o.length() != 0) {
                    AddData(t,r,c,ro,g,o);
                    tytul1.setText("");
                    autor.setText("");
                    iloscstron.setText("");
                    rok1.setText("");
                    gatunek1.setText("");
                    opis1.setText("");
                } else {
                    toastMessage("To pole nie może być puste!");
                }

            }
        });
        btnViewData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dodajksiazke.this, index.class);
                intent.putExtra("id",idBiblioteki);
                startActivity(intent);
            }
        });
    }

    public void AddData(String tytul1,String autor,String iloscstron,String rok1,String gatunek1,String opis1) {
        boolean insertData = mDatabaseHelper.addDatabook(tytul1,autor,iloscstron,rok1,gatunek1,opis1,idBiblioteki);

        if (insertData) {
            toastMessage("Ksiazka została dodana");
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