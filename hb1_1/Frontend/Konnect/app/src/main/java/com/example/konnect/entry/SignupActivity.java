package com.example.konnect.entry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class represents the SignupActivity and handles the signup process for new users. This activity connects to LoginActivity and ChooseHobbiesActivity.
 *
 * @author Chanho Yang
 */
public class SignupActivity extends AppCompatActivity {

    private EditText usernameEditText;  // define username edittext variable
    private EditText passwordEditText;  // define password edittext variable
    private EditText confirmEditText;   // define confirm edittext variable
    private EditText emailAccountEditText; // define emailAccount edittext variable
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText dobEditText;
    private EditText genderEditText;
    private Button loginButton;         // define login button variable
    private Button signupButton;        // define signup button variable
    private Button homeButton;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity_signup);

        /* initialize UI elements */
        usernameEditText = findViewById(R.id.signup_username_edt);  // link to username edtext in the Signup activity XML
        passwordEditText = findViewById(R.id.signup_password_edt);  // link to password edtext in the Signup activity XML
        confirmEditText = findViewById(R.id.signup_confirm_edt);    // link to confirm edtext in the Signup activity XML
        emailAccountEditText = findViewById(R.id.signup_email_edt);
        nameEditText = findViewById(R.id.signup_name_edt);
        ageEditText = findViewById(R.id.signup_age_edt);
        dobEditText = findViewById(R.id.signup_dob_edt);
        loginButton = findViewById(R.id.signup_login_btn);    // link to login button in the Signup activity XML
        signupButton = findViewById(R.id.signup_signup_btn);  // link to signup button in the Signup activity XML
        homeButton = findViewById(R.id.signup_home_btn);
        genderEditText = findViewById(R.id.signup_gender_edt);
/**
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        genderSpinner.setAdapter(adapter);

        // Set the selected gender when an item is selected in the spinner
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               gender = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
**/

        loginButton.setOnClickListener(v -> {

            /* when login button is pressed, use intent to switch to Login Activity */
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);  // go to LoginActivity
        });

        signupButton.setOnClickListener(v -> {

            /* grab strings from user inputs */

            String username = usernameEditText.getText().toString();
            String name = nameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirm = confirmEditText.getText().toString();
            String email = emailAccountEditText.getText().toString();
            String ageString = ageEditText.getText().toString();
            String dobString = dobEditText.getText().toString();
            String gender = genderEditText.getText().toString();

            Log.d("Debug", "Username: " + username);

            int age = Integer.parseInt(ageString);

            //check if user didn't left the blank
            if(username.isEmpty()){
                Toast.makeText(SignupActivity.this, "Please, provide your username", Toast.LENGTH_SHORT).show();
                return;

            }else if(email.isEmpty()){
                Toast.makeText(SignupActivity.this, "Please, provide your email.", Toast.LENGTH_SHORT).show();
                return;

            }else if(password.isEmpty()){
                Toast.makeText(SignupActivity.this, "Please, provide your password.", Toast.LENGTH_SHORT).show();
                return;

            }else if(confirm.isEmpty()){
                Toast.makeText(SignupActivity.this, "Please, provide your confirm password.", Toast.LENGTH_SHORT).show();
                return;

            }else if(dobString.isEmpty()){
                Toast.makeText(SignupActivity.this, "Please, provide your date of birth.", Toast.LENGTH_SHORT).show();
                return;

            }else if(gender.isEmpty()){
                Toast.makeText(SignupActivity.this, "Please, provide your gender.", Toast.LENGTH_SHORT).show();
                return;

            }else if(ageEditText.getText().toString().isEmpty()){
                Toast.makeText(SignupActivity.this, "Please, provide your age.", Toast.LENGTH_SHORT).show();
                return;

            }

            /**SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String currentDate = df.format(new Date());**/

            if (password.equals(confirm)){
                Toast.makeText(getApplicationContext(), "Signing up", Toast.LENGTH_LONG).show();

                // Parse the date string into a Date object
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date dob;
                try {
                    dob = df.parse(dobString);
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(SignupActivity.this, "Please, provide the correct form of DOB", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Format the Date object into an ISO 8601 string
                df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault());
                String dobISO = df.format(dob);


                String url = "http://coms-309-001.class.las.iastate.edu:8080/users/";
                JSONObject params = new JSONObject();
                try {
                    params.put("name", name);
                    params.put("emailId", email);
                    params.put("userPassword", password);
                    params.put("username", username);
                    params.put("age", age);
                    params.put("birthday", dobISO);
                    params.put("gender", gender);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Error Json",e.toString());
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest

                        (Request.Method.POST, url, params, response -> {
                            try {
                                String status = response.getString("message"); // Get the status from the response

                                if (status.equals("success")) {
                                    Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else { Toast.makeText(SignupActivity.this, "Signup failed!", Toast.LENGTH_SHORT).show(); }
                            } catch (JSONException e) { Log.e("Error", e.toString()); }
                        }, error -> {
                            if (error instanceof com.android.volley.NoConnectionError) { Toast.makeText(SignupActivity.this, "Network error", Toast.LENGTH_LONG).show(); }
                            else { Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_LONG).show(); }
                        });



                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(jsonObjectRequest);

            }
            else {
                Toast.makeText(getApplicationContext(), "Password don't match", Toast.LENGTH_LONG).show();
            }
        });


        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
