package com.example.echo.models;

import com.google.gson.InstanceCreator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


import javax.naming.Context;
import java.io.Serializable;
import java.lang.reflect.Type;


@Entity
public class Question implements Serializable
{
    @Id
    public String QID;
    @SerializedName("question")
    @Expose
    public String question;
    @SerializedName("A")
    @Expose
    public String a;
    @SerializedName("B")
    @Expose
    public String b;
    @SerializedName("C")
    @Expose
    public String c;
    @SerializedName("D")
    @Expose
    public String d;
    @SerializedName("Answer")
    @Expose
    public String answer;

    @SerializedName("context")
    @Expose
    private Context context;
    public Question(Context context) {
        this.context = context;
    }
    private final static long serialVersionUID = -4991386491965013968L;


    public Question() {
    }


    public Question(String question, String a, String b, String c, String d, String answer) {
        super();
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

}

