<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="../css/lodgment-index.css">
<!-- 자바스크립트 -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/lodgment-index.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">숙박 정보</div>
	</div>
	<div id="bigbox">
	<!-- 좌측 필터 기능 박스  -->
	<aside>
		<form action="" method="post">
			<div id="left-bigbox">
				<div id="searchbox">
				<input type="text" name="search" id="search" placeholder="숙소 이름..">
				</div>
				<div id="select-datebox">
					<ul>
						<li><h3>날짜</h3></li>
						<li><span> &nbsp;체 크 인&nbsp; </span><input type="date" id="start_date" name="reservation-indate" onchange="start_date_change()"></li>
						<li><span>체크아웃 &nbsp;</span><input type="date" id="end_date" name="reservation-outdate" onchange="end_date_check()"></li>
					</ul>
				</div>
				<div id="button-box">
					<ul>
						<li><h3>상세조건</h3></li>
						<li>
							<input type="button" id="resetbtn" name="check-reset" value="초기화" onclick="window.location.reload()">
							<input type="button" id="searchbtn" value="검색">
						</li>
					</ul>
				</div>
				<div id="person-choice">
					<h4>인원 </h4>
					<div id="cnt-person">
					<button type="button" id="down" onclick="per_cnt('minus')"></button>
					<span id="cnt-per">2</span>
					<button type="button" id="up" onclick="per_cnt('plus')"></button>
					</div>
				</div>
				<div id="rating-checkbox">
					<br><h4>숙소 등급</h4>
					<ul>
						<li>
							<input type="range" name="rating" min=4 max=7 value=5 id="search_rating">
							<br><span id="value4">~4성급</span><span id="value5">~5성급</span>
							<span id="value6">특급</span><span id="value7">비지니스</span>
						</li>
<!-- 						<li> -->
<!-- 							<input type="radio" id="rating4" name="rating" value=4> <label for="rating4">&nbsp;~4성급</label> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<input type="radio" id="rating5" name="rating" value=5> <label for="rating5">&nbsp;~5성급</label> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<input type="radio" id="rating6" name="rating" value=6> <label for="rating6">&nbsp;특급</label> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<input type="radio" id="rating7" name="rating" value=7> <label for="rating7">&nbsp;비지니스</label> -->
<!-- 						</li> -->
					</ul>
				</div>
				<div id="bed-typebox">
					<p>
						<input type="radio" name="bed-type" id="bed-type-s" value="s">
						<label for="bed-type-s" class="bed-type1" id="bed-type_s"><img alt="single" src="images/icon-bed-s.png" id="single_bed_img"><br> &nbsp;싱글</label>
					</p>
					<p>
						<input type="radio" name="bed-type" id="bed-type-d" value="d">
						<label for="bed-type-d" class="bed-type2" id="bed-type_d"><img alt="double" src="images/icon-double-bed.png" id="double_bed_img"><br> &nbsp;더블</label>
					</p>
					<p>
						<input type="radio" name="bed-type" id="bed-type-t" value="t">
						<label for="bed-type-t" class="bed-type3" id="bed-type_t"><img alt="twin" src="images/icon-twin-beds.png" id="twin_bed_img"><br> &nbsp;트윈</label>
					</p>
					<p>
						<input type="radio" name="bed-type" id="bed-type-o" value="o">
						<label for="bed-type-o" class="bed-type4" id="bed-type_o"><img alt="ondol" src="images/icon-bed-o.png" id="ondol_bed_img"><br> &nbsp;온돌</label>
					</p>
				</div>
				<div id="public-facbox">
					<h4>공용시설</h4>
					<div id="public-check">
						<ul>
							<li>
								<input type="checkbox" class="check" name="selects" value="pc"> <span>공용PC</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="라운지"> <span>라운지</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="카페"> <span>카페</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="주차장"> <span>주차장</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="바베큐"> <span>BBQ</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="수영장"> <span>수영장</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="레스토랑"> <span>레스토랑</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="편의점"> <span>편의점</span>
							</li>
						</ul>
					</div>
				</div>
				<div id="etc-selectbox">
					<h4>기타 조건</h4>
					<div id="etc-check">
						<ul>
							<li>
								<input type="checkbox" class="check" name="selects" value="용품"> <span>욕실용품</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="조식"> <span>조식포함</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="동반"> <span>반려동물동반</span>
							</li>
							<li>
								<input type="checkbox" class="check" name="selects" value="장애인"> <span>장애인편의시설</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
			</form>
			</aside>
 			<!-- 우측 숙소 리스트 -->
 			<section>
			<div id="right-bigbox">
				<div id="lodgment-title"><h3>숙소 리스트</h3></div>
				<ul>
				<c:forEach var="i" begin="0" end="${list.size()-1}">
<%-- 					<a href="lodgment-detail.lm?hid=${list.get(i).getHid()}"> --%>
					<li>
						<div class="lodgment-contents" style="background-image: url('images/${list.get(i).getMain_photo()}');" hid="${list.get(i).getHid()}">
							<div class="contents-info">
								<div class="ratingbox">
									<c:set var="businessRating" value="7" />
									<c:choose>
									<c:when test="${businessRating eq list.get(i).getRating()}">
										<span>비지니스</span>
									</c:when>
									<c:otherwise>
										<span>${list.get(i).getRating()}성급</span>
									</c:otherwise>
									</c:choose>
								</div>
								<h2>${list.get(i).getName()}</h2>
								<h3>${list.get(i).getRegion()}</h3>
							</div>
							<div class="pricebox">
								<h2><fmt:formatNumber value="${list.get(i).getMain_price()}" pattern="#,###,###"/>원
								</h2>
							</div>
							<div class="img-brightbox"></div>
						</div>
					</li>
<!-- 					</a> -->
				</c:forEach>
				</ul>
			</div>
		
	</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>