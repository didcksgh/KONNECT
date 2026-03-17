package com.example.konnect.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminfindbyidActivity extends AppCompatActivity {

    private EditText editTextId;
    private TextView textViewName, textViewUsername;
    // Add more TextViews for other user details
    private TextView textViewEmail, textViewJoiningDate, textViewGender, textViewAge, textViewLastLogin, textViewAppearances, textViewViewCount, textViewProfileImage, textViewUserBio;

    String adm_email;
    String adm_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_admin_find_by_id);

        Intent intent = getIntent();

        editTextId = findViewById(R.id.edit_text_id);
        textViewName = findViewById(R.id.text_view_name);
        textViewUsername = findViewById(R.id.text_view_username);
        // Initialize more TextViews for other user details
        textViewEmail = findViewById(R.id.text_view_email);
        textViewJoiningDate = findViewById(R.id.text_view_joining_date);
        textViewGender = findViewById(R.id.text_view_gender);
        textViewAge = findViewById(R.id.text_view_age);
        textViewLastLogin = findViewById(R.id.text_view_last_login);
        textViewAppearances = findViewById(R.id.text_view_appearances);
        textViewViewCount = findViewById(R.id.text_view_view_count);
        textViewProfileImage = findViewById(R.id.text_view_profile_image);
        textViewUserBio = findViewById(R.id.text_view_user_bio);

        adm_email = intent.getStringExtra("AD_EMAIL");
        adm_pw = intent.getStringExtra("AD_PW");

        Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                String url = "http://coms-309-001.class.las.iastate.edu:8080/adminUser/getUser/" + id + "/" + adm_email + "/" + adm_pw + "/";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String name = "Name: " + response.getString("name");
                                    String username = "Username: " + response.getString("username");
                                    String email = "Email: " + response.getString("emailId");
                                    String joiningDate = "Joining Date: " + response.getString("joiningDate");
                                    String gender = "Gender: " + response.getString("gender");
                                    String age = "Age: " + response.getInt("age");
                                    String lastLogin = "Last Login: " + response.getString("lastLoggin");
                                    String appearances = "Appearances: " + response.getInt("appearences");
                                    String viewCount = "View Count: " + response.getInt("viewCount");
                                    String profileImage = "Profile Image: " + response.optString("profileImage", "No image"); // Use optString for potential null values
                                    String userBio = "User Bio: " + response.optString("userBio", "No bio"); // Use optString for potential null values

                                    textViewName.setText(name);
                                    textViewUsername.setText(username);
                                    textViewEmail.setText(email);
                                    textViewJoiningDate.setText(joiningDate);
                                    textViewGender.setText(gender);
                                    textViewAge.setText(String.valueOf(age));
                                    textViewLastLogin.setText(lastLogin);
                                    textViewAppearances.setText(String.valueOf(appearances));
                                    textViewViewCount.setText(String.valueOf(viewCount));
                                    textViewProfileImage.setText(profileImage);
                                    textViewUserBio.setText(userBio);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(AdminfindbyidActivity.this);
                queue.add(jsonObjectRequest);
            }
        });
    }
}
