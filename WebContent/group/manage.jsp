<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.time.LocalDate"%>
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
<link rel="stylesheet" type="text/css" href="../css/group-common.css">
<link rel="stylesheet" type="text/css" href="../css/group-header-nav.css">
<link rel="stylesheet" type="text/css" href="../css/group-manage.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/group-common.js"></script>
<script type="text/javascript" src="../js/group-manage.js"></script>
<script type="text/javascript" src="../js/with-detail.js"></script>
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
					<jsp:include page="../group-header-nav.jsp" />
				</header>
				<article>
					<div id="group-info" wid="${data.getWid()}">
						<div>
							<div class="title">
								<span class="material-symbols-outlined">
									group
								</span>
								<span>${data.getTitle()}</span>
							</div>
							<div class="photo">
									<img src="../boardUpload/${data.getPhotos()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
								<span>${data.getReadcount()}</span>
								<span>${data.getMojib_person()}</span>
							</div>
						</div>
						<div>
							<div class="info">
								<span class="label">개설자</span>
								<span class="name"><a href="../profile/timeline.pr?pid=${data.getUid()}">${data.getNick()}</a></span>
							</div>
							<div class="info">
								<span class="label">개설일</span>
								 <span>${data.getWritedate()}</span>
							</div>
							<div class="info">
								<span class="label">회　비</span>
								<span>${data.getDues()} 원</span>
							</div>
						</div>
						<div>
							<div class="info">
								<span class="label royalblue">여행시작</span>
								<span>${data.getTrip_start()}</span>
							</div>
							<div class="info">
								<span class="label royalblue">여행종료</span>
								<span>${data.getTrip_end()}</span>
							</div>
						</div>
						<div>
							<div class="info">
								<span class="label darkred">모집기한</span>
								<c:if test="${LocalDate.now().isBefore(LocalDate.parse(data.getMojib_limit().toString())) || LocalDate.now().isEqual(LocalDate.parse(data.getMojib_limit().toString()))}">
									<span><font color="blue">~${data.getMojib_limit()}</font></span>
								</c:if>
								<c:if test="${LocalDate.now().isAfter(LocalDate.parse(data.getMojib_limit().toString()))}">
									<span><font color="red">~${data.getMojib_limit()}(만료)</font></span>
								</c:if>
							</div>
							<div class="info">
								<span class="label darkred">모집정원</span>
								<span>${data.getUsercount()}/${data.getMojib_person()}명</span>
							</div>
						</div>
					</div>
					<div id="manage-table" wid="${param.wid}">
						
					</div>
					<nav>
						<button id="group-list" type="button" page="${param.page}">목록</button>
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