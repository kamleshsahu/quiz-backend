package com.example.echo.models;

/**
 * Created by linus on 06-01-2018.
 */

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.util.List;

@Entity
@Cache
public class QuestionsApi {

    @Id long id;

    @Parent
    private Key<Rooms> RoomKey;
    private String topic;
    private List<Question> questionlist = null;
    /**
     * No args constructor for use in serialization
     *
     */
    public QuestionsApi() {
    }

    /**
     *
     * @param topic
     * @param questionlist
     */
    public QuestionsApi(String topic, List<Question> questionlist) {
        super();
        this.topic = topic;
        this.questionlist = questionlist;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Question> getQuestionlist() {
        return questionlist;
    }

    public void setQuestionlist(List<Question> questionlist) {
        this.questionlist = questionlist;
    }

    public void setRoomId(String roomId) {

        Key<Rooms> rk=Key.create(Rooms.class,roomId);
        RoomKey =rk;
    }

//    public String getRoomId() {
//        return RoomKey;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
