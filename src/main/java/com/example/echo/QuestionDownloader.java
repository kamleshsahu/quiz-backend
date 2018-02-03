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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.echo.OfyService.ofy;

public class QuestionDownloader
{

   public Object QuestionDownloader(int no) {
//        super();
//    }
//
 //   public static void main(String... args){
        ClassLoader classLoader = QuestionDownloader.class.getClassLoader();
        URL resource = classLoader.getResource("org/apache/http/message/BasicLineFormatter.class");
        System.out.println("here is the resource :"+resource);
       String[] topics={
               "ancient indian history",
               "astrology",
               "biology",
               "chemestry",
               "computers",
               "environment",
               "important days",
               "india freedom history",
               "indian culture",
               "indian economy",
               "indian geography",
               "indian politics & constitution",
               "medieval indian history",
               "physics",
               "sports",
               "world geography",

       };


           String encodedKey = null;
           try {

               encodedKey = URLEncoder.encode(topics[no], "UTF-8").replaceAll("\\+", "%20");
               System.out.println("here is encoded key :" + encodedKey);
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
               return e.toString();
           }


           String url = "https://storage.googleapis.com/question_astro_json/" + encodedKey + ".json";

           CloseableHttpClient httpclient = HttpClients.createDefault();
           HttpGet httpGet = new HttpGet(url);
           CloseableHttpResponse response1 = null;
           try {
               response1 = httpclient.execute(httpGet);
           } catch (IOException e) {
               e.printStackTrace();
               return e.toString();
           }

           String result = "";
           try {
               System.out.println(response1.getStatusLine());

               BufferedReader rd = new BufferedReader
                       (new InputStreamReader(
                               response1.getEntity().getContent()));


               String line = "";
               StringBuilder sb = new StringBuilder();
               while ((line = rd.readLine()) != null) {
                   //result += line;
                   sb.append(line);
               }

               result = sb.toString();


               Gson gson = new GsonBuilder()
                       .registerTypeAdapter(QuestionBank.class, new QuestionBankInstanceCreator())
                       .registerTypeAdapter(Question.class, new QuestionInstanceCreator())
                       .create();


               QuestionBank qb = gson.fromJson(result, QuestionBank.class);

               for (int k = 0; k < qb.questionlist.size(); k++) {

                   qb.questionlist.get(k).QID = qb.topic + "-" + k;
                   qb.questionlist.get(k).topic = qb.topic;
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

           } catch (Exception e) {
               e.fillInStackTrace();
               return e.toString();

           } finally {
               try {
                   response1.close();
               } catch (IOException e) {
                   e.printStackTrace();
                   return e.toString();
               }
           }



    }

}
