package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.helper.Hobby;
import com.example.konnect.helper.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.example.konnect.dashboard.DashboardActivity;

/**
 * This class represents the ChooseHobbiesActivity and handles the process of choosing hobbies. This activity connects to ShowChosenHobbiesActivity.
 *
 * @author Chanho Yang
 */
public class ChoosehobbiesActivity extends AppCompatActivity {

    /**
     * Username of the user.
     */
    private String username_hobby;

    /**
     * List of all the Check Boxes.
     */
    private List<CheckBox> checkBoxes = new ArrayList<>();

    /**
     * The URL of the server.
     */
    private String url = "http://coms-309-001.class.las.iastate.edu:8080/hobbies";

    List<Hobby> hobbies = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hobby);

        //Intent intent = getIntent();
        username_hobby = User.getInstance().getUsername();

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username_hobby + ", Choose your hobbies!");

        hobbies.add(new Hobby("SOCCER", "OUTDOOR"));
        hobbies.add(new Hobby("MOVIE", "INDIVIDUAL"));
        hobbies.add(new Hobby("COOKING", "INDOOR"));
        hobbies.add(new Hobby("READING", "INDOOR"));
        hobbies.add(new Hobby("VIDEOGAME", "INDOOR"));
        hobbies.add(new Hobby("Fishing", "OUTDOOR"));
        hobbies.add(new Hobby("DRAWING", "INDIVIDUAL"));
        hobbies.add(new Hobby("HIKING", "OUTDOOR"));
        hobbies.add(new Hobby("TRAVELING", "INDIVIDUAL"));
        hobbies.add(new Hobby("WORKING OUT", "INDIVIDUAL"));




        checkBoxes.add((CheckBox) findViewById(R.id.checkBox1));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox2));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox3));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox4));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox5));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox6));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox7));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox8));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox9));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox10));


        //assign Hobby value to each corresponding checkbox
        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            Hobby hobby = hobbies.get(i);
            checkBox.setText(hobby.getName() + ", " + hobby.getType());
        }

        Button submitButton = findViewById(R.id.hobby_choose_btn);

        //to check if user choose more than 5 hobbies
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int checkedCount = 0;
                    for (CheckBox cb : checkBoxes) {
                        if (cb.isChecked()) {
                            checkedCount++;
                        }
                    }

                    if (checkedCount > 1) {
                        checkBox.setChecked(false);
                        Toast.makeText(ChoosehobbiesActivity.this, "You should choose one hobbies", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                submitHobbies();
            }
        });



    }

    /**
     * Checks if a Hobby is selected and submits all selected hobbies to sendPostRequest.
     */
    private void submitHobbies(){
        //JSON array for storing hobbies
        boolean isHobbySelected = false;

        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                isHobbySelected = true;
                break;
            }
        }

        // If no hobby is selected, show a message and return
        if (!isHobbySelected) {
            Toast.makeText(this, "Choose at least one hobby", Toast.LENGTH_SHORT).show();
            return;
        }

        // Loop over each checkbox
        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            if (checkBox.isChecked()) {
                Hobby hobby = hobbies.get(i);
                JSONObject hobbyJson = new JSONObject();
                try {
                    hobbyJson.put("name", hobby.getName());
                    hobbyJson.put("hobbyType", hobby.getType());

                    // Send a POST request for this hobby
                    sendPostRequest(url, hobbyJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * Sends a POST request to server with all chosen hobbies of the user in a json object.
     *
     * @param url
     * @param jsonObject
     */
    private void sendPostRequest(String url, JSONObject jsonObject) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server
                        try {
                            String status = response.getString("message"); // Get the status from the response

                            if (status.equals("success")) {
                                // Display success message
                                Toast.makeText(ChoosehobbiesActivity.this, "Hobby submitted successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChoosehobbiesActivity.this, DashboardActivity.class);
                                //intent.putExtra("USERNAME", username_hobby);
                                startActivity(intent);
                            } else {
                                // Display failure message
                                Toast.makeText(ChoosehobbiesActivity.this, "Failed to submit hobby!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(ChoosehobbiesActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(ChoosehobbiesActivity.this, "Authentication error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(ChoosehobbiesActivity.this, "Server error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(ChoosehobbiesActivity.this, "Parse error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add the request to the request queue
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }


}
