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
			<input type="text" name="txt_search" placeholder=" 여행지,박물관,축제 검색">
			<span class="material-symbols-outlined">
				search
			</span>
			<p>추천 : ....</p>
			<button>지도에서 검색</button>
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
						<div><a href=""><img src="images/pland.png"><br>동균유저님의 여행계획</a></div>
						<div><a href=""><img src="images/plang.png"><br>건준유저님의 여행계획</a></div>
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
					<a href="" target="_self">
						<div class="smallbox">
							<div class="info-imgbox">
								<img src="images/info-museum.png" />
								<div class="icon-more">
									<img src="images/icon-museum.svg" alt="">
								</div>
							</div>
							<p class="info-imgbox-desc">박물관 정보</p>
						</div>
					</a>
					<a href="" target="_self">
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
					<a href="lodgment" target="_self">
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
									<img src="images/best-01.png" />
									<div class="more">
										1st
									</div>
								</div>
								<div class="location-name">
									서울페스타 2022
								</div>
								<div class="location-desc">
									흥겨운 음악, 뜨거운 열기, 사람들의 들뜬 마음이 더해 축제는 즐겁다. 그곳에는 사람들의 환희로 가득하다. 즐거움과 환희가 가득한 그곳을 우리는 2년이라는 긴 시간 동안 묻어두어야만 했다. 하지만 다시 축제를 즐길 시간이 돌아왔다.
								</div>
								<div class="big-imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">123</p>
								</div>
							</div>
						</a>
					</div>
					<div class="right-box">
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images/best-02.png" />
									<div class="more">
										2nd
									</div>
								</div>
								<div class="location-name">
									용리단길
								</div>
								<div class="location-desc">
									서울의 시간이 켜켜이 내려앉은 거리 용산에 새로운 바람이 불기 시작했다. 독특한 음식들을 맛보고 색다른 분위기를 즐기며 세련된 제품들을 판매하는 곳들이 여기저기 생겨났다. 오래된 것과 새로운 것들은 서로 사이좋게 잘 어울리며 멋진 거리, 용리단길을 탄생시켰다. 용리단길은 더 이상 수식어가 필요 없는 서울의 핫 플레이스다.
								</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">123</p>
								</div>
							</div>
						</a>
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images/best-03.png" />
									<div class="more">
										3rd
									</div>
								</div>
								<div class="location-name">
									난지 캠핑장
								</div>
								<div class="location-desc">
									진심(盡心) 은 마음을 다한다는 뜻이다. 한국 사람들은 매사에 진심이다. 무엇을 하든, 마음을 다한다. 모든 일에 진심인 한국에서 최근 캠핑이 여행의 대세로 자리 잡고 있다. 진심을 다해 즐기는 한국에서 캠핑의 매력은 무엇일까?
								</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">123</p>
								</div>
							</div>
						</a>
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images/best-04.png" />
									<div class="more">
										4th
									</div>
								</div>
								<div class="location-name">용산공원</div>
								<div class="location-desc">이태원이 있는 용산에는 놀거리, 볼거리가 가득하다. 박물관, 미술관을 비롯해 미디어에 소개된 맛집, SNS 핫플 등 어느 한 곳만을 손에 꼽기 어려울 만큼 특색 있는 장소가 많다. 용산, 이태원에 갈 때면 수많은 곳 중 오늘의 베스트는 어디일까 하는 기대를 하게 된다. 그런데 최근 멋진 장소가 또 하나 생겼다고 한다.</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">123</p>
								</div>
							</div>
						</a>
						<a href="" target="_self">
							<div class="smallbox-ex">
								<div class="imgbox">
									<img src="images/best-05.png" />
									<div class="more">
										5th
									</div>
								</div>
								<div class="location-name">홍대 야외 공연장</div>
								<div class="location-desc">음악, 그중에서 길거리 공연을 뜻하는 ‘버스킹’은 홍대 문화의 대표적인 아이콘이라고 할 수 있다. 팬데믹과 함께 금지되었던 홍대 버스킹이 방역지침 완화와 함께 1년 4개월 만에 재개되었다. 아직은 홍대 앞 ‘걷고 싶은 거리’ 야외 공연장 4개 구역 중 2곳만 사용할 수 있지만, 이미 4월 예약이 꽉 찼을 정도로 공연을 기다려 온 열기가 뜨겁다.</div>
								<div class="imgbox-desc-ex">
									<span class="material-symbols-outlined">
										favorite
									</span>
									<p class="conter">123</p>
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
						<a href="#">
							<img src="images/p1.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p2.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p3.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p4.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p5.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p6.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p7.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p8.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p9.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p10.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p11.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p12.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/seoulforest.jpeg" alt="" />
						</a>
						<a href="#">
							<img src="images/sakura.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/sadang.png" alt="" />
						</a>
						<a href="#">
							<img src="images/war.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/Banner02.png" alt="" />
						</a>
						<a href="#">
							<img src="images/bokgung.png" alt="" />
						</a>
						<a href="#">
							<img src="images/getImage.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p13.jpg" alt="" />
						</a>
						<a href="#">
							<img src="images/p14.jpg" alt="" />
						</a>
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