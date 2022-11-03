package com.inseoul.vo;

public class InfoPasswordBean {
  private int uid;
  private String pwOld, pwNew, pwChk;
  public int getUid() {
    return uid;
  }
  public void setUid(int uid) {
    this.uid = uid;
  }
  public String getPwOld() {
    return pwOld;
  }
  public void setPwOld(String pwOld) {
    this.pwOld = pwOld;
  }
  public String getPwNew() {
    return pwNew;
  }
  public void setPwNew(String pwNew) {
    this.pwNew = pwNew;
  }
  public String getPwChk() {
    return pwChk;
  }
  public void setPwChk(String pwChk) {
    this.pwChk = pwChk;
  }
  
}
