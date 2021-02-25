package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Ejercicio2Activity extends AppCompatActivity {

    Button btnSend, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        btnSend = findViewById(R.id.btnSend);
        btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(v -> {
            finish();
        });
    }
}