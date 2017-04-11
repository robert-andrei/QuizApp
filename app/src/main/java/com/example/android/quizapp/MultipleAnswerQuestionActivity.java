package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MultipleAnswerQuestionActivity extends AppCompatActivity {

    int score, questionNo, noOfQuestions;

    Question question;
    CheckBox[] answers = new CheckBox[4];
    TextView questionView;
    QuizApp quiz = QuizApp.getInstance();
    Button nextButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_answers_question);

        score = quiz.getScore();
        questionNo = quiz.getQuestionNo();
        noOfQuestions = quiz.getNoOfQuestions();
        question = quiz.getQuestionAtIndex(questionNo);

        questionView = (TextView) findViewById(R.id.questionTextView);
        questionView.setText(question.getQuestion());

        nextButton = (Button) findViewById(R.id.nextButton);
        if (questionNo == noOfQuestions - 1)
            nextButton.setText(getResources().getString(R.string.endButton));

        answers[0] = (CheckBox) findViewById(R.id.checkbox1);
        answers[1] = (CheckBox) findViewById(R.id.checkbox2);
        answers[2] = (CheckBox) findViewById(R.id.checkbox3);
        answers[3] = (CheckBox) findViewById(R.id.checkbox4);

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
            boolean firstCorrectAnswer = true;

            for (int i = 0; i < 4; i++) {
                if (question.getIsCorrect(i) == 1) {
                    if (!answers[i].isChecked()) {
                        answerIsCorrect = false;
                    } else {
                        givenAnswerStringBuilder.append((firstCorrectAnswer ? "" : ", ") + (char)('A' + i));
                        firstCorrectAnswer = false;
                    }
                } else if (question.getIsCorrect(i) == 0) {
                    if (answers[i].isChecked()) {
                        answerIsCorrect = false;
                        givenAnswerStringBuilder.append((firstCorrectAnswer ? "" : ", ") + (char)('A' + i));
                        firstCorrectAnswer = false;
                    }
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
                        startActivity(new Intent(MultipleAnswerQuestionActivity.this, MultipleAnswerQuestionActivity.class));
                        break;
                    case "single":
                        startActivity(new Intent(MultipleAnswerQuestionActivity.this, SingleAnswerQuestionActivity.class));
                        break;
                    case "free":
                        startActivity(new Intent(MultipleAnswerQuestionActivity.this, FreeAnswerQuestionActivity.class));
                        break;
                }
            } else {
                startActivity(new Intent(MultipleAnswerQuestionActivity.this, FinalActivity.class));
            }
        } else {
            Toast.makeText(this, R.string.noMultipleAnswersToast, Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//
//        outState.putInt("SCORE", score);
//        outState.putInt("QUESTIONNO", questionNo);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        if(savedInstanceState != null && savedInstanceState.containsKey("SCORE")){
//            score = savedInstanceState.getInt("SCORE");
//        }else{
//            score = 0;
//        }
//
//        if(savedInstanceState != null && savedInstanceState.containsKey("QUESTIONNO")){
//            questionNo = savedInstanceState.getInt("QUESTIONNO");
//        }else{
//            questionNo = 0;
//        }
//    }
}
