package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

public class ShowExercise3Activity extends AppCompatActivity {

    TextView txtEmployee1, txtEmployee2, txtEmployee3, txtHigherSalary, txtLowerSalary, txtSalaryCount;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3_show);

        elementsDeclaration();

        Bundle bundle = getIntent().getExtras();
        String[] employee1 = bundle.getStringArray("employee1");
        double[] employeeValues1 = bundle.getDoubleArray("employeeValues1");
        String[] employee2 = bundle.getStringArray("employee2");
        double[] employeeValues2 = bundle.getDoubleArray("employeeValues2");
        String[] employee3 = bundle.getStringArray("employee3");
        double[] employeeValues3 = bundle.getDoubleArray("employeeValues3");

        // Bonus
        employeeValues1 = calculateBonus(employee1, employeeValues1);
        employeeValues2 = calculateBonus(employee2, employeeValues2);
        employeeValues3 = calculateBonus(employee3, employeeValues3);

        if(employee1[2].equals("Gerente") && employee2[2].equals("Asistente") && employee3[2].equals("Secretaria")) {
            employeeValues1[5] = 0.0;
            employeeValues1[6] = employeeValues1[4];

            employeeValues2[5] = 0.0;
            employeeValues2[6] = employeeValues2[4];

            employeeValues3[5] = 0.0;
            employeeValues3[6] = employeeValues3[4];
        }

        printEmployeeData(employee1, employeeValues1, txtEmployee1);
        printEmployeeData(employee2, employeeValues2, txtEmployee2);
        printEmployeeData(employee3, employeeValues3, txtEmployee3);

        // Calculate higher salary
        if(employeeValues1[6] > employeeValues2[6] && employeeValues1[6] > employeeValues3[6]) {
            String text = employee1[0] + " " + employee1[1] + " ($" + round(employeeValues1[6]) + ")";
            txtHigherSalary.setText(text);
        }
        else if(employeeValues2[6] > employeeValues1[6] && employeeValues2[6] > employeeValues3[6]) {
            String text = employee2[0] + " " + employee2[1] + " ($" + round(employeeValues2[6]) + ")";
            txtHigherSalary.setText(text);
        }
        else if(employeeValues3[6] > employeeValues1[6] && employeeValues3[6] > employeeValues2[6]) {
            String text = employee3[0] + " " + employee3[1] + " ($" + round(employeeValues3[6]) + ")";
            txtHigherSalary.setText(text);
        }
        else {
            txtHigherSalary.setText("Más de un empleado comparten el salario mayor");
        }

        // Calculate lower salary
        if(employeeValues1[6] < employeeValues2[6] && employeeValues1[6] < employeeValues3[6]) {
            String text = employee1[0] + " " + employee1[1] + " ($" + round(employeeValues1[6]) + ")";
            txtLowerSalary.setText(text);
        }
        else if(employeeValues2[6] < employeeValues1[6] && employeeValues2[6] < employeeValues3[6]) {
            String text = employee2[0] + " " + employee2[1] + " ($" + round(employeeValues2[6]) + ")";
            txtLowerSalary.setText(text);
        }
        else if(employeeValues3[6] < employeeValues1[6] && employeeValues3[6] < employeeValues2[6]) {
            String text = employee3[0] + " " + employee3[1] + " ($" + round(employeeValues3[6]) + ")";
            txtLowerSalary.setText(text);
        }
        else {
            txtHigherSalary.setText("Más de un empleado comparten el salario menor");
        }

        // Calculate salary count
        int countSalary = 0;
        if(employeeValues1[6] > 300.0) countSalary++;
        if(employeeValues2[6] > 300.0) countSalary++;
        if(employeeValues3[6] > 300.0) countSalary++;

        txtSalaryCount.setText("" + countSalary);

        btnFinish.setOnClickListener(v -> {
            finish();
        });
    }

    private void elementsDeclaration() {
        txtEmployee1 = findViewById(R.id.txtEmployee1);
        txtEmployee2 = findViewById(R.id.txtEmployee2);
        txtEmployee3 = findViewById(R.id.txtEmployee3);

        txtHigherSalary = findViewById(R.id.txtHigherSalary);
        txtLowerSalary = findViewById(R.id.txtLowerSalary);
        txtSalaryCount = findViewById(R.id.txtSalarCount);

        btnFinish = findViewById(R.id.btnFinish);
    }

    private double[] calculateBonus(String[] employee, double[] employeeValues) {
        double discount = 0.0;

        if(employee[2].equals("Gerente")) {
            discount = 0.1;
        }
        else if(employee[2].equals("Asistente")) {
            discount = 0.05;
        }
        else if(employee[2].equals("Secretaria")) {
            discount = 0.03;
        }
        else {
            discount = 0.02;
        }

        employeeValues[5] = employeeValues[4] * discount;
        employeeValues[6] = employeeValues[4] + employeeValues[5];

        return employeeValues;
    }

    private void printEmployeeData(String[] employee, double[] employeeValues, TextView textView) {
        String bonus;
        if(employeeValues[5] == 0.0) {
            bonus = "NO HAY BONO";
        }
        else {
            bonus = "$" + String.valueOf(round(employeeValues[5]));
        }

        String text = "Nombre: " + employee[0] + " " + employee[1] + "\n" +
                      "Cargo: " + employee[2] + "\n" +
                      "Horas trabajadas: " + employee[3] + " horas\n" +
                      "Salario base: $" + employeeValues[0] + "\n" +
                      "Descuento ISSS (5.25%): -$" + employeeValues[1] + "\n" +
                      "Descuento AFP (6.88%): -$" + employeeValues[2] + "\n" +
                      "Descuento Renta (10%): -$" + employeeValues[3] + "\n" +
                      "Salario liquido: $" + employeeValues[4] + "\n" +
                      "Bonus: " + bonus + "\n" +
                      "Salario total: $" + round(employeeValues[6]);

        textView.setText(text);
    }

    private double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}