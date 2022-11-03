<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<link rel="stylesheet" type="text/css" href="../css/location-detail.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
</head>
<body>	
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">관 광 지 정 보</div>
	</div>
	<form action="travelsUpdating.tv" method="post" enctype="multipart/form-data">
	<input type="hidden" name="tid" value="${tid}">
	<section>
		<div id="location_w_info">
			<div id="location_w_name">
				<h2>${spot.getName()}</h2>
			</div>
			<div id="location_w_img">
				<img alt="image" src="../images2/${spot.getImg()}">
			</div>
		</div>
	</section>
	<section>
		<div id="write_">
			<input type="text" name="title" id="write_title" value="${article.getTitle()}" maxlength="50">
			<textarea name="contents" id="write_contents" maxlength="1000">${article.getContents()}</textarea>
		</div>
		<div id="write_btns">
			<input type="file" id="file_btn">
			<input type="submit" id="write_sub" value="수정하기">
			<input type="button" id="cancle" value="취소" onclick="javascript:history.back()">
		</div>
	</section>
	
	</form>
	<jsp:include page="../footer.jsp" />
</body>
</html>