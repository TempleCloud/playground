package com.xepsa.memo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "memo")
public class Memo {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    private String extraInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Memo withId(String id){
        this.setId(id);
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Memo withUserId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Memo withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Memo withMessage(String message) {
        this.setMessage(message);
        return this;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Memo withExtraInfo(String extraInfo) {
        this.setExtraInfo(extraInfo);
        return this;
    }

}
