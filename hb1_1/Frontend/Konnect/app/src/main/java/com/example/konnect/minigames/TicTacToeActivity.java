package com.example.konnect.minigames;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.helper.RequestJson;
import com.example.konnect.helper.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeActivity extends AppCompatActivity {
    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    String playerOneName = User.getInstance().getName();
    String playerTwoName = "Tic Tac Toe Bot";
    LinearLayout playerOneLayout, playerTwoLayout;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame_ttt_activity);

        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {2,4,6});
        combinationList.add(new int[] {0,4,8});

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);

        TextView playerOneTVName = findViewById(R.id.playerOneName);
        playerOneTVName.setText(playerOneName);



        image1.setOnClickListener(v -> { if (isBoxSelectable(0)){ performAction((ImageView)v, 0); } });
        image2.setOnClickListener(v -> { if (isBoxSelectable(1)){ performAction((ImageView)v, 1); } });
        image3.setOnClickListener(v -> { if (isBoxSelectable(2)){ performAction((ImageView)v, 2); } });
        image4.setOnClickListener(v -> { if (isBoxSelectable(3)){ performAction((ImageView)v, 3); } });
        image5.setOnClickListener(v -> { if (isBoxSelectable(4)){ performAction((ImageView)v, 4); } });
        image6.setOnClickListener(v -> { if (isBoxSelectable(5)){ performAction((ImageView)v, 5); } });
        image7.setOnClickListener(v -> { if (isBoxSelectable(6)){ performAction((ImageView)v, 6); } });
        image8.setOnClickListener(v -> { if (isBoxSelectable(7)){ performAction((ImageView)v, 7); } });
        image9.setOnClickListener(v -> { if (isBoxSelectable(8)){ performAction((ImageView)v, 8); } });

    }

    private void performAction(ImageView  imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) { createAlertDialog("We have a winner!", playerOneName + " is a Winner!"); score++;}
            else if(totalSelectedBoxes == 9) { createAlertDialog("Match Draw", "No winners today!"); }
            else { changePlayerTurn(2); totalSelectedBoxes++; botTurn(); }
        }
        else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) { createAlertDialog("We have a winner!", playerTwoName + " is a Winner!"); score--;}
            else if(totalSelectedBoxes == 9) { createAlertDialog("Match Draw", "No winners today!"); }
            else { changePlayerTurn(1); totalSelectedBoxes++; }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            playerOneLayout.setBackgroundResource(R.drawable.black_border);
            playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {

            playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResults(){
        boolean response = false;
        for (int i = 0; i < combinationList.size(); i++){
            final int[] combination = combinationList.get(i);

            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) { response = true; }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) { return boxPositions[boxPosition] == 0; }

    private void restartMatch(){
        boxPositions = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 1;

        image1.setImageResource(R.drawable.white_box);
        image2.setImageResource(R.drawable.white_box);
        image3.setImageResource(R.drawable.white_box);
        image4.setImageResource(R.drawable.white_box);
        image5.setImageResource(R.drawable.white_box);
        image6.setImageResource(R.drawable.white_box);
        image7.setImageResource(R.drawable.white_box);
        image8.setImageResource(R.drawable.white_box);
        image9.setImageResource(R.drawable.white_box);
    }

    private void createAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title)
                .setCancelable(true)
                .setPositiveButton("Play again?", (dialog, i) -> restartMatch())
                .setNegativeButton("Go back to Dashboard", (dialog, i) -> finishActivity())
                .setOnCancelListener(listener -> finishActivity());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void finishActivity(){
        RequestQueue queue = Volley.newRequestQueue(this);
        for(int i = 0; i < score; i++){
            StringRequest stringRequest = RequestJson.updateScoreBoard(this, this, "api/tictactoe/results");
            queue.add(stringRequest);
        }
    }

    private List<Integer> getAvailableBoxes() {
        List<Integer> availableBoxes = new ArrayList<>();
        for (int i = 0; i < boxPositions.length; i++) { if (boxPositions[i] == 0) { availableBoxes.add(i); } }
        return availableBoxes;
    }


    private int getUserWinningBox() {
        for (int[] combination : combinationList) {
            if (boxPositions[combination[0]] == 1 && boxPositions[combination[1]] == 1 && boxPositions[combination[2]] == 0) { return combination[2]; }
            if (boxPositions[combination[0]] == 1 && boxPositions[combination[1]] == 0 && boxPositions[combination[2]] == 1) { return combination[1]; }
            if (boxPositions[combination[0]] == 0 && boxPositions[combination[1]] == 1 && boxPositions[combination[2]] == 1) { return combination[0]; }
        }
        return -1;
    }

    private int getBotWinningBox() {
        for (int[] combination : combinationList) {
            if (boxPositions[combination[0]] == 1 && boxPositions[combination[1]] == 1 && boxPositions[combination[2]] == 0) { return combination[2]; }
            if (boxPositions[combination[0]] == 1 && boxPositions[combination[1]] == 0 && boxPositions[combination[2]] == 1) { return combination[1]; }
            if (boxPositions[combination[0]] == 0 && boxPositions[combination[1]] == 1 && boxPositions[combination[2]] == 1) { return combination[0]; }
        }
        return -1;
    }

    private void botTurn() {
        int selectedBoxPosition = getBotWinningBox();

        if (selectedBoxPosition == -1) {
            selectedBoxPosition = getUserWinningBox();
            if (selectedBoxPosition == -1) {
                List<Integer> availableBoxes = getAvailableBoxes();
                if (!availableBoxes.isEmpty()) {
                    int randomBox = new Random().nextInt(availableBoxes.size());
                    selectedBoxPosition = availableBoxes.get(randomBox);
                }
            }
        }
        ImageView selectedImageView;
        switch (selectedBoxPosition) {
            case 0:
                selectedImageView = image1;
                break;
            case 1:
                selectedImageView = image2;
                break;
            case 2:
                selectedImageView = image3;
                break;
            case 3:
                selectedImageView = image4;
                break;
            case 4:
                selectedImageView = image5;
                break;
            case 5:
                selectedImageView = image6;
                break;
            case 6:
                selectedImageView = image7;
                break;
            case 7:
                selectedImageView = image8;
                break;
            case 8:
                selectedImageView = image9;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedBoxPosition);
        }
        performAction(selectedImageView, selectedBoxPosition);
    }

}
