package com.example.konnect.minigames;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.helper.Question;
import com.example.konnect.helper.ServerURLs;
import com.example.konnect.helper.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UnquoteActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;

    ImageView ivMainQuestionImage;
    TextView tvMainQuestionTitle;
    TextView tvMainQuestionsRemainingCount;
    Button btnMainSubmitAnswer;
    Button btnMainAnswer0;
    Button btnMainAnswer1;
    Button btnMainAnswer2;
    Button btnMainAnswer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.minigame_unquote_activity);

        ivMainQuestionImage = findViewById(R.id.iv_main_question_image);
        tvMainQuestionTitle = findViewById(R.id.tv_main_question_title);
        tvMainQuestionsRemainingCount = findViewById(R.id.tv_main_questions_remaining_count);
        btnMainSubmitAnswer = findViewById(R.id.btn_main_submit_answer);
        btnMainAnswer0 = findViewById(R.id.btn_main_answer_0);
        btnMainAnswer1 = findViewById(R.id.btn_main_answer_1);
        btnMainAnswer2 = findViewById(R.id.btn_main_answer_2);
        btnMainAnswer3 = findViewById(R.id.btn_main_answer_3);

        btnMainAnswer0.setOnClickListener(view -> onAnswerSelected(0));
        btnMainAnswer1.setOnClickListener(view -> onAnswerSelected(1));
        btnMainAnswer2.setOnClickListener(view -> onAnswerSelected(2));
        btnMainAnswer3.setOnClickListener(view -> onAnswerSelected(3));
        btnMainSubmitAnswer.setOnClickListener(view -> onAnswerSubmission());

        startNewGame();

        JsonObjectRequest jsonObjectRequest = getJsonObjectRequest(totalCorrect);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    @NonNull
    private JsonObjectRequest getJsonObjectRequest(int score) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", User.getInstance().getUsername());
            jsonObject.put("score", score);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return new JsonObjectRequest(Request.Method.POST, ServerURLs.getServerUrl() + "game1Score/", jsonObject, response -> {
            try {
                Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {});
    }

    void displayQuestion(Question currentQuestion){
        ivMainQuestionImage.setImageResource(currentQuestion.getImageId());
        tvMainQuestionTitle.setText(currentQuestion.getQuestionText());
        btnMainAnswer0.setText(currentQuestion.getAnswer0());
        btnMainAnswer1.setText(currentQuestion.getAnswer1());
        btnMainAnswer2.setText(currentQuestion.getAnswer2());
        btnMainAnswer3.setText(currentQuestion.getAnswer3());
    }

    // Updates the image view which displays the number of remaining questions
    @SuppressLint("SetTextI18n")
    void displayQuestionsRemaining(int remainingQuestions){
        tvMainQuestionsRemainingCount.setText(Integer.toString(remainingQuestions));
    }

    // Updates all buttons and checks if the answer is correct or incorrect and displays a symbol
    void onAnswerSelected(int answerSelected){
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.setPlayerAnswer(answerSelected);
        btnMainAnswer0.setText(currentQuestion.getAnswer0());
        btnMainAnswer1.setText(currentQuestion.getAnswer1());
        btnMainAnswer2.setText(currentQuestion.getAnswer2());
        btnMainAnswer3.setText(currentQuestion.getAnswer3());

        switch (currentQuestion.getPlayerAnswer()){
            case 0:
                if (currentQuestion.isCorrect()){
                    btnMainAnswer0.setText("✔");
                    btnMainAnswer0.setBackgroundColor(Color.GREEN);
                }
                else{
                    btnMainAnswer0.setText("✖");
                    btnMainAnswer0.setBackgroundColor(Color.RED);
                }
                break;
            case 1:
                if (currentQuestion.isCorrect()){
                    btnMainAnswer1.setText("✔");
                    btnMainAnswer1.setBackgroundColor(Color.GREEN);
                }
                else{
                    btnMainAnswer1.setText("✖");
                    btnMainAnswer1.setBackgroundColor(Color.RED);
                }
                break;
            case 2:
                if (currentQuestion.isCorrect()){
                    btnMainAnswer2.setText("✔");
                    btnMainAnswer2.setBackgroundColor(Color.GREEN);
                }
                else{
                    btnMainAnswer2.setText("✖");
                    btnMainAnswer2.setBackgroundColor(Color.RED);

                }
                break;
            case 3:
                if (currentQuestion.isCorrect()){
                    btnMainAnswer3.setText("✔");
                    btnMainAnswer3.setBackgroundColor(Color.GREEN);
                }
                else{
                    btnMainAnswer3.setText("✖");
                    btnMainAnswer3.setBackgroundColor(Color.RED);
                }
                break;
            default:
                break;
        }
    }

    // Checks to see if the last button that was pressed was the correct one.
    void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if(currentQuestion.getPlayerAnswer() == -1){
            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(UnquoteActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("Error");
            gameOverDialogBuilder.setMessage("Please select an answer before submitting.");
            gameOverDialogBuilder.setPositiveButton("Okay", (dialogInterface, i) -> displayQuestion(currentQuestion));
            gameOverDialogBuilder.create().show();
            return;
        }
        else if (currentQuestion.isCorrect()) {
            totalCorrect = totalCorrect + 1;
        }
        questions.remove(currentQuestion);


        displayQuestionsRemaining(questions.size());

        if (questions.isEmpty()) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(UnquoteActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("Game Over!");
            gameOverDialogBuilder.setMessage(gameOverMessage);
            gameOverDialogBuilder.setPositiveButton("Play Again!", (dialog, i) -> startNewGame());
            gameOverDialogBuilder.setNegativeButton("Stop here!", (dialog, i) -> finish());
            gameOverDialogBuilder.create().show();
        } else {
            chooseNewQuestion();

            displayQuestion(getCurrentQuestion());
        }
    }

    // Starts a new game, creating questions objects and adding them to an ArrayList
    void startNewGame() {
        questions = new ArrayList<>();

        Question question0 = new Question(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it... Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
        Question question1 = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authorized this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
        Question question2 = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow - who actually said it?", "Martin Luther King Jr", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);
        Question question3 = new Question(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);
        Question question4 = new Question(R.drawable.img_quote_4, "A peace worth striving for — who actually reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr", "Liu Xiaobo", "Dalai Lama ", 1);
        Question question5 = new Question(R.drawable.img_quote_5, "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich ", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);
        Question question6 = new Question(R.drawable.img_quote_6, "Here’s the truth, Will Smith did say this, but in which movie?","Independence Day", "Bad Boys", "Men In Black", "The Pursuit of Happiness", 2);
        Question question7 = new Question(R.drawable.img_quote_7, "Which TV funny gal actually quipped this 1-liner?", "Ellen Degeneres", "Amy Poehler", "Betty White", "Tina Fay", 3);
        Question question8 = new Question(R.drawable.img_quote_8, "This mayor won’t get my vote — but did he actually give this piece of advice? And if not, who did?", "Forrest Gump, Forrest Gump", "Dorry, Finding Nemo", "Esther Williams", "The Mayor, Jaws", 1);
        Question question9 = new Question(R.drawable.img_quote_9, "Her heart will go on, but whose heart is it?","Whitney Houston","Diana Ross","Celine Dion", "Mariah Carey", 0);
        Question question10 = new Question(R.drawable.img_quote_10, "He’s the king of something alright — to whom does this self-titling line belong to?", "Tony Montana, Scarface", "Joker, The Dark Knight", "Lex Luthor, Batman v Superman", "Jack, Titanic", 3);
        Question question11 = new Question(R.drawable.img_quote_11, "Is “Grey” synonymous for “wise”? If so, maybe Gandalf did preach this advice. If not, who did?", "Yoda, Star Wars", "Gandalf The Grey, Lord of the Rings", "Dumbledore, Harry Potter", "Uncle Ben, Spider-Man", 0);
        Question question12 = new Question(R.drawable.img_quote_12, "Houston, we have a problem with this quote — which space-traveler does this catch-phrase actually belong to?", "Han Solo, Star Wars", "Captain Kirk, Star Trek", "Buzz Lightyear, Toy Story", "Jim Lovell, Apollo 13", 2);

        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);


        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();

        displayQuestionsRemaining(questions.size());

        displayQuestion(firstQuestion);
    }

    // Chooses a new question at random
    Question chooseNewQuestion() {
        currentQuestionIndex = generateRandomNumber(questions.size());
        return questions.get(currentQuestionIndex);
    }

    // Generates a new number for the chooseNewQuestion object
    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    // Gets the currently displayed questions
    Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    // Gets a message after the game is complete
    String getGameOverMessage(int totalCorrect, int totalQuestions) {
            return "You got " + totalCorrect + " right out of " + totalQuestions + "!";
    }
}
