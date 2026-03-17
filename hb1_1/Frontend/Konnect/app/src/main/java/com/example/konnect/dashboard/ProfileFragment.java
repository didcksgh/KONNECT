package com.example.konnect.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.konnect.R;
import com.example.konnect.helper.User;

public class ProfileFragment extends Fragment {
    public ProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dashboard_fragment_profile, container, false);

        /* Initialize Buttons used in activity_login.xml */
        ImageView profilePicture= view.findViewById(R.id.ProfilePicture);

        /* Initialize TextViews used in activity_login.xml */
        TextView profileName= view.findViewById(R.id.ProfileName);
        TextView profileUsername= view.findViewById(R.id.ProfileUsername);
        TextView profileEmail= view.findViewById(R.id.ProfileEmail);
        TextView profileBio= view.findViewById(R.id.ProfileBio);
        TextView profileGender= view.findViewById(R.id.ProfileGender);
        TextView profileBirthday= view.findViewById(R.id.ProfileBirthday);
        TextView profileJoinDate= view.findViewById(R.id.ProfileJoinDate);

        /* Set text inside of TextViews */
        profileName.setText(User.getInstance().getName());
        profileUsername.setText(User.getInstance().getUsername());
        profileEmail.setText(User.getInstance().getEmail());
        profileBio.setText(User.getInstance().getBio());
        profileGender.setText(User.getInstance().getGender());
        profileBirthday.setText(User.getInstance().getBirthday());
        profileJoinDate.setText(User.getInstance().getJoinDate());

        /* Initialize and set onClick listener for edit button*/
        Button editButton = view.findViewById(R.id.Edit_Button);

        return view;
    }
}
