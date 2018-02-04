package com.example.echo.models;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache
public class Rooms {
    @Id
    Long  id;
    @Index
    QuestionsApi questionsApi;

    public Rooms() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionsApi(QuestionsApi questionsApi) {
        this.questionsApi = questionsApi;
    }

    public QuestionsApi getQuestionsApi() {
        return questionsApi;
    }
}
