package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    QuizApp quiz;
    int noOfQuestions;
    boolean[] answerIsCorrect;
    ImageView[] statusOfAnswer;
    TextView[] givenAnswer;
    TextView[] correctAnswer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        quiz = QuizApp.getInstance();
        noOfQuestions = quiz.getNoOfQuestions();

        answerIsCorrect = quiz.getAnswerIsCorrect();

        givenAnswer = new TextView[noOfQuestions];
        givenAnswer[0] = (TextView) findViewById(R.id.myAnswer1);
        givenAnswer[1] = (TextView) findViewById(R.id.myAnswer2);
        givenAnswer[2] = (TextView) findViewById(R.id.myAnswer3);
        givenAnswer[3] = (TextView) findViewById(R.id.myAnswer4);
        givenAnswer[4] = (TextView) findViewById(R.id.myAnswer5);
        givenAnswer[5] = (TextView) findViewById(R.id.myAnswer6);
        givenAnswer[6] = (TextView) findViewById(R.id.myAnswer7);
        givenAnswer[7] = (TextView) findViewById(R.id.myAnswer8);
        givenAnswer[8] = (TextView) findViewById(R.id.myAnswer9);
        givenAnswer[9] = (TextView) findViewById(R.id.myAnswer10);

        for (int i = 0; i < noOfQuestions; i++) {
            givenAnswer[i].setText(quiz.getGivenAnswerAtIndex(i));
            if (quiz.getAnswerIsCorrectAtIndex(i)) {
                givenAnswer[i].setTextColor(getResources().getColor(R.color.correctAnswerTextColor));
            } else {
                givenAnswer[i].setTextColor(getResources().getColor(R.color.wrongAnswerTextColor));
            }
        }

        correctAnswer = new TextView[noOfQuestions];
        correctAnswer[0] = (TextView) findViewById(R.id.correctAnswer1);
        correctAnswer[1] = (TextView) findViewById(R.id.correctAnswer2);
        correctAnswer[2] = (TextView) findViewById(R.id.correctAnswer3);
        correctAnswer[3] = (TextView) findViewById(R.id.correctAnswer4);
        correctAnswer[4] = (TextView) findViewById(R.id.correctAnswer5);
        correctAnswer[5] = (TextView) findViewById(R.id.correctAnswer6);
        correctAnswer[6] = (TextView) findViewById(R.id.correctAnswer7);
        correctAnswer[7] = (TextView) findViewById(R.id.correctAnswer8);
        correctAnswer[8] = (TextView) findViewById(R.id.correctAnswer9);
        correctAnswer[9] = (TextView) findViewById(R.id.correctAnswer10);

        completeCorrectAnswers();

        statusOfAnswer = new ImageView[noOfQuestions];
        statusOfAnswer[0] = (ImageView) findViewById(R.id.result1);
        statusOfAnswer[1] = (ImageView) findViewById(R.id.result2);
        statusOfAnswer[2] = (ImageView) findViewById(R.id.result3);
        statusOfAnswer[3] = (ImageView) findViewById(R.id.result4);
        statusOfAnswer[4] = (ImageView) findViewById(R.id.result5);
        statusOfAnswer[5] = (ImageView) findViewById(R.id.result6);
        statusOfAnswer[6] = (ImageView) findViewById(R.id.result7);
        statusOfAnswer[7] = (ImageView) findViewById(R.id.result8);
        statusOfAnswer[8] = (ImageView) findViewById(R.id.result9);
        statusOfAnswer[9] = (ImageView) findViewById(R.id.result10);

        for (int i = 0; i < noOfQuestions; i++) {
            if (answerIsCorrect[i])
                statusOfAnswer[i].setImageResource(R.drawable.right_answer);
            else
                statusOfAnswer[i].setImageResource(R.drawable.wrong_answer);
        }

    }

    public void goBack(View view) {
        startActivity(new Intent(ResultsActivity.this, FinalActivity.class));
    }

    private void completeCorrectAnswers() {

        for (int i = 0; i < noOfQuestions; i++) {

            StringBuilder correctAnswerBuilder = new StringBuilder();
            boolean firstCorrectAnswer = true;
            switch (quiz.getQuestions()[i].getType()) {
                case "multiple":
                    for (int j = 0; j < 4; j++) {
                        if (quiz.getQuestions()[i].getIsCorrect(j) == 1) {
                            correctAnswerBuilder.append((firstCorrectAnswer ? "" : ", ") + (char)('A' + j));
                            firstCorrectAnswer = false;
                        }
                    }
                    break;
                case "single":
                    for (int j = 0; j < 4; j++) {
                        if (quiz.getQuestions()[i].getIsCorrect(j) == 1) {
                            correctAnswerBuilder.append((char)('A' + j));
                            break;
                        }
                    }
                    break;
                case "free":
                    if (quiz.getQuestions()[i].getIsCorrect(0) == 1) {
                        correctAnswerBuilder.append(quiz.getQuestions()[i].getAnswer(0));
                    }
                    break;
            }

            correctAnswer[i].setText(correctAnswerBuilder);
        }
    }
}
