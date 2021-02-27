package com.udb.dsm.desafiopractico1_dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise2Activity extends AppCompatActivity {

    EditText editTextVotes;
    Button btnSend, btnFinish;

    TextView txtVotesNumber, txtFirstPlace, txtSecondPlace, txtThirdPlace, txtForthPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        editTextVotes = findViewById(R.id.editTextVotes);

        txtVotesNumber = findViewById(R.id.txtVotesNumber);
        txtFirstPlace = findViewById(R.id.txtFirstPlace);
        txtSecondPlace = findViewById(R.id.txtSecondPlace);
        txtThirdPlace = findViewById(R.id.txtThirdPlace);
        txtForthPlace = findViewById(R.id.txtForthPlace);

        btnSend = findViewById(R.id.btnSend);
        btnFinish = findViewById(R.id.btnFinish);

        btnSend.setOnClickListener(v -> {
            txtVotesNumber.setText("---");
            txtFirstPlace.setText("---");
            txtSecondPlace.setText("---");
            txtThirdPlace.setText("---");
            txtForthPlace.setText("---");

            calculate();
        });

        btnFinish.setOnClickListener(v -> {
            finish();
        });
    }

    private void calculate() {
        if(!editTextVotes.getText().equals("")) {
            boolean validateNumbers = true;
            String sequence = editTextVotes.getText().toString();
            String[] votes = sequence.split(",");

            for(String vote : votes) {
                if(!isNumber(vote.trim())) {
                    validateNumbers = false;
                    break;
                }
            }

            if(validateNumbers) {
                Integer totalVotes;
                Integer[] candidatesVotes = new Integer[]{0, 0, 0, 0};
                String[] candidatesName = new String[]{"Candidato 1", "Candidato 2", "Candidato 3", "Candidato 4"};

                for(String vote : votes) {
                    if(Integer.parseInt(vote.trim()) == 1) {
                        candidatesVotes[0]++;
                    }
                    else if(Integer.parseInt(vote.trim()) == 2) {
                        candidatesVotes[1]++;
                    }
                    else if(Integer.parseInt(vote.trim()) == 3) {
                        candidatesVotes[2]++;
                    }
                    else if(Integer.parseInt(vote.trim()) == 4) {
                        candidatesVotes[3]++;
                    }
                }

                for(int i = 0; i < 4; i++) {
                    for(int j = i; j < 4; j++) {
                        if(candidatesVotes[i] < candidatesVotes[j]) {
                            int value = candidatesVotes[i];
                            candidatesVotes[i] = candidatesVotes[j];
                            candidatesVotes[j] = value;

                            String text = candidatesName[i];
                            candidatesName[i] = candidatesName[j];
                            candidatesName[j] = text;
                        }
                    }
                }

                totalVotes = candidatesVotes[0] + candidatesVotes[1] + candidatesVotes[2] + candidatesVotes[3];

                txtVotesNumber.setText(String.valueOf(totalVotes));

                String textFirst = candidatesName[0] + " - " + candidatesVotes[0] + " votos (" + percentage(candidatesVotes[0], totalVotes) + "%)";
                txtFirstPlace.setText(textFirst);
                String textSecond = candidatesName[1] + " - " + candidatesVotes[1] + " votos (" + percentage(candidatesVotes[1], totalVotes) + "%)";
                txtSecondPlace.setText(textSecond);
                String textThird = candidatesName[2] + " - " + candidatesVotes[2] + " votos (" + percentage(candidatesVotes[2], totalVotes) + "%)";
                txtThirdPlace.setText(textThird);
                String textForth = candidatesName[3] + " - " + candidatesVotes[3] + " votos (" + percentage(candidatesVotes[3], totalVotes) + "%)";
                txtForthPlace.setText(textForth);

                if(candidatesVotes[0] == candidatesVotes[1]) {
                    Toast toastMessage = Toast.makeText(this,"Los primeros lugares tienen la misma cantidad de votos, por lo que no se puede definir formalmente un ganador",Toast.LENGTH_LONG);
                    toastMessage.show();
                }
            }
            else {
                Toast toastMessage = Toast.makeText(this,"Solo puede colocar números",Toast.LENGTH_LONG);
                toastMessage.show();
            }
        }
        else {
            Toast toastMessage = Toast.makeText(this,"Tiene que rellenar todos los campos",Toast.LENGTH_LONG);
            toastMessage.show();
        }
    }

    public boolean isNumber(String str) {
        if (str == null) return false;

        try {
            double transform = Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public double percentage(Integer votes, Integer totalVotes) {
        double percent = votes * 100.0 / totalVotes;
        double percentRounded = Math.round(percent * 100.0) / 100.0;

        return percentRounded;
    }
}