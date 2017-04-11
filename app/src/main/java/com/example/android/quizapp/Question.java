package com.example.android.quizapp;

public class Question {
    private String question;
    private String type;   // single - single answer; multiple multiple answers; free - free answer
    private String[] answers;
    private int[] isCorrect;


    public Question (String question, String type, String answerA, String answerB,
                     String answerC, String answerD, int isCorrectA, int isCorrectB,
                     int isCorrectC, int isCorrectD) {
        this.question = question;
        this.type = type;
        this.answers = new String[4];
        this.answers[0] = answerA;
        this.answers[1] = answerB;
        this.answers[2] = answerC;
        this.answers[3] = answerD;
        this.isCorrect = new int[4];
        this.isCorrect[0] = isCorrectA;
        this.isCorrect[1] = isCorrectB;
        this.isCorrect[2] = isCorrectC;
        this.isCorrect[3] = isCorrectD;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getType() {
        return type;
    }

    public String getAnswer(int index) {
        return this.answers[index];
    }

    public int getIsCorrect(int index) {
        return this.isCorrect[index];
    }

}
