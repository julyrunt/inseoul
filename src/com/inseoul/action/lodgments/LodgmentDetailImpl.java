package com.inseoul.action.lodgments;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.lodgments.LodgmentDetailModel;
import com.inseoul.model.lodgments.LodgmentsModel;
import com.inseoul.vo.LodgmentRoomBean;
import com.inseoul.vo.Lodgment_ReviewBean;
import com.inseoul.vo.LodgmentsBean;

public class LodgmentDetailImpl implements CommandInter{
	static LodgmentDetailImpl impl = new LodgmentDetailImpl();

	public static LodgmentDetailImpl instance() {
		return impl;
	}
	
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LodgmentDetailImpl showDetail 도착~");

		int hid = Integer.parseInt(request.getParameter("hid"));
		String capacity = request.getParameter("capacity");
			
		LodgmentDetailModel model = LodgmentDetailModel.instance();
		LodgmentsBean list = null;
		ArrayList <LodgmentRoomBean> roominfo = null;
		ArrayList <Lodgment_ReviewBean> review = null;
		
		list = model.selectDetail(hid);
		roominfo = (ArrayList<LodgmentRoomBean>) model.selectRoom(hid);
		review = (ArrayList<Lodgment_ReviewBean>) model.getReview(hid);
		
		request.setAttribute("list", list);
		request.setAttribute("capacity",Integer.parseInt(capacity));
		request.setAttribute("roominfo", roominfo);
		request.setAttribute("review", review);
		
		return "detail.jsp";
	}
}
