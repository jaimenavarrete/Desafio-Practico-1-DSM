package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

public class ShowExercise3Activity extends AppCompatActivity {

    TextView txtEmployee1, txtEmployee2, txtEmployee3;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3_show);

        Bundle bundle = getIntent().getExtras();
        String[] employee1 = bundle.getStringArray("employee1");
        double[] employeeValues1 = bundle.getDoubleArray("employeeValues1");
        String[] employee2 = bundle.getStringArray("employee2");
        double[] employeeValues2 = bundle.getDoubleArray("employeeValues2");
        String[] employee3 = bundle.getStringArray("employee3");
        double[] employeeValues3 = bundle.getDoubleArray("employeeValues3");

        txtEmployee1 = findViewById(R.id.txtEmployee1);
        txtEmployee2 = findViewById(R.id.txtEmployee2);
        txtEmployee3 = findViewById(R.id.txtEmployee3);

        btnFinish = findViewById(R.id.btnFinish);

        printEmployeeData(employee1, employeeValues1, txtEmployee1);
        printEmployeeData(employee2, employeeValues2, txtEmployee2);
        printEmployeeData(employee3, employeeValues3, txtEmployee3);

        btnFinish.setOnClickListener(v -> {
            finish();
        });
    }

    private void printEmployeeData(String[] employee, double[] employeeValues, TextView textView) {
        String text = "Nombre: " + employee[0] + " " + employee[1] + "\n" +
                      "Cargo: " + employee[2] + "\n" +
                      "Horas trabajadas: " + employee[3] + " horas\n" +
                      "Salario base: $" + employeeValues[0] + "\n" +
                      "Descuento AFP: -$" + employeeValues[1] + "\n" +
                      "Descuento ISS: -$" + employeeValues[2] + "\n" +
                      "Descuento Renta: -$" + employeeValues[3] + "\n" +
                      "Salario liquido: -$" + employeeValues[4];

        textView.setText(text);
    }
}