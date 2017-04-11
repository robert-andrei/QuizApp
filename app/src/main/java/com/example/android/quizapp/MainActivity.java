package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    int score, questionNo, noOfQuestions;
    Question question;
    Question[] questions = new Question[10];
    QuizApp quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        quiz = QuizApp.getInstance();
        noOfQuestions = quiz.getNoOfQuestions();

        for (int i = 0; i < noOfQuestions; i++) {
            questions[i] = new Question(getResources().getStringArray(R.array.questions)[i],
                    getResources().getStringArray(R.array.questionTypes)[i],
                    getResources().getStringArray(R.array.answers)[4 * i],
                    getResources().getStringArray(R.array.answers)[4 * i + 1],
                    getResources().getStringArray(R.array.answers)[4 * i + 2],
                    getResources().getStringArray(R.array.answers)[4 * i + 3],
                    getResources().getIntArray(R.array.correctAnswers)[4 * i],
                    getResources().getIntArray(R.array.correctAnswers)[4 * i + 1],
                    getResources().getIntArray(R.array.correctAnswers)[4 * i + 2],
                    getResources().getIntArray(R.array.correctAnswers)[4 * i + 3]);
        }
        quiz.setQuestions(questions);

        score = quiz.getScore();
        questionNo = quiz.getQuestionNo();
        question = quiz.getQuestionAtIndex(questionNo);

        name = (EditText) findViewById(R.id.enterName);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//
//        outState.putInt("SCORE", score);
//        outState.putInt("QUESTIONNO", questionNo);
//    }

    public void startQuiz(View view) {
        if (name.getText().length() != 0) {
            quiz.setPlayerName(name.getText().toString());
            quiz.setScore(0);
            quiz.setQuestionNo(0);
            switch (question.getType()) {
            case "multiple":
                startActivity(new Intent(MainActivity.this, MultipleAnswerQuestionActivity.class));
                break;
            case "single":
                startActivity(new Intent(MainActivity.this, SingleAnswerQuestionActivity.class));
                break;
            case "free":
                startActivity(new Intent(MainActivity.this, FreeAnswerQuestionActivity.class));
                break;
            }
        } else {
            Toast.makeText(this, R.string.noNameToast, Toast.LENGTH_SHORT).show();
        }
    }
}
