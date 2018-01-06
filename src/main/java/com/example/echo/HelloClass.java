package com.example.echo;




import com.example.echo.models.Player;
import com.example.echo.models.Stats;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

import java.util.ArrayList;
import java.util.List;

import static com.example.echo.OfyService.ofy;


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

    public HelloClass saveStats(String PID,String topic,int Q_Correct,int Score){
        final Player[] player = new Player[1];
        final String[] message = {""};
        ObjectifyService.run(new VoidWork() {
            public void vrun() {
                //   System.out.println();
//                    Player player =new Player(PID,name,pic_url,country);
//                    ofy().save().entity(player).now();

                player[0] = ofy().load().key(Key.create(Player.class, PID)).now();

                System.out.println("here is the data :"+ player[0]);
                if(player[0]!=null){

                    boolean gotTopic=false;
                     if(player[0].getStatsList()!=null){
                         System.out.println("stats list is not null....");
                         if(player[0].getStatsList().size()>0){
                         for (Stats stats:player[0].getStatsList()) {
                            if(stats.getTopic().equals(topic)){
                                 stats.setQ_Solved(stats.getQ_Solved()+7);
                                 stats.setCorrect(stats.getCorrect()+Q_Correct);
                                 stats.setTotalScore(stats.getTotalScore()+Score);
                                 gotTopic=true;
                                ofy().save().entity(player[0]).now();
                                System.out.println("got topic : true");
                                System.out.println("data saved successfully");
                                message[0] ="got topic : true,data saved successfully";
                             }
                           }

                         }
                        if(!gotTopic){
                            Stats newstats =new Stats(topic,Q_Correct,7,Score);
                            player[0].getStatsList().add(newstats);
                            ofy().save().entity(player[0]).now();
                            System.out.println("got topic : false ");
                            System.out.println("data saved successfully");
                            message[0] ="got topic : false,data saved successfully";
                        }

                     }else{
//                         Stats newstats =new Stats(topic,Q_Correct,7,Score);
//                         statsList.add(newstats);
//                         player[0].setStatsList(statsList);
                         System.out.println("data not saved...");
                         System.out.println("else part,player.getlist may be null");
                         message[0] ="data not saved...,else part,player.getlist may be null";
                     }
                }


            }
        });
        this.message=message[0];
        return this;
    }





}
