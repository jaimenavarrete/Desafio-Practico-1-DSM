package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnEjercicio1, btnEjercicio2, btnEjercicio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEjercicio1 = findViewById(R.id.btnEjercicio1);
        btnEjercicio2 = findViewById(R.id.btnEjercicio2);
        btnEjercicio3 = findViewById(R.id.btnEjercicio3);

        btnEjercicio1.setOnClickListener(v -> {
            Intent i = new Intent(this, Exercise1Activity.class);
            startActivity(i);
        });

        btnEjercicio2.setOnClickListener(v -> {
            Intent i = new Intent(this, Exercise2Activity.class);
            startActivity(i);
        });

        btnEjercicio3.setOnClickListener(v -> {
            Intent i = new Intent(this, Exercise3Activity.class);
            startActivity(i);
        });
    }
}