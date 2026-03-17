package com.example.konnect.minigames;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.konnect.R;
import com.example.konnect.helper.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LeaderboardActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        ImageButton backButton = findViewById(R.id.Minigames_BackButton);
        backButton.setOnClickListener(v -> finish());

        LinearLayout containerLB = findViewById(R.id.Container_LB);
        try {
            JSONArray data = User.getInstance().getLeaderboardData();
            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);
                int id = item.getInt("id");
                String username = item.getString("username");
                int score = item.getInt("score");
                containerLB.addView(createLeaderboardLayout(username, id, score));
            }
        }
        catch (JSONException | NullPointerException e) { User.dialogError(this, e.toString()); }
    }

    @SuppressLint("SetTextI18n")
    public ConstraintLayout createLeaderboardLayout(String username, int num, int score){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , this.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(this);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView textViewUsername = new TextView(this);
        textViewUsername.setId(200000 + num);
        textViewUsername.setText(username);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        textViewUsername.setLayoutParams(layoutParams2);
        textViewUsername.setTextColor(Color.BLACK);
        textViewUsername.setTextSize(24);
        constraintLayout.addView(textViewUsername);

        TextView displayScore = new TextView(this);
        displayScore.setId(300000 + num);
        displayScore.setText("Score: " + score);
        displayScore.setTextColor(Color.DKGRAY);
        displayScore.setTextSize(24);
        constraintLayout.addView(displayScore);


        /* Set Constraints */
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(pfp.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(pfp.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(pfp.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(textViewUsername.getId(), ConstraintSet.START, pfp.getId(), ConstraintSet.END);
        constraintSet.connect(textViewUsername.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(textViewUsername.getId(),ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(displayScore.getId(),ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
        constraintSet.connect(displayScore.getId(),ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(displayScore.getId(),ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);


        constraintSet.applyTo(constraintLayout);
        return constraintLayout;
    }


}
