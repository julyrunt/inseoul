<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 헤더 배경 이미지 변경</title>
<link rel="stylesheet" type="text/css" href="css/profile-background-edit.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="js/profile-background-edit.js"></script>
</head>
<body>
	<main>
		<section>
			<form action="backgroundEditPro.pr" onsubmit="return photoCheck()" method="post" enctype="multipart/form-data">
				<article>
					<div id="preview" style="background-image: url(images/transparent.svg)"></div>
					<input id="image" type="file" name="image" accept="image/png" onchange="return photoCheck()" required="required" /> ※ png 파일, 최대 5MB까지 업로드 가능
				</article>
				<footer id="article-footer">
					<input class="cancel" type="button" value="취소" />
					<input type="submit" />
				</footer>
			</form>
		</section>
	</main>
</body>
</html>