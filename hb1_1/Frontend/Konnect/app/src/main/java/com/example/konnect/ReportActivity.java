package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportActivity extends AppCompatActivity {

    private EditText reportContentEditText;
    private Button createReportButton;
    private Button updateReportButton;
    private Button deleteReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        reportContentEditText = findViewById(R.id.report_content);
        createReportButton = findViewById(R.id.create_report_button);
        updateReportButton = findViewById(R.id.update_report_button);
        deleteReportButton = findViewById(R.id.delete_report_button);

        Button createReportButton = findViewById(R.id.create_report_button);

        createReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, ReportFormActivity.class);
                startActivity(intent);
            }
        });
        updateReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, UpdateReportActivity.class);
                startActivity(intent);
            }
        });

        deleteReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, ReportDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
    private void createReport() {
        String url = "http://coms-309-001.class.las.iastate.edu:8080/reports/";
        JSONObject params = new JSONObject();
        try {
            params.put("report", reportContentEditText.getText().toString());
            // Add other report details...
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(ReportActivity.this);
        queue.add(jsonObjectRequest);
    }

    private void updateReport() {

    }

    private void deleteReport() {

    }**/
}
