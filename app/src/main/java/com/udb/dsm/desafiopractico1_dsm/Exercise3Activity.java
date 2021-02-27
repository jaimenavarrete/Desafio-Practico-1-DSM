package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Exercise3Activity extends AppCompatActivity {

    EditText editTextName1, editTextName2, editTextName3, editTextLastName1, editTextLastName2, editTextLastName3, editTextHours1, editTextHours2, editTextHours3;
    Spinner spinnerRole1, spinnerRole2, spinnerRole3;
    Button btnSend, btnFinish;

    double hourValue = 9.75, specialHourValue = 11.50, AFP = 0.0688, ISSS = 0.0525, Rent = 0.1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        // Variables declaration
        elementsDeclaration();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole1.setAdapter(adapter);
        spinnerRole2.setAdapter(adapter);
        spinnerRole3.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {
            // Employee -> Name - Last Name - Role - Worked hours
            // EmployeeValues -> Base salary - AFP discount - ISSS discount - Rent discount - Liquid salary

            // Employee 1
            String[] employee1 = new String[]{
                    editTextName1.getText().toString(),
                    editTextLastName1.getText().toString(),
                    String.valueOf(spinnerRole1.getSelectedItem()),
                    editTextHours1.getText().toString()
            };

            // Employee 2
            String[] employee2 = new String[]{
                    editTextName2.getText().toString(),
                    editTextLastName2.getText().toString(),
                    String.valueOf(spinnerRole2.getSelectedItem()),
                    editTextHours2.getText().toString()
            };

            // Employee 3
            String[] employee3 = new String[]{
                    editTextName3.getText().toString(),
                    editTextLastName3.getText().toString(),
                    String.valueOf(spinnerRole3.getSelectedItem()),
                    editTextHours3.getText().toString()
            };

            if(isEmpty(employee1) || isEmpty(employee2) || isEmpty(employee3)) {
                Toast toastMessage = Toast.makeText(this,"Tiene que rellenar todos los campos de cada empleado",Toast.LENGTH_LONG);
                toastMessage.show();
                return;
            }
            if(Integer.parseInt(employee1[3]) <= 0 || Integer.parseInt(employee2[3]) <= 0 || Integer.parseInt(employee3[3]) <= 0) {
                Toast toastMessage = Toast.makeText(this,"Las horas trabajadas deben ser mayor a 0",Toast.LENGTH_LONG);
                toastMessage.show();
                return;
            }

            double[] employeeValues1 = calculateSalaryValues(employee1);
            double[] employeeValues2 = calculateSalaryValues(employee2);
            double[] employeeValues3 = calculateSalaryValues(employee3);

            Intent i = new Intent(this, ShowExercise3Activity.class);
            i.putExtra("employee1", employee1);
            i.putExtra("employeeValues1", employeeValues1);
            i.putExtra("employee2", employee2);
            i.putExtra("employeeValues2", employeeValues2);
            i.putExtra("employee3", employee3);
            i.putExtra("employeeValues3", employeeValues3);
            startActivity(i);
        });

        btnFinish.setOnClickListener(v -> {
            finish();
        });
    }

    private double[] calculateSalaryValues(String[] employee) {
        int hours = Integer.parseInt(employee[3]);
        double baseSalary, AFPDiscount, ISSSDiscount, RentDiscount, liquidSalary;

        // Base salary
        if(hours > 160) {
            baseSalary = 160.0 * hourValue + (hours - 160) * specialHourValue;
        }
        else {
            baseSalary = hours * hourValue;
        }

        AFPDiscount = baseSalary * AFP;
        ISSSDiscount = baseSalary * ISSS;
        RentDiscount = baseSalary * Rent;

        liquidSalary = baseSalary - AFPDiscount - ISSSDiscount - RentDiscount;

        return new double[]{ round(baseSalary), round(AFPDiscount), round(ISSSDiscount), round(RentDiscount), round(liquidSalary) };
    }

    private boolean isEmpty(String[] employee) {
        for(String data : employee) {
            if(data.equals("")) {
                return true;
            }
        }
        return false;
    }

    private double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    private void elementsDeclaration() {
        editTextName1 = findViewById(R.id.editTextName1);
        editTextName2 = findViewById(R.id.editTextName2);
        editTextName3 = findViewById(R.id.editTextName3);

        editTextLastName1 = findViewById(R.id.editTextLastName1);
        editTextLastName2 = findViewById(R.id.editTextLastName2);
        editTextLastName3 = findViewById(R.id.editTextLastName3);

        spinnerRole1 = findViewById(R.id.spinnerRole1);
        spinnerRole2 = findViewById(R.id.spinnerRole2);
        spinnerRole3 = findViewById(R.id.spinnerRole3);

        editTextHours1 = findViewById(R.id.editTextHours1);
        editTextHours2 = findViewById(R.id.editTextHours2);
        editTextHours3 = findViewById(R.id.editTextHours3);

        btnSend = findViewById(R.id.btnSend);
        btnFinish = findViewById(R.id.btnFinish);
    }
}