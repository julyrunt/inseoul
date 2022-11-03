<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<!-- 아이콘 폰트 스타일시트 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css"
	href="../css/lodgment-reservation.css">
<!-- 자바스크립트 -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/lodgment-reservation.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">숙박 예약</div>
	</div>
	<div id="main_bigbox">
		<form action="reservation_success.lm" method="post">
			<c:set var="TextValue" value="${roominfo.getIn_out()}" />
			<aside>
				<div id="aside_in">
					<h3>숙소 이름</h3>
					<br>
					<h4>${roominfo.getName()}</h4>
					<br>
					<h3>방 이름</h3>
					<br>
					<h4>${roominfo.getRoomname()}</h4>
					<br>
					<h3>체크인</h3>
					<br>
					<h4>
						<fmt:formatDate value="${start}" pattern="MM-dd (E)" />
						/ ${fn:substring(TextValue,5,12)}
					</h4>
					<br>
					<h3>체크아웃</h3>
					<br>
					<h4>
						<fmt:formatDate value="${end}" pattern="MM-dd (E)" />
						/ ${fn:substring(TextValue,20,27)}
					</h4>
					<br>
					<h3>인 원</h3>
					<br>
					<h4>${capacity}명</h4>
					<br>
					<h3>가 격</h3>
					<span><fmt:formatNumber value="${roominfo.getRoomprice()}"
							pattern="#,###,###" /> 원 * ${nightday}박</span>
					<h3>
						총 가격 :
						<fmt:formatNumber value="${total_price}" pattern="#,###,###" />
						원
					</h3>
					<input type="submit" value="예약하기" id="reservation_btn">
				</div>
			</aside>
			<section>
				<div id="sec_in">
					<h3>예약자 정보</h3>
					<ul>
						<li><h4>예약자 이름</h4></li>
						<li><input type="text" name="reser_name" id="reser_name"
							value="${user.getName()}" readonly></li>
						<li><h4>핸드폰 번호</h4></li>
						<li><input type="text" name="reser_phone" id="reser_phone"
							value="${user.getPhone()}" readonly> <input type="button"
							id="phone_confirm" value="인증번호"><br> <input
							type="text" name="certify" id="certify">
							<div id="certify_" oncontextmenu="return false"></div></li>
						<li><h4>결제 수단</h4></li>
						<li><select name="pay_sel" id="pay_sel" required>
								<option value="신용/체크 카드" selected>신용/체크 카드</option>
								<option value="카카오페이">카카오페이</option>
								<option value="토스">토스</option>
								<option value="핸드폰 결제">핸드폰 결제</option>
						</select></li>
					</ul>
					<div id="terms_">
						<ul>
							<li><input type="checkbox" id="allcheck"><label
								for="allcheck">전체 동의</label></li>
							<li><input type="checkbox" name="checkT" id="check1"><label
								for="check1">숙소이용규칙 및 취소/환불규정 동의<span> (필수)</span></label></li>
							<li><input type="checkbox" name="checkT" id="check2"><label
								for="check2">개인정보 수집 및 이용 동의<span> (필수)</span></label></li>
							<li><input type="checkbox" name="checkT" id="check3"><label
								for="check3">개인정보 제 3자 제공 동의<span> (필수)</span></label></li>
							<li><input type="checkbox" name="checkT" id="check4"><label
								for="check4">만 14세 이상 확인<span> (필수)</span></label></li>
						</ul>
					</div>
				</div>
			</section>
			<input type="hidden" name="r_id" value="${roominfo.getRoomid()}">
			<input type="hidden" name="capacity" value="${capacity}"> <input
				type="hidden" name="t_price"
				value="<fmt:formatNumber value="${total_price}" pattern="#,###,###"/>">
			<input type="hidden" name="s_date"
				value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd (E)' />">
			<input type="hidden" name="e_date"
				value="<fmt:formatDate value='${end}' pattern='yyyy-MM-dd (E)' />">
		</form>
	</div>


	<jsp:include page="../footer.jsp" />
</body>
</html>