package com.inseoul.action.lodgments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.lodgments.RoomReservaionModel;
import com.inseoul.vo.LodgmentRoomBean;
import com.inseoul.vo.LodgmentsBean;
import com.inseoul.vo.UserBean;

public class RoomReservation implements CommandInter {

	static RoomReservation impl = new RoomReservation();

	public static RoomReservation instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RoomReservaion showData 도착~");
		RoomReservaionModel model = RoomReservaionModel.instance();
		HttpSession session = request.getSession();
//		SimpleDateFormat simpleDate = new SimpleDateFormat("MM-dd");
		System.out.println("=_=;"+request.getParameter("start_date"));
		int uid = Integer.parseInt((String) session.getAttribute("uid"));
		int hid = Integer.parseInt(request.getParameter("hid"));
		int roomid = Integer.parseInt(request.getParameter("roomid"));
		int price = Integer.parseInt(request.getParameter("price"));
		System.out.println("?? :"+ request.getParameter("capacity"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		
		String startStr = request.getParameter("start_date").toString();
		java.util.Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(startStr);
		
		String endStr = request.getParameter("end_date").toString();
		java.util.Date endD = new SimpleDateFormat("yyyy-MM-dd").parse(endStr);

		long calcul = endD.getTime() - startD.getTime();
		int day = (int) (calcul / ( 24*60*60*1000));
		System.out.println("몇 박? :"+day);
		int total_price = price * day;
		LodgmentRoomBean roominfo = model.selectRoomInfo(roomid);
		UserBean user = model.selectUser(uid);
		
		request.setAttribute("user", user);
		request.setAttribute("start", startD);
		request.setAttribute("end", endD);
		request.setAttribute("nightday", day);
		request.setAttribute("total_price", total_price);
		request.setAttribute("capacity", capacity);
		request.setAttribute("roominfo", roominfo);
		
		return "reservation.jsp";
		
	}

}
