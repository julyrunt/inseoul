<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.inseoul.vo.LocationBean" %>
<jsp:useBean id="conn" class="com.inseoul.dao.ConnectDB" />
<c:set var="best" value="${conn.getBest5()}" />
<c:set var="imgs" value="${conn.getImgs()}" />
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<!-- 폰트 스타일시트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<!-- 아이콘 폰트 스타일시트 -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<!-- 슬라이더 스타일시트 -->
	<link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css">
	<!-- 스타일시트 -->
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/slick.css"/>
	<link rel="stylesheet" type="text/css" href="css/slick-theme.css"/>
	<!-- 자바스크립트 -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.js"></script>
	<script type="text/javascript" src="js/slick.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<jsp:include page="index-header.jsp" />
	<div class="slider-over">
		<h1>서울 여행의 모든 것!</h1>
		<p>관람, 축제, 숙박 예약 등 모든 여정을 IN서울과 함께하세요.</p>
		<div class="searchBox">
			<input type="text" name="txt_search" placeholder=" 여행지,축제 검색">
			<span class="material-symbols-outlined">
				search
			</span>
			<button>검색</button>
		</div>
	</div>
	<div class="slider">
		<div>
			<img src="images/Banner02.png" />
		</div>
		<div>
			<img src="images/Banner03.png" />
		</div>
		<div>
			<img src="images/Banner04.png" />
		</div>
	</div>
	<section>
		<div class="contents">
			<div class="topview">
				<div class="dt-innerContainer">
					<div class="topTitle">
						<h1>
							서울<strong>여행계획!</strong>은<br>
							IN서울에서!
						</h1>
						<p class="toprecommend">
							"IN서울의 추천리스트!"
						</p>
					</div>
					<div class="responsive">
						<div class="slick-slide-spanbox"><a href=""><div><img src="images/plan.png"></div><br><span>추천여행 코스</span></a></div>
						<div><a href=""><img src="images/pland.png"><br>동균유저님의 여행계획</span></a></div>
						<div><a href=""><img src="images/plang.png"><br>건준유저님의 여행계획</span></a></div>
						<div><a href=""><img src="images/planh.png"><br>현준유저님의 여행계획</a></div>	
						<div><a href=""><img src="images/blossom3.png"><br>벛꽃축제</a></div>
						<div><a href=""><img src="images/festival1.jpg"><br>서울청소년 연극 축제</a></div>
						<div><a href=""><img src="images/refestival2.jpg"><br>한강페스티벌</a></div>
						<div><a href=""><img src="images/festival3.jpg"><br>구석구석 라이브</a></div>
						<div><a href=""><img src="images/exhibition1.jpg"><br>훈민정음 천년의 문자계획</a></div>
						<div><a href=""><img src="images/exhibition2.jpg"><br>별 여름 밤 여름 별</a></div>
						<div><a href=""><img src="images/exhibition3.jpg"><br>어느 수집가의 초대</a></div>
						<div><a href=""><img src="images/exhibition4.jpg"><br>반고흐인사이드더씨어터</a></div>	
					</div>
				</div>				
			</div>
			<div class="midulesection">
				<img src="images/logo-info.png" width="150" height="50">
				<h2>서울을 여행하는 당신에게 필요한 각종 정보들</h2>
				<div class="midulesection-contents">
					<a href="travels/locationIndex.tv" target="_self">
						<div class="smallbox">
							<div class="info-imgbox">
								<img src="images/info-sights.png" />
								<div class="icon-more">
									<img src="images/icon-museum.svg" alt="">
								</div>
							</div>
							<p class="info-imgbox-desc">관광지 정보</p>
						</div>
					</a>
					<a href="festival/festivalList.val" target="_self">
						<div class="smallbox">
							<div class="info-imgbox">
								<img src="images/info-festival.png" />
								<div class="icon-more">
									<img src="images/icon-festival.svg" alt="">
								</div>
							</div>
							<p class="info-imgbox-desc">축제 정보</p>
						</div>
					</a>
					<a href="lodgment/lodgment.lm" target="_self">
						<div class="smallbox">
							<div class="info-imgbox">
								<img src="images/info-hotel.png" />
								<div class="icon-more">
									<img src="images/icon-hotel.svg" alt="">
								</div>
							</div>
							<p class="info-imgbox-desc">숙박 정보</p>
						</div>
					</a>
				</div>
			</div>
			<div class="midulesection">
				<img src="images/logo-best5.png" width="150" height="50">
				<h2>여행자들이 추천하는 서울 최고의 여행지 탑5!!</h2>
				<div class="midulesection-contents-ex">
					<div class="left-box">
						<a href="" target="_self">
							<div class="bigbox-ex">
								<div class="big-imgbox">
									<img src="images2/${best.get(0).getImg()}" />
									<div class="more">
										1st
									</div>
								</div>
								<div class="location-name">
									${best.get(0).getName()}
								</div>
								<div class="location-desc">
									${best.get(0).getInfo()}
								</div>
								<div class="big-imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">${best.get(0).getHotscore()}</p>
								</div>
							</div>
						</a>
					</div>
					<div class="right-box">
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images2/${best.get(1).getImg()}" />
									<div class="more">
										2nd
									</div>
								</div>
								<div class="location-name">
									${best.get(1).getName()}
								</div>
								<div class="location-desc">
									${best.get(1).getInfo()}
								</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">${best.get(1).getHotscore()}</p>
								</div>
							</div>
						</a>
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images2/${best.get(2).getImg()}" />
									<div class="more">
										3rd
									</div>
								</div>
								<div class="location-name">
									${best.get(2).getName()}
								</div>
								<div class="location-desc">
									${best.get(2).getInfo()}
								</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">${best.get(2).getHotscore()}</p>
								</div>
							</div>
						</a>
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images2/${best.get(3).getImg()}" />
									<div class="more">
										4th
									</div>
								</div>
								<div class="location-name">${best.get(3).getName()}</div>
								<div class="location-desc">${best.get(3).getInfo()}</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">${best.get(3).getHotscore()}</p>
								</div>
							</div>
						</a>
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images2/${best.get(4).getImg()}" />
									<div class="more">
										5th
									</div>
								</div>
								<div class="location-name">${best.get(4).getName()}</div>
								<div class="location-desc">${best.get(4).getInfo()}</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">${best.get(4).getHotscore()}</p>
								</div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<div class="midulesection">
				<img src="images/logo-community.png" width="150" height="50">
				<h2>함께 만들어 가는 여행의 추억들</h2>
				<div class="midulesection-contents">
					<div class="mainbox">
					<c:forEach var="imgs" items="${imgs}" varStatus="status">
						<c:if test="${imgs.img01 != null}">
							<a href="gallery/view.do?pid=${imgs.uid}&category=my&aid=${imgs.aid}&page=1">
								<img src="albumUpload/${imgs.img01}" alt="albumImg" />
							</a>
						</c:if>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="quickmenu-view">
			<div class="quickmenu">
				<a href="plan/markerList.mk" name="write-a-route">
					<span class="material-symbols-outlined">
						map
					</span>
					<div class="quickmenu-desc">
						작성
					</div>
				</a>
				<a href="#top" name="to-the-top">
					<span class="material-symbols-outlined">
						vertical_align_top
					</span>
					<div class="quickmenu-desc">
						위로
					</div>
				</a>
			</div>
		</div>
	</section>
	<jsp:include page="index-footer.jsp" />
</body>
</html>