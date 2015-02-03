package com.example.august.mixmaster;

/**
 * Created by Alex and gus on 1/30/2015.
 */
public class Recipes {
    private String name;
    private String description;
    private int icon;
    private int small;
    private String how;
/*
    String[] drinks = {
            "Screwdriver",
            "Gin and Tonic",
            "Fuzzy Naval",
            "Cactus Cooler",
            "Jager Bomb",
            "Irish Car Bomb",
    };
    Integer[] imadeID = {
            R.drawable.screwdriver,
            R.drawable.gin_tonic,
            R.drawable.fuzzy_navel,
            R.drawable.cactus_cooler,
            R.drawable.jager_bomb,
            R.drawable.irish_car_bomb,
    };
    Integer[] DescID ={
            R.string.screwdriver,
            R.string.gin_tonic,
            R.string.fuzzynavel,
            R.string.cactuscooler,
            R.string.jagerbomb,
            R.string.irishcarbomb
    };
*/
    public Recipes (String name, int icon,int small, String description, String how) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.how = how;
        this.small = small;
    }

    public String getName(){return name;}
    public String getDescription(){return description;}
    public int getIcon() {return icon;}
    public String getHow() {return how;}
    public int getSmall() {return small;}
}
