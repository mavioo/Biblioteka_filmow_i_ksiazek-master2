package com.example.kora.biblioteka_filmow_i_ksiazek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonActivity2 = (Button)findViewById(R.id.enter);
        // do odkomentowanie jak dodajesz nowa tabele
       //   getBaseContext().deleteDatabase("people_table");

        buttonActivity2.setOnClickListener(new StartNewActivity2());

    }

    private class StartNewActivity2 implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, biblioteka.class);
            startActivity(intent);
        }
    }
    }
