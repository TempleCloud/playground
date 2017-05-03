package com.xepsa.memo.model;

public class User {

    String id;

    String title;

    String givenName;

    String familyName;


    public String getFullname() {
        return getTitle() + " " + getGivenName() + " " + getFamilyName();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public User withId(String id) {
        this.setId(id);
        return this;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public User withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public User withGivenName(String givenName) {
        this.setGivenName(givenName);
        return this;
    }

    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public User withFamilyName(String familyName) {
        this.setFamilyName(familyName);
        return this;
    }

}
