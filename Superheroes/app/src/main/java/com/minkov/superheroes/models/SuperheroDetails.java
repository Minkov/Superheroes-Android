package com.minkov.superheroes.models;

public class SuperheroDetails extends Superhero {

    private String story;
    private String[] powers;
    private String[] factions;

    public SuperheroDetails() {
    }

    public SuperheroDetails(String id, String name, String secretIdentity, String imgUrl, String story, String[] powers, String[] factions) {
        super(id, name, secretIdentity, imgUrl);
        this.setStory(story);
        this.setFactions(factions);
        this.setPowers(powers);
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String[] getPowers() {
        return powers;
    }

    public void setPowers(String[] powers) {
        this.powers = powers;
    }

    public String[] getFactions() {
        return factions;
    }

    public void setFactions(String[] factions) {
        this.factions = factions;
    }
}
