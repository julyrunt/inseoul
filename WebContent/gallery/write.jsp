<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/gallery-common.css">
<link rel="stylesheet" type="text/css" href="../css/gallery-write.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/gallery-write.js"></script>
</head>
<body>
	<c:if test="${uid == null}">
		<c:redirect url="../log-in" />
	</c:if>
	<c:if test="${uid != null}">
		<jsp:include page="../header.jsp" />
		<header id="main-header">
			<jsp:include page="../profile-header.jsp" />
		</header>
		<main>
			<section>
				<header id="article-header">
					<jsp:include page="../profile-article-header-nav.jsp" />
				</header>
				<article>
					<form action="albumWritePro.do" method="post" enctype="multipart/form-data" name="boardform" onsubmit="return checkFileSize()">
						<input type="hidden" name="pid" value="${param.pid}" />
						<header>
							<div class="ui-widget">
								<select id="location" name="location" required="required">
									<option value>Select one...</option>
									<c:if test="${locationList.size() > 0}">
										<c:forEach var="i" begin="0" end="${locationList.size() - 1}">
											<option value=${locationList.get(i).getLid()} bg="${locationList.get(i).getImg()}" >${locationList.get(i).getName()}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<input id="title" type="text" name="title" required="required"/>
						</header>
						<textarea name="contents" ></textarea>
						사진 (<span id="photo-count">1</span>/10)
						<input id="add" type="button" value="+" />
						<input id='remove' type='button' value='-' disabled />
						<span id="file-size">0MB / 50MB · 파일형식: png · 파일명: 공백문자 제외</span>
						<ul id="file-input-list">
							<li class="file-input">
								<div id="preview01"></div>
								<input name="img01" type="file" id="img01" accept="image/png" required="required" />
							</li>
						</ul>
						<footer>
							<input id="cancel" type="button" value="취소" />
							<input type="submit" value="등록" />
						</footer>
					</form>
				</article>
				<footer id="article-footer">
				</footer>
			</section>
			<nav id="section-nav">
				<jsp:include page="../profile-nav.jsp" />
			</nav>
		</main>
		<jsp:include page="../footer.jsp" />
	</c:if>
</body>
</html>