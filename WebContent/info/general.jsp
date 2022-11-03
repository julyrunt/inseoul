<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.time.LocalDate"%>
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
<link rel="stylesheet" type="text/css" href="../css/info-general.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/info-general.js"></script>
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
					<form action="generalPro.pr" onsubmit="return checkUpdatable()" method="post" enctype="multipart/form-data">
						<div class="input-label">프로필사진</div>
						<div class="input-preview" style="background-image: url('../profileUpload/${generalInfo.getPhoto()}'), url('../images/transparent.svg');"></div>
						<div class="input-alert"></div>
						<div class="input-field">
							<input id="profileUpload" type="file" name="photo" accept="image/png" onchange="return photoCheck()" />
						</div>
						<div class="input-alert file-size">0MB / 5MB · 파일형식: png · 파일명: 공백문자 제외</div>
						<div class="input-label">이메일</div>
						<div class="input-field">
							${generalInfo.getEmail()}
						</div>
						<div class="input-alert"></div>
						<div class="input-label">이름</div>
						<div class="input-field">
							${generalInfo.getName()}
						</div>
						<div class="input-alert"></div>
						<div class="input-label">별명</div>
						<div class="input-field">
							<input id="nickNameInput" type="text" name="nick" maxlength="45" value="${generalInfo.getNick()}" onblur="nickCheck()" required="required" />
						</div>
						<div class="input-alert nick"></div>
						<div class="input-label">생년월일</div>
						<div class="input-field">
							${generalInfo.getBirth().split('-')[0]}년 ${generalInfo.getBirth().split('-')[1]}월 ${generalInfo.getBirth().split('-')[2]}일
						</div>
						<div class="input-alert"></div>
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