package com.example.echo;




import com.example.echo.models.Player;
import com.example.echo.models.Ranking;
import com.example.echo.models.Stats;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.*;
import endpoints.repackaged.com.google.gson.Gson;


import java.util.Collections;
import java.util.List;

import static com.example.echo.OfyService.ofy;
import static java.lang.Math.round;


public class HelloClass {
    public Object message = "Hello World";

    public HelloClass () {
    }

    public HelloClass (String name,int rollno,int sem) {
        this.message = "Hello " + name + "!";
    }

    public HelloClass check(String no){
        this.message ="git added ."+no+" yo yo my son!!!";

//        try {
//
//            System.out.println(new Gson().toJson(new HelloClass().checker()));
//            ESessiondetails s = (ESessiondetails) new HelloClass().checker();
//           // this.message = new pnrDemo().pnrDemo(no, s.sessionid,s.captchaAns);
//            this.message= new pnr_apicall().pnr_apicall(s.captchaAns,s.sessionid,no);
//        }catch (Exception e){
//            this.message ="error:"+e.toString();
//        }

        return this;
    }


     public HelloClass uploadQuestions(){
         this.message= new QuestionDownloader().QuestionDownloader();
        return this;
     }


     public HelloClass dnldQuest(String PID,String Topic){

          this.message =new makeQue_forGamePlay().makeQue_forGamePlay(PID,Topic);

         return this;
     }

        public HelloClass savePlayerDetails(String PID,String name,String pic_url,String country){
                String[] message = {""};
            ObjectifyService.run(new VoidWork() {
                public void vrun() {
                    //   System.out.println();

                    Player player =ofy().load().key(Key.create(Player.class, PID)).now();

                    if(player ==null) {
                       player= new Player(PID, name, pic_url, country);
                        ofy().save().entity(player).now();
                        message[0] ="success";
                    }else{
                        System.out.println("here is the player name :"+ player.getName());
                        message[0]="player already exists";
                    }
                }
            });

            this.message =message[0];
         return this;
        }


    public HelloClass getPlayerDetails(String PID){
        final Player[] player = new Player[1];

        ObjectifyService.run(new VoidWork() {
            public void vrun() {
                //   System.out.println();
//                    Player player =new Player(PID,name,pic_url,country);
//                    ofy().save().entity(player).now();

                player[0] = ofy().load().key(Key.create(Player.class, PID)).now();

                System.out.println("here is the data :"+ player[0]);
                if(player[0]!=null)
                System.out.println("here is the data :"+ player[0].getName());

            }
        });
        this.message = player[0];
        return this;
    }

    public HelloClass updateStats(String PID,String topic,int Q_Correct,int Score){
        final Object[] obj=new Object[1];
        final Stats[] stats1=new Stats[1];
        final String[] message = {""};
        ObjectifyService.run(new VoidWork() {
            public void vrun() {

               try {
                   System.out.println(topic);
                   System.out.println(PID);
                   String id = PID + "-" + topic;
                   //    Key([Key("ancestorKindName", idOrName),] "kindName", idOrName)
                   //   Key<Stats> statskey = Key.create(stats.getId());

                   System.out.println(id);
                   stats1[0] = ofy().load().key(Key.create(Stats.class, id)).now();

                   if (stats1[0] == null) {
                       Stats stats = new Stats(topic, Q_Correct, 7, Score, PID);
                       ofy().save().entity(stats).now();
                       message[0]="succesfully created and saved stats entity";
                   } else if (stats1[0] != null) {
                       stats1[0].setQ_Solved(stats1[0].getQ_Solved() + 7);
                       stats1[0].setCorrect(stats1[0].getCorrect() + Q_Correct);
                       stats1[0].setTotalScore(stats1[0].getTotalScore() + Score);
                       ofy().save().entity(stats1[0]).now();
                       message[0]="succesfully updated stats entity";
                   }
               }catch (Exception e){
                   e.fillInStackTrace();
                   message[0]="error,saveStats fn :"+e.toString();
               }
            }
        });
        this.message=message[0];
        return this;
    }

     public HelloClass getStats(String topic,String PID){
         final Stats[] stats = new Stats[1];
         ObjectifyService.run(new VoidWork() {
             public void vrun() {

//                 Query<Stats> statsList =ofy().load().type(Stats.class).filter("topic =",topic).order("-totalScore").limit(5);
//                 System.out.println(statsList);
                 stats[0]=ofy().load().key(Key.create(Stats.class,PID+"-"+topic)).now();
                 if(stats[0] == null){
                     Stats stats1 =new Stats(topic,0,0,0,PID);
                     stats1.setAccuracy("-");
                     stats1.setQ_Total(200);
                     stats[0]=stats1;
                 }else {
                     float accuracy = (float) stats[0].getCorrect() / (float) stats[0].getQ_Solved() * 100;
                     stats[0].setQ_Total(200);
                     stats[0].setAccuracy(String.valueOf(Math.round(accuracy * 100.0) / 100.0));
                 }

             }
         });

         this.message = stats[0];
         return this;
     }

     public HelloClass ranking(String topic,String PID){


         final Ranking ranking = ObjectifyService.run(new Work<Ranking>() {
              @Override
              public Ranking run() {
              //    return ofy().load().key(Key.create(Stats.class,"kamlesh@gmail.com-astrology")).now();
                  boolean userfound=false;
                 List<Stats> statsList= ofy().load().type(Stats.class).filter("topic =",topic).order("-totalScore").limit(5).list();
                 Ranking ranking1 =new Ranking();
                  for (int k=0;k<statsList.size();k++) {
                      System.out.println(statsList.get(k).getPID());
                      if(statsList.get(k).getPID().equals(PID)){
                          userfound=true;
                      }

                      Player p = ofy().load().key(Key.create(Player.class, statsList.get(k).getPID())).now();
                      if (p != null) {
                          float accuracy = (float) statsList.get(k).getCorrect() / (float) statsList.get(k).getQ_Solved() * 100;

                          p.setAccuracy(String.valueOf(Math.round(accuracy * 100.0) / 100.0));
                          p.setRank(k + 1);
                          ranking1.top5.add(p);
                      }
                  }

                  if(!userfound) {
                      Player p2 = ofy().load().key(Key.create(Player.class, PID)).now();
                      Stats stats = ofy().load().key(Key.create(Stats.class, PID + "-" + topic)).now();
                      if (p2 != null && stats != null) {
                          List<Stats> statsList2 = ofy().load().type(Stats.class).filter("topic =", topic).order("-totalScore").list();
                          int PlayerRank = 0;
                          for (int m = 0; m < statsList2.size(); m++) {
                              if (statsList2.get(m).getPID().equals(PID)) {
                                  float accuracy = (float) statsList2.get(m).getCorrect() / (float) statsList2.get(m).getQ_Solved() * 100;
                                  p2.setAccuracy(String.valueOf(Math.round(accuracy * 100.0) / 100.0));
                                  p2.setRank(m + 1);
                                  ranking1.top5.add(p2);
                                  break;
                              }
                          }
                      }

                  }


               return ranking1;
              }
          });

          this.message=ranking;
         return this;
     }

//     public HelloClass query1() {
//         Query q = new Query("Player");
//         q.addProjection(new PropertyProjection("PID", String.class));
//         System.out.println("here is query result :" + q);
//         Object obj = ObjectifyService.run(new Work<Object>() {
//                                               @Override
//                                               public Object run() {
//                                                   return ofy().load().project("SELECT PID FROM Player WHERE NAME EQUAL KAMLESH");
//                                               }
//                                           }
//         );
//
//         return this;
//     }
}
