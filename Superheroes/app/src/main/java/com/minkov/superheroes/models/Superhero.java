package com.minkov.superheroes.models;

import java.io.Serializable;

public class Superhero extends ModelBase {
    private String name;
    private String secretIdentity;
    private String imgUrl;

    public Superhero() {
    }

    public Superhero(String id, String name, String secretIdentity, String imgUrl) {
        super(id);
        this.setName(name);
        this.setSecretIdentity(secretIdentity);
        this.setImgUrl(imgUrl);
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


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
