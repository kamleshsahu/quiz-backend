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
    @ApiMethod(name = "sayHello", path = "sayHello",
            httpMethod = HttpMethod.GET)

    public HelloClass sayHello() {
        return new HelloClass();
    }

    // Declare this method as a method available externally through Endpoints
    @ApiMethod(name = "sayHelloByName", path = "sayHelloByName",
            httpMethod = HttpMethod.POST)

    public HelloClass sayHelloByName (@Named("name") String name ,
                                      @Named("rollno") int rollno  ,
                                      @Named("sem") int sem
                                    ) {
                                       return new HelloClass(name,rollno,sem);
                                     }

    @ApiMethod(name = "check", path = "check",
            httpMethod = HttpMethod.GET)

    public HelloClass check (@Named("no") String no) {
        return new HelloClass().check(no);
    }

    public HelloClass uploadQuestion(){
        return new HelloClass().uploadQuestions();
    }

    @ApiMethod(name = "dnldQuests", path = "dnldQuests",
            httpMethod = HttpMethod.GET)

    public Object dnldQuests (@Named("PID") String PID,
                              @Named("Topic") String Topic) {
       // return new HelloClass().dnldQuest(PID,Topic);

        return new makeQue_forGamePlay().makeQue_forGamePlay(PID,Topic);
    }


}
