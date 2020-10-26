package com.ivanl.endevinaelnumero;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int tries, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tries = 0;
        time = 0;
        // We generate a random number between 1 and 100 (both included)
        final int randomNumber = (int)(Math.random() * 100 + 1);
        // A timer is set and started
        final Chronometer chronometer = (Chronometer)findViewById(R.id.textChronometer);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer chronometer) {
                time++;
            }
        });
        chronometer.start();
        // When the button to check is pressed...
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View v) {
                EditText text = findViewById(R.id.editTextNumberSigned);
                String valueString = text.getText().toString();
                // Toast is initialized
                String toastString = new String();
                Toast toast = Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_SHORT);
                // First, the program checks whether the EditText is empty or not
                if (!valueString.matches("")) {
                    // The number introduced by the user is returned
                    int value = Integer.parseInt(text.getText().toString());
                    // Program checks if the value is between the allowed rank
                    if (!(value < 0 || value > 100)) {
                        // The result is checked, and the user is notified about it
                        if (value == randomNumber) {
                            chronometer.stop();
                            toast.cancel();
                            LayoutInflater inflater = getLayoutInflater();
                            final View dialogLayout = inflater.inflate(R.layout.dialog_gameover, null);
                            AlertDialog dialog = gameOverDialogBuilder(dialogLayout).create();
                            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                 @Override
                                 public void onShow(final DialogInterface dialog) {
                                     Button button1 = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                                     button1.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             final EditText usernameText = dialogLayout.findViewById(R.id.editTextTextPersonName);
                                             String valueString = usernameText.getText().toString();
                                             if (!valueString.equalsIgnoreCase("")) {
                                                 Score score = new Score();
                                                 score.setUsername(usernameText.getText().toString());
                                                 score.setTries(MainActivity.tries);
                                                 score.setTime(MainActivity.time);
                                                 RankingCollection.rankingList.add(score);
                                                 RankingCollection.print();
                                                 dialog.dismiss();
                                                 Intent intent = new Intent(dialogLayout.getContext(), RankingActivity.class);
                                                 finish();
                                                 startActivity(intent);
                                             } else {
                                                 Toast dialogToast = Toast.makeText(MainActivity.this, "El campo está vacío", Toast.LENGTH_SHORT);
                                                 dialogToast.show();
                                             }

                                         }
                                     });
                                     Button button2 = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                                     button2.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             exitConfirmationDialog();

                                         }
                                     });
                                 }
                             });
                            dialog.show();
                        } else {
                            if (randomNumber > value)
                                toastString = "El número es más grande";
                            else
                                toastString = "El número es más pequeño";
                        }
                        // One try is added
                        tries++;
                        final TextView textView = findViewById(R.id.textView3);
                        textView.setText(String.valueOf(tries));
                    } else {
                        toastString = "El número debe estar entre 1 y 100";
                    }
                    // EditText is cleared
                    text.getText().clear();
                } else {
                    toastString = "Campo vacío";
                }
                toast.setText(toastString);
                toast.show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private AlertDialog.Builder gameOverDialogBuilder(View dialogLayout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogLayout);
        builder.setTitle("Fin del juego");
        builder.setMessage("Introduce tu nombre de usuario para guardar tu puntuación:");
        TextView tvTries = dialogLayout.findViewById(R.id.tvTries);
        tvTries.setText("Intentos: " + tries);
        TextView tvTime = dialogLayout.findViewById(R.id.tvTime);
        tvTime.setText("Tiempo: " + time + " s");
        builder.setPositiveButton("Enviar", null);
        builder.setNegativeButton("Descartar", null);
        builder.setCancelable(false);
        return builder;
    }

    private void exitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("¿Estás seguro de querer salir sin guardar tu puntuación?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                })
                .show();
    }

}
