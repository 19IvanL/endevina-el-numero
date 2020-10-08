package com.ivanl.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // We generate a random number between 1 and 100 (both included)
        final int randomNumber = (int)(Math.random() * 100 + 1);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text = findViewById(R.id.editTextNumberSigned);
                String valueString = text.getText().toString();
                // First, the program checks whether the EditText is empty or not
                if (!valueString.matches("")) {
                    // The number introduced by the user is returned
                    int value = Integer.parseInt(text.getText().toString());
                    // Program checks if the value is between the allowed rank
                    if (!(value < 0 || value > 100)) {
                        // The result is checked and the user is notified about it
                        String notify = "";
                        if (value == randomNumber) {
                            Intent intent = new Intent(v.getContext(), RankingActivity.class);
                            startActivity(intent);
                        } else {
                            if (randomNumber > value)
                                notify = "El número es más grande";
                            else
                                notify = "El número es más pequeño";
                            Toast toast = Toast.makeText(MainActivity.this, notify, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        // One try is added
                        tries++;
                        final TextView textView = findViewById(R.id.textView3);
                        textView.setText(Integer.toString(tries));
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "El número debe estar entre 1 y 100", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    // EditText is cleared
                    text.getText().clear();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Campo vacío", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}
