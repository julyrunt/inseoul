package com.inseoul.action.lodgments;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.lodgments.LodgmentDetailModel;
import com.inseoul.vo.Lodgment_ReviewBean;

public class Review_Write implements CommandInter{
	
	static Review_Write impl = new Review_Write();

	public static Review_Write instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Review_Write showData 옴");
		HttpSession session = request.getSession();
		Lodgment_ReviewBean article = new Lodgment_ReviewBean();
		LodgmentDetailModel model = new LodgmentDetailModel();

		int uid = Integer.parseInt((String) session.getAttribute("uid"));
		String title = (String) request.getParameter("title");
		String contents = (String) request.getParameter("contents");
		int hid = Integer.parseInt((String) request.getParameter("hid"));
		int capacity = Integer.parseInt((String) request.getParameter("capacity"));
		
		article.setReview_hid(hid);
		article.setReview_uid(uid);
		article.setReview_title(title);
		article.setReview_contents(contents);
		model.writeReivew(article);
		
		return "lodgment-detail.lm?hid="+hid+"&capacity="+capacity+"&category=review";
	}
}
