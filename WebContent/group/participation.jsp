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
<link rel="stylesheet" type="text/css" href="../css/group-common.css">
<link rel="stylesheet" type="text/css" href="../css/group-header-nav.css">
<link rel="stylesheet" type="text/css" href="../css/group-participation.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/group-common.js"></script>
<script type="text/javascript" src="../js/group-participation.js"></script>
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
					<ul id="group-page" page="${nowPage}">
						<c:if test="${data.size() > 0}">
							<c:forEach var="i" begin="0" end="${data.size() - 1}">
								<li class="group" wid="${data.get(i).getWid()}">
									<div class="title">
										<span class="material-symbols-outlined">
											group
										</span>
										<span>${data.get(i).getTitle()}</span>
									</div>
									<div class="photo">
										<img src="../boardUpload/${data.get(i).getPhotos()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										<span>${data.get(i).getReadcount()}</span>
										<span>${data.get(i).getMojib_person()}</span>
									</div>
									<div class="info">
										<span class="label">개설자</span>
										<span class="name"><a href="../profile/timeline.pr?pid=${data.get(i).getUid()}">${data.get(i).getNick()}</a></span>
									</div>
									<div class="info">
										<span class="label">개설일</span>
										 <span>${data.get(i).getWritedate()}</span>
									</div>
									<div class="info">
										<span class="label">회　비</span>
										<span>${data.get(i).getDues()} 원</span>
									</div>
									<hr />
									<div class="info">
										<span class="label royalblue">여행시작</span>
										<span>${data.get(i).getTrip_start()}</span>
									</div>
									<div class="info">
										<span class="label royalblue">여행종료</span>
										<span>${data.get(i).getTrip_end()}</span>
									</div>
									<hr />
									<div class="info">
										<span class="label darkred">모집기한</span>
										<c:if test="${LocalDate.now().isBefore(LocalDate.parse(data.get(i).getMojib_limit().toString())) || LocalDate.now().isEqual(LocalDate.parse(data.get(i).getMojib_limit().toString()))}">
											<span><font color="blue">~${data.get(i).getMojib_limit()}</font></span>
										</c:if>
										<c:if test="${LocalDate.now().isAfter(LocalDate.parse(data.get(i).getMojib_limit().toString()))}">
											<span><font color="red">~${data.get(i).getMojib_limit()}(만료)</font></span>
										</c:if>
									</div>
									<div class="info">
										<span class="label darkred">모집정원</span>
										<span>${data.get(i).getUsercount()}/${data.get(i).getMojib_person()}명</span>
									</div>
									<div class="buttons">
										<input class="exit" type="button" value="그룹탈퇴"/>
									</div>
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
								<button type="button" value="1" href="participation.pr?page=1">처음</button>&nbsp;
								<button type="button" value="${nowPage - 1}" href="participation.pr?page=${nowPage - 1}">이전</button>&nbsp;
							</c:if>
							<c:forEach var="a" begin="${startPage}" end="${endPage}">
								<c:if test="${a == nowPage}">
									<button type="button" value="${a}" disabled>${a}</button>&nbsp;
								</c:if>
								<c:if test="${a != nowPage}">
									<button type="button" value="${a}" href="participation.pr?page=${a}">${a}</button>&nbsp;
								</c:if>
							</c:forEach>
							<c:if test="${nowPage >= maxPage}">
								<button type="button" value="${maxPage}" disabled>다음</button>&nbsp;
								<button type="button" value="${maxPage}" disabled>마지막</button>
							</c:if>
							<c:if test="${nowPage < maxPage}">
								<button type="button" value="${nowPage + 1}" href="participation.pr?page=${nowPage + 1}">다음</button>&nbsp;
								<button type="button" value="${maxPage}" href="participation.pr?page=${maxPage}">마지막</button>
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