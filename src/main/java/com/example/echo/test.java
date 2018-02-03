package com.example.echo;

import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class test {
    public static void main(String... args){
        System.out.println();
//ThreadLocalRandom.current().ints(5, 10).distinct().limit(5).forEach(System.out::println);

//        int[] array = new Random().ints(0, 185).distinct().limit(5).toArray();
//        System.out.println(Arrays.toString(array));
        String encodedKey = null;
        try {

           encodedKey= URLEncoder.encode("important days", "UTF-8").replaceAll("\\+", "%20");
            System.out.println("here is encoded key :"+encodedKey);
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
           // return e.toString();
            System.out.println(e.getCause());
        }
    }
}
