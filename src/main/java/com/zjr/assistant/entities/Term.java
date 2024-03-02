package com.zjr.assistant.entities;


public class Term {

  private long id;
  private String termName;
  private String createDate;
  private long userId;
  private long selected;
  private boolean selection;
  private String courseJson;

  public boolean isSelection() {
    return selection;
  }

  public void setSelection(boolean selection) {
    this.selection = selection;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTermName() {
    return termName;
  }

  public void setTermName(String termName) {
    this.termName = termName;
  }


  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getSelected() {
    return selected;
  }

  public void setSelected(long selected) {
    this.selected = selected;
  }


  public String getCourseJson() {
    return courseJson;
  }

  public void setCourseJson(String courseJson) {
    this.courseJson = courseJson;
  }

}
