package com.inseoul.action.lodgments;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.lodgments.LodgmentsSearchModel;
import com.inseoul.vo.LodgmentsBean;

public class LodgmentsSearchImpl implements CommandInter{
	
	static LodgmentsSearchImpl impl = new LodgmentsSearchImpl();

	public static LodgmentsSearchImpl instance() {
		return impl;
	}
	

	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LodgmentsSearchImpl showData 도착~");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String search = (String) request.getParameter("search");
		System.out.println("..."+search);
		LodgmentsSearchModel model = LodgmentsSearchModel.instance();
		ArrayList<LodgmentsBean> list = (ArrayList<LodgmentsBean>) model.selectSearch(search);
		request.setAttribute("list", list);
		System.out.println("서치임플 리스트 : "+list);
		return "index-SearchList.jsp";
	}
}
