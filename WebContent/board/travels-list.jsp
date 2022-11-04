<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="listCount" value="${pageInfo.getListCount()}" />
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/top-header.css">
<link rel="stylesheet" type="text/css" href="../css/slick.css">
<link rel="stylesheet" type="text/css" href="../css/slick-theme.css">
<link rel="stylesheet" type="text/css" href="../css/travels.css">
<link rel="stylesheet" type="text/css" href="../css/board-common.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="travels-bigbox">
		<section>
			<nav class="travels-toplist"></nav>
			<div id="travels-midbox">
				<jsp:include page="board-sidebox.jsp" />
				<div id="travels-contentsAll">
					<c:if test="${articlelist != null && pageInfo.getListCount() > 0}">
						<c:forEach var="list" items="${articlelist}" varStatus="status">
							<a href="travelsDetail.tv?tid=${list.tid}&page=${nowPage}">
								<div class="travels-contentsbox">
									<c:if test="${list.img01 == null}">
										<div class="travels-contents-img">
											<img alt="img01" src="../images/logo2.png">
										</div>
									</c:if>
									<c:if test="${list.img01 != null}">
										<div class="travels-contents-img">
											<img alt="img01" src="../boardUpload/${list.img01}">
										</div>
									</c:if>
									<div class="travels-contents-text">
										<div class="travels-contents-lefttext">
											<h4>여 행 기</h4>
											<span class="l_name">${list.name}</span>
											<div class="travels-contents-long">
												<span class="l_title">${list.title}</span><br> <span
													class="l_contents">${list.contents}</span>
											</div>
										</div>
										<div class="travels-contents-date">${list.date}</div>
									</div>
									<div class="travels-contents-info">
										<div class="travels-contents-writer">
											<span>${list.nick}님의 여행기</span>
										</div>
										<div class="travels-contents-readcount">
											<ul>
												<li><span class="material-icons">visibility</span></li>
												<li>${list.readcount}&nbsp;&nbsp;</li>
												<li><span class="material-icons">comment</span></li>
												<li>${list.replycount}</li>
											</ul>
										</div>
									</div>
								</div>
							</a>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</section>

		<section id="pageList">
			<c:if test="${nowPage <= 1}">
				[처음]&nbsp;[이전]&nbsp;
			</c:if>
			<c:if test="${nowPage > 1}">
				<a href="travelsList.tv?page=1">[처음]</a>&nbsp;
				<a href="travelsList.tv?page=${nowPage - 1}">[이전]</a>&nbsp;
			</c:if>
			<c:forEach var="a" begin="${startPage}" end="${endPage}">
				<c:if test="${a eq nowPage}">
					[${a}]&nbsp;
				</c:if>
				<c:if test="${a != nowPage}">
					<a href="travelsList.tv?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>
			<c:if test="${nowPage >= maxPage}">
				[다음]
			</c:if>
			<c:if test="${nowPage < maxPage}">
				<a href="travelsList.tv?page=${nowPage + 1}">[다음]</a>
				<a href="travelsList.tv?page=${maxPage}">[마지막]</a>
			</c:if>
		</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>