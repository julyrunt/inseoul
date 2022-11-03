<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/info-common.css">
<link rel="stylesheet" type="text/css" href="../css/info-question.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/info-question.js"></script>
</head>
<body>
	<c:if test="${uid == null}">
		<c:redirect url="../log-in" />
	</c:if>
	<c:if test="${uid != null}">
		<jsp:include page="../header.jsp" />
		<header id="main-header">
			<%@ include file="../profile-header.jsp" %>
		</header>
		<main>
			<section>
				<header id="article-header">
					<jsp:include page="../info-article-header-nav.jsp" />
				</header>
				<article>
					<form action="questionPro.pr" onsubmit="return checkUpdatable()" method="post">
						<div class="input-label">본인 확인 질문</div>
						<div class="input-field">
							<input id="questionInput" type="text" name="question" maxlength="40" onblur="questionCheck()" required="required" value="${questionInfo.getQuestion()}">
						</div>
						<div class="input-alert question"></div>
						<div class="input-label">본인 확인 답변</div>
						<div class="input-field">
							<input id="answerInput" type="text" name="answer" maxlength="40" onblur="answerCheck()" required="required" value="${questionInfo.getAnswer()}">
						</div>
						<div class="input-alert answer"></div>
						<input type="submit" value="정보 수정" />
					</form>
				</article>
				<footer id="article-footer">
					
				</footer>
			</section>
			<nav id="section-nav">
				<%@ include file="../profile-nav.jsp" %>
			</nav>
		</main>
		<jsp:include page="../footer.jsp" />		
	</c:if>
</body>
</html>