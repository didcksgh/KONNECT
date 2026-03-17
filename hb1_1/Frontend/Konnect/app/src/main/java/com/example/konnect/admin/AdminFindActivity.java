package com.example.konnect.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.konnect.R;
import com.example.konnect.helper.User;


public class AdminFindActivity extends AppCompatActivity {


    String adm_username;
    String adm_email;
    String adm_pw;
    private EditText confirmpw;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_adfindusers);
        Intent intent = getIntent();
        if (intent != null) {
            String value = intent.getStringExtra("key");
            // rest of your code
        }


        TextView adminName = findViewById(R.id.find_Name);
        TextView adminEmail = findViewById(R.id.find_Email);
        TextView adminID = findViewById(R.id.find_id);

        Button findAll = findViewById(R.id.findAll_btn);
        Button findOne = findViewById(R.id.find_user_btn);
        confirmpw = findViewById(R.id.type_pw_edt);

        adm_pw = intent.getStringExtra("AD_PW");
        adm_username = intent.getStringExtra("AD_USERNAME");
        adm_email = intent.getStringExtra("AD_EMAIL");
        String pw = confirmpw.getText().toString();
        Log.d("admin_confirm_pw", "confirm pw: " + pw);
        Log.d("admin_pw", "admin_pw: " + adm_pw);

        findAll.setOnClickListener(v -> {

                //startActivity(new Intent(v.getContext(), AdminFindAllActivity.class));
                // Start AdminFindAllActivity
                Intent intent1 = new Intent(AdminFindActivity.this, AdminFindAllActivity.class);
                intent1.putExtra("AD_USERNAME", adm_username);
                intent1.putExtra("AD_EMAIL", adm_email);
                intent1.putExtra("AD_PW", adm_pw);
                startActivity(intent1);

        });

        findOne.setOnClickListener(v -> {

                //startActivity(new Intent(v.getContext(), AdminfindbyidActivity.class));
                Intent intent2 = new Intent(AdminFindActivity.this, AdminfindbyidActivity.class);
                intent2.putExtra("AD_USERNAME", adm_username);
                intent2.putExtra("AD_EMAIL", adm_email);
                intent2.putExtra("AD_PW", adm_pw);
                startActivity(intent2);

        });



        adminName.setText(User.getInstance().getName());
        adminEmail.setText(adm_email);
        adminID.setText(String.format("ID: %s", adm_username));
    }

}
