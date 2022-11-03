package com.inseoul.vo;

import java.sql.Date;

public class TravelsListBean {
  private int tid, lid, uid, readCount, replyCount, recommendCount, favorite;
  private String nick, name, title, contents, img01, img02, img03;
  private Date date;
  public int getTid() {
    return tid;
  }
  public void setTid(int tid) {
    this.tid = tid;
  }
  public int getLid() {
    return lid;
  }
  public void setLid(int lid) {
    this.lid = lid;
  }
  public int getUid() {
    return uid;
  }
  public void setUid(int uid) {
    this.uid = uid;
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
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
  public String getImg01() {
    return img01;
  }
  public void setImg01(String img01) {
    this.img01 = img01;
  }
  public String getImg02() {
    return img02;
  }
  public void setImg02(String img02) {
    this.img02 = img02;
  }
  public String getImg03() {
    return img03;
  }
  public void setImg03(String img03) {
    this.img03 = img03;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  
}
