package com.minkov.superheroes.models;

import java.io.Serializable;

public abstract class ModelBase implements Serializable{
    private String id;

    public ModelBase() {

    }

    public ModelBase(String id) {
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
