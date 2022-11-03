<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="listCount" value="${pageInfo.getListCount()}" />
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 아이콘 폰트 스타일시트 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/festival.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">축 제 정 보</div>
	</div>
	<div id="location_">
		<section id="top_section">
			<div id="filterbox">
<!-- 				<input type="button" id="region_filter" value="지역필터"> -->
				<input type="button" id="search" value="키워드검색">
			</div>
		</section>
		<section id="midd_section">
			<div id="list_box">
				<ul>
					<c:forEach var="i" begin="0" end="${articlelist.size()-1}">
					<li class="sights-list">
						<a href="festivalDetail.val?fid=${articlelist.get(i).getFid()}">
						<div class="sights">
							<img alt="img" src="../festival-img/${articlelist.get(i).getImg()}">
							<div class="title">
							<span class="fname">${articlelist.get(i).getName()}</span>
							<span class="fsumm">${articlelist.get(i).getSummary()}</span>
							</div>
						</div>
						</a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</section>
		<section id="pageList">
			<c:if test="${nowPage <= 1}">
				[처음]&nbsp;[이전]&nbsp;
			</c:if>
			<c:if test="${nowPage > 1}">
				<a href="festivalList.val?page=1">[처음]</a>&nbsp;
				<a href="festivalList.val?page=${nowPage - 1}">[이전]</a>&nbsp;
			</c:if>
			<c:forEach var="a" begin="${startPage}" end="${endPage}">
				<c:if test="${a eq nowPage}">
					[${a}]&nbsp;
				</c:if>
				<c:if test="${a != nowPage}">
					<a href="festivalList.val?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>
			<c:if test="${nowPage >= maxPage}">
				[다음]
			</c:if>
			<c:if test="${nowPage < maxPage}">
				<a href="festivalList.val?page=${nowPage + 1}">[다음]</a>
				<a href="festivalList.val?page=${maxPage}">[마지막]</a>
			</c:if>
		</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>