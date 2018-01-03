package com.example.echo;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.util.ArrayList;
import java.util.List;

@Entity
public class QuestionBank {
@Id
private String Topic;

private List<Questions> questionlist =new ArrayList<Questions>();

    public QuestionBank(String Topic,List<Questions> questionlist) {
        this.questionlist=questionlist;
        this.Topic=Topic;
    }

    public List<Questions> getQuestionlist() {
        return questionlist;
    }

    public String getTopic() {
        return Topic;
    }
}
