package com.zjr.assistant.entities;


import java.util.List;

public class Counter {

  private long id;
  private long userId;
  private String counterName;
  private String dateString;
  private String people;
  private String color;
  private long colorID;
  private long flagNum;
  private Boolean flag;
  private long rrule;
  private String addTime;
  private long focusNum;
  private Boolean foucs;
  private String listFocusJson;
  private List<Integer> listFocus;

  public Boolean getFlag() {
    return flag;
  }

  public void setFlag(Boolean flag) {
    this.flag = flag;
  }

  public Boolean getFoucs() {
    return foucs;
  }

  public void setFoucs(Boolean foucs) {
    this.foucs = foucs;
  }

  public List<Integer> getListFocus() {
    return listFocus;
  }

  public void setListFocus(List<Integer> list_focus) {
    this.listFocus = list_focus;
  }

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


  public String getCounterName() {
    return counterName;
  }

  public void setCounterName(String counterName) {
    this.counterName = counterName;
  }


  public String getDataString() {
    return dateString;
  }

  public void setDataString(String dataString) {
    this.dateString = dataString;
  }


  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }


  public long getColorID() {
    return colorID;
  }

  public void setColorID(long colorID) {
    this.colorID = colorID;
  }


  public long getFlagNum() {
    return flagNum;
  }

  public void setFlagNum(long flagNum) {
    this.flagNum = flagNum;
  }


  public long getRrule() {
    return rrule;
  }

  public void setRrule(long rrule) {
    this.rrule = rrule;
  }


  public String getAddTime() {
    return addTime;
  }

  public void setAddTime(String addTime) {
    this.addTime = addTime;
  }


  public long getFocusNum() {
    return focusNum;
  }

  public void setFocusNum(long focusNum) {
    this.focusNum = focusNum;
  }


  public String getListFocusJson() {
    return listFocusJson;
  }

  public void setListFocusJson(String listFocusJson) {
    this.listFocusJson = listFocusJson;
  }

}
