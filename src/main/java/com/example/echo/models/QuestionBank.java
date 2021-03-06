package com.example.echo.models;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


import javax.naming.Context;

@Entity
public class QuestionBank implements Serializable
{
    @Id
    @SerializedName("topic")
    @Expose
    public String topic;


    @SerializedName("questionlist")
    @Expose
    public List<Question> questionlist = null;

    @SerializedName("context")
    @Expose
    private Context context;
    public QuestionBank(Context context) {
        this.context = context;
    }
    private final static long serialVersionUID = -8690622590848483763L;


    public QuestionBank() {
    }


    public QuestionBank(String topic, List<Question> questionlist) {
        super();
        this.topic = topic;
        this.questionlist = questionlist;
    }

}

