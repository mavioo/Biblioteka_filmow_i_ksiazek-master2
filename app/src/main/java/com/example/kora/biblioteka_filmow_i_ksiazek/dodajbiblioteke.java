package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
/**
 * Created by Kora on 2017-12-27.
 */

public class dodajbiblioteke extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    databasehelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodajbiblioteke);
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new databasehelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("To pole nie może być puste!");
                }

            }
        });
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dodajbiblioteke.this, menu.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addDataLibrary(newEntry);

        if (insertData) {
            toastMessage("Biblioteka została dodana");
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