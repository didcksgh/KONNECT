package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.example.konnect.helper.User;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportFormActivity extends AppCompatActivity {

    private EditText user2IdEditText;
    private EditText reportContentEditText;
    private Button submitReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportform);

        user2IdEditText = findViewById(R.id.user2_id_edit_text);
        reportContentEditText = findViewById(R.id.report_content_edit_text);
        submitReportButton = findViewById(R.id.submit_report_button);

        submitReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1Id = User.getInstance().getID(); // Get the ID of user1
                String user2Id = user2IdEditText.getText().toString(); // The ID of the user being reported
                String reportContent = reportContentEditText.getText().toString();

                // Now you can send a POST request to your server with user1Id, user2Id, and report content
                // Create a new RequestQueue
                RequestQueue queue = Volley.newRequestQueue(ReportFormActivity.this);

                // URL of your server
                String url = "http://coms-309-001.class.las.iastate.edu:8080/users/addReport/" + user1Id + "/" + user2Id+"/";

                // Create a new JSONObject
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("report", reportContent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a new JsonObjectRequest
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the response from the server
                                Toast.makeText(ReportFormActivity.this, "Report submitted successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        Toast.makeText(ReportFormActivity.this, "Error submitting report", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue
                queue.add(jsonObjectRequest);
            }
        });
    }
}
