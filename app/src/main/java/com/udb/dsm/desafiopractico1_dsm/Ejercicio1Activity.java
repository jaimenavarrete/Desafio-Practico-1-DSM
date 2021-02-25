package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio1Activity extends AppCompatActivity {

    EditText editTextA, editTextB, editTextC;
    TextView txtResultX1, txtResultX2;
    Button btnSend, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);

        txtResultX1 = findViewById(R.id.txtResultX1);
        txtResultX2 = findViewById(R.id.txtResultX2);

        btnSend = findViewById(R.id.btnSend);
        btnFinish = findViewById(R.id.btnFinish);

        btnSend.setOnClickListener(v -> {
            calculate();
        });

        btnFinish.setOnClickListener(v -> {
            finish();
        });
    }

    private void calculate() {
        double a, b, c, root, x1, x2;

        String textA, textB, textC;

        textA = editTextA.getText().toString();
        textB = editTextB.getText().toString();
        textC = editTextC.getText().toString();

        if(textA.equals("") || textB.equals("") || textC.equals("")) {
            Toast toastMessage = Toast.makeText(this,"Tiene que rellenar todos los campos",Toast.LENGTH_LONG);
            toastMessage.show();
        }
        else {
            a = Double.parseDouble(textA);
            b = Double.parseDouble(textB);
            c = Double.parseDouble(textC);

            root = Math.pow(b, 2) - 4 * a * c;

            if(root < 0) {
                Toast toastMessage = Toast.makeText(this,"No se puede evaluar. Los valores son incompatibles",Toast.LENGTH_LONG);
                toastMessage.show();
            }
            else {
                x1 = Math.round(((- b + Math.sqrt(root)) / (2.0 * a)) * 100.0) / 100.0;
                x2 = Math.round(((- b - Math.sqrt(root)) / (2.0 * a)) * 100.0) / 100.0;

                txtResultX1.setText(String.valueOf(x1));
                txtResultX2.setText(String.valueOf(x2));
            }
        }
    }
}