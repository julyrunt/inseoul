<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.inseoul.vo.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
<link rel="stylesheet" type="text/css" href="../css/slick.css" />
<link rel="stylesheet" type="text/css" href="../css/slick-theme.css" />
<link rel="stylesheet" type="text/css" href="../css/with-detail.css" />
<link rel="stylesheet" type="text/css" href="../css/board-common.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/with-detail.js"></script>
<%
	ArrayList<WithBoardBean> replyList = (ArrayList<WithBoardBean>) request.getAttribute("replyList");
	WithBoardBean article = (WithBoardBean) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
	String uid = (String) session.getAttribute("uid");
	PageInfo replypageInfo = (PageInfo) request.getAttribute("replypageInfo");
	int replylistCount = replypageInfo.getListCount();
	int replynowPage = replypageInfo.getPage();
	int replymaxPage = replypageInfo.getMaxPage();
	int replystartPage = replypageInfo.getStartPage();
	int replyendPage = replypageInfo.getEndPage();
	Date date = new Date();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowDate = simpleDateFormat.format(date);
	Date nowDate1 = simpleDateFormat.parse(nowDate);
	String del1 = "ori";
%>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="with-bigbox">
		<div class="with-detail-midbox">
			<section id="with-detail-topsection">
					<jsp:include page="board-sidebox.jsp" />
					<input type="hidden" name="wid" value=<%=article.getWid()%>>
					<input type="hidden" name="page" value=<%=nowPage%>>
					
				<div class="with-detail-topbox">
					<div class="with-detail-top-imgbox">
						<img alt="pf" src="../profileUpload/${article.getPf()}">
					</div>
					<div class="with-detail-top-writerbox">
						<span><%=article.getNick()%></span> <span><%=article.getWritedate()%></span>
					</div>
					<%
						if (uid == null) {
					%>
					<div class="with-detail-btnbox">
						<a href="boardList.with"> <input type="button" value="목록"></a>
					</div>
					<%
						} else {
							if (Integer.parseInt(uid) == (article.getUid())) {
					%>
					<div class="with-detail-btnbox">
						<a href="boardList.with"> <input type="button" value="목록"></a>
						<%
// 							Date mojibdate = simpleDateFormat.parse(article.getMojib_limit());
									if (nowDate1.before(article.getMojib_limit()) || nowDate1.equals(article.getMojib_limit())) {
						%>
						<a
							href="withModifyForm.with?wid=<%=article.getWid()%>&page=<%=nowPage%>&ref=<%=article.getRef()%>"><input
							type="button" value="수정"></a>
						<%
									} else {}
						%>
						<a
							href="withDelete.with?wid=<%=article.getWid()%>&page=<%=nowPage%>&ref=<%=article.getRef()%>&type=<%=del1%>"
							onclick="return confirm('글을 삭제 하시겠습니까?');">
							<input type="button" value="삭제"></a>
							<c:if test="${uid == article.getUid()}">
								<div id="manage-btn">
									<input type="button" value="그룹관리">
<!-- 									<img src="../images/group_manage.png"> <span>그룹 관리</span> -->
								</div>
							</c:if>
					</div>
					<%
						} else {
					%>
					<div class="with-detail-btnbox">
						<a href="boardList.with"> <input type="button" value="목록"></a>
					</div>
					<%
						}
						}
					%>
				</div>
				<div class="with-writebox">

					<div class="with-detail-info">
						<div class="with-detail-info-top">
							모집기한 <input type="text" value=<%=article.getMojib_limit()%>
								readonly>까지
							<%
// 							Date mojibdate = simpleDateFormat.parse(article.getMojib_limit());
							if (nowDate1.before(article.getMojib_limit()) || nowDate1.equals(article.getMojib_limit())) {
						%>
							<div id="mojib_checkbox"></div>
							<%
								} else {
							%>
							<div id="mojib_checkbox">모집기간 만료</div>
							<%
								}
							%>
						</div>
						<div class="with-detail-info-top">
							여행날짜 <input type="text" value=<%=article.getTrip_start()%>
								readonly> ~ <input type="text"
								value=<%=article.getTrip_end()%> readonly>
						</div>
						<div class="with-detail-info-bottom">
							여행회비 <input type="text" value=<%=article.getDues()%> readonly>원
						</div>
						<div class="with-detail-info-bottom">
							모집인원 <input type="text" value=<%=article.getMojib_person()%>
								readonly>명 이내
						</div>
					</div>
					<div class="with-detail-title">
						<span>그룹여행모집</span>
						<textarea name="title" readonly><%=article.getTitle()%></textarea>
					</div>
					<%
						if (article.getPhotos() != null) {
					%>
					<!-- 					<div class="with-detail-imgbox"> -->
					<%-- 						<img alt="" src="../boardUpload/${article.getPhotos()}"> --%>
					<!-- 					</div> -->
					<%
						}
					%>
