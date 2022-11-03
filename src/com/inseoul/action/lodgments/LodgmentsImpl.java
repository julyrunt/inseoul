package com.inseoul.action.lodgments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.lodgments.LodgmentsModel;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.LodgmentsBean;

public class LodgmentsImpl implements CommandInter {

	static LodgmentsImpl impl = new LodgmentsImpl();

	public static LodgmentsImpl instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LodgmentsImpl showData 도착~");
		LodgmentsModel model = LodgmentsModel.instance();
		ArrayList<LodgmentsBean> list = (ArrayList<LodgmentsBean>) model.selectAllLodgments();
		request.setAttribute("list", list);
		return "./";

//		    int page = 1;
//		    int limit = 10;
//		    int range = 10;
//		    TimelineModel model = TimelineModel.instance();
//		    HttpSession session = request.getSession();
//		    String uid = (String)session.getAttribute("uid");
//		    String pid = request.getParameter("pid");
//		    if (pid == null) {
//		      pid = uid;
//		    }
//		    if (request.getParameter("page") != null) {
//		      page = Integer.parseInt(request.getParameter("page"));
//		    }
//		    PageInfo pageInfo = new PageInfo();
//		    pageInfo.setId(Integer.parseInt(pid));
//		    pageInfo.setPage(page);
//		    pageInfo.setLimit(limit);
//		    
//		    int listCount = model.getListCount(pageInfo);
//		    int maxPage = (int)((double)(listCount + limit - 1) / limit);
//		    int startPage = page - (page % range) + 1;
//		    int endPage = Math.min(startPage + range - 1, maxPage);
//		    pageInfo.setEndPage(endPage);
//		    pageInfo.setListCount(listCount);
//		    pageInfo.setMaxPage(maxPage);
//		    pageInfo.setPage(page);
//		    pageInfo.setStartPage(startPage);   
//		    request.setAttribute("pageInfo", pageInfo);
//		    
//		    ArrayList<TimelineBean> list = (ArrayList<TimelineBean>) model.selectTimeline(pageInfo);
//		    request.setAttribute("data", list);
//		    return "./";
	}

	public String showFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LodgmentsImpl showFilter 도착~");
		int rating = Integer.parseInt(request.getParameter("rating"));
		//자바스크립트에서 ajax로 받아온 배열은 배열같이 생겼지만 for문으로 확인해보면 String 문자열임..
		//그래서 String으로 형변환 후 패턴으로 쪼개서 다시 배열로 만들어줌 
		String [] selects = request.getParameterValues("selects");	
		String arrayToString = Arrays.stream(selects).collect(Collectors.joining());	
		Pattern pattern = Pattern.compile(",");
		String [] select_arr = pattern.split(arrayToString);
		
		String bedtype = request.getParameter("bedtype");
//		String [] bedtypes = request.getParameterValues("bedtype");
//		String arrayToString2 = Arrays.stream(bedtypes).collect(Collectors.joining());	
//		Pattern pattern2 = Pattern.compile(",");
//		String [] bedtype_arr = pattern2.split(arrayToString2);
		
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String startStr = request.getParameter("checkin").toString();
		java.util.Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(startStr);
		String endStr = request.getParameter("checkout").toString();
		java.util.Date endD = new SimpleDateFormat("yyyy-MM-dd").parse(endStr);
		// Sat Oct 08 00:00:00 KST 2022 이런 형태로 변경됨.
		
		LodgmentsModel model = LodgmentsModel.instance();
		ArrayList<LodgmentsBean> list = null;
		list = (ArrayList<LodgmentsBean>) model.selectFilterLodgments(rating, select_arr, bedtype);
		
		request.setAttribute("list", list);
		return "index-list.jsp";

//		    int page = 1;
//		    int limit = 10;
//		    int range = 10;
//		    TimelineModel model = TimelineModel.instance();
//		    HttpSession session = request.getSession();
//		    String uid = (String)session.getAttribute("uid");
//		    String pid = request.getParameter("pid");
//		    if (pid == null) {
//		      pid = uid;
//		    }
//		    if (request.getParameter("page") != null) {
//		      page = Integer.parseInt(request.getParameter("page"));
//		    }
//		    PageInfo pageInfo = new PageInfo();
//		    pageInfo.setId(Integer.parseInt(pid));
//		    pageInfo.setPage(page);
//		    pageInfo.setLimit(limit);
//		    
//		    int listCount = model.getListCount(pageInfo);
//		    int maxPage = (int)((double)(listCount + limit - 1) / limit);
//		    int startPage = page - (page % range) + 1;
//		    int endPage = Math.min(startPage + range - 1, maxPage);
//		    pageInfo.setEndPage(endPage);
//		    pageInfo.setListCount(listCount);
//		    pageInfo.setMaxPage(maxPage);
//		    pageInfo.setPage(page);
//		    pageInfo.setStartPage(startPage);   
//		    request.setAttribute("pageInfo", pageInfo);
//		    
//		    ArrayList<TimelineBean> list = (ArrayList<TimelineBean>) model.selectTimeline(pageInfo);
//		    request.setAttribute("data", list);
//		    return "./";
	}

}