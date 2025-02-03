package com.example.multi_activityapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView nameDisplay = findViewById(R.id.nameDisplay);
        ImageView imageView = findViewById(R.id.imageView);

        String firstName = getIntent().getStringExtra("FIRST_NAME");
        String secondName = getIntent().getStringExtra("SECOND_NAME");

        nameDisplay.setText("Welcome, " + firstName + " " + secondName);
        imageView.setImageResource(R.drawable.sample_image);
    }
}
