package com.zjr.assistant.entities;


import java.util.Date;

public class Trail {

  private Integer userId;
  private Integer articleId;
  private java.util.Date createTime;

  public Trail(Integer userId, Integer articleId, Date createTime) {
    this.userId = userId;
    this.articleId = articleId;
    this.createTime = createTime;
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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
