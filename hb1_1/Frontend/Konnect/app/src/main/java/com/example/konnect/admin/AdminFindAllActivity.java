package com.example.konnect.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.UserAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

import com.example.konnect.helper.OtherUser;

public class AdminFindAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<OtherUser> userList;

    String adm_email;
    String adm_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_admin_find_all);
        Intent intent = getIntent();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(this);
        adm_email = intent.getStringExtra("AD_EMAIL");
        adm_pw = intent.getStringExtra("AD_PW");
        String url = "http://coms-309-001.class.las.iastate.edu:8080/adminUser/getUsers/" + adm_email + "/" + adm_pw + "/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<OtherUser>>() {}.getType();
                        userList = gson.fromJson(response.toString(), listType);
                        recyclerView.setAdapter(new UserAdapter(userList));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        queue.add(jsonArrayRequest);
    }

}
