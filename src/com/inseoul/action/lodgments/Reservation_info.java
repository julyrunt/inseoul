package com.inseoul.action.lodgments;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.lodgments.ReservationModel;
import com.inseoul.vo.Lodgment_ReservationBean;

public class Reservation_info implements CommandInter {

	static Reservation_info impl = new Reservation_info();

	public static Reservation_info instance() {
		return impl;

	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Reservation_info showData~");
		ReservationModel model = ReservationModel.instance();
		ArrayList<Lodgment_ReservationBean> list = null;
		
//		int rnum = Integer.parseInt(request.getParameter("rnum"));
		int rnum = (Integer) request.getAttribute("rnum");
		System.out.println("Reservation_info rnum:"+rnum);
		list = (ArrayList<Lodgment_ReservationBean>) model.selectReservaion(rnum);
		
		request.setAttribute("list", list);
		return "reservation_final.jsp";
	}
}
