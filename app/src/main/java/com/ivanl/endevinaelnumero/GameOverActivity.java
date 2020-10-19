package com.ivanl.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity {

    // TODO Replace with Dialog
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        TextView triesText = findViewById(R.id.textView5);
        triesText.setText("Intentos: " + MainActivity.tries);
        TextView timeText = findViewById(R.id.textView6);
        timeText.setText("Tiempo: " + MainActivity.time);
        final Button confirmButton = findViewById(R.id.button2);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Score score = new Score();
                final EditText usernameText = findViewById(R.id.editTextTextPersonName);
                score.setUsername(usernameText.getText().toString());
                score.setTries(MainActivity.tries);
                score.setTime(MainActivity.time);
                RankingCollection.rankingList.add(score);
                RankingCollection.print();
                Intent intent = new Intent(view.getContext(), RankingActivity.class);
                finish();
                startActivity(intent);
            }
        });
        final Button cancelButton = findViewById(R.id.button3);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                exitConfirmationDialog();
            }
        });
    }

    private void exitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("¿Estás seguro de querer salir sin guardar tu puntuación?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        exitConfirmationDialog();
    }

}