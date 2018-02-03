package com.example.echo;


import com.example.echo.models.Question;
import com.example.echo.models.QuestionBank;
import com.example.echo.models.Stats;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.googlecode.objectify.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.echo.OfyService.ofy;

public class makeQue_forGamePlay {


    public makeQue_forGamePlay() {
        super();
    }


    public Object makeQue_forGamePlay(String PID,String topic) {


       int max=ObjectifyService.run(new Work<Integer>() {
           @Override
           public Integer run() {
              return ofy().load().type(Question.class).filter("topic =",topic).count();
           }
       });
              //ofy().load().type(Question.class).filter("topic =",topic).count();
        System.out.println("topic length :"+max);
       // String topic="astrology";
        QuestionBank qb =new QuestionBank();
        List<Question> questionList = new ArrayList<Question>();
        qb.topic =topic;
        String id = null;


        int[] randno = new Random().ints(0, max).distinct().limit(8).toArray();

        try {
            for (int k = 0; k < 8; k++) {

                id = topic + "-" + randno[k];
                System.out.println(id);

                String finalId = id;
                ObjectifyService.run(new VoidWork() {
                    public void vrun() {
                        //   System.out.println();

                        Question question = ofy().load().key(Key.create(Question.class, finalId)).now();

                          question.topic=null;
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
