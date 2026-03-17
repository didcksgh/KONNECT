package com.example.konnect.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.konnect.R;
import com.example.konnect.helper.FlashUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.konnect.helper.User;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FlashcardFragment extends Fragment {
    private int currentUserIndex = 0;
    private List<FlashUser> users;

    String idString = User.getInstance().getID();
    int id = Integer.parseInt(idString);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment_flashcard, container, false);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView dobTextView = view.findViewById(R.id.dobTextView);
        TextView genderTextView = view.findViewById(R.id.genderTextView);
        TextView hobbiesTextView = view.findViewById(R.id.hobbiesTextView);
        ImageView profileImageView = view.findViewById(R.id.profileImageView); // New ImageView for profile image

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            currentUserIndex++;
            if (currentUserIndex >= users.size()) {
                currentUserIndex = 0; // Loop back to the first user
            }
            FlashUser nextUser = users.get(currentUserIndex);
            updateViews(nextUser, nameTextView, dobTextView, genderTextView, hobbiesTextView, profileImageView); // Pass the ImageView to updateViews
        });

        // Call the method to fetch users
        fetchUsers(nameTextView, dobTextView, genderTextView, hobbiesTextView, profileImageView); // Pass the ImageView to fetchUsers

        return view;
    }

    private void fetchUsers(TextView nameTextView, TextView dobTextView, TextView genderTextView, TextView hobbiesTextView, ImageView profileImageView) {
        String url = "http://coms-309-001.class.las.iastate.edu:8080/users/"+ id + "/getFlashcards";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response and update your list of users
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<FlashUser>>(){}.getType();
                        users = gson.fromJson(response, listType);

                        // Then update your UI (TextViews, etc.) with the new data
                        if (!users.isEmpty()) {
                            FlashUser firstUser = users.get(0);
                            updateViews(firstUser, nameTextView, dobTextView, genderTextView, hobbiesTextView, profileImageView); // Pass the ImageView to updateViews
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    private void updateViews(FlashUser user, TextView nameTextView, TextView dobTextView, TextView genderTextView, TextView hobbiesTextView, ImageView profileImageView) {
        nameTextView.setText(user.getName());

        // Format the Date object into a string
        if (user.getDateOfBirth() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = sdf.format(user.getDateOfBirth());
            dobTextView.setText(formattedDate);
        } else {
            dobTextView.setText("Hidden by User");
        }

        genderTextView.setText(user.getGender());

        // Check if hobbies list is null before calling toString
        if (user.getHobbies() != null) {
            hobbiesTextView.setText(user.getHobbies().toString());
        } else {
            hobbiesTextView.setText("Soccer");
        }

        // Load the profile image from the server into the ImageView
        if (user.getProfileImage() != null && !user.getProfileImage().isEmpty()) {
            Glide.with(this)
                    .load(user.getProfileImage())
                    .into(profileImageView);
        } else {
            // Load a default image
            Glide.with(this)
                    .load(R.drawable.default_pfp)
                    .into(profileImageView);
        }
    }
}
