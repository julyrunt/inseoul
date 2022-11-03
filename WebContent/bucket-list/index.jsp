<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="listCount" value="${pageInfo.getListCount()}" />
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
<c:if test="${param.pid != null}">
	<c:set var="pid" value="?pid=${param.pid}" />
</c:if>
<c:if test="${param.pid == null}">
	<c:set var="pid" value="?pid=${uid}" />
</c:if>
<c:if test="${param.category != null}">
	<c:set var="category" value="&category=${param.category}" />
</c:if>
<c:if test="${param.search != null}">
	<c:set var="search" value="&search=${param.search}" />
</c:if>
<c:if test="${param.keywords != null}">
	<c:set var="keywords" value="&keywords=${param.keywords}" />
</c:if>
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
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-common.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-index.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/bucket-index.js"></script>
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
					<jsp:include page="../profile-article-header-nav.jsp" />
					<div class="search-box">
						<form action="">
							<c:if test="${param.pid != null}">
								<input type="hidden" name="pid" value="${param.pid}" />
							</c:if>
							<c:if test="${param.pid == null}">
								<input type="hidden" name="pid" value="${uid}" />
							</c:if>
							<input type="hidden" name="category" value="${param.category}" />
							<jsp:useBean id="map" class="java.util.LinkedHashMap" />
							<c:set target="${map}" property="nick" value="작성자" />
							<c:set target="${map}" property="item" value="할일" />
							<c:if test="${param.search == null || param.search == 'location'}">
								<input type="radio" name="search" value="location" checked /> 장소
							</c:if>
							<c:if test="${param.search != null && param.search != 'location'}">
								<input type="radio" name="search" value="location" /> 장소
							</c:if>
							<c:forEach var="hash" items="${map}">
								<c:if test="${param.search == hash.key}">
									<input type="radio" name="search" value="${hash.key}" checked /> ${hash.value}
								</c:if>
								<c:if test="${param.search != hash.key}">
									<input type="radio" name="search" value="${hash.key}" /> ${hash.value}
								</c:if>
							</c:forEach>
							<input type="text" id="keywords" name="keywords" value="${param.keywords}" />
							<span id="submit" class="material-symbols-outlined">
								search
							</span>
						</form>
					</div>
					<nav class="category-nav">
						<ul>
							<li>
								<a href="list.do?pid=${param.pid}&category=my">
									<c:if test="${param.category == null || param.category == 'my'}">
										<b>
									</c:if>
									<c:if test="${param.pid == uid}">
										내 리스트
									</c:if>
									<c:if test="${param.pid != uid}">
										${profile.getNick()}님의 리스트
									</c:if>
									<c:if test="${param.category == null || param.category == 'my'}">
										</b>
									</c:if>
								</a>
							</li>
							<li>
								<a href="list.do?pid=${param.pid}&category=favorite">
									<c:if test="${param.category == 'favorite'}">
										<b>
									</c:if>
									<c:if test="${param.pid == uid}">
										내 즐겨찾기
									</c:if>
									<c:if test="${param.pid != uid}">
										${profile.getNick()}님의 즐겨찾기
									</c:if>
									<c:if test="${param.category == 'favorite'}">
										</b>
									</c:if>
								</a>
							</li>
							<li>
								<a href="list.do?pid=${param.pid}&category=recommend">
									<c:if test="${param.category == 'recommend'}">
										<b>
									</c:if>
									추천 리스트
									<c:if test="${param.category == 'recommend'}">
										</b>
									</c:if>
								</a>
							</li>
						</ul>
					</nav>
				</header>
				<article>
					<div>
						<c:if test="${bucketList != null && bucketList.size() > 0}">
							<ul>
								<c:forEach var="i" begin="0" end="${bucketList.size() - 1}">
									<li><a href="view.do${pid}${category}&bid=${bucketList.get(i).getBid()}&page=${nowPage}">
											<div class='bucket-ticket' style='background: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url("../images/${bucketList.get(i).getImg()}"), url("../images/transparent.svg");background-size: 100%;background-position: center;'>
												<div class='left-semicircle'></div>
												<div class='bucket-no'>
													${bucketList.get(i).getBid()}
													<span class="bucket-info">
														<img alt="" src="../images/comment_FILL0_wght400_GRAD0_opsz20.svg"> ${bucketList.get(i).getReplyCount()}
														<img alt="" src="../images/favorite_FILL0_wght400_GRAD0_opsz20.svg"> ${bucketList.get(i).getRecommendCount()}
													</span>
												</div>
												<div class='location-name'>${bucketList.get(i).getName()}</div>
												<div class='bucket-item'>${bucketList.get(i).getItem()}</div>
											</div>
											<div>
												<div class='write-date'>${bucketList.get(i).getWritedate()}</div>
												<div class='author'>${bucketList.get(i).getNick()}</div>
												<c:if test="${bucketList.get(i).getProgress() == 0}">
													<span class='material-symbols-outlined progress'>
														pending 
													</span>
												</c:if>
												<c:if test="${bucketList.get(i).getProgress() == 1}">
													<span class='material-symbols-outlined complete'>
														task_alt
													</span>
												</c:if>
												<div class='right-semicircle'></div>
											</div>
									</a></li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
					<footer>
						<c:if test="${param.pid == uid}">
							<input id="write" type="button" value="글쓰기" />
						</c:if>
					</footer>
				</article>
				<footer id="article-footer">
					<div class="paging">
						<div class="paging-buttons">
							<c:if test="${nowPage <= 1}">
								<button type="button" value="1" disabled>처음</button>&nbsp;
								<button type="button" value="1" disabled>이전</button>&nbsp;
							</c:if>
							<c:if test="${nowPage > 1}">
								<button type="button" value="1" href="list.do${pid}${category}&page=1${search}${keywords}">처음</button>&nbsp;
								<button type="button" value="${nowPage - 1}" href="list.do${pid}${category}&page=${nowPage - 1}${search}${keywords}">이전</button>&nbsp;
							</c:if>
							<c:forEach var="a" begin="${startPage}" end="${endPage}">
								<c:if test="${a == nowPage}">
									<button type="button" value="${a}" disabled>${a}</button>&nbsp;
								</c:if>
								<c:if test="${a != nowPage}">
									<button type="button" value="${a}" href="list.do${pid}${category}&page=${a}${search}${keywords}">${a}</button>&nbsp;
								</c:if>
							</c:forEach>
							<c:if test="${nowPage >= maxPage}">
								<button type="button" value="${maxPage}" disabled>다음</button>&nbsp;
								<button type="button" value="${maxPage}" disabled>마지막</button>
							</c:if>
							<c:if test="${nowPage < maxPage}">
								<button type="button" value="${nowPage + 1}" href="list.do${pid}${category}&page=${nowPage + 1}${search}${keywords}">다음</button>&nbsp;
								<button type="button" value="${maxPage}" href="list.do${pid}${category}&page=${maxPage}${search}${keywords}">마지막</button>
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