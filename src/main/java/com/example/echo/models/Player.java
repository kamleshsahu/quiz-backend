package com.example.echo.models;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    String email;
    String name;
    String pic_url;
    @Index
    String country;

    @Ignore
    int Rank;
    @Ignore
    String Accuracy;




    public Player(String email,String name,String pic_url,String country) {
    this.email=email;
    this.name=name;
    this.pic_url=pic_url;
    this.country=country;
    }

    public Player(String email,String name,String pic_url,String country,int Rank,String accuracy) {
        this.email=email;
        this.name=name;
        this.pic_url=pic_url;
        this.country=country;
        this.Rank=Rank;
        this.Accuracy=accuracy;
    }

    public Player() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public String getAccuracy() {
        return Accuracy;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAccuracy(String accuracy) {
        Accuracy = accuracy;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
