package com.zjr.assistant.entities;


public class Report {

  private Integer id;
  private Integer userId;
  private Integer articleId;
  private String description;
  private Integer solve;
  private Integer adminId;


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


  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Integer getSolve() {
    return solve;
  }

  public void setSolve(Integer solve) {
    this.solve = solve;
  }


  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }

}
