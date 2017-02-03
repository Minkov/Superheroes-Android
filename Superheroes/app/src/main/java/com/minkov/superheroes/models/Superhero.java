package com.minkov.superheroes.models;

import java.io.Serializable;

public class Superhero implements Serializable {
    private String name;
    private String secretIdentity;

    public Superhero() {
    }

    public Superhero(String name, String secretIdentity) {
        this.setName(name);
        this.setSecretIdentity(secretIdentity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }
}
