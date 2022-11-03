<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="../css/receipt-header-nav.css">
<c:if test='${param.category == null or param.category == "all"}'>
	<c:set var="category" value="all" />
	<style type="text/css">
		#article-header .board-nav li:first-child {
			border-bottom: 3px solid #71B7E5;
			font-weight: bold;
		}
	</style>
</c:if>
<c:if test='${param.category == "schedule"}'>
	<c:set var="category" value="schedule" />
	<style type="text/css">
		#article-header .board-nav li:nth-child(2) {
			border-bottom: 3px solid #71B7E5;
			font-weight: bold;
		}
	</style>
</c:if>
<c:if test='${param.category == "expired"}'>
	<c:set var="category" value="expired" />
	<style type="text/css">
		#article-header .board-nav li:last-child {
			border-bottom: 3px solid #71B7E5;
			font-weight: bold;
		}
	</style>
</c:if>
<c:if test='${param.page == null}'>
	<c:set var="page" value="1" />
</c:if>
<c:if test='${param.page != null}'>
	<c:set var="page" value="${param.page}" />
</c:if>
<link rel="stylesheet" type="text/css" href="../css/receipt-view.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/receipt-view.js"></script>
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
					<jsp:include page="../receipt-header-nav.jsp" />
				</header>
				<article>
					<div id="contents">
						<div class="content_left">
							<ul>
								<li>예약번호</li>
								<li>예약일</li>
								<li>예약자</li>
								<li>연락처</li>
								<li>숙소 이름</li>
								<li>방 이름</li>
								<li>체크인</li>
								<li>체크아웃</li>
								<li>예약인원</li>
								<li>총 가격</li>
							</ul>
						</div>
						<div class="content_right">
							<ul>
								<li>${receiptInfo.getReservation_num()}</li>
								<li><fmt:formatDate value='${receiptInfo.getReservation_date()}' pattern='yyyy-MM-dd (E)' /></li>
								<li>${receiptInfo.getUsername()}</li>
								<c:if test="${receiptInfo.getPhone().length() <= 12}">
									<li>${receiptInfo.getPhone().substring(0, receiptInfo.getPhone().length()-8)}-${receiptInfo.getPhone().substring(receiptInfo.getPhone().length()-8, receiptInfo.getPhone().length()-4)}-${receiptInfo.getPhone().substring(receiptInfo.getPhone().length()-4, receiptInfo.getPhone().length())}</li>
								</c:if>
								<c:if test="${receiptInfo.getPhone().length() > 12}">
									<li>${receiptInfo.getPhone()}</li>
								</c:if>
								<li>${receiptInfo.getHotelname()}</li>
								<li>${receiptInfo.getRoomname()}</li>
								<li><fmt:formatDate value='${receiptInfo.getReservation_checkin()}' pattern='yyyy-MM-dd (E)' /></li>
								<li><fmt:formatDate value='${receiptInfo.getReservation_checkout()}' pattern='yyyy-MM-dd (E)' /></li>
								<li>${receiptInfo.getReservation_person()} 명</li>
								<li><fmt:formatNumber value="${receiptInfo.getReservation_price()}" pattern="#,###,###" /> 원</li>
							</ul>
						</div>
					</div>		
					<nav>
						<button id="receipt-list" type="button" page="${page}" category="${category}">목록</button>
					</nav>
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