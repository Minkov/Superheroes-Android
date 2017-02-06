package com.minkov.superheroes;

import android.app.Application;

import com.minkov.superheroes.models.Superhero;

import java.util.ArrayList;

public class SuperheroesApplication extends Application {
    private static final String API_BASE_URL = "http://192.168.43.140:3001/";

    public SuperheroesApplication() {
        super();
    }

    public String getApiBaseUrl() {
        return API_BASE_URL;
    }
}
