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
<script src="../js/travels-detail.js"></script>
</head>
<body>	
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">관 광 지 정 보</div>
	</div>
	<form action="travelsWriteComple.tv" method="post" enctype="multipart/form-data">
	<section>
		<div id="location_w_info">
			<div id="location_w_name">
				<h2>${article.getName()}</h2>
			</div>
			<div id="location_w_img">
				<img alt="image" src="../images2/${article.getImg()}">
			</div>
		</div>
	</section>
	<section>
		<div id="write_">
			<input type="text" name="write_title" id="write_title" maxlength="50" required>
			<textarea name="write_contents" id="write_contents" maxlength="1000" required></textarea>
		</div>
		<div id="write_btns">
			<div class="filebox">
				<input type="file" id="file_btn1" name="file_01" class="file_btn" accept="image/*">
				<label for="file_btn1">이미지1</label>
			</div>
			<div class="filebox">
				<input type="file" id="file_btn2" name="file_02" class="file_btn" accept="image/*">
				<label for="file_btn2" id="file_btn2_label">이미지2</label>
			</div>
			<div class="filebox">
				<input type="file" id="file_btn3" name="file_03" class="file_btn" accept="image/*">
				<label for="file_btn3" id="file_btn3_label">이미지3</label>
			</div>
			<input type="submit" id="write_sub" value="작성하기">
			<input type="button" id="cancle" value="취소" onclick="javascript:history.back()">
		</div>
		<div id="imagesbox">
			<ul>
				<li><div id="img01"></div></li>
				<li><div id="img02"></div></li>
				<li><div id="img03"></div></li>
			</ul>
		</div>
	</section>
		<input type="hidden" name="lid" value="${article.getLid()}">
	</form>
	<jsp:include page="../footer.jsp" />
</body>
</html>