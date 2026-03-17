package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportDetailActivity extends AppCompatActivity {

    private EditText reportContentEditText;
    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportdetail);

        reportContentEditText = findViewById(R.id.report_content_edit_text);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        String reportContent = getIntent().getStringExtra("reportContent");
        reportContentEditText.setText(reportContent);

        // Get the report ID from the Intent
        int reportId = getIntent().getIntExtra("reportId", -1);
        if (reportId == -1) {
            // Handle the error
            return;
        }
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated report content from the EditText
                String updatedReportContent = reportContentEditText.getText().toString();

                // URL of your server
                String url = "http://coms-309-001.class.las.iastate.edu:8080/users/" + reportId;

                // Create a new JSONObject with the updated report content
                JSONObject reportJson = new JSONObject();
                try {
                    reportJson.put("report", updatedReportContent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a new JsonObjectRequest
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, reportJson,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // The response is a JSON object of the updated report
                                // You can handle the response here
                                Toast.makeText(ReportDetailActivity.this, "Report updated", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                    }
                });

                // Add the request to the RequestQueue
                RequestQueue queue = Volley.newRequestQueue(ReportDetailActivity.this);
                queue.add(jsonObjectRequest);
            }
        });



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // URL of your server
                String url = "http://coms-309-001.class.las.iastate.edu:8080/reports/" + reportId;

                // Create a new StringRequest with method DELETE
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // The response is a string indicating whether the deletion was successful
                                // You can handle the response here
                                Toast.makeText(ReportDetailActivity.this, "Report deleted", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                    }
                });

                // Add the request to the RequestQueue
                RequestQueue queue = Volley.newRequestQueue(ReportDetailActivity.this);
                queue.add(stringRequest);
            }
        });


    }
}
