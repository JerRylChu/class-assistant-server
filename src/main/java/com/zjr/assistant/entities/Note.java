package com.zjr.assistant.entities;


public class Note {

  private long id;
  private long userId;
  private String addDate;
  private String subject;
  private String content;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getAddDate() {
    return addDate;
  }

  public void setAddDate(String addDate) {
    this.addDate = addDate;
  }


  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
