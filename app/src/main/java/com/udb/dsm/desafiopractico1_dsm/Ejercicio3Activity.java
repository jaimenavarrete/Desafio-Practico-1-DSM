package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Ejercicio3Activity extends AppCompatActivity {

    EditText editTextName1, editTextName2, editTextName3, editTextLastName1, editTextLastName2, editTextLastName3, editTextYears1, editTextYears2, editTextYears3;
    Spinner spinnerRole1, spinnerRole2, spinnerRole3;
    Button btnSend, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        editTextName1 = findViewById(R.id.editTextName1);
        editTextName2 = findViewById(R.id.editTextName2);
        editTextName3 = findViewById(R.id.editTextName3);
        editTextLastName1 = findViewById(R.id.editTextLastName1);
        editTextLastName2 = findViewById(R.id.editTextLastName2);
        editTextLastName3 = findViewById(R.id.editTextLastName3);
        spinnerRole1 = findViewById(R.id.spinnerRole1);
        spinnerRole2 = findViewById(R.id.spinnerRole2);
        spinnerRole3 = findViewById(R.id.spinnerRole3);
        editTextYears1 = findViewById(R.id.editTextYears1);
        editTextYears2 = findViewById(R.id.editTextYears2);
        editTextYears3 = findViewById(R.id.editTextYears3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole1.setAdapter(adapter);
        spinnerRole2.setAdapter(adapter);
        spinnerRole3.setAdapter(adapter);

        btnSend = findViewById(R.id.btnSend);
        btnFinish = findViewById(R.id.btnFinish);

        btnSend.setOnClickListener(v -> {
            // Employee 1
            String[] employee1 = new String[]{ editTextName1.getText().toString(), editTextLastName1.getText().toString(), String.valueOf(spinnerRole1.getSelectedItem()), editTextYears1.getText().toString() };
            // Employee 2
            String[] employee2 = new String[]{ editTextName2.getText().toString(), editTextLastName2.getText().toString(), String.valueOf(spinnerRole2.getSelectedItem()), editTextYears2.getText().toString() };
            // Employee 3
            String[] employee3 = new String[]{ editTextName3.getText().toString(), editTextLastName3.getText().toString(), String.valueOf(spinnerRole3.getSelectedItem()), editTextYears3.getText().toString() };

            if(isEmpty(employee1) || isEmpty(employee2) || isEmpty(employee3)) {
                Toast toastMessage = Toast.makeText(this,"Tiene que rellenar todos los campos de cada empleado",Toast.LENGTH_LONG);
                toastMessage.show();
            }
            else {

            }
        });

        btnFinish.setOnClickListener(v -> {
            finish();
        });
    }

    private boolean isEmpty(String[] employee) {
        for(String data : employee) {
            if(data.equals("")) {
                return true;
            }
        }
        return false;
    }
}