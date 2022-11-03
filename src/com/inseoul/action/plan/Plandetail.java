package com.inseoul.action.plan;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.plan.DetailplanModel;
import com.inseoul.vo.Routemaps;

public class Plandetail implements CommandInter{
	static Plandetail impl = new Plandetail();

	  public static Plandetail instance(){
	    return impl;
	  }

	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DetailplanModel model = DetailplanModel.instance();
		int mid = Integer.parseInt(request.getParameter("mid"));
		System.out.println("맵번호" + mid);
		System.out.println("맵번호지나감");
		
		Routemaps dto = (Routemaps) model.selectdtplan(mid);
		System.out.println(111);
		Date dat1 = dto.getPlan_start();
		Date dat2 = dto.getPlan_end();
		
		System.out.println(dat1);
		System.out.println(dat2);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
//		Date mojib_limit = new Date(dateFormat.parse(mojibs).getTime()); //String값을 Date값으로 변환
//		Date today = new Date(dateFormat.parse(todayFM).getTime());
		
		long calcul = dat2.getTime() - dat1.getTime(); 	// e.getTime()은 1970년 00:00:00 부터 몇초가 흘렀는지 계산, 
																// 디데이 기준이 되는 시간 - 현재시간 = 계산
		int day = (int) (calcul / ( 24*60*60*1000));			// 24*60*60*1000 값으로 위에 초를 나눠서 일수로 계산
										// 일수로 계산된 값 
	    System.out.println(day);
	    
	    
	    if(day == 0) {
	    	day=1;
	    }
	    else {
	    	day=(day+1);
	    }
	    
	    System.out.println(day);
		
//		request.setAttribute("day", day);
//		System.out.println(request.getAttribute("day"));
		request.setAttribute("dtplan", dto);
		
		
		return "../board/plandetail.jsp?day="+day;
	}
}
