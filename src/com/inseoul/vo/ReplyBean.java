package com.inseoul.vo;

import java.sql.Date;

public class ReplyBean {
  private int rid, aid, bid, uid, modified;
  private String photo, nick, contents;
  private Date writedate;
  public int getRid() {
    return rid;
  }
  public void setRid(int rid) {
    this.rid = rid;
  }
  public int getAid() {
    return aid;
  }
  public void setAid(int aid) {
    this.aid = aid;
  }
  public int getBid() {
    return bid;
  }
  public void setBid(int bid) {
    this.bid = bid;
  }
  public int getUid() {
    return uid;
  }
  public void setUid(int uid) {
    this.uid = uid;
  }
  public int getModified() {
    return modified;
  }
  public void setModified(int modified) {
    this.modified = modified;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public Date getWritedate() {
    return writedate;
  }
  public void setWritedate(Date writedate) {
    this.writedate = writedate;
  }
  
}
