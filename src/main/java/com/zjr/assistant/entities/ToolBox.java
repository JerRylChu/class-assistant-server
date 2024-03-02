package com.zjr.assistant.entities;


import org.springframework.web.multipart.MultipartFile;

public class ToolBox {

  private long id;
  private long userId;
  private long kindId;
  private String name;
  private String url;
  private String headiconAddress;
  private MultipartFile file;

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
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


  public long getKindId() {
    return kindId;
  }

  public void setKindId(long kindId) {
    this.kindId = kindId;
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


  public String getHeadiconAddress() {
    return headiconAddress;
  }

  public void setHeadiconAddress(String headiconAddress) {
    this.headiconAddress = headiconAddress;
  }

}
