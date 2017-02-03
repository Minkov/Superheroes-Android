package com.minkov.superheroes;

import android.app.Application;

import com.minkov.superheroes.models.Superhero;

import java.util.ArrayList;

public class SuperheroesApplication extends Application {
    private static final String API_BASE_URL = "http://192.168.192.229:3001/";
    private ArrayList<Superhero> superheroes;

    public SuperheroesApplication() {
        super();
        this.superheroes = new ArrayList<>();
        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
        this.superheroes.add(new Superhero("The Hulk", "Bruce Banner"));
        this.superheroes.add(new Superhero("Thor", "Thor"));
        this.superheroes.add(new Superhero("Dr. Strange", "Dr. Stephen Strange"));
        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
        this.superheroes.add(new Superhero("The Hulk", "Bruce Banner"));
        this.superheroes.add(new Superhero("Thor", "Thor"));
        this.superheroes.add(new Superhero("Dr. Strange", "Dr. Stephen Strange"));
        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
        this.superheroes.add(new Superhero("The Hulk", "Bruce Banner"));
        this.superheroes.add(new Superhero("Thor", "Thor"));
        this.superheroes.add(new Superhero("Dr. Strange", "Dr. Stephen Strange"));
        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
        this.superheroes.add(new Superhero("The Hulk", "Bruce Banner"));
        this.superheroes.add(new Superhero("Thor", "Thor"));
        this.superheroes.add(new Superhero("Dr. Strange", "Dr. Stephen Strange"));
        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
        this.superheroes.add(new Superhero("The Hulk", "Bruce Banner"));
        this.superheroes.add(new Superhero("Thor", "Thor"));
        this.superheroes.add(new Superhero("Dr. Strange", "Dr. Stephen Strange"));
    }

    public String getApiBaseUrl() {
        return API_BASE_URL;
    }

    public ArrayList<Superhero> getSuperheroes() {
        return this.superheroes;
    }
}
