<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" type="text/css" href="../css/follow-page.css">
<link rel="stylesheet" type="text/css" href="../css/follower.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/follow.js"></script>
</head>
<body>
	<c:if test="${uid == null}">
		<c:redirect url="../log-in" />
	</c:if>
	<c:if test="${uid != null}">
		<jsp:include page="../header.jsp" />
		<header id="main-header">
			<jsp:include page="../profile-header.jsp" />
		</header>
		<main>
			<section>
				<header id="article-header">
					<nav class="board-nav">
						<ul>
							<li>
								<a href="../follower/list.do?pid=${param.pid}">
									<span>
										팔로워
									</span>
								</a>
							</li>
							<li>
								<a href="../following/list.do?pid=${param.pid}">
									<span>
										팔로잉
									</span>
								</a>
							</li>
						</ul>
					</nav>
				</header>
				<article>
					<div id="follow-page">
						<input type="hidden" id="pid" value="${param.pid}" />
						<ul class="follows" page="${nowPage}">
							<c:if test="${followerList != null && followerList.size() > 0}">
								<c:forEach var="i" begin="0" end="${followerList.size() - 1}">
									<li class="follow" pid="${followerList.get(i).getFollower()}">
										<img src="../profileUpload/${followerList.get(i).getPhoto()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										<div class="follow-contents">
											<span class="follow-nick">${followerList.get(i).getNick()}</span>
											<span class="follow-date">
												${followerList.get(i).getFdate()}
											</span>
											<div class="follow-introduction">${followerList.get(i).getIntroduction()}</div>
											<c:if test="${followerList.get(i).getMyfollowing() > 0}">
												<span class="follow-info">
													${followerList.get(i).getNick()}님은 내가 팔로우하는 사용자 입니다.
												</span>
											</c:if>
										</div>
										<c:if test="${followerList.get(i).getMyfollowing() > 0}">
											<span class="follow-remove">
												<span class="material-symbols-outlined">
													person
												</span>
												<span class="symbol-label">
													팔로잉
												</span>
											</span>
										</c:if>
										<c:if test="${followerList.get(i).getMyfollowing() == 0}">
											<span class="follow-add">
												<span class="material-symbols-outlined">
													person_add
												</span>
												<span class="symbol-label">
													팔로우
												</span>
											</span>
										</c:if>
									</li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</article>
				<footer id="article-footer">
					<div class="paging">
						<div class="paging-buttons"> 
							<c:if test="${nowPage <= 1}">
								<button type="button" value="1" disabled>처음</button>&nbsp;
								<button type="button" value="1" disabled>이전</button>&nbsp;
							</c:if>
							<c:if test="${nowPage > 1}">
								<button type="button" value="1" href="list.do?pid=${param.pid}&page=1">처음</button>&nbsp;
								<button type="button" value="${nowPage - 1}" href="list.do?pid=${param.pid}&page=${nowPage - 1}">이전</button>&nbsp;
							</c:if>
							<c:forEach var="a" begin="${startPage}" end="${endPage}">
								<c:if test="${a == nowPage}">
									<button type="button" value="${a}" disabled>${a}</button>&nbsp;
								</c:if>
								<c:if test="${a != nowPage}">
									<button type="button" value="${a}" href="list.do?pid=${param.pid}&page=${a}">${a}</button>&nbsp;
								</c:if>
							</c:forEach>
							<c:if test="${nowPage >= maxPage}">
								<button type="button" value="${maxPage}" disabled>다음</button>&nbsp;
								<button type="button" value="${maxPage}" disabled>마지막</button>
							</c:if>
							<c:if test="${nowPage < maxPage}">
								<button type="button" value="${nowPage + 1}" href="list.do?pid=${param.pid}&page=${nowPage + 1}">다음</button>&nbsp;
								<button type="button" value="${maxPage}" href="list.do?pid=${param.pid}&page=${maxPage}">마지막</button>
							</c:if>
						</div>
					</div>
				</footer>
			</section>
			<nav id="section-nav">
				<jsp:include page="../profile-nav.jsp" />
			</nav>
		</main>
		<jsp:include page="../footer.jsp" />		
	</c:if>
</body>
</html>