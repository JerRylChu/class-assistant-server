package com.zjr.assistant.entities;


import java.util.List;

public class Course {

  private long id;
  private long term;
  private String name;
  private String room;
  private String teacher;
  private String weekJson;
  private List<Integer> weekList;
  private long start;
  private long step;
  private long day;
  private long colorRandom;
  List<Photo> photos;

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }

  public List<Integer> getWeekList() {
    return weekList;
  }

  public void setWeekList(List<Integer> weekList) {
    this.weekList = weekList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getTerm() {
    return term;
  }

  public void setTerm(long term) {
    this.term = term;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }


  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }


  public String getWeekJson() {
    return weekJson;
  }

  public void setWeekJson(String weekJson) {
    this.weekJson = weekJson;
  }


  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }


  public long getStep() {
    return step;
  }

  public void setStep(long step) {
    this.step = step;
  }


  public long getDay() {
    return day;
  }

  public void setDay(long day) {
    this.day = day;
  }


  public long getColorRandom() {
    return colorRandom;
  }

  public void setColorRandom(long colorRandom) {
    this.colorRandom = colorRandom;
  }

}
