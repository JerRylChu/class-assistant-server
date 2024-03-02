package com.zjr.assistant.entities;


import java.util.List;

public class Menu {

  private Integer id;
  private String name;
  private String url;
  private Integer parent;
  private String iconType;
  private Integer isDisable;
  private java.util.Date createTime;
  private Integer type;
  List<Menu> child;


  public List<Menu> getChild() {
    return child;
  }

  public void setChild(List<Menu> child) {
    this.child = child;
  }

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


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public Integer getParent() {
    return parent;
  }

  public void setParent(Integer parent) {
    this.parent = parent;
  }


  public String getIconType() {
    return iconType;
  }

  public void setIconType(String iconType) {
    this.iconType = iconType;
  }


  public Integer getIsDisable() {
    return isDisable;
  }

  public void setIsDisable(Integer isDisable) {
    this.isDisable = isDisable;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

}
