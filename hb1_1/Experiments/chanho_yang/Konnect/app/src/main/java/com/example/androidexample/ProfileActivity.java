package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private EditText phoneNumber;
    private EditText dob;
    private EditText hobby;
    private EditText address;
    private Spinner gender;
    private Button submitButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);            // link to profile activity XML

        /* initialize UI elements */
        phoneNumber = findViewById(R.id.profile_phoneNumber_edt);
        dob = findViewById(R.id.profile_dob_edt);
        hobby = findViewById(R.id.profile_hobby_edt);
        address = findViewById(R.id.profile_address_edt);
        gender = findViewById(R.id.profile_gender_spin);
        submitButton = findViewById(R.id.profile_submit_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender_array,  // Create a string array resource with gender options
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {

            String selectedGender = gender.getSelectedItem().toString();
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
