package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SingleAnswerQuestionActivity extends AppCompatActivity {

    int score, questionNo, noOfQuestions;

    Question question;
    RadioButton[] answers = new RadioButton[4];
    TextView questionView;

    QuizApp quiz;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_answer_question);

        quiz = QuizApp.getInstance();

        score = quiz.getScore();
        questionNo = quiz.getQuestionNo();
        noOfQuestions = quiz.getNoOfQuestions();
        question = quiz.getQuestionAtIndex(questionNo);

        questionView = (TextView) findViewById(R.id.questionTextView);
        questionView.setText(question.getQuestion());

        answers[0] = (RadioButton) findViewById(R.id.radio1);
        answers[1] = (RadioButton) findViewById(R.id.radio2);
        answers[2] = (RadioButton) findViewById(R.id.radio3);
        answers[3] = (RadioButton) findViewById(R.id.radio4);

        for (int i = 0; i < 4; i++) {
            answers[i].setText(getResources().getStringArray(R.array.answers)[questionNo * 4 + i]);
        }
    }

    public void nextQuestion(View view) {

        boolean checked = false;
        for (int i = 0; i < 4; i++) {
            if (answers[i].isChecked()) {
                checked = true;
                break;
            }
        }

        if (checked) {
            boolean answerIsCorrect = true;
            StringBuilder givenAnswerStringBuilder = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                if (question.getIsCorrect(i) == 1) {
                    if (!answers[i].isChecked()) {
                        answerIsCorrect = false;
//                        break;
                    }
                    givenAnswerStringBuilder.append((char)('A' + i));
                    break;
                }
            }

            quiz.setGivenAnswerAtIndex(givenAnswerStringBuilder.toString(), questionNo);
            quiz.setAnswerIsCorrect(questionNo, answerIsCorrect);

            if (answerIsCorrect) {
                quiz.setScore(++score);
            }
            if (questionNo < noOfQuestions - 1) {
                quiz.setQuestionNo(++questionNo);
                switch (quiz.getQuestionAtIndex(questionNo).getType()) {
                    case "multiple":
                        startActivity(new Intent(SingleAnswerQuestionActivity.this, MultipleAnswerQuestionActivity.class));
                        break;
                    case "single":
                        startActivity(new Intent(SingleAnswerQuestionActivity.this, SingleAnswerQuestionActivity.class));
                        break;
                    case "free":
                        startActivity(new Intent(SingleAnswerQuestionActivity.this, FreeAnswerQuestionActivity.class));
                        break;
                }
            } else {
                startActivity(new Intent(SingleAnswerQuestionActivity.this, FinalActivity.class));
            }
        } else {
            Toast.makeText(this, R.string.noSingleAnswersToast, Toast.LENGTH_SHORT).show();
        }
    }
}
