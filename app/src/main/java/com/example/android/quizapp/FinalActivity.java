package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    TextView scoreView;
    TextView playerName;

    QuizApp quiz;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_page);

        quiz = QuizApp.getInstance();

        playerName = (TextView) findViewById(R.id.playerName);
        playerName.setText(quiz.getPlayerName());

        scoreView = (TextView) findViewById(R.id.score);
        scoreView.setText(String.valueOf(quiz.getScore()));
    }

    public void restart(View view) {
        startActivity(new Intent(FinalActivity.this, MainActivity.class));
    }

    public void showDetails(View view) {
        startActivity(new Intent(FinalActivity.this, ResultsActivity.class));
    }
}
