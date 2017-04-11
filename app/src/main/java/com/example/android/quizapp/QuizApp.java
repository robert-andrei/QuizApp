package com.example.android.quizapp;

import android.app.Application;

public class QuizApp extends Application {

    private static QuizApp quizSingleton = null;

    private final int noOfQuestions = 10;
    private int score, questionNo;
    private Question[] questions;
    private boolean[] answerIsCorrect;
    private String[] givenAnswers;
    private String playerName;

    private QuizApp () {
        score = 0;
        questionNo = 0;
        questions = new Question[noOfQuestions];
        answerIsCorrect = new boolean[noOfQuestions];
        givenAnswers = new String[noOfQuestions];
    }

    public static QuizApp getInstance() {
        if (quizSingleton == null)
            quizSingleton =  new QuizApp();
        return quizSingleton;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void nextQuestion() {
        questionNo++;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public Question getQuestionAtIndex(int index) {
        return questions[index];
    }

    public boolean[] getAnswerIsCorrect() {
        return answerIsCorrect;
    }

    public boolean getAnswerIsCorrectAtIndex(int index) {
        return answerIsCorrect[index];
    }

    public void setAnswerIsCorrect(int index, boolean value) {
        this.answerIsCorrect[index] = value;
    }

    public String getGivenAnswerAtIndex(int index) {
        return givenAnswers[index];
    }

    public void setGivenAnswerAtIndex(String givenAnswer, int index) {
        this.givenAnswers[index] = givenAnswer;
    }
}
