package com.example.konnect.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.konnect.R;
import com.example.konnect.helper.User;

public class AdminUserActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_adminuser);

        TextView adminName = findViewById(R.id.Admin_Name);
        TextView adminEmail = findViewById(R.id.Admin_Email);
        TextView adminID = findViewById(R.id.Admin_id);

        Button Signup = findViewById(R.id.admin_signup_btn);


        Signup.setOnClickListener(v->startActivity(new Intent(v.getContext(), AdminSignupActivity.class)));



        adminName.setText(User.getInstance().getName());
        adminEmail.setText(User.getInstance().getEmail());
        adminID.setText(String.format("ID: %s", User.getInstance().getID()));
    }
}
