package com.example.multi_activityapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.multi_activityapp.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {
    private ActivityThirdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String firstName = getIntent().getStringExtra("FIRST_NAME");
        String lastName = getIntent().getStringExtra("LAST_NAME");

        binding.textViewName.setText("Full Name: " + firstName + " " + lastName);
        binding.imageView.setImageResource(R.drawable.sample_image);
    }
}
