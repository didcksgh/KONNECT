package com.example.konnect.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.helper.User;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class AdminSignupActivity extends AppCompatActivity {
    private EditText usernameEditText;  // define username edittext variable
    private EditText passwordEditText;  // define password edittext variable
    private EditText confirmEditText;   // define confirm edittext variable
    private EditText emailAccountEditText; // define emailAccount edittext variable
    private String name;
    private String username;
    private String password;
    private String email;

    private Button signup_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_adminsignup);


        usernameEditText = findViewById(R.id.admin_username_edt);  // link to username edtext in the Signup activity XML
        passwordEditText = findViewById(R.id.admin_password_edt);  // link to password edtext in the Signup activity XML
        confirmEditText = findViewById(R.id.admin_confirm_edt);    // link to confirm edtext in the Signup activity XML
        emailAccountEditText = findViewById(R.id.admin_email_edt);
        signup_btn = findViewById(R.id.signupButton);
        name = User.getInstance().getName();


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* grab strings from user inputs */

                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                String confirm = confirmEditText.getText().toString();
                email = emailAccountEditText.getText().toString();

                //check if user didn't left the blank
                if(username.isEmpty()){
                    Toast.makeText(AdminSignupActivity.this, "Please, provide your username", Toast.LENGTH_SHORT).show();
                    return;

                }else if(email.isEmpty()){
                    Toast.makeText(AdminSignupActivity.this, "Please, provide your email.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(password.isEmpty()){
                    Toast.makeText(AdminSignupActivity.this, "Plesae, provide your password.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(confirm.isEmpty()){
                    Toast.makeText(AdminSignupActivity.this, "Please, provide your confirm password.", Toast.LENGTH_SHORT).show();
                    return;

                }
                /**
                 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                 String currentDate = df.format(new Date());
                 **/
                if (password.equals(confirm)){
                    Toast.makeText(getApplicationContext(), "Signing up", Toast.LENGTH_LONG).show();


                    Log.d("adminsignup", "Variable value: " + name);
                    Log.d("adminsignup", "Variable value: " + username);
                    Log.d("adminsignup", "Variable value: " + email);
                    Log.d("adminsignup", "Variable value: " + password);

                    String url = "http://coms-309-001.class.las.iastate.edu:8080/adminUser/309";
                    JSONObject params = new JSONObject();
                    try {
                        params.put("name", name);
                        params.put("emailId", email);
                        params.put("adminPassword", password);
                        params.put("username", username);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error",e.toString());
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest

                            (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String status = response.getString("message"); // Get the status from the response

                                        if (status.equals("success")) {
                                            // Display success message
                                            Toast.makeText(AdminSignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();

                                            // Start AdminFind
                                            Intent intent = new Intent(AdminSignupActivity.this, AdminFindActivity.class);
                                            intent.putExtra("AD_USERNAME", username);
                                            intent.putExtra("AD_EMAIL", email);
                                            intent.putExtra("AD_PW", password);
                                            startActivity(intent);
                                        } else {
                                            // Display failure message
                                            Toast.makeText(AdminSignupActivity.this, "Signup failed!", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Check if the error is a network error
                                    if (error instanceof com.android.volley.NoConnectionError) {
                                        Toast.makeText(AdminSignupActivity.this, "Network error", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(AdminSignupActivity.this, "Error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });



                    RequestQueue queue = Volley.newRequestQueue(AdminSignupActivity.this);
                    queue.add(jsonObjectRequest);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Password don't match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
