package com.inseoul.vo;

import java.util.Date;

public class Lodgment_ReservationBean {
	int reservation_num, roomid, reservation_uid, reservation_person, reservation_price;
	String name, phone, hname, rname; 
	Date reservation_date, reservation_checkin, reservation_checkout;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	
}
