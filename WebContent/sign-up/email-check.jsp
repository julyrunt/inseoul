<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.inseoul.dao.ConnectDB"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
  request.setCharacterEncoding("UTF-8");
  String email = request.getParameter("email");
  ConnectDB conn = new ConnectDB();
  if (conn.checkEmail(email)) {
    out.print("Y");
  } else {
    out.print("N");
  }
%>