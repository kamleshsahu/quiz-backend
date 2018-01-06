package com.example.echo;




import com.example.echo.models.Question;
import com.example.echo.models.QuestionBank;
import com.example.echo.models.QuestionBankInstanceCreator;
import com.example.echo.models.QuestionInstanceCreator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static com.example.echo.OfyService.ofy;

public class QuestionDownloader
{

   public Object QuestionDownloader() {
//        super();
//    }
//
 //   public static void main(String... args){
        ClassLoader classLoader = QuestionDownloader.class.getClassLoader();
        URL resource = classLoader.getResource("org/apache/http/message/BasicLineFormatter.class");
        System.out.println("here is the resource :"+resource);

        String url="https://storage.googleapis.com/question_astro_json/astro1.json";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }

        String result="";
        try {
            System.out.println(response1.getStatusLine());

            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(
                            response1.getEntity().getContent()));


            String line = "";
            StringBuilder sb=new StringBuilder();
            while ((line = rd.readLine()) != null) {
                //result += line;
                sb.append(line);
            }

            result= sb.toString();


            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(QuestionBank.class, new QuestionBankInstanceCreator())
                    .registerTypeAdapter(Question.class, new QuestionInstanceCreator())
                    .create();


            QuestionBank qb =gson.fromJson(result,QuestionBank.class);

            for(int k=0;k<qb.questionlist.size();k++){

                qb.questionlist.get(k).QID=qb.topic+"-"+k;
            }


            ObjectifyService.run(new VoidWork() {
                public void vrun() {
//                myObj = new MyObject();
//                myObj.loadEverything();
                    ofy().save().entities(qb.questionlist).now();
                    ofy().save().entity(qb).now();
                }
            });

            System.out.println("Here is the result :");
            System.out.println(result);

            return qb;
        }catch (Exception e) {
             e.fillInStackTrace();
             return e.toString();

        } finally
         {
            try {
                response1.close();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
        }



    }

}
