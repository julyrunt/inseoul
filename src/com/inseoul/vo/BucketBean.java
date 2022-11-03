package com.inseoul.vo;

import java.sql.Date;

public class BucketBean {
  private int bid, uid, lid, progress, readCount, replyCount, recommendCount, favorite;
  private String name, img, nick, item;
  private Date writedate;
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
  public int getLid() {
    return lid;
  }
  public void setLid(int lid) {
    this.lid = lid;
  }
  public int getProgress() {
    return progress;
  }
  public void setProgress(int progress) {
    this.progress = progress;
  }
  public int getReadCount() {
    return readCount;
  }
  public void setReadCount(int readCount) {
    this.readCount = readCount;
  }
  public int getReplyCount() {
    return replyCount;
  }
  public void setReplyCount(int replyCount) {
    this.replyCount = replyCount;
  }
  public int getRecommendCount() {
    return recommendCount;
  }
  public void setRecommendCount(int recommendCount) {
    this.recommendCount = recommendCount;
  }
  public int getFavorite() {
    return favorite;
  }
  public void setFavorite(int favorite) {
    this.favorite = favorite;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getImg() {
    return img;
  }
  public void setImg(String img) {
    this.img = img;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public String getItem() {
    return item;
  }
  public void setItem(String item) {
    this.item = item;
  }
  public Date getWritedate() {
    return writedate;
  }
  public void setWritedate(Date writedate) {
    this.writedate = writedate;
  }
  
}
