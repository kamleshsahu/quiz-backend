package com.example.echo.models;

import com.googlecode.objectify.annotation.*;


public class Stats {



    String topic;
    String accuracy;
    String Ranking;
    int totalScore=0;
    int Correct=0;
    int Q_Solved=0;

    public Stats(String topic,int Correct,int Q_Solved,int totalScore) {
        this.Correct =Correct;
        this.Q_Solved =Q_Solved;
        this.totalScore=totalScore;
        this.topic=topic;
     }

    public Stats() {
        super();
    }

    public String getTopic() {
        return topic;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public String getRanking() {
        return Ranking;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCorrect() {
        return Correct;
    }

    public int getQ_Solved() {
        return Q_Solved;
    }

    public void setCorrect(int correct) {
        Correct = correct;
    }

    public void setQ_Solved(int q_Solved) {
        Q_Solved = q_Solved;
    }
}