<!-- 					<div id="manage-box"></div> -->
					<div class="with-detail-contents">
						
						<textarea placeholder="내용" name="contents" readonly><%=article.getContents()%></textarea>
					</div>
				</div>
				<div id="group-box">
					<%
						if (nowDate1.before(article.getMojib_limit()) || nowDate1.equals(article.getMojib_limit())) {
					%>
					<c:if test="${uid == null}">
						<div id="unjoin-btn">
							<img src="../images/group_join.png"> <span>그룹 신청</span>
						</div>
					</c:if>
<%-- 					<c:if test="${uid == article.getUid()}"> --%>
<!-- 						<div id="manage-btn"> -->
<!-- 							<img src="../images/group_manage.png"> <span>그룹 관리</span> -->
<!-- 						</div> -->
<%-- 					</c:if> --%>
					<c:if test="${uid != article.getUid() && uid != null}">

						<c:if test="${stateCount == 0}">
							<div id="join-btn">
								<img src="../images/group_join.png"> <span>그룹 신청</span>
							</div>
						</c:if>
						<c:if test="${stateCount > 0}">
							<c:choose>
								<c:when test="${article.getState() eq 0}">
									<div id="wait-btn">
										<img src="../images/group_wait.png" title="클릭시 취소"> <span>신청대기중</span>
									</div>
								</c:when>
								<c:when test="${article.getState() eq 1}">
									<div id="con-btn">
										<img src="../images/group_con.png" title="클릭시 취소"> <span>그룹중</span>
									</div>
								</c:when>
							</c:choose>
						</c:if>
					</c:if>
					<%
						}
					%>
				</div>
			</section>
			<section id="with-detail-bottomsection">
				<form action="../replyWrite.with" method="post">
					<input type="hidden" name="wid" id="wid"
						value="<%=article.getWid()%>"> <input type="hidden"
						name="ref" id="reref" value=<%=article.getRef()%>> <input
						type="hidden" name="seq" id="reseq" value=<%=article.getSeq()%>>
					<input type="hidden" name="page" id="repage" value=<%=nowPage%>>
					<div class="with-detail-replywritebox">
						<%
							if (nowDate1.before(article.getMojib_limit()) || nowDate1.equals(article.getMojib_limit())) {
						%>
						<textarea placeholder="댓글 입력.." id="replybox" name="reply"
							onkeyup="rowcheck()" required></textarea>
						<%
							if (uid == null) {
						%>
						<input type="button" value="댓글"
							onclick="alert('로그인 해야 댓글을 쓸수있습니다')">
						<%
							} else {
						%>
						<input type="button" class="reply-submit" value="댓글">
						<%
							}
							} else {
						%>
						<textarea placeholder="모집기한이 만료 되었습니다.." id="replybox" readonly></textarea>
						<input type="button" value="댓글"
							onclick="alert('모집기한이 만료되어 댓글을 쓸수없습니다')">
						<%
							}
						%>
					</div>
					<div class="with-detail-replybox">
						<%
							for (int i = 0; i < replyList.size(); i++) {
						%>
						<div class="with-detail-replybox-one">
							<div class="with-detail-replybox-photo">
								<img alt="프로필사진"
									src="../profileUpload/<%=replyList.get(i).getPf()%>">
							</div>
							<div class="with-detail-replybox-contents">
								<p>
									작성자 &nbsp;<%=replyList.get(i).getNick()%></p>
								<span><%=replyList.get(i).getWritedate()%></span>
								<textarea readonly><%=replyList.get(i).getContents()%></textarea>
								<%
									if (uid != null) {
											if (uid.equals(String.valueOf(replyList.get(i).getUid()))) {
								%>
								<div class="with-detail-replydeletebox">
									<span class="clear-box" rewid=<%=replyList.get(i).getWid()%>>
										<span class="material-icons" title="삭제"> clear </span>
									</span>
								</div>
								<%
									} else {
								%>
								<div class="with-detail-replydeletebox"></div>
								<%
									}
										} else {
								%>
								<div class="with-detail-replydeletebox"></div>
								<%
									}
								%>
							</div>
						</div>
						<%
							}
						%>

						<section id="pageList">
							<%
								if (replynowPage <= 1) {
							%>
							[이전]&nbsp;
							<%
								} else {
							%>
							<a
								href="withDetail.with?replypage=<%=replynowPage - 1%>&ref=<%=article.getRef()%>&wid=<%=article.getWid()%>&page=<%=nowPage%>">[이전]</a>&nbsp;
							<%
								}
							%>

							<%
								for (int a = replystartPage; a <= replyendPage; a++) {
									if (a == replynowPage) {
							%>
							[<%=a%>]&nbsp;
							<%
								} else {
							%>
							<a
								href="withDetail.with?replypage=<%=a%>&ref=<%=article.getRef()%>&wid=<%=article.getWid()%>&page=<%=nowPage%>">[<%=a%>]
							</a>&nbsp;
							<%
								}
								}
							%>

							<%
								if (replynowPage >= replymaxPage) {
							%>
							[다음]
							<%
								} else {
							%>
							<a
								href="withDetail.with?replypage=<%=replynowPage + 1%>&ref=<%=article.getRef()%>&wid=<%=article.getWid()%>&page=<%=nowPage%>">[다음]</a>
							<%
								}
							%>
						</section>
					</div>

				</form>
			</section>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>
