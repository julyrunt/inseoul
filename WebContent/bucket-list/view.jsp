<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
<c:set var="pageUrl" value="view.do" />
<c:if test="${param.pid != null}">
	<c:set var="params" value="?pid=${param.pid}" />
</c:if>
<c:if test="${param.category != null}">
	<c:set var="params" value="${params}&category=${param.category}" />
</c:if>
<c:if test="${param.bid != null}">
	<c:set var="params" value="${params}&bid=${param.bid}" />
</c:if>
<c:if test="${param.page == null}">
	<c:set var="params" value="${params}&page=1" />
</c:if>
<c:if test="${param.page != null}">
	<c:set var="params" value="${params}&page=${param.page}" />
</c:if>
<c:if test="${param.search != null}">
	<c:set var="params" value="${params}&search=${param.search}" />
</c:if>
<c:if test="${param.keywords != null}">
	<c:set var="params" value="${params}&keywords=${param.keywords}" />
</c:if>
<c:if test="${param.rp != null}">
	<c:set var="rp" value="&rp=${param.rp}" />
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
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-common.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-view.css">
<link rel="stylesheet" type="text/css" href="../css/follow-button.css">
<link rel="stylesheet" type="text/css" href="../css/recommend.css">
<link rel="stylesheet" type="text/css" href="../css/reply-write.css">
<link rel="stylesheet" type="text/css" href="../css/reply-page.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/bucket-common.js"></script>
<script type="text/javascript" src="../js/bucket-view.js"></script>
<script type="text/javascript" src="../js/follow-button.js"></script>
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
				</header>
				<article>
					<div class="article-title">
						<a class="location-info" href="../location/detail.lc?lid=${bucket.getLid()}">
							<span class="material-symbols-outlined">
								info
							</span>
							${bucket.getName()}
						</a>
						<span>&nbsp;${bucket.getItem()}</span>
					</div>
					<a href="../profile/timeline.pr?pid=${author.getUid()}">
						<img class="bucket-writer-photo" src="../profileUpload/${author.getPhoto()}" onerror="this.onerror=null; this.src='../images/transparent.svg';">
						<span class="bucket-writer-nick">${author.getNick()}</span>
					</a>
					<span id="follow-button" pid="${author.getUid()}">
						<c:if test="${author.getUid() == uid}">
							<span class="timeline">
								<span class="material-symbols-outlined">
									person
								</span>
								<span class="symbol-label">
									프로필
								</span>
							</span>
						</c:if>
						<c:if test="${author.getUid() != uid}">
							<c:if test="${author.getMyfollowing() > 0}">
								<span class="follow-remove">
									<span class="material-symbols-outlined">
										person
									</span>
									<span class="symbol-label">
										팔로잉
									</span>
								</span>
							</c:if>
							<c:if test="${author.getMyfollowing() == 0}">
								<span class="follow-add">
									<span class="material-symbols-outlined">
										person_add
									</span>
									<span class="symbol-label">
										팔로우
									</span>
								</span>
							</c:if>
						</c:if>
					</span>
					<span class="bucket-write-date">${bucket.getWritedate()}</span>
					<span class="bucket-article-info">댓글 <b>${bucket.getReplyCount()}</b></span>
					<span class="bucket-article-info">추천수 <b>${bucket.getRecommendCount()}</b></span>
					<br />
					<form action="edit.jsp">
						<input type="hidden" name="pid" value="${param.pid}" />
						<input type="hidden" name="bid" value="${param.bid}" />
						<div class="bucket-ticket-write">
							<div class='bucket-ticket'>
								<div style='background: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url("../images/${bucket.getImg()}"), url("../images/transparent.svg");background-size: 100%;background-position: center;'>
									<div class='left-semicircle'></div>
									<div class='bucket-no'>
										${param.bid}
										<span class="bucket-info">
											<img alt="" src="../images/comment_FILL0_wght400_GRAD0_opsz20.svg">
											${bucket.getReplyCount()}
											<img alt="" src="../images/favorite_FILL0_wght400_GRAD0_opsz20.svg">
											${bucket.getRecommendCount()}
										</span>
									</div>
									<div class='location-name'>${bucket.getName()}</div>
									<div class='bucket-item'>${bucket.getItem()}</div>
								</div>
								<div>
									<div class='write-date'>${bucket.getWritedate()}</div>
									<div class='author'>${bucket.getNick()}</div>
									<c:if test="${bucket.getProgress() == 0}">
										<span id="icon-progress" class='material-symbols-outlined progress'>
											pending 
										</span>
									</c:if>
									<c:if test="${bucket.getProgress() != 0}">
										<span id="icon-progress" class='material-symbols-outlined complete'> 
											task_alt
										</span>
									</c:if>
									<div class='right-semicircle'></div>
								</div>
							</div>
						</div>
						<div>
							<div class="form-items">
								<ul class="flex-list">
									<li>지역</li>
									<li>${bucket.getName()}</li>
								</ul>
								<ul class="flex-list">
									<li>할일</li>
									<li>${bucket.getItem()}</li>
								</ul>
								<ul class="flex-list">
									<li>진행</li>
									<li>
										<c:if test="${bucket.getProgress() == 0}">
											예정
										</c:if>
										<c:if test="${bucket.getProgress() != 0}">
											완료
										</c:if>
									</li>
								</ul>
							</div>
						</div>
						<div id="recommend">
							<c:if test="${bucket.getFavorite() > 0}">
								<span class="material-symbols-outlined"> favorite </span><br />
								추천됨
							</c:if>
							<c:if test="${bucket.getFavorite() == 0}">
								<span class="material-symbols-outlined"> heart_plus </span><br />
								추천하기
							</c:if>
						</div>
						<nav id="bucket-manage">
							<button id="bucket-list" type="button">목록</button>
							<c:if test="${bucket.getUid() == uid}">
								<button type="button" id="bucket-modify" type="button">수정</button>
								<button id="bucket-delete" type="button">삭제</button>
							</c:if>
						</nav>
					</form>
				</article>
				<footer id="article-footer">
					<div id="reply-write">
						<img alt="photo" src="../profileUpload/${photo}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
						<textarea rows="5" cols="30" name="contents"></textarea>
						<input id="pid" type="hidden" name="pid" value="${param.pid}" />
						<input id="category" type="hidden" name="category" value="${param.category}" />
						<input id="bid" type="hidden" name="bid" value="${param.bid}" />
						<c:if test="${param.page == null}">
							<input type="hidden" name="page" value="1" />
						</c:if>
						<c:if test="${param.page != null}">
							<input type="hidden" name="page" value="${param.page}" />
						</c:if>
						<c:if test="${param.search != null}">
							<input type="hidden" name="search" value="${param.search}" />
						</c:if>
						<c:if test="${param.keywords != null}">
							<input type="hidden" name="keywords" value="${param.keywords}" />
						</c:if>
						<input class="reply-submit" type="button" value="작성" />
					</div>
					<div id="reply-page">
						<ul class="replies" rp="${nowPage}">
							<c:if test="${replyList != null && replyList.size() > 0}">
								<c:forEach var="i" begin="0" end="${replyList.size() - 1}">
									<li class="reply">
										<a href="../profile/timeline.pr?pid=${replyList.get(i).getUid()}"><img src="../profileUpload/${replyList.get(i).getPhoto()}" /></a>
										<div class="reply-contents">
											<span class="reply-writer-nick"><a href="../profile/timeline.pr?pid=${replyList.get(i).getUid()}">${replyList.get(i).getNick()}</a></span>
											<span class="reply-write-date">
												${replyList.get(i).getWritedate()}
												<c:if test="${replyList.get(i).getModified() > 0}">
													&nbsp;(수정됨)
												</c:if>
											</span>
											<c:if test="${replyList.get(i).getUid() == uid}">
												<span class="reply-manage">
													<span class="reply-modify" rid="${replyList.get(i).getRid()}">
														<span class="material-symbols-outlined">
															edit
														</span>
													</span>
													<span class="reply-delete" rid="${replyList.get(i).getRid()}">
														<span class="material-symbols-outlined">
															delete
														</span>
													</span>
												</span>
											</c:if>
											<br />
											<div class="reply-text">${replyList.get(i).getContents()}</div>
										</div>
									</li>
								</c:forEach>
							</c:if>
						</ul>
						<div class="paging">
							<div class="paging-buttons"> 
								<c:if test="${nowPage <= 1}">
									<button type="button" value="1" disabled>처음</button>&nbsp;
									<button type="button" value="1" disabled>이전</button>&nbsp;
								</c:if>
								<c:if test="${nowPage > 1}">
									<button type="button" value="1">처음</button>&nbsp;
									<button type="button" value="${nowPage - 1}">이전</button>&nbsp;
								</c:if>
								<c:forEach var="a" begin="${startPage}" end="${endPage}">
									<c:if test="${a == nowPage}">
										<button type="button" value="${a}" disabled>${a}</button>&nbsp;
									</c:if>
									<c:if test="${a != nowPage}">
										<button type="button" value="${a}">${a}</button>&nbsp;
									</c:if>
								</c:forEach>
								<c:if test="${nowPage >= maxPage}">
									<button type="button" value="${maxPage}" disabled>다음</button>&nbsp;
									<button type="button" value="${maxPage}" disabled>마지막</button>
								</c:if>
								<c:if test="${nowPage < maxPage}">
									<button type="button" value="${nowPage + 1}">다음</button>&nbsp;
									<button type="button" value="${maxPage}">마지막</button>
								</c:if>
							</div>
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