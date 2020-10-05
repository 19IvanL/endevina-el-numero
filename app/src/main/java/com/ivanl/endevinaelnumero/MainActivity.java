package com.ivanl.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

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
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText text = findViewById(R.id.editTextNumberSigned);
                String valueString = text.getText().toString();
                if (!valueString.matches("")) {
                    // We return the number introduced by the user
                    int value = Integer.parseInt(text.getText().toString());
                    // We generate a random number between 1 and 100 (both included)
                    int randomNumber = (int) (Math.random() * 100 + 1);
                    String result = Integer.toString(randomNumber);
                    // We notify the user about the result
                    Toast toast = Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT);
                    toast.show();
                    // One try is added
                    tries++;
                    final TextView textView = findViewById(R.id.textView3);
                    textView.setText(Integer.toString(tries));
                }

            }
        });
    }
}
