<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
session.removeAttribute("uid");
session.removeAttribute("nick");
if (request.getRequestURL().toString().contains("/jsp/")) {
  response.sendRedirect("../");
} else {
  response.sendRedirect("./");
}
%>