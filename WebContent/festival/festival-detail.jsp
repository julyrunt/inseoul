<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<section>
			<div id="festival_">
				<div id="top_name">
					<h2>${article.getName()}</h2>
				</div>
				<div id="img_box">
					<img alt="img" src="../festival-img/${article.getImg()}">
				</div>
				<div id="festival_info">
					<span>${article.getSummary()}</span><br><br>
					<span>${article.getInfo()}</span>
				</div>
				<div id="useinfo">
					<h3>상세 정보</h3>
					<ul>
						<li><strong> 주 관 - </strong>&nbsp;<span>${article.getManagement()}</span></li>
						<li><strong> 주 소 - </strong>&nbsp;<span>${article.getAddr()}</span></li>
						<li><strong> 위 치 - </strong>&nbsp;<span>${article.getPlace()}</span></li>
						<li><strong>연락처 - </strong>&nbsp;<span>${article.getPhone()}</span></li>
						<li><strong> 시 작 - </strong>&nbsp;<span>${article.getStart()}</span></li>
						<li><strong> 종 료 - </strong>&nbsp;<span>${article.getEnd()}</span></li>
						<c:if test="${article.getTime() != ''}">
							<li><strong>이용시간 - </strong>&nbsp;<span>${article.getTime()}</span></li>
						</c:if>
						<c:if test="${article.getTime() == ''}">
							<li><strong>이용시간 - </strong>&nbsp;<span></span></li>
						</c:if>
						<c:if test="${article.getPrice() != ''}">
							<li><strong>이용요금 - </strong>&nbsp;<span>${article.getPrice()}</span></li>
						</c:if>
						<c:if test="${article.getPrice() == ''}">
							<li><strong>이용요금 - </strong>&nbsp;<span></span></li>
						</c:if>
					</ul>
				</div>
			</div>
		</section>
	<jsp:include page="../footer.jsp" />
</body>
</html>