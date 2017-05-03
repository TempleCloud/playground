package com.xepsa.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    String id;

    @Column(name = "title", nullable = true)
    String title;

    @Column(name = "given_name", nullable = false)
    String givenName;

    @Column(name = "family_name", nullable = false)
    String familyName;

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
