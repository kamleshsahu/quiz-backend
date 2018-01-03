package com.example.echo;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.echo.OfyService.ofy;

public class makeQue_forGamePlay {


    public makeQue_forGamePlay() {
        super();
    }


    public Object makeQue_forGamePlay(String PID,String topic) {

        Random randno =new Random();
        int max=185;
       // String topic="astrology";
        QuestionBank qb =new QuestionBank();
        List<Question> questionList = new ArrayList<Question>();
        qb.topic =topic;
        String id = null;
        try {
            for (int k = 0; k < 7; k++) {

                id = topic + "-" + randno.nextInt(max);
                System.out.println(id);

                String finalId = id;
                ObjectifyService.run(new VoidWork() {
                    public void vrun() {
                        //   System.out.println();

                        Question question = ofy().load().key(Key.create(Question.class, finalId)).now();
                        questionList.add(question);
                    }
                });

            }
            qb.questionlist =questionList;
            return qb;
        }catch (Exception e){
            e.fillInStackTrace();
            return e.toString();
        }


    }
    public static void main(String... args){





    }
}