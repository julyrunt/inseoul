package com.inseoul.vo;

import java.sql.Date;

public class GroupListBean {
  private int wid, uid, ref, seq, readcount, dues, mojib_person, usercount;
  private String photos, title, contents, nick;
  private Date writedate, mojib_limit, trip_start, trip_end, apply_date;
  public int getWid() {
    return wid;
  }
  public void setWid(int wid) {
    this.wid = wid;
  }
  public int getUid() {
    return uid;
  }
  public void setUid(int uid) {
    this.uid = uid;
  }
  public int getRef() {
    return ref;
  }
  public void setRef(int ref) {
    this.ref = ref;
  }
  public int getSeq() {
    return seq;
  }
  public void setSeq(int seq) {
    this.seq = seq;
  }
  public int getReadcount() {
    return readcount;
  }
  public void setReadcount(int readcount) {
    this.readcount = readcount;
  }
  public int getDues() {
    return dues;
  }
  public void setDues(int dues) {
    this.dues = dues;
  }
  public int getMojib_person() {
    return mojib_person;
  }
  public void setMojib_person(int mojib_person) {
    this.mojib_person = mojib_person;
  }
  public int getUsercount() {
    return usercount;
  }
  public void setUsercount(int usercount) {
    this.usercount = usercount;
  }
  public String getPhotos() {
    return photos;
  }
  public void setPhotos(String photos) {
    this.photos = photos;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public Date getWritedate() {
    return writedate;
  }
  public void setWritedate(Date writedate) {
    this.writedate = writedate;
  }
  public Date getMojib_limit() {
    return mojib_limit;
  }
  public void setMojib_limit(Date mojib_limit) {
    this.mojib_limit = mojib_limit;
  }
  public Date getTrip_start() {
    return trip_start;
  }
  public void setTrip_start(Date trip_start) {
    this.trip_start = trip_start;
  }
  public Date getTrip_end() {
    return trip_end;
  }
  public void setTrip_end(Date trip_end) {
    this.trip_end = trip_end;
  }
  public Date getApply_date() {
    return apply_date;
  }
  public void setApply_date(Date apply_date) {
    this.apply_date = apply_date;
  }

}
