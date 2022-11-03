package com.inseoul.action.plan;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.plan.InsertplanModel;
import com.inseoul.model.plan.LocationModel;
import com.inseoul.vo.LocationBean;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.Routemaps;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Planinsert implements CommandInter {
	 static Planinsert impl = new Planinsert();
	 
	 public static Planinsert instance(){
		    return impl;
	 }
	 @Override
	  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 InsertplanModel model = InsertplanModel.instance();
		 String title = request.getParameter("title");
		 HttpSession session = request.getSession();
		 int uid =  Integer.parseInt((String)session.getAttribute("uid"));
		 System.out.println(title);
		 System.out.println(uid);
		 
		 
		 String trip_startStr = request.getParameter("plan_start").toString();
		 java.util.Date tripS = new SimpleDateFormat("yyyy-MM-dd").parse(trip_startStr);
		 Date trip_startDate = new Date(tripS.getTime());
		 
		 String trip_endStr = request.getParameter("plan_end").toString();
		 java.util.Date tripE = new SimpleDateFormat("yyyy-MM-dd").parse(trip_endStr);
		 Date trip_endDate = new Date(tripE.getTime());
		 
		 System.out.println(trip_startDate);
		 System.out.println(trip_endDate);

			
		 
			String d1_0 = request.getParameter("1-0");
			String d1_1 = request.getParameter("1-1");
			String d1_2 = request.getParameter("1-2");
			String d1_3 = request.getParameter("1-3");
			String d2_0 = request.getParameter("2-0");
			String d2_1 = request.getParameter("2-1");
			String d2_2 = request.getParameter("2-2");
			String d2_3 = request.getParameter("2-3");
			String d3_0 = request.getParameter("3-0");
			String d3_1 = request.getParameter("3-1");
			String d3_2 = request.getParameter("3-2");
			String d3_3 = request.getParameter("3-3");
			String d4_0 = request.getParameter("4-0");
			String d4_1 = request.getParameter("4-1");
			String d4_2 = request.getParameter("4-2");
			String d4_3 = request.getParameter("4-3");
			String d5_0 = request.getParameter("5-0");
			String d5_1 = request.getParameter("5-1");
			String d5_2 = request.getParameter("5-2");
			String d5_3 = request.getParameter("5-3");
			String d6_0 = request.getParameter("6-0");
			String d6_1 = request.getParameter("6-1");
			String d6_2 = request.getParameter("6-2");
			String d6_3 = request.getParameter("6-3");
			String d7_0 = request.getParameter("7-0");
			String d7_1 = request.getParameter("7-1");
			String d7_2 = request.getParameter("7-2");
			String d7_3 = request.getParameter("7-3");
			
			
			Routemaps list = new Routemaps();
			list.setImg("route"+ Math.round(Math.random() * 16 + 1) + ".png");
			list.setUid(uid);
			list.setTitle(title);
			list.setDay1_0(d1_0);
			list.setDay1_1(d1_1);
			list.setDay1_2(d1_2);
			list.setDay1_3(d1_3);
			list.setDay2_0(d2_0);
			list.setDay2_1(d2_1);
			list.setDay2_2(d2_2);
			list.setDay2_3(d2_3);
			list.setDay3_0(d3_0);
			list.setDay3_1(d3_1);
			list.setDay3_2(d3_2);
			list.setDay3_3(d3_3);
			list.setDay4_0(d4_0);
			list.setDay4_1(d4_1);
			list.setDay4_2(d4_2);
			list.setDay4_3(d4_3);
			list.setDay5_0(d5_0);
			list.setDay5_1(d5_1);
			list.setDay5_2(d5_2);
			list.setDay5_3(d5_3);
			list.setDay6_0(d6_0);
			list.setDay6_1(d6_1);
			list.setDay6_2(d6_2);
			list.setDay6_3(d6_3);
			list.setDay7_0(d7_0);
			list.setDay7_1(d7_1);
			list.setDay7_2(d7_2);
			list.setDay7_3(d7_3);
			list.setPlan_start(trip_startDate);
			list.setPlan_end(trip_endDate);
			model.insertplan(list);
			return "planselect.mk";
	  }
}
