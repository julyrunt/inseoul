<%@page import="com.inseoul.vo.QnABoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/with.js"></script>
	<%
		ArrayList<QnABoardBean> replyList = (ArrayList<QnABoardBean>)request.getAttribute("replyList");
	    QnABoardBean article = (QnABoardBean)request.getAttribute("article");
		String nowPage = (String)request.getAttribute("page");
		String uid = (String)session.getAttribute("uid");
		String nick = (String)session.getAttribute("nick");
		
	%>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="with-bigbox">
		<nav class="with-toplist">
			<ul>
				<li><a href="">In서울</a></li>
				<li><span>></span></li>
				<li><a href="">커뮤니티</a></li>
			</ul>
		</nav>
			<div class="with-detail-midbox">
			<section id="with-detail-topsection">
				<jsp:include page="board-sidebox.jsp"/>
					<input type="hidden" name="qid" value=<%=article.getQid() %>>
					<input type="hidden" name="page" value=<%=nowPage %>>
					<div class="with-detail-topbox">
						<div class="with-detail-top-imgbox"><img alt="pf" src="../profileUpload/${article.getPf()}"></div>
						<div class="with-detail-top-writerbox">
							<span><%=article.getNick() %></span><br>
							<span><%=article.getWritedate() %></span>
						</div>
						<%
						if(uid==null){%>
							<div class="with-detail-btnbox"> 
							<a href="qnaList.qa"> <input type="button" value="목록"></a>
						</div>
						<%}else{
							if (Integer.parseInt(uid)==(article.getUid())) {%>
						<div class="with-detail-btnbox">
							<a href="qnaList.qa"> <input type="button" value="목록"></a>
							<a href="qnaModifyForm.qa?qid=<%=article.getQid()%>&page=<%=nowPage%>&ref=<%=article.getRef()%>"><input type="button" value="수정"></a>
							<a href="qnaDelete.qa?qid=<%=article.getQid()%>&page=<%=nowPage%>"><input type="button" value="삭제"></a>
						</div>
						<%}else{%>
						<div class="with-detail-btnbox"> 
							<a href="qnaList.qa"> <input type="button" value="목록"></a>
						</div>
						<%
							}
						}
						%>
					</div> 
					<div class="with-writebox">
							<div class="with-detail-title">
								<span>그룹여행모집</span>
								<h3><%=article.getTitle() %></h3>
							</div>
							<%if (article.getPhotos() != null){ %>
							<div class="with-detail-imgbox">
							<img alt="photos" src="../boardUpload/${article.getPhotos()}"></div>
							<%}%>
						<div class="with-detail-contents">
							<textarea rows="15" cols="100" placeholder="내용" name="text" readonly><%=article.getContents() %></textarea>
						</div>
					</div>
				</section>
				<section id="with-detail-bottomsection">
						<form action="qnaReply.qa" method="post">
							<input type="hidden" name="qid" value=<%=article.getQid() %>>
							<input type="hidden" name="uid" value=<%=uid %>>
							<input type="hidden" name="ref" value=<%=article.getRef() %>>
							<input type="hidden" name="seq" value=<%=article.getSeq() %>>
							<input type="hidden" name="nick" value=<%=nick %>>
<%-- 							<input type="hidden" name="page" value=<%=nowPage %>> --%>
						<div class="with-detail-replywritebox">
						<textarea placeholder="댓글 입력.." id="replybox" name="reply" onkeyup="rowcheck()"></textarea>
						<% if(uid==null){ %>
							<input type="button" value="댓글" onclick="alert('로그인 해야 댓글을 쓸수있습니다')">
						<%}else{%>
							<input type="submit" value="댓글">
						<%} %>
						</div>
						<div class="with-detail-replybox">
						<%for(int i=0; i<replyList.size(); i++) { %>
<%-- 						<% if(uid.equals(replyList.get(i).getUid())){ %> --%>
							<div class="with-detail-replybox-one">
								<div class="with-detail-replybox-photo"><img alt="프로필사진" src="../profileUpload/<%=replyList.get(i).getPhotos() %>"></div>
								<div class="with-detail-replybox-contents">
								<p>작성자 &nbsp;<%=replyList.get(i).getNick() %></p><span><%=replyList.get(i).getWritedate() %></span>
								<textarea readonly><%=replyList.get(i).getContents() %></textarea></div>
<%-- 								<a href="qnaModifyForm.qa?qid=<%=article.getQid()%>&page=<%=nowPage%>&ref=<%=article.getRef()%>"><input type="button" value="수정"></a> --%>
<%-- 							<a href="qnaDelete.qa?qid=<%=article.getQid()%>&page=<%=nowPage%>"><input type="button" value="삭제"></a> --%>
							</div>
<%-- 							<%}else{ %> --%>
<!-- 							<div class="with-detail-replybox-one"> -->
<%-- 								<div class="with-detail-replybox-photo"><img alt="프로필사진" src="profileUpload/<%=replyList.get(i).getPhotos() %>"></div> --%>
<!-- 								<div class="with-detail-replybox-contents"> -->
<%-- 								<p>작성자 &nbsp;<%=replyList.get(i).getNick() %></p><span><%=replyList.get(i).getWritedate() %></span> --%>
<%-- 								<textarea readonly><%=replyList.get(i).getContents() %></textarea></div> --%>
<!-- 							</div> -->
						<%} %>
						</div>
						</form>
				</section>
			</div>
		
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>