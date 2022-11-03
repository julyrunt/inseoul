<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>
<%@page import="com.inseoul.dao.ConnectDB"%>
<%@page import="com.inseoul.vo.UserBean"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/log-in-result.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/common.js"></script>
<title>로그인</title>
</head>
<body>
	<c:if test="${uid != null}">
		<c:redirect url="../" />
	</c:if>
	<c:if test="${uid == null}">
	<%
	  request.setCharacterEncoding("UTF-8");
		  String email = request.getParameter("email");
		  String pw = request.getParameter("pw");
		  ConnectDB conn = new ConnectDB();
		  UserBean user = conn.checkLogIn(email, pw);
		  if (user != null) {
		    session.setAttribute("uid", String.valueOf(user.getUid()));
		    session.setAttribute("nick", user.getNick());
		    session.setAttribute("photo", user.getPhoto());
		  }
	%>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">로그인</div>
	</div>
	<section>
		<ul class='flex-list'>
			<li>
				<c:if test="${uid == null}">
					해당 정보를 지닌 회원님을 찾을 수 없습니다.
				</c:if>
				<c:if test="${uid != null}">
					<c:if test="${param.remember == 'checked'}">
						<%
						response.addCookie(new Cookie("email", email));
						response.addCookie(new Cookie("remember", "checked"));
						%>
					</c:if>
					<c:if test="${param.remember != 'checked'}">
						<%
						response.addCookie(new Cookie("email", ""));
						response.addCookie(new Cookie("remember", ""));
						%>
					</c:if>
					<% response.sendRedirect("../"); %>>
				</c:if>
			</li>
		</ul>
		<c:if test="${uid == null}">
			<input id="btIndex" type="button" value="돌아가기" />
		</c:if>
		<c:if test="${uid != null}">
			<input id="btBack" type="button" value="돌아가기" />
		</c:if>
	</section>
	<jsp:include page="../footer.jsp" />
	</c:if>
</body>
</html>