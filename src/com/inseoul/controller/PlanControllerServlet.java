package com.inseoul.controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.plan.Plandelete;
import com.inseoul.action.plan.Plandetail;
import com.inseoul.action.plan.Planinsert;
import com.inseoul.action.plan.Planselect;
import com.inseoul.action.plan.Planupdate;
import com.inseoul.action.plan.Planupdateview;
import com.inseoul.action.plan.Sidepage;
import com.inseoul.action.plan.Sidepage2;



public class PlanControllerServlet extends HttpServlet{

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    String RequestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String command = RequestURI.substring(contextPath.length());
    if (uid == null) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('로그인 페이지로 이동합니다.');");
      out.println("location.href = '../log-in';");
      out.println("</script>");
      out.close();
    } else {
      CommandInter inter = null;
      String viewName = "";
      System.out.println("1");
      try {
        if(command.equals("/plan/markerList.mk")){
        	System.out.println("2");
          inter = Sidepage.instance();
          viewName = inter.showData(request, response);
          request.getRequestDispatcher(viewName).forward(request, response);
        }else if(command.equals("/plan/markerList2.mk")){
        	inter = Sidepage2.instance();
            viewName = inter.showData(request, response);
            request.getRequestDispatcher(viewName).forward(request, response);
        }else if(command.equals("/plan/planinsert.mk")) {
        	inter = Planinsert.instance();
        	viewName = inter.showData(request, response);
        	response.sendRedirect(viewName);
        }else if(command.equals("/plan/planselect.mk")) {
        	inter = Planselect.instance();
        	viewName = inter.showData(request, response);
        	request.getRequestDispatcher(viewName).forward(request, response);
        }else if(command.equals("/plan/plandetail.mk")) {
        	System.out.println("detail오나");
        	inter = Plandetail.instance();
        	viewName = inter.showData(request, response);
        	request.getRequestDispatcher(viewName).forward(request, response);
        }else if(command.equals("/plan/plandelete.mk")) {
        	System.out.println("delete오나");
        	inter = Plandelete.instance();
        	viewName = inter.showData(request, response);
        	response.sendRedirect(viewName);
        }else if(command.equals("/plan/planupdateview.mk")) {
        	System.out.println("update오나");
        	inter = Planupdateview.instance();
        	viewName = inter.showData(request, response);
        	request.getRequestDispatcher(viewName).forward(request, response);
        }else if(command.equals("/plan/planupdate.mk")) {
        	System.out.println("update오나");
        	inter = Planupdate.instance();
        	viewName = inter.showData(request, response);
        	request.getRequestDispatcher(viewName).forward(request, response);
        }else {
          viewName = "error.html";
          response.sendRedirect(viewName);
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }
}
