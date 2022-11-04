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
<link rel="stylesheet" type="text/css" href="../css/planboard.css" />
<link rel="stylesheet" type="text/css" href="../css/plan-write.css" />
<link rel="stylesheet" type="text/css" href="../css/board-common.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>


</head>
<body>
	<%@ include file="../header.jsp"%>
	<div class="pb-bigbox">
		<nav class="pb-toplist">
			<ul>
				<li><a href="">In서울</a></li>
				<li><span>></span></li>
				<li><a href="">커뮤니티</a></li>
			</ul>
		</nav>
		<section>
			<div class="pb-midbox">
				<jsp:include page="board-sidebox.jsp"/>
				<div class="pw-topsection">
					<p>게시물 작성</p>
				</div>
				<%
					
				%>
				<form action="writing.jsp">
					<div class="pw-writebox">
						<div class="pw-topic-select">
							<select name="">
								<option>일정공유</option>
								<option>동행모집</option>
								<option>질문과 답변</option>
								<option>여행리뷰</option>
							</select>
						</div>
						<div class="pw-write-name">
							<input type="text" placeholder="제목" name="name">
						</div>
						<div class="pw-write-text">
							<textarea rows="15" cols="100" placeholder="내용" name="text"></textarea>
						</div>
						<div class="pw-write-submit">
							<input type="submit" value="등록"> <a href="./"><input
								type="button" value="취소"></a>
						</div>
					</div>
				</form>
			</div>
		</section>
	</div>
	<%@ include file="../footer.jsp"%>
</body>
</html>