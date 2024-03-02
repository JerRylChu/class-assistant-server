package com.zjr.assistant.entities;


import java.util.Date;

public class MobileInfo {

  private Integer userId;
  private String jsonString;
  private Date uploadTime;


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getJsonString() {
    return jsonString;
  }

  public void setJsonString(String jsonString) {
    this.jsonString = jsonString;
  }

  public Date getUploadTime() {
    return uploadTime;
  }

  public void setUploadTime(Date uploadTime) {
    this.uploadTime = uploadTime;
  }
}
