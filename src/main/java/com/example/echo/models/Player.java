package com.example.echo.models;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

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


    List<Stats> statsList =new ArrayList<Stats>();


    public Player(String email,String name,String pic_url,String country) {
    this.email=email;
    this.name=name;
    this.pic_url=pic_url;
    this.country=country;
    }

    public Player(String email,List<Stats> statsList) {
     this.email =email;
     this.statsList=statsList;
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

    public List<Stats> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<Stats> statsList) {
        this.statsList = statsList;
    }
}
