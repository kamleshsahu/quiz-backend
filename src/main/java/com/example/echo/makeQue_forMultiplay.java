package com.example.echo;


import com.example.echo.models.*;

import com.google.appengine.api.datastore.ReadPolicy;
import com.google.gson.Gson;
import com.googlecode.objectify.*;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.echo.OfyService.factory;
import static com.example.echo.OfyService.ofy;

public class makeQue_forMultiplay {


    public makeQue_forMultiplay() {
        super();
    }


    public Object makeQue_forMultiPlay(String RoomID,String topic) {

       try {


           QuestionsApi questionsApi0 = ObjectifyService.run(new Work<QuestionsApi>() {
               @Override
               public QuestionsApi run() {
                   System.out.println("yeh here is the questions found already ");
                   Key<Rooms> rk=Key.create(Rooms.class, RoomID);
                   QuestionsApi questionsApi=ofy().consistency(ReadPolicy.Consistency.STRONG).load().type(QuestionsApi.class).ancestor(rk).first().now();
          //
                   if(questionsApi !=null) {
                       System.out.println("data already available....");
                       System.out.println(new Gson().toJson(questionsApi));
                   }else{

                   }
                  // System.out.println(ofy().load().key(Key.create(QuestionsApi.class, RoomID)).now());
                   return questionsApi;
               }
           });

           if (questionsApi0 != null) {
               System.out.println("quests already available...");
               return questionsApi0;
           } else {
               System.out.println("quests not found........creating");
               int max = ObjectifyService.run(new Work<Integer>() {
                   @Override
                   public Integer run() {
                       return ofy().load().type(Question.class).filter("topic =", topic).count();
                   }
               });
               //ofy().load().type(Question.class).filter("topic =",topic).count();
               System.out.println("topic length :" + max);
               // String topic="astrology";
               QuestionBank qb = new QuestionBank();
               List<Question> questionList = new ArrayList<Question>();
               qb.topic = topic;
               String id = null;


               int[] randno = new Random().ints(0, max).distinct().limit(7).toArray();

               try {
                   for (int k = 0; k < 7; k++) {

                       id = topic + "-" + randno[k];
                       System.out.println(id);

                       String finalId = id;
                       synchronized (ObjectifyService.run(new VoidWork() {
                           public void vrun() {
                               //   System.out.println();

                               Question question = ofy().load().key(Key.create(Question.class, finalId)).now();

                               question.topic = null;
                               questionList.add(question);
                           }
                       })){

                       }

                   }
                   qb.questionlist = questionList;
                   QuestionsApi questionsApi = new QuestionsApi();
                   questionsApi.setTopic(topic);
                   questionsApi.setRoomId(RoomID);
                   questionsApi.setQuestionlist(questionList);
//                   ofy().transact(new VoidWork() {
//                       public void vrun() {
//                           //   System.out.println();

                           Key<QuestionsApi> key=factory().allocateId(QuestionsApi.class);
                           questionsApi.setId(key.getId());
                           ofy().consistency(ReadPolicy.Consistency.STRONG).save().entity(questionsApi).now();

//                       }
//                   });



                   return qb;
               } catch (Exception e) {
                   e.fillInStackTrace();
                   return e.toString();
               }
           }
       }catch (Exception e){
           e.getStackTrace();
           e.printStackTrace();
           return e.toString();
       }
    }
    public static void main(String... args){





    }
}
