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
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/slick.css" />
<link rel="stylesheet" type="text/css" href="../css/slick-theme.css" />
<link rel="stylesheet" type="text/css" href="../css/qna-update.css" />
<link rel="stylesheet" type="text/css" href="../css/board-common.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
	<%
		QnABoardBean article = (QnABoardBean)request.getAttribute("article");
		String nowPage = request.getParameter("page");
		String uid = (String)session.getAttribute("uid");
	%>

</head>
<body>
	<%@ include file="../index-header.jsp"%>
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="qna-update-bigbox">
		<nav class="qna-update-toplist">
			<ul>
				<li><a href="">In서울</a></li>
				<li><span>></span></li>
				<li><a href="">커뮤니티</a></li>
			</ul>
		</nav>
		<section>
			<div class="qna-update-midbox">
				<jsp:include page="board-sidebox.jsp"/>
				<form action="qnaModify.qa" method="post" name="modifyform">
				<input type="hidden" name="page" value=<%=nowPage %>>
					<div class="qna-update-topbox">
						<div class="qna-update-top-imgbox"><img alt="pf" src="profileUpload/${article.getPhotos()}"></div>
						<div class="qna-update-top-writerbox">
							<span><%=article.getNick() %></span><br>
							<span><%=article.getWritedate() %></span>
						</div>
						<div class="qna-update-btnbox">
							<input type="button" value="취소" onClick ="javascript:history.go(-1)">
							<input type="submit" value="수정">
						</div>
					</div>
				
				<input type="hidden" name="qid" value=<%=article.getQid() %>>
				<input type="hidden" name="ref" value=<%=article.getRef() %>>
					<div class="qna-update-writebox">
						<div class="qna-update-title">
							<input type="text" value="<%=article.getTitle() %>" name="title" id="planname"  maxlength="50">
						</div>
						<div class="qna-update-contents">
							<textarea placeholder="내용" name="contents" maxlength="1000"><%=article.getContents() %></textarea>
						</div>
						
					</div>
				</form>
			</div>
		</section>
	</div>
	<%@ include file="../index-footer.jsp"%>
</body>
</html>