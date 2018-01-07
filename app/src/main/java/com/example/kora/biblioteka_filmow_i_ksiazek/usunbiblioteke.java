package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by Kora on 2017-12-27.
 */

public class usunbiblioteke extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete,btnViewData;
    private EditText editable_item;

    databasehelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usunbiblioteke);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        mDatabaseHelper = new databasehelper(this);
        btnViewData = (Button) findViewById(R.id.btnLista);

        Intent receivedIntent = getIntent();


        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value


        selectedName = receivedIntent.getStringExtra("name");


        editable_item.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateNameLibrary(item,selectedID,selectedName);
                }else{
                    toastMessage("Musisz podać nazwe");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteNameLibrary(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("Biblioteka zostałą usunięta");
            }
        });
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usunbiblioteke.this, menu.class);
                startActivity(intent);
            }
        });
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

