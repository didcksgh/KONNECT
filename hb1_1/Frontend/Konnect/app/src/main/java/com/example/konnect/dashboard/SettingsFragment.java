package com.example.konnect.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.konnect.ChoosehobbiesActivity;
import com.example.konnect.R;
import com.example.konnect.ReportActivity;
import com.example.konnect.admin.AdminFindActivity;
import com.example.konnect.admin.AdminUserActivity;
import com.example.konnect.entry.MainActivity;
import com.example.konnect.friendsandgroups.GroupsActivity;
import com.example.konnect.helper.User;

public class SettingsFragment extends Fragment {
    @SuppressLint("ApplySharedPref")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dashboard_fragment_settings, container, false);
        Button editProfileButton = view.findViewById(R.id.Profile_Edit_Button);
        Button chooseHobbiesButton = view.findViewById(R.id.ChooseHobbies_Button);
        Button reportButton = view.findViewById(R.id.Report_Button);
        Button logoutButton = view.findViewById(R.id.Logout_Button);
        Button AdminButton = view.findViewById(R.id.Admin_Button);
        Button groupButton = view.findViewById(R.id.Group_Button);

        String userType = User.getInstance().getType();




        chooseHobbiesButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChoosehobbiesActivity.class)));
        reportButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ReportActivity.class)));
        AdminButton.setOnClickListener(v -> {
            Intent intent;
            if ("n".equals(userType)) {
                intent = new Intent(v.getContext(), AdminUserActivity.class);

            } else if ("A".equals(userType)) {
                intent = new Intent(v.getContext(), AdminFindActivity.class);

                intent.putExtra("AD_USERNAME", User.getInstance().getUsername());
                intent.putExtra("AD_EMAIL", User.getInstance().getEmail());
                intent.putExtra("AD_PW", User.getInstance().getEmail());

            } else {
                // Handle other cases if necessary
                return;
            }
            startActivity(intent);
        });
        groupButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), GroupsActivity.class)));
        logoutButton.setOnClickListener(v -> {
            v.getContext().getSharedPreferences("USERDATA", 0).edit().clear().commit();
            startActivity(new Intent(v.getContext(), MainActivity.class));
            requireActivity().finish();
        });


        return view;
    }
}
