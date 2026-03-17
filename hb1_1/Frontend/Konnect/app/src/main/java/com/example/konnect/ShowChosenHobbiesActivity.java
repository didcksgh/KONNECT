package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents the ShowChosenHobbiesActivity and displays the user's chosen hobbies.
 *
 * @author Chanho Yang
 */
public class ShowChosenHobbiesActivity extends AppCompatActivity {
    private String username_hobby;
    private int user_id;
    private String url = "http://coms-309-001.class.las.iastate.edu:8080/hobbies";
    //List<Hobby> hobbies = new ArrayList<>();

    private LinearLayout hobbiesLayout;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayhobby);


        Intent intent = getIntent();
        username_hobby = intent.getStringExtra("USERNAME");

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username_hobby + "'s hobby'");
        hobbiesLayout = findViewById(R.id.hobbiesLayout);



        user_id = 4;

        sendGetRequest(url, user_id);



    }

    /**
     * Sends a GET request to the specified URL to retrieve the user's hobbies.
     *
     * @param url_send The URL to send the GET request.
     * @param user_id  The ID of the user whose hobbies are to be retrieved.
     */
    public void sendGetRequest(String url_send, int user_id){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url_send + "/" + user_id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parse the hobby from the response
                    String name = response.getString("name");
                    String type = response.getString("hobbyType");

                    TextView hobbyView = new TextView(ShowChosenHobbiesActivity.this);
                    hobbyView.setText("name: " + name + ", hobby type: " + type);

                    hobbiesLayout.addView(hobbyView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}
