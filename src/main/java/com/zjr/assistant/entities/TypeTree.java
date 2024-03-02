package com.zjr.assistant.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TypeTree implements Serializable {

  private long id;
  private long userId;
  private String name;
  private long parent;
  private Date createTime;
  private List<TypeTree> typeTrees;

  public List<TypeTree> getTypeTrees() {
    return typeTrees;
  }

  public void setTypeTrees(List<TypeTree> typeTrees) {
    this.typeTrees = typeTrees;
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


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getParent() {
    return parent;
  }

  public void setParent(long parent) {
    this.parent = parent;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
