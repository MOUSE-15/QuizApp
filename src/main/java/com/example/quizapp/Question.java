package com.example.quizapp;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String correctAnswer; // "A", "B", or "C"

    public Question(String question, String optionA, String optionB, String optionC, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() { return question; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }

    public String getCorrectAnswerText() {
        switch (correctAnswer) {
            case "A": return optionA;
            case "B": return optionB;
            case "C": return optionC;
            default: return "";
        }
    }
}
