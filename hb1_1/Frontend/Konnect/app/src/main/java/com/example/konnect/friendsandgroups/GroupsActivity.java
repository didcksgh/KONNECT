package com.example.konnect.friendsandgroups;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.helper.RequestJson;
import com.example.konnect.helper.User;

import java.util.concurrent.atomic.AtomicReference;

public class GroupsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_activity);

        ImageView backButton = findViewById(R.id.Group_BackButton);
        ImageView createGroup = findViewById(R.id.CreateGroup);
        ImageView addUser = findViewById(R.id.AddUser);
        RequestQueue queue = Volley.newRequestQueue(this);

        TextView groupNameTV = findViewById(R.id.GroupName);
        TextView groupUser = findViewById(R.id.GroupUser);
        AtomicReference<String> groupName = new AtomicReference<>();

        backButton.setOnClickListener(v -> finish());
        createGroup.setOnClickListener(v-> {
            groupName.getAndSet(groupNameTV.getText().toString());
            queue.add(RequestJson.createGroup(this, groupName.get()));
            queue.add(RequestJson.addUser(this, groupName.get(), User.getInstance().getUsername()));
        });
        addUser.setOnClickListener(v -> {
            String username = groupUser.getText().toString();
            queue.add(RequestJson.addUser(this, groupName.get(), username));
        });

//        To create a group Post "/groups/create/{groupname}"
//        to List all groups: Get: "/groups/getGroups"
//        to get all the Groups that a user has: Get "/groups/list/{username}"
//        to add a User to a group: Post "/users/addGroup/{userid}/{groupName}"
//        for websockets: "/chat/{groupid}/{username}"




    }

    /**
     * context method creates a new constraint layout with an ImageView 2 TextViews
     * @param groupName name of the group
     * @param num id of the group
     * @return Constraint layout to be added to screen
     */
    private ConstraintLayout createGLayout(String groupName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , this.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(this);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView group = new TextView(this);
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

        Intent intent = new Intent(this, GroupChatActivity.class);
        intent.putExtra("groupName", groupName);
        constraintLayout.setOnClickListener(v -> startActivity(intent));

        return constraintLayout;
    }
}
