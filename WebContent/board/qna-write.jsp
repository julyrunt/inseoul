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
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/slick.css" />
<link rel="stylesheet" type="text/css" href="../css/slick-theme.css" />
<link rel="stylesheet" type="text/css" href="../css/qna-write.css" />
<link rel="stylesheet" type="text/css" href="../css/board-common.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/with.js"></script>
<%
	String uid = (String)session.getAttribute("uid");
	String nick = (String)session.getAttribute("nick");
%>

</head>
<body>
	<%@ include file="../header.jsp"%>
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="qna-write-bigbox">
		<nav class="qna-write-toplist">
			<ul>
				<li><a href="">In서울</a></li>
				<li><span>></span></li>
				<li><a href="">커뮤니티</a></li>
			</ul>
		</nav>
		<section>
			<div class="qna-write-midbox">
				<jsp:include page="board-sidebox.jsp"/>
				<div class="qna-write-topsection">
					<p>질문 답변 게시판</p>
				</div>
				<%
					
				%>
				<form action="qnaWritePro.qa" method="post" enctype="multipart/form-data" name="">
					<div class="qna-write-writebox">
						<div class="qna-write-topic-select">
							<select name="board_sel">
								<option value="1">여행일정공유</option>
								<option value="2" >그룹여행모집</option>
								<option value="3" selected>질문과 답변</option>
								<option value="4">여행리뷰</option>
							</select>
						</div>
						<input type="hidden" name="nick" value=<%=nick %>>
						<input type="hidden" name="uid" value=<%=uid %>>
						<div class="qna-write-name">
							<input type="text" placeholder="제목" name="title"  maxlength="100" required>
						</div>
						<div class="qna-write-text">
							<textarea rows="15" cols="100" placeholder="내용" name="contents" maxlength="1000" required></textarea>
						</div>
						<div class="qna-write-submit">
						<input type="file" name="photo" accept="image/*" id="photobtn"  value="">
						<input type="button" value="취소" onClick ="javascript:history.go(-1)">
						<input type="submit" value="등록"> 
						</div>
						<div id="photozone">
						<ul><li class="photozone-list">
							<div class="photozone-one"></div>
							</li>
							</ul>
						</div>
					</div>
				</form>
			</div>
		</section>
	</div>
	<%@ include file="../footer.jsp"%>
</body>
</html>