package com.example.echo.models;
import java.util.ArrayList;
import java.util.List;

public class Ranking {

    public List<Player> top5 = new ArrayList<Player>();
    public Player you;

    public Ranking() {
    }
    public Ranking(List<Player> top5, Player you) {
        super();
        this.top5 = top5;
        this.you = you;
    }

}



