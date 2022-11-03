<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<c:redirect url="../log-in" />
	</c:if>
	<c:if test="${uid == null}">
		<jsp:include page="../header.jsp" />
		<div class="top-title">
			<img src="../images/top-bg-001.png" />
			<div class="titleBox">비밀번호 찾기</div>
		</div>
		<section>
			<jsp:useBean id="conn" class="com.inseoul.dao.ConnectDB" />
			<c:set var="pw" value="${conn.findPw(param.email, param.question, param.answer)}" />
			<ul class='flex-list'>
				<li>
					<c:if test="${pw != null}">
						당신의 비밀번호는 『${pw}』입니다.
					</c:if>
					<c:if test="${pw == null}">
						해당 정보를 지닌 회원님을 찾을 수 없습니다.
					</c:if>
				</li>
			</ul>
			<c:if test="${pw != null}">
				<input id="btToLogIn" type="button" value="돌아가기" />
			</c:if>
			<c:if test="${pw == null}">
				<input id="btIndex" type="button" value="돌아가기" />
			</c:if>
		</section>
		<jsp:include page="../footer.jsp" />
	</c:if>
</body>
</html>