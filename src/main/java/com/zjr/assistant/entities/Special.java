package com.zjr.assistant.entities;


import java.util.List;

public class Special {

  private Integer id;
  private String name;
  private Integer parentId;
  private List<Special> children;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Integer getParent() {
    return parentId;
  }

  public void setParent(Integer parent) {
    this.parentId = parent;
  }

  public List<Special> getChildren() {
    return children;
  }

  public void setChildren(List<Special> children) {
    this.children = children;
  }
}
