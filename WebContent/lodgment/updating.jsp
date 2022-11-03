<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="portfolio_1.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정 버튼 눌렀을시 정보 가져오기</title>
	<%
	String email = (String)session.getAttribute("email");
	String nick = (String)session.getAttribute("nick");
	int num = (int)session.getAttribute("num");
	String title = request.getParameter("review_title");
	String contents = request.getParameter("review_contents");
	DB_Controler con = new DB_Controler();
	con.db_ReviewUpdate(num, title, contents);
	response.sendRedirect("./");
	%>
</head>
<body>

</body>
</html>