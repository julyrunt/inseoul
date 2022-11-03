package com.inseoul.vo;

import java.sql.Date;

public class FollowBean {
  private int follower, following, myfollower, myfollowing;
  private String photo, nick, introduction;
  private Date fdate;
  public int getFollower() {
    return follower;
  }
  public void setFollower(int follower) {
    this.follower = follower;
  }
  public int getFollowing() {
    return following;
  }
  public void setFollowing(int following) {
    this.following = following;
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
  public String getIntroduction() {
    return introduction;
  }
  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }
  public Date getFdate() {
    return fdate;
  }
  public void setFdate(Date fdate) {
    this.fdate = fdate;
  }
  
}
