package com.example.echo.models;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;



@Entity
public class Stats {

    @Id
    String id ;
    @Index
    String topic;
    @Index
    String PID;

//    @Parent
//    Key<Player> playerKey ;
    @Index
    int totalScore=0;
    int Correct=0;
    int Q_Solved=0;
    int Q_Total;
    String accuracy="";



    public Stats(String topic,int Correct,int Q_Solved,int totalScore,String PID) {
        this.id=PID+"-"+topic;
        this.Correct =Correct;
        this.Q_Solved =Q_Solved;
        this.totalScore=totalScore;
        this.topic=topic;
    //    this.playerKey=Key.create(Player.class,PID);
        this.PID=PID;
     }

    public Stats() {
        super();
    }

    public String getTopic() {
        return topic;
    }



    public int getTotalScore() {
        return totalScore;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

//    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
//    public Key<Player> getPlayerKey() {
//        return playerKey;
//    }
//
//    public void setPlayerKey(Key<Player> playerKey) {
//        this.playerKey = playerKey;
//    }


    public String getId() {
        return id;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public int getQ_Total() {
        return Q_Total;
    }

    public void setQ_Total(int q_Total) {
        Q_Total = q_Total;
    }
}


