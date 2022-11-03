<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-common.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-write.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" ></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/bucket-common.js"></script>
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
					<form action="bucketWritePro.do" onsubmit="return check()">
						<input type="hidden" name="pid" value="${param.pid}" />
						<div class="bucket-ticket-write">
							<div class='bucket-ticket'>
								<div style='background: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)); background-size: 100%; background-position: center;'>
									<div class='left-semicircle'></div>
									<div class='bucket-no'>○○○
										<span class="bucket-info">
											<img alt="" src="../images/comment_FILL0_wght400_GRAD0_opsz20.svg"> 0
											<img alt="" src="../images/favorite_FILL0_wght400_GRAD0_opsz20.svg"> 0
										</span>
									</div>
									<div class='location-name'></div>
									<div class='bucket-item'></div>
								</div>
								<div>
									<div class='write-date'>####-##-##</div>
									<div class='author'>${nick}</div>
									<span id="icon-progress" class='material-symbols-outlined progress'> pending </span>
									<div class='right-semicircle'></div>
								</div>
							</div>
						</div>
						<div>
							<div class="form-items">
								<ul class="flex-list">
									<li>지역</li>
									<li>
										<div class="ui-widget">
											<select id="location" name="location">
												<option value>Select one...</option>
												<c:if test="${locationList.size() > 0}">
													<c:forEach var="i" begin="0" end="${locationList.size() - 1}">
														<option value=${locationList.get(i).getLid()} bg="${locationList.get(i).getImg()}">${locationList.get(i).getName()}</option>
													</c:forEach>
												</c:if>
											</select>
										</div>
									</li>
								</ul>
								<ul class="flex-list">
									<li>할일</li>
									<li>
										<input id="item" type="text" name="item" maxlength="100"/>
									</li>
								</ul>
								<ul class="flex-list">
									<li>진행</li>
									<li>
										<input class="progress" type="radio" name="progress" value="0" checked="checked" />
										<span>예정</span>
										<input class="progress" type="radio" name="progress" value="1" disabled="disabled" />
										<span>완료</span>
									</li>
								</ul>
							</div>
						</div>
						<footer>
							<input id="cancel" type="button" value="취소" />
							<input type="submit" value="등록" />
						</footer>
					</form>
				</article>
				<footer id="article-footer"> </footer>
			</section>
			<nav id="section-nav">
				<jsp:include page="../profile-nav.jsp" />
			</nav>
		</main>
		<jsp:include page="../footer.jsp" />
	</c:if>
</body>
</html>