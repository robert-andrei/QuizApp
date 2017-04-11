package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FreeAnswerQuestionActivity extends AppCompatActivity {

    int score, questionNo, noOfQuestions;

    Question question;
    EditText answer;
    TextView questionView;

    QuizApp quiz;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_answer);

        quiz = QuizApp.getInstance();

        score = quiz.getScore();
        questionNo = quiz.getQuestionNo();
        noOfQuestions = quiz.getNoOfQuestions();
        question = quiz.getQuestionAtIndex(questionNo);

        questionView = (TextView) findViewById(R.id.questionTextView);
        questionView.setText(question.getQuestion());

        answer = (EditText) findViewById(R.id.eAnswer);
        answer.setHint(getResources().getStringArray(R.array.freeQuestionHint)[questionNo]);
        if (questionNo == 5)
            answer.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        else if (questionNo == 8)
            answer.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
    }

    public void nextQuestion(View view) {

        if (answer.getText().length() != 0) {
            boolean answerIsCorrect = true;

           if (answer.getText().toString().replaceAll(" ", "").compareToIgnoreCase(question.getAnswer(0).replaceAll(" ", "")) != 0) {
                answerIsCorrect = false;
            }

            quiz.setGivenAnswerAtIndex(answer.getText().toString(), questionNo);
            quiz.setAnswerIsCorrect(questionNo, answerIsCorrect);

            if (answerIsCorrect) {
                quiz.setScore(++score);
            }
            if (questionNo < noOfQuestions - 1) {
                quiz.setQuestionNo(++questionNo);

                switch (quiz.getQuestionAtIndex(questionNo).getType()) {
                    case "multiple":
                        startActivity(new Intent(FreeAnswerQuestionActivity.this, MultipleAnswerQuestionActivity.class));
                        break;
                    case "single":
                        startActivity(new Intent(FreeAnswerQuestionActivity.this, SingleAnswerQuestionActivity.class));
                        break;
                    case "free":
                        startActivity(new Intent(FreeAnswerQuestionActivity.this, FreeAnswerQuestionActivity.class));
                        break;
                }
            } else {
                startActivity(new Intent(FreeAnswerQuestionActivity.this, FinalActivity.class));
            }
        } else {
            Toast.makeText(this, R.string.noFreeAnswersToast, Toast.LENGTH_SHORT).show();
        }
    }
}
