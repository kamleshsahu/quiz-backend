package com.example.echo;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class test {
    public static void main(String... args){
        System.out.println();
//ThreadLocalRandom.current().ints(5, 10).distinct().limit(5).forEach(System.out::println);

        int[] array = new Random().ints(0, 185).distinct().limit(5).toArray();
        System.out.println(Arrays.toString(array));

    }
}
