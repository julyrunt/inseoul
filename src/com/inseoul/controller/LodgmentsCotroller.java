package com.inseoul.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.action.lodgments.*;
import com.inseoul.model.lodgments.LodgmentsModel;

public class LodgmentsCotroller extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		CommandInter inter = null;
		String viewName = "";
		
		try {
			/* 인덱스 전체 출력 */
			if(command.equals("/lodgment/lodgment.lm")){
				System.out.println("lodgment.lm 가자");
				inter = LodgmentsImpl.instance();
				viewName = inter.showData(request, response);
				viewName = "/lodgment/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 검색  */
			else if(command.equals("/lodgment/lodgment-search.lm")){
				System.out.println("lodgment-search.lm 가자");
				inter = LodgmentsSearchImpl.instance();
				viewName = ((LodgmentsSearchImpl) inter).showData(request, response); 
				System.out.println("viewName1 : "+viewName);
				viewName = "/lodgment/"+viewName; 
				System.out.println("viewName2 : "+viewName);
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 필터 적용 출력 */
			else if(command.equals("/lodgment/lodgment-filter.lm")){
				System.out.println("lodgment-filter.lm 가자");
				inter = LodgmentsImpl.instance();
				viewName = ((LodgmentsImpl) inter).showFilter(request, response);
				viewName = "/lodgment/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			} 
			/* 숙소 디테일 */
			else if(command.equals("/lodgment/lodgment-detail.lm")){
				System.out.println("lodgment-detail.lm ㄱㄱ");
				inter = LodgmentDetailImpl.instance();
				viewName = ((LodgmentDetailImpl) inter).showData(request, response); 
				viewName = "/lodgment/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 방 예약 */
			else if(command.equals("/lodgment/RoomReservation.lm")){
				System.out.println("lodgment-RoomReservation.lm ㄱㄱ");
				inter = RoomReservation.instance();
				viewName = ((RoomReservation) inter).showData(request, response); 
				viewName = "/lodgment/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 예약 완료 */
			else if(command.equals("/lodgment/reservation_success.lm")){
				System.out.println("reservation_success.lm ㄱㄱ");
				inter = Reservation.instance();
				viewName = ((Reservation) inter).showData(request, response); 
				viewName = "/lodgment/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 예약 확인 */
			else if(command.equals("/lodgment/reservation_info.lm")){
				System.out.println("reservation_info.lm ㄱㄱ");
				inter = Reservation_info.instance();
				viewName = ((Reservation_info) inter).showData(request, response); 
				viewName = "/lodgment/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 리뷰작성 */
			else if(command.equals("/lodgment/review_write.lm")){
				System.out.println("review_write.lm ㄱㄱ");
				inter = Review_Write.instance();
				viewName = ((Review_Write) inter).showData(request, response); 
				viewName = "/lodgment/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			else {
				viewName = "error.html";
				response.sendRedirect(viewName);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("이거?");
		}
	}
}
