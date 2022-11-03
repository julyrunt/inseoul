<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<!-- 아이콘 폰트 스타일시트 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css"
	href="../css/reservation-confrim.css">
<!-- 자바스크립트 -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/lodgment-reservation.js"></script>

<meta charset="UTF-8">
<title></title>

</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">예약 확인</div>
	</div>
	<section>
	<div id="_big">
<!-- 		<h1>예약이 완료되었습니다</h1> -->
		<div id="content_big">
			<div id="content_left">
				<ul>
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
			<div id="content_right">
				<ul><c:forEach var="list" items="${list}" varStatus="status">
					<li><fmt:formatDate value='${list.getReservation_date()}' pattern='yyyy-MM-dd (E)' /></li>
					<li>${list.getName()}</li>
					<li>${list.getPhone()}</li>
					<li>${list.getHname()}</li>
					<li>${list.getRname()}</li>
					<li><fmt:formatDate value='${list.getReservation_checkin()}' pattern='yyyy-MM-dd (E)' /></li>
					<li><fmt:formatDate value='${list.getReservation_checkout()}' pattern='yyyy-MM-dd (E)' /></li>
					<li>${list.getReservation_person()}</li>
					<li><fmt:formatNumber value="${list.getReservation_price()}" pattern="#,###,###" /> 원</li>
					</c:forEach>
				</ul>
			</div>
			<div id="btn_box">
				<input type="button" name="go_index" value="홈으로" onclick="gohome()">
				<input type="button" name="go_list" value="목록" onclick="golist()">
			</div>
		</div>
	</div>
	</section>
	<jsp:include page="../footer.jsp" />
</body>
</html>