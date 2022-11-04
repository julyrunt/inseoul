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
<link rel="stylesheet" type="text/css" href="../css/with.css" />
<link rel="stylesheet" type="text/css" href="../css/board-common.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/with-W-U.js"></script>
<%
	String uid = (String)session.getAttribute("uid");
%>

</head>
<body>
	<%@ include file="../header.jsp"%>
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="with-bigbox">
		<nav class="with-toplist">
<!-- 			<ul> -->
<!-- 				<li><a href="">In서울</a></li> -->
<!-- 				<li><span>></span></li> -->
<!-- 				<li><a href="">커뮤니티</a></li> -->
<!-- 			</ul> -->
		</nav>
		<section>
			<div class="with-midbox">
				<jsp:include page="board-sidebox.jsp"/>
				
				<form action="WithBoardWriteAction.with" method="post"
						enctype="multipart/form-data">
					<div class="with-writebox">
						<div class="with-topic-select">
							<div id="board_sel">그룹여행모집</div>
						</div>
						<input type="hidden" name="uid" value=<%=uid %>>
						<div class="with-write-name">
							<input type="text" placeholder="제목" name="title"  maxlength="50" required>
						</div>
						<div class="with-write-text">
							<div class="with-write-info">
								<div class="with-write-info-top">
									모집기한 <input type="date" name="mojib_limit" id="mojib_limit" onchange="mojib_limit_check()" required>까지
								</div>
								<div class="with-write-info-top">
									여행날짜 <input type="date" name="trip_start" id="trip_start" onchange="trip_day_check()" required> ~ 
									<input type="date" name="trip_end" id="trip_end" onchange="trip_endday_check()" required>
								</div>
								<div class="with-write-info-bottom">
									여행회비 <input type="text" name="dues" id="dues" maxlength="7" onblur="dues_check()" required>원
								</div>
								<div class="with-write-info-bottom">
									모집인원 <select name="mojib_person" id="mojib_person">
												<option value="3">3명 이내</option>
												<option value="5">5명 이내</option>
												<option value="10">10명 이내</option>
												<option value="15">15명 이내</option>
												<option value="20">20명 이내</option>
											</select>
								</div>
							</div>
							<textarea placeholder="내용..." name="contents" maxlength="1000" required></textarea>
						</div>
						<div class="with-write-submit">
<!-- 						<input type="file" name="with_file" accept="image/*" id="photobtn"  value=""> -->
							<input type="file" id="file_btn" name="file_01" class="file_btn" accept="image/*">
							<label for="file_btn">엠블럼</label>
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