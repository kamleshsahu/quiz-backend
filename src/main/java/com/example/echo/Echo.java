package com.example.echo;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "", version = "v1",
scopes = {Constants.EMAIL_SCOPE },
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
        description = "API for hello world endpoints.")

public class Echo {

   // Declare this method as a method available externally through Endpoints
//    @ApiMethod(name = "sayHello", path = "sayHello",
//            httpMethod = HttpMethod.GET)
//
//    public HelloClass sayHello() {
//        return new HelloClass();
//    }
//
//    // Declare this method as a method available externally through Endpoints
//    @ApiMethod(name = "sayHelloByName", path = "sayHelloByName",
//            httpMethod = HttpMethod.POST)
//
//    public HelloClass sayHelloByName (@Named("name") String name ,
//                                      @Named("rollno") int rollno  ,
//                                      @Named("sem") int sem
//                                    ) {
//                                       return new HelloClass(name,rollno,sem);
//                                     }
//
//    @ApiMethod(name = "check", path = "check",
//            httpMethod = HttpMethod.GET)
//
//    public HelloClass check (@Named("no") String no) {
//        return new HelloClass().check(no);
//    }

    public HelloClass uploadQuestion(@Named("no") int no){
        return new HelloClass().uploadQuestions(no);
    }

    @ApiMethod(name = "dnldQuests", path = "dnldQuests",
            httpMethod = HttpMethod.GET)

    public Object dnldQuests (@Named("PID") String PID,
                              @Named("Topic") String Topic) {
       // return new HelloClass().dnldQuest(PID,Topic);
     //   return new savePlayerDetails().savePlayerDetailss();

       return new makeQue_forGamePlay().makeQue_forGamePlay(PID,Topic);
    }

    @ApiMethod(name = "RegPlayer", path = "RegPlayer",
            httpMethod = HttpMethod.GET)

    public HelloClass RegPlayer ( @Named("PID") String PID,
                                  @Named("Name") String name,
                                  @Named("pic_url") String pic_url,
                                  @Named("country") String country) {
         return new HelloClass().savePlayerDetails(PID,name,pic_url,country);

    }
    @ApiMethod(name = "getPlayer", path = "getPlayer",
            httpMethod = HttpMethod.GET)
    public HelloClass getP ( @Named("PID") String PID) {
        return new HelloClass().getPlayerDetails(PID);

    }


    @ApiMethod(name = "updateStats", path = "updateStats",
            httpMethod = HttpMethod.GET)

     public HelloClass updateStats (@Named("PID") String PID,
                                    @Named("Q_Correct") int Q_Correct,
                                    @Named("Score") int Score,
                                    @Named("Topic") String topic) {
        return new HelloClass().updateStats(PID,topic,Q_Correct,Score);
    }

    @ApiMethod(name = "getStats", path = "getStats",
            httpMethod = HttpMethod.GET)

    public HelloClass query (@Named("Topic") String topic,
                             @Named("PID") String PID) {
        return new HelloClass().getStats(topic,PID);
    }

    @ApiMethod(name = "ranking", path = "ranking",
            httpMethod = HttpMethod.GET)

    public HelloClass ranking (@Named("Topic") String topic,
                               @Named("PID") String PID) {
        return new HelloClass().ranking(topic,PID);
    }


//    @ApiMethod(name = "query1", path = "query1",
//            httpMethod = HttpMethod.GET)
//
//    public HelloClass query1 () {
//        return new HelloClass().query1();
//    }

}
