package com.inseoul.action.lodgments;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.lodgments.ReservationModel;
import com.inseoul.vo.Lodgment_ReservationBean;

public class Reservation implements CommandInter {

	static Reservation impl = new Reservation();

	public static Reservation instance() {
		return impl;
}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Reservation showData 옴!");
		ReservationModel model = ReservationModel.instance();
		HttpSession session = request.getSession();
		Lodgment_ReservationBean reservation = new Lodgment_ReservationBean();
		
		int uid = Integer.parseInt((String) session.getAttribute("uid"));
		
		int roomid = Integer.parseInt((String) request.getParameter("r_id"));
		
		String s_date = (String) request.getParameter("s_date");
		Date sd = new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
		java.sql.Date start_date = new java.sql.Date(sd.getTime());
		
		String e_date = (String) request.getParameter("e_date");
		Date ed = new SimpleDateFormat("yyyy-MM-dd").parse(e_date);
		java.sql.Date end_date = new java.sql.Date(ed.getTime());
		
		int capacity = Integer.parseInt((String) request.getParameter("capacity"));
		
		String priceStr = request.getParameter("t_price");
		String priceNewStr = priceStr.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
		int price = Integer.parseInt(priceNewStr);
		
		reservation.setReservation_uid(uid);
		reservation.setRoomid(roomid);
		reservation.setReservation_checkin(start_date);
		reservation.setReservation_checkout(end_date);
		reservation.setReservation_person(capacity);
		reservation.setReservation_price(price);
		model.insertReservation(reservation);
		
		int rnum = model.countRnum();
		request.setAttribute("rnum", rnum);
		
		return "reservation_info.lm";
	}
}