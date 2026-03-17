package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.friendsandgroups.GroupChatActivity;
import com.example.konnect.helper.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FindPeopleActivity extends AppCompatActivity {

    private ListView usersListView;
    private Button addUserButton, startChatButton;
    private EditText usernameEditText;
    private ArrayList<String> selectedUsers = new ArrayList<>();
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpeople);

        usersListView = findViewById(R.id.usersListView);
        addUserButton = findViewById(R.id.addUserButton);
        startChatButton = findViewById(R.id.startChatButton);
        usernameEditText = findViewById(R.id.usernameEditText);

        requestQueue = Volley.newRequestQueue(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedUsers);
        usersListView.setAdapter(adapter);

        addUserButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            if (!username.isEmpty()) {
                getUserByUsername(username);
            }
        });

        startChatButton.setOnClickListener(v -> {
            Intent intent = new Intent(FindPeopleActivity.this, GroupChatActivity.class);
            intent.putStringArrayListExtra("selectedUsers", selectedUsers);
            startActivity(intent);
        });
    }

    private void getUserByUsername(String username) {
        String url = "http://coms-309-001.class.las.iastate.edu:8080/users/" + username;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response as a User object
                        // This will depend on the format of your response
                        // Here's a basic example if your response is JSON:
                        User user = new Gson().fromJson(response, User.class);
                        if (user != null) {
                            selectedUsers.add(user.getUsername());
                            ((ArrayAdapter) usersListView.getAdapter()).notifyDataSetChanged();
                        } else {
                            Toast.makeText(FindPeopleActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);
    }
}
