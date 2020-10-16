package com.ivanl.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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
                            Intent intent = new Intent(v.getContext(), GameOverActivity.class);
                            finish();
                            startActivity(intent);
                        } else {
                            if (randomNumber > value)
                                toastString = "El número es más grande";
                            else
                                toastString = "El número es más pequeño";
                        }
                        // One try is added
                        tries++;
                        final TextView textView = findViewById(R.id.textView3);
                        textView.setText(Integer.toString(tries));
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
}
