package com.xepsa.memo.model;


public class Memo {
  private String id;
  private String userId;
  private String message;

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

}
