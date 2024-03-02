package com.zjr.assistant.entities;


public class Article {

  private Integer id;
  private Integer userId;
  private String nickName;
  private String title;
  private String description;
  private String articleContent;
  private java.util.Date createTime;
  private Integer stars;
  private Integer reads;
  private Integer comments;
  private Integer del;
  private Integer major;
  private String majorName;

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getArticleContent() {
    return articleContent;
  }

  public void setArticleContent(String articleContent) {
    this.articleContent = articleContent;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public Integer getStars() {
    return stars;
  }

  public void setStars(Integer stars) {
    this.stars = stars;
  }


  public Integer getReads() {
    return reads;
  }

  public void setReads(Integer reads) {
    this.reads = reads;
  }


  public Integer getComments() {
    return comments;
  }

  public void setComments(Integer comments) {
    this.comments = comments;
  }


  public Integer getDel() {
    return del;
  }

  public void setDel(Integer del) {
    this.del = del;
  }


  public Integer getMajor() {
    return major;
  }

  public void setMajor(Integer major) {
    this.major = major;
  }

}
