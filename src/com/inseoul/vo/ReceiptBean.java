package com.inseoul.vo;

import java.sql.Date;

public class ReceiptBean {
  private int reservation_num, roomid, reservation_uid;
  private Date reservation_date, reservation_checkin, reservation_checkout;
  private int reservation_person, reservation_price;
  private String username, hotelname, roomname, phone;
  public int getReservation_num() {
    return reservation_num;
  }
  public void setReservation_num(int reservation_num) {
    this.reservation_num = reservation_num;
  }
  public int getRoomid() {
    return roomid;
  }
  public void setRoomid(int roomid) {
    this.roomid = roomid;
  }
  public int getReservation_uid() {
    return reservation_uid;
  }
  public void setReservation_uid(int reservation_uid) {
    this.reservation_uid = reservation_uid;
  }
  public Date getReservation_date() {
    return reservation_date;
  }
  public void setReservation_date(Date reservation_date) {
    this.reservation_date = reservation_date;
  }
  public Date getReservation_checkin() {
    return reservation_checkin;
  }
  public void setReservation_checkin(Date reservation_checkin) {
    this.reservation_checkin = reservation_checkin;
  }
  public Date getReservation_checkout() {
    return reservation_checkout;
  }
  public void setReservation_checkout(Date reservation_checkout) {
    this.reservation_checkout = reservation_checkout;
  }
  public int getReservation_person() {
    return reservation_person;
  }
  public void setReservation_person(int reservation_person) {
    this.reservation_person = reservation_person;
  }
  public int getReservation_price() {
    return reservation_price;
  }
  public void setReservation_price(int reservation_price) {
    this.reservation_price = reservation_price;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getHotelname() {
    return hotelname;
  }
  public void setHotelname(String hotelname) {
    this.hotelname = hotelname;
  }
  public String getRoomname() {
    return roomname;
  }
  public void setRoomname(String roomname) {
    this.roomname = roomname;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
}
