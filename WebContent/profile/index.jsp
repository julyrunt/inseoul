<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
<c:if test="${param.pid != null}">
	<c:set var="pid" value="${param.pid}" />
</c:if>
<c:if test="${param.pid == null}">
	<c:set var="pid" value="${uid}" />
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
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/timeline.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/timeline.js"></script>
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
					<jsp:include page="../profile-article-header-nav.jsp" />
				</header>
				<article>
					<ul id="timeline-page">
					<c:if test="${data.size() > 0}">
						<c:forEach var="i" begin="0" end="${data.size() - 1}">
							<c:if test="${data.get(i).getType() == 'albums' || data.get(i).getType() == 'buckets' || data.get(i).getType() == 'travels' || data.get(i).getType() == 'routemaps'}">
								<li class="write-activity">
							</c:if>
							<c:if test="${data.get(i).getType() == 'album-recommend' || data.get(i).getType() == 'bucket-recommend' || data.get(i).getType() == 'travels-recommend' || data.get(i).getType() == 'routemap-recommend'}">
								<li class="recommend-activity">
							</c:if>
								<div class="timeline-item">
									<div class="timeline-title">
										<a href="timeline.pr?pid=${data.get(i).getUserId()}">
											<img class="photo" src="../profileUpload/${data.get(i).getPhoto()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
										<div class="timeline-contents">
											<a href="timeline.pr?pid=${data.get(i).getUserId()}"><span><b>${data.get(i).getNick()}</b></span></a>
											<span class="timeline-date">${data.get(i).getDate()}</span>
											<br />
											<c:if test="${data.get(i).getType() == 'albums'}">
												<a href="../gallery/view.do?pid=${data.get(i).getUserId()}&category=my&aid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>앨범을 <b><i>작성</i></b> 했습니다.</span>
											</c:if>
											<c:if test="${data.get(i).getType() == 'album-recommend'}">
												<a href="../gallery/view.do?pid=${data.get(i).getUserId()}&category=favorite&aid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>앨범을 <b><i>추천</i></b> 했습니다.</span>
											</c:if>
											<c:if test="${data.get(i).getType() == 'buckets'}">
												<a href="../bucket-list/view.do?pid=${data.get(i).getUserId()}&category=my&bid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>버킷 리스트를 <b><i>작성</i></b> 했습니다.</span>
											</c:if>
											<c:if test="${data.get(i).getType() == 'bucket-recommend'}">
												<a href="../bucket-list/view.do?pid=${data.get(i).getUserId()}&category=favorite&bid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>버킷 리스트를 <b><i>추천</i></b> 했습니다.</span>
											</c:if>
											<c:if test="${data.get(i).getType() == 'travels'}">
												<a href="../board/travels.tv?pid=${data.get(i).getUserId()}&category=my&tid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>여행기를 <b><i>작성</i></b> 했습니다.</span>
											</c:if>
											<c:if test="${data.get(i).getType() == 'travels-recommend'}">
												<a href="../board/travels.tv?pid=${data.get(i).getUserId()}&category=favorite&tid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>여행기를 <b><i>추천</i></b> 했습니다.</span>
											</c:if>
											<c:if test="${data.get(i).getType() == 'routemaps'}">
												<a href="../board/plandetail.pl?pid=${data.get(i).getUserId()}&category=my&mid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>루트맵을<b><i>작성</i></b> 했습니다.</span>
											</c:if>
											<c:if test="${data.get(i).getType() == 'routemap-recommend'}">
												<a href="../board/plandetail.pl?pid=${data.get(i).getUserId()}&category=favorite&mid=${data.get(i).getArticleId()}&page=1" >
													<span class="title">${data.get(i).getTitle()}</span>
												</a>
												<span>루트맵을 <b><i>추천</i></b> 했습니다.</span>
											</c:if>
										</div>
									</div>
									<c:if test="${data.get(i).getType() == 'albums'}">
										<a href="../gallery/view.do?pid=${data.get(i).getUserId()}&category=my&aid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../albumUpload/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
									<c:if test="${data.get(i).getType() == 'album-recommend'}">
										<a href="../gallery/view.do?pid=${data.get(i).getUserId()}&category=favorite&aid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../albumUpload/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
									<c:if test="${data.get(i).getType() == 'buckets'}">
										<a href="../bucket-list/view.do?pid=${data.get(i).getUserId()}&category=my&bid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../images/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
									<c:if test="${data.get(i).getType() == 'bucket-recommend'}">
										<a href="../bucket-list/view.do?pid=${data.get(i).getUserId()}&category=favorite&bid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../images/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
									<c:if test="${data.get(i).getType() == 'travels'}">
										<a href="../board/travels.tv?pid=${data.get(i).getUserId()}&category=my&tid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../boardUpload/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
									<c:if test="${data.get(i).getType() == 'travels-recommend'}">
										<a href="../board/travels.tv?pid=${data.get(i).getUserId()}&category=favorite&tid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../boardUpload/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
									<c:if test="${data.get(i).getType() == 'routemaps'}">
										<a href="../board/plandetail.pl?pid=${data.get(i).getUserId()}&category=my&mid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../images/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
									<c:if test="${data.get(i).getType() == 'routemap-recommend'}">
										<a href="../board/plandetail.pl?pid=${data.get(i).getUserId()}&category=favorite&mid=${data.get(i).getArticleId()}&page=1" >
											<img class="thumbnail" src="../images/${data.get(i).getThumbnail()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
										</a>
									</c:if>
								</div>
								<c:if test="${data.get(i).getType() == 'albums'}">
									<a href="../gallery/view.do?pid=${data.get(i).getUserId()}&category=my&aid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											photo_library
										</span>
									</a>
								</c:if>
								<c:if test="${data.get(i).getType() == 'album-recommend'}">
									<a href="../gallery/view.do?pid=${data.get(i).getUserId()}&category=favorite&aid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											favorite
										</span>
									</a>
								</c:if>
								<c:if test="${data.get(i).getType() == 'buckets'}">
									<a href="../bucket-list/view.do?pid=${data.get(i).getUserId()}&category=my&bid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											confirmation_number
										</span>
									</a>
								</c:if>
								<c:if test="${data.get(i).getType() == 'bucket-recommend'}">
									<a href="../bucket-list/view.do?pid=${data.get(i).getUserId()}&category=favorite&bid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											favorite
										</span>
									</a>
								</c:if>
								<c:if test="${data.get(i).getType() == 'travels'}">
									<a href="../board/travels.tv?pid=${data.get(i).getUserId()}&category=my&tid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											luggage
										</span>
									</a>
								</c:if>
								<c:if test="${data.get(i).getType() == 'travels-recommend'}">
									<a href="../board/travels.tv?pid=${data.get(i).getUserId()}&category=favorite&tid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											favorite
										</span>
									</a>
								</c:if>
								<c:if test="${data.get(i).getType() == 'routemaps'}">
									<a href="../board/plandetail.pl?pid=${data.get(i).getUserId()}&category=my&mid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											map
										</span>
									</a>
								</c:if>
								<c:if test="${data.get(i).getType() == 'routemap-recommend'}">
									<a href="../board/plandetail.pl?pid=${data.get(i).getUserId()}&category=favorite&mid=${data.get(i).getArticleId()}&page=1" >
										<span class="material-symbols-outlined">
											favorite
										</span>
									</a>
								</c:if>
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
								<button type="button" value="1" href="timeline.pr?pid=${pid}&page=1">처음</button>&nbsp;
								<button type="button" value="${nowPage - 1}" href="timeline.pr?pid=${pid}&page=${nowPage - 1}">이전</button>&nbsp;
							</c:if>
							<c:forEach var="a" begin="${startPage}" end="${endPage}">
								<c:if test="${a == nowPage}">
									<button type="button" value="${a}" disabled>${a}</button>&nbsp;
								</c:if>
								<c:if test="${a != nowPage}">
									<button type="button" value="${a}" href="timeline.pr?pid=${pid}&page=${a}">${a}</button>&nbsp;
								</c:if>
							</c:forEach>
							<c:if test="${nowPage >= maxPage}">
								<button type="button" value="${maxPage}" disabled>다음</button>&nbsp;
								<button type="button" value="${maxPage}" disabled>마지막</button>
							</c:if>
							<c:if test="${nowPage < maxPage}">
								<button type="button" value="${nowPage + 1}" href="timeline.pr?pid=${pid}&page=${nowPage + 1}">다음</button>&nbsp;
								<button type="button" value="${maxPage}" href="timeline.pr?pid=${pid}&page=${maxPage}">마지막</button>
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