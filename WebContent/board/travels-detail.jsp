<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="replylistCount" value="${replypageInfo.getListCount()}" />
<c:set var="replynowPage" value="${replypageInfo.getPage()}" />
<c:set var="replymaxPage" value="${replypageInfo.getMaxPage()}" />
<c:set var="replystartPage" value="${replypageInfo.getStartPage()}" />
<c:set var="replyendPage" value="${replypageInfo.getEndPage()}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<link rel="stylesheet" type="text/css" href="../css/travels-detail.css">
<link rel="stylesheet" type="text/css" href="../css/board-common.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/travels-detail.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">여 행 기</div>
	</div>
	<div id="travels_">
	<section>
		<jsp:include page="board-sidebox.jsp" />
		<div class="travels-detail-topbox">
			<div class="travels-detail-top-imgbox">
				<img alt="pf" src="../profileUpload/${article.getPf()}">
			</div>
			<div class="travels-detail-top-writerbox">
				<span>${article.getNick()}</span> <span>${article.getDate()}</span>
			</div>
				<c:if test="${uid==null}">
			<div class="travels-detail-btnbox">
				<input type="button" value="목록" onclick="javascript:history.back()">
			</div>
				</c:if>
				<c:if test="${uid != null && uid == article.getUid()}">
			<div class="travels-detail-btnbox">
				<input type="button" value="목록" onclick="javascript:history.back()">
				
				<input type="button" value="수정" onclick="travels_up()">
				
				<input type="button" value="삭제" onclick="travels_del()">
			</div>
			</c:if>
				<c:if test="${uid != article.getUid()}">
			<div class="travels-detail-btnbox">
				<input type="button" value="목록" onclick="javascript:history.back()">
			</div>
			</c:if>
		</div>
		<div id="travels-contents">
			<div id="contents-title">
				<a href="../travels/locationDetail.tv?lid=${article.getLid()}">
				<h3>${article.getName()}</h3> &nbsp;<p>관광지 정보 ></p>
				</a>
				<span>여 행 기 </span><h4>${article.getTitle()}</h4>
			</div>
			<c:if test="${article.getImg01() != null}">
			<div id="imagesbox">
				<ul>
					<li><div id="image01" style="background-image: url('../boardUpload/${article.getImg01()}');"></div></li>
					<c:if test="${article.getImg02() != null}">
					<li><div id="image02" style="background-image: url('../boardUpload/${article.getImg02()}');"></div></li>
					</c:if>
					<c:if test="${article.getImg03() != null}">
					<li><div id="image03" style="background-image: url('../boardUpload/${article.getImg03()}');"></div></li>
					</c:if>
				</ul>
			</div>
			</c:if>
			<div id="contents-content">
				<pre>${article.getContents()}</pre>
			</div>
			<div id="like_box">
				<c:if test="${uid != null && uid != article.getUid()}">
					<c:if test="${likeCount == 0}">
						<img alt="like" src="../images/like_b.png" class="likes">
					</c:if>
					<c:if test="${likeCount > 0}">
						<img alt="like" src="../images/like_a.png" class="likes">
					</c:if>
				</c:if>
				<c:if test="${uid == null}">
					<img alt="like" src="../images/like_b.png" onclick="login()">
				</c:if>
			</div>
		</div>
	</section>
	
	<section>
		<form action="">
					<input type="hidden" id="tid" value="${article.getTid()}">
					<input type="hidden" id="lid" value="${article.getLid()}">
					<div class="travels-detail-replywritebox">
						
						<textarea placeholder="댓글 입력.." id="replybox" name="reply"
							onkeyup="rowcheck()" required></textarea>
						<c:if test="${uid == null}">
							<input type="button" value="댓글"
							onclick="alert('로그인 해야 댓글을 쓸수있습니다')">
						</c:if>
						<c:if test="${uid != null}">
							<input type="button" class="reply-submit" value="댓글">
						</c:if>
					</div>
					<div class="travels-detail-replybox">
						<c:forEach var="reply" varStatus="status" items="${replylist}">
						
						<div class="travels-detail-replybox-one">
							<div class="travels-detail-replybox-photo">
								<img alt="프로필사진"
									src="../profileUpload/${reply.photo}">
							</div>
							<div class="travels-detail-replybox-contents">
								<p>
									작성자 &nbsp;${reply.nick}</p>
								<span>${reply.writedate}</span>
								<textarea readonly>${reply.contents}</textarea>
									<c:if test="${uid != null}">
										<c:if test="${uid eq reply.uid}">
								<div class="travels-detail-replydeletebox">
									<span class="clear-box" rerid="${reply.rid}">
										<span class="material-icons" title="삭제"> clear </span>
									</span>
								</div>
								</c:if>
								<c:if test="${uid != reply.uid}">
								<div class="travels-detail-replydeletebox"></div>
								</c:if>
								</c:if>
								<c:if test="${uid eq null}">
								<div class="travels-detail-replydeletebox"></div>
								</c:if>
							</div>
						</div>
						</c:forEach>
						<section id="pageList">
							<c:if test="${replynowPage <= 1}">
								[처음]&nbsp;[이전]&nbsp;
							</c:if>
							<c:if test="${replynowPage > 1}">
								<a href="travelsDetail.tv?tid=${article.getTid()}&replypage=1">[처음]</a>&nbsp;
								<a href="travelsDetail.tv?tid=${article.getTid()}&replypage=${replynowPage - 1}">[이전]</a>&nbsp;
							</c:if>
							<c:forEach var="a" begin="${replystartPage}" end="${replyendPage}">
								<c:if test="${a eq replynowPage}">
									[${a}]&nbsp;
								</c:if>
								<c:if test="${a != replynowPage}">
								<a href="travelsDetail.tv?tid=${article.getTid()}&replypage=${a}">[${a}]</a>&nbsp;
								</c:if>
							</c:forEach>
							<c:if test="${replynowPage >= replymaxPage}">
								[다음]
							</c:if>
							<c:if test="${replynowPage < replymaxPage}">
							<a href="travelsDetail.tv?tid=${article.getTid()}&replypage=${replynowPage + 1}">[다음]</a>
							<a href="travelsDetail.tv?tid=${article.getTid()}&replypage=${replymaxPage}">[마지막]</a>
							</c:if>
						</section>
					</div>
					</form>
			</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>