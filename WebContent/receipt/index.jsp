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
<link rel="stylesheet" type="text/css" href="../css/receipt-list.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/receipt-list.js"></script>
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
					<ul id="receipt-page" category="${category}">
						<li class="receipt-title">
							<span>번호</span>
							<span>예약일</span>
							
							<span>숙소명</span>
							<span>객실명</span>
							
							<span>예약자명</span>
							<span>체크인</span>
							<span>체크아웃</span>
							
							<span>인원</span>
							<span>요금</span>
						</li>
						<c:if test="${data.size() > 0}">
							<c:forEach var="i" begin="0" end="${data.size() - 1}">
								<li class="receipt" rid="${data.get(i).getReservation_num()}">
									<span>${data.get(i).getReservation_num()}</span>
									<span>${data.get(i).getReservation_date()}</span>
									<span>${data.get(i).getHotelname()}</span>	
									<span>${data.get(i).getRoomname()}</span>
									<span>${data.get(i).getUsername()}</span>
									<c:if test="${LocalDate.now().isBefore(LocalDate.parse(data.get(i).getReservation_checkin().toString())) || LocalDate.now().isEqual(LocalDate.parse(data.get(i).getReservation_checkin().toString()))}">
										<span>${data.get(i).getReservation_checkin()}</span>
									</c:if>
									<c:if test="${LocalDate.now().isAfter(LocalDate.parse(data.get(i).getReservation_checkin().toString()))}">
										<span><font color="red">${data.get(i).getReservation_checkin()}</font></span>
									</c:if>
									<c:if test="${LocalDate.now().isBefore(LocalDate.parse(data.get(i).getReservation_checkout().toString())) || LocalDate.now().isEqual(LocalDate.parse(data.get(i).getReservation_checkout().toString()))}">
										<span>${data.get(i).getReservation_checkout()}</span>
									</c:if>
									<c:if test="${LocalDate.now().isAfter(LocalDate.parse(data.get(i).getReservation_checkout().toString()))}">
										<span><font color="red">${data.get(i).getReservation_checkout()}</font></span>
									</c:if>
									<span>${data.get(i).getReservation_person()}명</span>	
									<span>${data.get(i).getReservation_price()}원</span>	
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</article>
				<footer id="article-footer">
					<div class="paging">
						<div class="paging-buttons"> 
							<c:if test="${nowPage <= 1}">
								<button type="button" value="1" disabled>처음</button>&nbsp;
								<button type="button" value="1" disabled>이전</button>&nbsp;
							</c:if>
							<c:if test="${nowPage > 1}">
								<button type="button" value="1" href="list.pr?page=1">처음</button>&nbsp;
								<button type="button" value="${nowPage - 1}" href="list.pr?page=${nowPage - 1}">이전</button>&nbsp;
							</c:if>
							<c:forEach var="a" begin="${startPage}" end="${endPage}">
								<c:if test="${a == nowPage}">
									<button type="button" value="${a}" disabled>${a}</button>&nbsp;
								</c:if>
								<c:if test="${a != nowPage}">
									<button type="button" value="${a}" href="list.pr?page=${a}">${a}</button>&nbsp;
								</c:if>
							</c:forEach>
							<c:if test="${nowPage >= maxPage}">
								<button type="button" value="${maxPage}" disabled>다음</button>&nbsp;
								<button type="button" value="${maxPage}" disabled>마지막</button>
							</c:if>
							<c:if test="${nowPage < maxPage}">
								<button type="button" value="${nowPage + 1}" href="list.pr?page=${nowPage + 1}">다음</button>&nbsp;
								<button type="button" value="${maxPage}" href="list.pr?page=${maxPage}">마지막</button>
							</c:if>
						</div>
					</div>
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