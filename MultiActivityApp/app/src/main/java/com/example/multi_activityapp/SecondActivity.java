package com.example.multi_activityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    EditText secondNameInput;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondNameInput = findViewById(R.id.secondNameInput);
        nextButton = findViewById(R.id.nextButton);

        String firstName = getIntent().getStringExtra("FIRST_NAME");

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secondName = secondNameInput.getText().toString();
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("FIRST_NAME", firstName);
                intent.putExtra("SECOND_NAME", secondName);
                startActivity(intent);
            }
        });
    }
}
