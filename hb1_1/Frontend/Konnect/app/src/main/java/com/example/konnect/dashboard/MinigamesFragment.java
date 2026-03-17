package com.example.konnect.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.konnect.R;
import com.example.konnect.minigames.LeaderboardActivity;
import com.example.konnect.minigames.TicTacToeActivity;
import com.example.konnect.minigames.UnquoteActivity;

public class MinigamesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.minigames_fragment, container, false);

        Button leaderboardsButton = view.findViewById(R.id.Leaderboards_Button);
        Button unquoteButton = view.findViewById(R.id.Unquote_Button);
        Button tttButton = view.findViewById(R.id.TTT_Button);

        leaderboardsButton.setOnClickListener(v -> startActivity(new Intent(view.getContext(), LeaderboardActivity.class)));
        unquoteButton.setOnClickListener(v -> startActivity(new Intent(view.getContext(), UnquoteActivity.class)));
        tttButton.setOnClickListener(v -> startActivity(new Intent(view.getContext(), TicTacToeActivity.class)));

        return view;
    }
}
