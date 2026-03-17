package com.example.konnect.dashboard;

import static com.example.konnect.helper.RequestJson.friendRequestStatusUpdate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.friendsandgroups.ChatActivity;
import com.example.konnect.R;
import com.example.konnect.friendsandgroups.GroupChatActivity;
import com.example.konnect.helper.RequestJson;
import com.example.konnect.helper.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendsFragment extends Fragment {
    LinearLayout containerFR, containerF, containerG, containerP;
    View view;

    public FriendsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.dashboard_fragment_friends, container, false);

        containerFR = view.findViewById(R.id.Container_FR);
        containerF = view.findViewById(R.id.Container_F);
        containerG = view.findViewById(R.id.Container_G);
        containerP = view.findViewById(R.id.Container_P);

        ImageView imageViewVFR = view.findViewById(R.id.ImageView_VFR);
        ImageView imageViewVF = view.findViewById(R.id.ImageView_VF);
        ImageView imageViewG = view.findViewById(R.id.ImageView_G);
        ImageView imageViewP = view.findViewById(R.id.ImageView_P);

        imageViewVFR.setOnClickListener(v -> {
            if(containerFR.isShown()){
                containerFR.setVisibility(View.GONE);
                imageViewVFR.setImageResource(R.drawable.expand_more);
            } else {
                containerFR.setVisibility(View.VISIBLE);
                imageViewVFR.setImageResource(R.drawable.expand_less);
            }
        });
        imageViewVF.setOnClickListener(v -> {
            if(containerF.isShown()){
                containerF.setVisibility(View.GONE);
                imageViewVF.setImageResource(R.drawable.expand_more);
            } else {
                containerF.setVisibility(View.VISIBLE);
                imageViewVF.setImageResource(R.drawable.expand_less);
            }
        });
        imageViewG.setOnClickListener(v -> {
            if(containerG.isShown()){
                containerG.setVisibility(View.GONE);
                imageViewG.setImageResource(R.drawable.expand_more);
            } else {
                containerG.setVisibility(View.VISIBLE);
                imageViewG.setImageResource(R.drawable.expand_less);
            }
        });
        imageViewP.setOnClickListener(v -> {
            if(containerP.isShown()){
                containerP.setVisibility(View.GONE);
                imageViewP.setImageResource(R.drawable.expand_more);
            } else {
                containerP.setVisibility(View.VISIBLE);
                imageViewP.setImageResource(R.drawable.expand_less);
            }
        });

        containerG.addView(createGLayout("ComS-309 Group", 906));
        //containerG.addView(createGLayout("Admin Group", 101));

        // Instantiate the RequestQueue.
        RequestQueue queue1 = Volley.newRequestQueue(getContext());
        String url = "http://coms-309-001.class.las.iastate.edu:8080/groups/list/" + User.getInstance().getUsername();

        // Request a string response from the provided URL.
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response to get the list of group names.
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject groupObject = jsonArray.getJSONObject(i);
                                String groupName = groupObject.getString("name");
                                int groupId = groupObject.getInt("id");

                                // Add the group to the containerG view.
                                containerG.addView(createGLayout(groupName, groupId));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GroupRequest", "Error: " + error.getMessage());

            }
        });

