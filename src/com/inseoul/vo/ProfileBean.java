package com.inseoul.vo;

import java.sql.Date;

public class ProfileBean {
  private int uid, myfollower, myfollowing, followerCount, followingCount;
  private String photo, nick, joindate, introduction, background;
  private Date followerDate, followingDate;
  public int getUid() {
    return uid;
  }
  public void setUid(int uid) {
    this.uid = uid;
  }
  public int getMyfollower() {
    return myfollower;
  }
  public void setMyfollower(int myfollower) {
    this.myfollower = myfollower;
  }
  public int getMyfollowing() {
    return myfollowing;
  }
  public void setMyfollowing(int myfollowing) {
    this.myfollowing = myfollowing;
  }
  public int getFollowerCount() {
    return followerCount;
  }
  public void setFollowerCount(int followerCount) {
    this.followerCount = followerCount;
  }
  public int getFollowingCount() {
    return followingCount;
  }
  public void setFollowingCount(int followingCount) {
    this.followingCount = followingCount;
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
  public String getJoindate() {
    return joindate;
  }
  public void setJoindate(String joindate) {
    this.joindate = joindate;
  }
  public String getIntroduction() {
    return introduction;
  }
  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }
  public String getBackground() {
    return background;
  }
  public void setBackground(String background) {
    this.background = background;
  }
  public Date getFollowerDate() {
    return followerDate;
  }
  public void setFollowerDate(Date followerDate) {
    this.followerDate = followerDate;
  }
  public Date getFollowingDate() {
    return followingDate;
  }
  public void setFollowingDate(Date followingDate) {
    this.followingDate = followingDate;
  }
  
}
