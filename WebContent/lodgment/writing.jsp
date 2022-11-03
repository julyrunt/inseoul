<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="portfolio_1.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성 -> db인설트</title>
	<%
		String email = (String)session.getAttribute("email");
		String nick = (String)session.getAttribute("nick");
		
		String title = request.getParameter("review_title");
		String contents = request.getParameter("review_contents");
		
		DB_Controler con = new DB_Controler();
		con.db_ReviewInsert(nick, title, contents);
		response.sendRedirect("./");
	%>
</head>
<body>

</body>
</html>