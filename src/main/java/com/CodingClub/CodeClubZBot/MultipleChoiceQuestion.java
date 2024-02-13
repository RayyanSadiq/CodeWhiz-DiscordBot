package com.CodingClub.CodeClubZBot;

public class MultipleChoiceQuestion {

    private String question;



    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;

    public MultipleChoiceQuestion() {

    }

    public MultipleChoiceQuestion(String question, String answerA, String answerB, String answerC, String answerD) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public void setQuestion(String question) {
        this.question = question.substring(question.indexOf(":")+1);

    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA.replaceFirst("%", "");
        setCorrectAnswer(question);
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB.replaceFirst("%", "");
        setCorrectAnswer(question);
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC.replaceFirst("%", "");
        setCorrectAnswer(question);
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD.replaceFirst("%", "");
        setCorrectAnswer(question);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String answer) {
        if (answer.startsWith("%")){correctAnswer = answer;}
    }



}
