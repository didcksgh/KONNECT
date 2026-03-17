package com.example.konnect.entry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.helper.RequestJson;
import com.example.konnect.helper.ServerURLs;
import com.example.konnect.helper.User;
import com.google.gson.Gson;


/**
 * This class represents the main activity of the application. This activity connects to LoginActivity and SignupActivity.
 *
 * @author Jayson Acosta
 */

public class MainActivity extends AppCompatActivity {

    private static final String PREFSNAME = "USERDATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity_main);

        Button loginButton = findViewById(R.id.LoginButton);
        TextView signupButton = findViewById(R.id.Signup);
        ProgressBar progressBar = findViewById(R.id.ProgressBar);

        RequestQueue queue = Volley.newRequestQueue(this);

        SharedPreferences userData = getSharedPreferences(PREFSNAME, 0);
        if (userData.contains("user")) {
            String json = userData.getString("user", "");
            User user = new Gson().fromJson(json, User.class);

            if(User.isDataValid(user)){
                User.setInstance(user);
                ServerURLs.setURL_EP();
                queue.add(RequestJson.login(this, this, queue, progressBar));
            }
        }


        loginButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            EditText loginUsername = findViewById(R.id.Login_Username);
            EditText loginPassword = findViewById(R.id.Login_Password);

            User.getInstance().setPassword(loginPassword.getText().toString());
            String input = loginUsername.getText().toString();

            if (input.contains("@")) {
                User.getInstance().setEmail(input);
                ServerURLs.setURL_EP();
            } else {
                User.getInstance().setUsername(input);
                ServerURLs.setURL_UP();
            }
            queue.add(RequestJson.login(this, this, queue, progressBar));
        });
        signupButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SignupActivity.class)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences userData = getSharedPreferences(PREFSNAME, 0);
        SharedPreferences.Editor editor = userData.edit();

        Gson gson = new Gson();
        editor.clear();
        String json = gson.toJson(User.getInstance());
        editor.putString("user", json);
        editor.apply();
    }
}
