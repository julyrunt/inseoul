package com.inseoul.action.plan;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.plan.DeleteplanModel;
import com.inseoul.model.plan.InsertplanModel;
import com.inseoul.vo.Routemaps;

public class Plandelete implements CommandInter{
	static Plandelete impl = new Plandelete();
	 
	 public static Plandelete instance(){
		    return impl;
	 }
 public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 DeleteplanModel model = DeleteplanModel.instance();
		 int mid = Integer.parseInt(request.getParameter("mid"));
		 
		 model.deleteplan(mid);
		
		 return "planselect.mk";
	  }
}
