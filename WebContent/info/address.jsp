<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
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
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/info-common.css">
<link rel="stylesheet" type="text/css" href="../css/info-address.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/info-address.js"></script>
</head>
<body>
	<c:if test="${uid == null}">
		<c:redirect url="../log-in" />
	</c:if>
	<c:if test="${uid != null}">
		<jsp:include page="../header.jsp" />
		<header id="main-header">
			<%@ include file="../profile-header.jsp" %>
		</header>
		<main>
			<section>
				<header id="article-header">
					<jsp:include page="../info-article-header-nav.jsp" />
				</header>
				<article>
					<form action="addressPro.pr" onsubmit="return checkUpdatable()" method="post">
						<div class="input-label">연락처</div>
						<div class="input-field">
							<select class="half-size" name="nation" required="required">
								<c:forEach var="key" items="${Nations.keySet()}" >
									<c:if test="${!addressInfo.getNation().equals(key)}">
										<option value="${key}">${Nations.get(key)}</option>
									</c:if>
									<c:if test="${addressInfo.getNation().equals(key)}">
										<option value="${key}" selected="selected">${Nations.get(key)}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="input-field">
							<input id="phoneInput" type="text" name="phone" maxlength="16" onblur="phoneCheck()" required="required" value="${addressInfo.getPhone()}" />
						</div>
						<div class="input-alert phone"></div>
						<div class="input-label">기본주소</div>
						<div class="input-field">
							<input id="addressInput" type="text" name="addr" maxlength="100" required="required" value="${addressInfo.getAddr()}" readonly="readonly" />
							<button id="get-postcode" type="button" onclick="execDaumPostcode()" onblur="addrCheck()" value="주소검색">주소검색</button>
						</div>
						<div class="input-alert address"></div>
						<div class="input-label">상세주소</div>
						<div class="input-field">
							<input id="detailInput" type="text" name="detail" maxlength="100" onblur="detailCheck()" required="required" value="${addressInfo.getDetail()}" />
						</div>
						<div class="input-alert detail"></div>
						<input type="submit" value="정보 수정" />
					</form>
				</article>
				<footer id="article-footer">
					
				</footer>
			</section>
			<nav id="section-nav">
				<%@ include file="../profile-nav.jsp" %>
			</nav>
		</main>
		<jsp:include page="../footer.jsp" />		
	</c:if>
</body>
</html>