package com.zjr.assistant.entities;


import java.io.Serializable;

public class User implements Serializable {

  private Integer id;
  private String password;
  private java.util.Date createTime;
  private String nickName;
  private String signature;
  private String phoneNumber;
  private String school;
  private String headIconUrl;
  private String confirmCode;
  private Integer sex;
  private Integer major;
  private String majorName;

  public Integer getMajor() {
    return major;
  }

  public void setMajor(Integer major) {
    this.major = major;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getConfirmCode() {
    return confirmCode;
  }

  public void setConfirmCode(String confirmCode) {
    this.confirmCode = confirmCode;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }


  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }


  public String getHeadIconUrl() {
    return headIconUrl;
  }

  public void setHeadIconUrl(String headIconUrl) {
    this.headIconUrl = headIconUrl;
  }

}
