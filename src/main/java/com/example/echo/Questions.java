package com.example.echo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Questions {

@Id String QID;
@Index
private String Question;
private String A;
private String B;
private String C;
private String D;
@Index
private String ans;

    public Questions() {
        super();
    }

    public Questions(String Question, String A, String B, String C, String D, String ans) {
       this.Question=Question;
       this.A=A;
       this.B=B;
       this.C=C;
       this.D=D;
       this.ans=ans;
    }

    public void setQID(String QID) {
        this.QID = QID;
    }

    public String getQID() {
        return QID;
    }


}