// Add the request to the RequestQueue.
        queue1.add(stringRequest1);


        EditText sendToUsername = view.findViewById(R.id.Send_FR_username);
        ImageView sendFr = view.findViewById(R.id.Send_FR);
        sendFr.setOnClickListener(v -> {
            String receiverUsername = sendToUsername.getText().toString();
            StringRequest stringRequest = RequestJson.sendFriendRequest(view.getContext(), receiverUsername);
            RequestQueue queue = Volley.newRequestQueue(view.getContext());
            queue.add(stringRequest);
            containerP.addView(createFRLayout(containerP, containerF, receiverUsername, receiverUsername, 0, true));
        });



        if (User.getInstance().getFriendRequests() != null){
            try {
                for (int i = 0; i < User.getInstance().getFriendRequests().length(); i++) {
                    JSONObject item = User.getInstance().getFriendRequests().getJSONObject(i);
                    int id = item.getInt("id");
                    String senderUsername = item.getString("senderUsername");
                    String status = item.getString("status");

                    switch (status){
                        case "DECLINED":
                            break;
                        case "PENDING":
                            if(!senderUsername.equalsIgnoreCase(User.getInstance().getUsername())){ containerFR.addView(createFRLayout(containerFR, containerF, senderUsername, senderUsername, id, false)); }
                            else{
                                String receiverUsername = item.getString("receiverUsername");
                                containerP.addView(createFRLayout(containerP, containerF, receiverUsername, receiverUsername, id, true));
                            }
                            break;
                        case "ACCEPTED":
                            if(!User.getInstance().getUsername().equalsIgnoreCase(senderUsername)) { containerF.addView(createFLayout(senderUsername, senderUsername, id));}
                            else { containerF.addView(createFLayout(item.getString("receiverUsername"), item.getString("receiverUsername"), id)); }
                            break;
                    }
                }
            } catch (JSONException e) { User.dialogError(view.getContext(), e.toString()); }
        }

        return view;
    }

    /**
     * context method creates a new constraint layout with an ImageView 2 TextViews and 2 more image views with onClickListeners.
     * @param userUsername username of the user
     * @param userName name of the user
     * @param num ID of the user friend request
     * @return Constraint layout to be added to screen
     */
    private ConstraintLayout createFRLayout(LinearLayout container1, LinearLayout container2, String userUsername, String userName, int num, Boolean hideAcceptButton){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(view.getContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int fortyDPtoPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40 , this.getResources().getDisplayMetrics());
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , this.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(view.getContext());
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView username = new TextView(view.getContext());
        username.setId(200000 + num);
        username.setText(userUsername);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        username.setLayoutParams(layoutParams2);
        username.setTextColor(Color.BLACK);
        username.setTextSize(16);
        constraintLayout.addView(username);

        TextView name = new TextView(view.getContext());
        name.setId(300000 + num);
        name.setText(userName);
        name.setTextColor(Color.DKGRAY);
        name.setTextSize(14);
        constraintLayout.addView(name);

        ImageView accept = new ImageView(view.getContext());
        accept.setId(400000 + num);
        accept.setImageResource(R.drawable.check);
        accept.setAdjustViewBounds(true);
        accept.setLayoutParams(new ViewGroup.LayoutParams(fortyDPtoPX, fortyDPtoPX));

        ImageView deny = new ImageView(view.getContext());
        deny.setId(500000 + num);
        deny.setImageResource(R.drawable.close);
        deny.setAdjustViewBounds(true);
        deny.setLayoutParams(new ViewGroup.LayoutParams(fortyDPtoPX, fortyDPtoPX));

        accept.setOnClickListener(view -> {
            try {
                JSONObject params = new JSONObject();
                params.put("requestId", num);
                JsonObjectRequest jsonObjectRequest = friendRequestStatusUpdate(view.getContext(), params, "accept", num);
                RequestQueue queue = Volley.newRequestQueue(view.getContext());
                queue.add(jsonObjectRequest);
                accept.setVisibility(View.GONE);
                deny.setVisibility(View.GONE);
                container1.removeView(constraintLayout);
                container2.addView(constraintLayout);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        deny.setOnClickListener(view -> {
            try {
                JSONObject params = new JSONObject();
                params.put("requestId", num);
                JsonObjectRequest jsonObjectRequest = friendRequestStatusUpdate(view.getContext(), params, "decline", num);
                RequestQueue queue = Volley.newRequestQueue(view.getContext());
                queue.add(jsonObjectRequest);
                accept.setVisibility(View.GONE);
                deny.setVisibility(View.GONE);
                container1.removeView(constraintLayout);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        constraintLayout.addView(deny);
        constraintLayout.addView(accept);

        /* Set Constraints */
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(pfp.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(pfp.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(pfp.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(username.getId(), ConstraintSet.START, pfp.getId(), ConstraintSet.END);
        constraintSet.connect(username.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);

        constraintSet.connect(name.getId(), ConstraintSet.START, username.getId(), ConstraintSet.START);
        constraintSet.connect(name.getId(), ConstraintSet.TOP, username.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(deny.getId(),ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
        constraintSet.connect(deny.getId(),ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(deny.getId(),ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(accept.getId(), ConstraintSet.END ,deny.getId(),ConstraintSet.START);
        constraintSet.connect(accept.getId(), ConstraintSet.TOP, deny.getId(),ConstraintSet.TOP);
        constraintSet.connect(accept.getId(), ConstraintSet.BOTTOM, deny.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(constraintLayout);

        if (hideAcceptButton){ accept.setVisibility(View.GONE); }

        return constraintLayout;
    }

    /**
     * context method creates a new constraint layout with an ImageView and 2 TextViews
     * @param userUsername username of the user
     * @param userName name of the user
     * @param num ID of the user friend request
     * @return Constraint layout to be added to screen
     */
    private ConstraintLayout createFLayout(String userUsername, String userName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(view.getContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , this.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(view.getContext());
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView username = new TextView(view.getContext());
        username.setId(200000 + num);
        username.setText(userUsername);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        username.setLayoutParams(layoutParams2);
        username.setTextColor(Color.BLACK);
        username.setTextSize(16);
        constraintLayout.addView(username);

        TextView name = new TextView(view.getContext());
        name.setId(300000 + num);
        name.setText(userName);
        name.setTextColor(Color.DKGRAY);
        name.setTextSize(14);
        constraintLayout.addView(name);

        /* Set Constraints */
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(pfp.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(pfp.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(pfp.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(username.getId(), ConstraintSet.START, pfp.getId(), ConstraintSet.END);
        constraintSet.connect(username.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);

        constraintSet.connect(name.getId(), ConstraintSet.START, username.getId(), ConstraintSet.START);
        constraintSet.connect(name.getId(), ConstraintSet.TOP, username.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(constraintLayout);

        Intent intent = new Intent(view.getContext(), ChatActivity.class);
        intent.putExtra("user", userUsername);
        constraintLayout.setOnClickListener(v -> startActivity(intent));

        return constraintLayout;
    }

    /**
     * context method creates a new constraint layout with an ImageView 2 TextViews
     * @param groupName name of the group
     * @param num id of the group
     * @return Constraint layout to be added to screen
     */
    private ConstraintLayout createGLayout(String groupName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(view.getContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , this.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(view.getContext());
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView group = new TextView(view.getContext());
        group.setId(200000 + num);
        group.setText(groupName);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        group.setLayoutParams(layoutParams2);
        group.setTextColor(Color.BLACK);
        group.setTextSize(16);
        constraintLayout.addView(group);

        /* Set Constraints */
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(pfp.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(pfp.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(pfp.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(group.getId(), ConstraintSet.START, pfp.getId(), ConstraintSet.END);
        constraintSet.connect(group.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(group.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(constraintLayout);

        Intent intent = new Intent(view.getContext(), GroupChatActivity.class);
        intent.putExtra("groupName", groupName);
        constraintLayout.setOnClickListener(v -> startActivity(intent));

        return constraintLayout;
    }
}
