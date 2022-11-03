package com.inseoul.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.action.travels.*;

public class TravelsController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		CommandInter inter = null;
		String viewName = "";
		
		try {
			/* 인덱스 전체 출력 */
			if(command.equals("/travels/locationIndex.tv")){
				System.out.println("locationIndex.tv 가자");
				inter = LocationIndex.instance();
				viewName = inter.showData(request, response);
				viewName = "/travels/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 관광지 상세보기 */
			else if(command.equals("/travels/locationDetail.tv")){
				System.out.println("locationDetail.tv ㄱㄱ");
				inter = LocationDetail.instance();
				viewName = inter.showData(request, response); 
				viewName = "/travels/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 쓰기로 이동 */
			else if(command.equals("/travels/travelsWrite.tv")){
				System.out.println("traverlsWrite.tv ㄱㄱ");
				inter = TravelsWrite.instance();
				viewName = inter.showData(request, response); 
				viewName = "/travels/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 쓰기완료 */
			else if(command.equals("/travels/travelsWriteComple.tv")){
				System.out.println("travelsWriteComple.tv ㄱㄱ");
				inter = TravelsWriteComple.instance();
				viewName = inter.showData(request, response); 
				viewName = "/travels/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 리스트로 이동 */
			else if(command.equals("/board/travelsList.tv")){
				System.out.println("travelsList.tv ㄱㄱ");
				inter = TravelsList.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 디테일 */
			else if(command.equals("/board/travelsDetail.tv")){
				System.out.println("travelsDetail.tv ㄱㄱ");
				inter = TravelsDetail.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 댓글작성 */
			else if(command.equals("/board/travelsReplyWrite.tv")){
				System.out.println("travelsReplyWrite.tv ㄱㄱ");
				inter = TravelsReplyWrite.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 댓글리스트 페이징 */
			else if(command.equals("/board/travelsReply.tv")){
				System.out.println("travelsReply.tv ㄱㄱ");
				inter = TravelsReplyList.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 댓글 삭제 */
			else if(command.equals("/board/travelsReplyDelete.tv")){
				System.out.println("travelsReplyDelete.tv ㄱㄱ");
				inter = TravelsReplyDelete.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 본문 삭제 */
			else if(command.equals("/board/travelsDelete.tv")){
				System.out.println("travelsDelete.tv ㄱㄱ");
				inter = TravelsDelete.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 본문 수정 시작 */
			else if(command.equals("/board/travelsUpdate.tv")){
				System.out.println("travelsUpdate.tv ㄱㄱ");
				inter = TravelsUpdate.instance();
				viewName = inter.showData(request, response); 
				viewName = "/travels/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 본문 수정 완료*/
			else if(command.equals("/board/travelsUpdating.tv")){
				System.out.println("travelsUpdating.tv ㄱㄱ");
				inter = TravelsUpdating.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 여행기 좋아요 */
			else if(command.equals("/board/travelsLike.tv")){
				System.out.println("travelsLike.tv ㄱㄱ");
				inter = TravelsLike.instance();
				viewName = inter.showData(request, response); 
				viewName = "/board/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			else {
				viewName = "error.html";
				response.sendRedirect(viewName);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
