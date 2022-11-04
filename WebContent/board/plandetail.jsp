<%@page import="com.inseoul.vo.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.inseoul.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	int day = Integer.parseInt(request.getParameter("day"));

%>
<%-- <c:if test="${dtplan != null}" > --%>
<!-- 	있습니다. -->
<%-- </c:if> --%>
<%-- <c:if test="${dtplan == null}" > --%>
<!-- 	없습니다. -->
<%-- </c:if> --%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 슬라이더 스타일시트 -->
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/plan-index.css" />
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5d08d2c887149854d7a12187b108e986&libraries=services,clusterer,drawing"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div></div>
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">루 트 맵</div>
	</div>
	<div class="pl-bigbox">
		<nav class="pl-toplist">
		</nav>
		<section>
			<div class="pl-midbox">
				<div id="pl-mapbox"></div>
				<div id="bottom-box">
					<div id="select-date">


						여행시작 : <input type="date" id="start_date"
							value="<fm:formatDate value='${dtplan.getPlan_start()}' pattern='yyyy-MM-dd'/>"
							readonly />&nbsp;&nbsp;~ &nbsp;&nbsp; 여행종료 : <input type="date"
							id="end_date"
							value="<fm:formatDate value='${dtplan.getPlan_end()}' pattern='yyyy-MM-dd'/>"
							readonly />
						<!-- 				<input type="button" id="add" value="적용하기"> -->
					</div>
					<div id="title-box">
						<input type="text" name="plantitle" id="plantitle"
							placeholder="${dtplan.getTitle()}" readonly="readonly"
							style="width: 640px; height: 50px; font-size: 18px; font-weight: bold">
					</div>
					<div id="ovx-box">
						<div id="plan-big-box">
							<c:if test="${dtplan.getDay1_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title' style="background-color: #D23F36; ">
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-1))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay1_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle">${dtplan.getDay1_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay1_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
									</div>
									<c:if test="${dtplan.getDay1_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay1_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay1_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay1_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									
									<c:if test="${dtplan.getDay1_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay1_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay1_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay1_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay1_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay1_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay1_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay1_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay2_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title' style="background-color: #238CFA; ">
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-2))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay2_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle">${dtplan.getDay2_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay2_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
									</div>
									<c:if test="${dtplan.getDay2_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay2_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay2_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay2_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay2_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay2_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay2_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay2_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay2_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay2_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay2_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay2_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay3_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title' style="background-color: #3E9F62; ">
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;" style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-3))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay3_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle">${dtplan.getDay3_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay3_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
									</div>
									<c:if test="${dtplan.getDay3_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay3_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay3_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay3_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay3_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay3_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay3_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay3_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay3_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay3_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay3_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay3_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay4_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title'>
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-4))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay4_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle">${dtplan.getDay4_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay4_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
									</div>
									<c:if test="${dtplan.getDay4_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay4_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay4_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay4_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay4_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay4_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay4_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay4_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay4_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay4_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay4_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay4_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay5_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title'>
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-5))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay5_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle">${dtplan.getDay5_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay5_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
									</div>
									<c:if test="${dtplan.getDay5_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay5_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay5_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay5_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay5_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay5_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay5_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay5_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay5_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay5_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay5_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay5_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay6_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title'>
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-6))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay6_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle">${dtplan.getDay6_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay6_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
									</div>
									<c:if test="${dtplan.getDay6_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay6_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay6_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay6_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay6_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay6_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay6_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay6_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay6_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay6_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay6_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay6_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay7_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title'>
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-7))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay7_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle">${dtplan.getDay7_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay7_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
									</div>
									<c:if test="${dtplan.getDay7_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay7_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay7_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay7_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay7_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay7_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay7_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay7_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay7_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay7_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle">${dtplan.getDay7_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay7_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
										</div>
									</c:if>
								</div>
							</c:if>
						</div>
					</div>
					<div id="plan-btn">
						<input type="button" value="뒤로"> 
						<a href="plandelete.mk?mid=${dtplan.getMid() }"><input type="button" value="삭제"></a> 
						<a href="planupdateview.mk?mid=${dtplan.getMid() }"><input type="button" value="수정"></a>
					</div>
				</div>
				<script type="text/javascript">
					var container = document.getElementById('pl-mapbox'); //지도를 담을 영역의 DOM 레퍼런스
					
					var options = { //지도를 생성할 때 필요한 기본 옵션
						center : new kakao.maps.LatLng(37.57759076 , 126.9768945), //지도의 중심좌표.
						level : 8
					//지도의 레벨(확대, 축소 정도)
					};
					var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
					
					var imageSrcday1 = '../images/marker1.png',
					imageSize = new kakao.maps.Size(32, 42), // 마커이미지의 크기입니다
				    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
					var imageSrcday2 = '../images/marker5.png',
					imageSize = new kakao.maps.Size(32, 42), // 마커이미지의 크기입니다
				    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
					var imageSrcday3 = '../images/marker4.png',
					imageSize = new kakao.maps.Size(32, 42), // 마커이미지의 크기입니다
				    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
					var imageSrcday4 = '../images/marker3.png',
					imageSize = new kakao.maps.Size(32, 42), // 마커이미지의 크기입니다
				    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
					var imageSrcday5 = '../images/marker2.png',
					imageSize = new kakao.maps.Size(32, 42), // 마커이미지의 크기입니다
				    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
					var imageSrcday6 = '../images/marker6.png',
					imageSize = new kakao.maps.Size(32, 42), // 마커이미지의 크기입니다
				    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
					var imageSrcday7 = '../images/marker7.png',
					imageSize = new kakao.maps.Size(32, 42), // 마커이미지의 크기입니다
				    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		    
				
				    
				    var markerImageday1 = new kakao.maps.MarkerImage(imageSrcday1, imageSize, imageOption)
				    var markerImageday2 = new kakao.maps.MarkerImage(imageSrcday2, imageSize, imageOption)
				    var markerImageday3 = new kakao.maps.MarkerImage(imageSrcday3, imageSize, imageOption)
				    var markerImageday4 = new kakao.maps.MarkerImage(imageSrcday4, imageSize, imageOption)
				    var markerImageday5 = new kakao.maps.MarkerImage(imageSrcday5, imageSize, imageOption)
				    var markerImageday6 = new kakao.maps.MarkerImage(imageSrcday6, imageSize, imageOption)
				    var markerImageday7 = new kakao.maps.MarkerImage(imageSrcday7, imageSize, imageOption)
					
					var markerPositionday1_0  = new kakao.maps.LatLng(${dtplan.getDay1_0_latitude()} , ${dtplan.getDay1_0_longitude()}); 
 
					// 마커를 생성합니다
					var markerday1_0 = new kakao.maps.Marker({
					    position: markerPositionday1_0,
					    image: markerImageday1,
					    clickable: true
					});
					
					var iwContentday1_0 = '<div style="padding:5px;">${dtplan.getDay1_0_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_0_latitude())+0.003} , ${dtplan.getDay1_0_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday1_0 = new kakao.maps.InfoWindow({
				        content : iwContentday1_0,
				    	removable : iwRemoveable
					});
				
				    markerday1_0.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday1_0, 'click', function() {
						  closeinfo();
					      infowindowday1_0.open(map, markerday1_0);  
			        });	
					
					<c:if test="${dtplan.getDay1_1() !=null}">
					var markerPositionday1_1  = new kakao.maps.LatLng(${dtplan.getDay1_1_latitude()} , ${dtplan.getDay1_1_longitude()}); 
 
					// 마커를 생성합니다
					var markerday1_1 = new kakao.maps.Marker({
					    position: markerPositionday1_1,
					    image: markerImageday1,
					    clickable: true
					});
					
					var iwContentday1_1 = '<div style="padding:5px;">${dtplan.getDay1_1_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_1_latitude())+0.003} , ${dtplan.getDay1_1_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday1_1 = new kakao.maps.InfoWindow({
				        content : iwContentday1_1,
				    	removable : iwRemoveable
					});
				
				    markerday1_1.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday1_1, 'click', function() {
						closeinfo();
					      infowindowday1_1.open(map, markerday1_1);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay1_2() !=null}">
					var markerPositionday1_2  = new kakao.maps.LatLng(${dtplan.getDay1_2_latitude()} , ${dtplan.getDay1_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday1_2 = new kakao.maps.Marker({
					    position: markerPositionday1_2,
					    image: markerImageday1,
					    clickable: true
					});
					
					var iwContentday1_2 = '<div style="padding:5px;">${dtplan.getDay1_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_2_latitude())+0.003} , ${dtplan.getDay1_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday1_2 = new kakao.maps.InfoWindow({
				        content : iwContentday1_2,
				    	removable : iwRemoveable
					});
				
				    markerday1_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday1_2, 'click', function() {
						closeinfo();
					      infowindowday1_2.open(map, markerday1_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay1_3() !=null}">
					var markerPositionday1_3  = new kakao.maps.LatLng(${dtplan.getDay1_3_latitude()} , ${dtplan.getDay1_3_longitude()}); 
 
					// 마커를 생성합니다
					var markerday1_3 = new kakao.maps.Marker({
					    position: markerPositionday1_3,
					    image: markerImageday1,
					    clickable: true
					});
					
					var iwContentday1_3 = '<div style="padding:5px;">${dtplan.getDay1_3_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_3_latitude())+0.003} , ${dtplan.getDay1_3_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday1_3 = new kakao.maps.InfoWindow({
				        content : iwContentday1_3,
				    	removable : iwRemoveable
					});
				
				    markerday1_3.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday1_3, 'click', function() {
						closeinfo();
					      infowindowday1_3.open(map, markerday1_3);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay2_0() !=null}">
					var markerPositionday2_0  = new kakao.maps.LatLng(${dtplan.getDay2_0_latitude()} , ${dtplan.getDay2_0_longitude()}); 
 
					// 마커를 생성합니다
					var markerday2_0 = new kakao.maps.Marker({
					    position: markerPositionday2_0,
					    image: markerImageday2,
					    clickable: true
					});
					
					var iwContentday2_0 = '<div style="padding:5px;">${dtplan.getDay2_0_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_0_latitude())+0.003} , ${dtplan.getDay2_0_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday2_0 = new kakao.maps.InfoWindow({
				        content : iwContentday2_0,
				    	removable : iwRemoveable
					});
				
				    markerday2_0.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday2_0, 'click', function() {
					      closeinfo();
					      infowindowday2_0.open(map, markerday2_0);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay2_1() !=null}">
					var markerPositionday2_1  = new kakao.maps.LatLng(${dtplan.getDay2_1_latitude()} , ${dtplan.getDay2_1_longitude()}); 
 
					// 마커를 생성합니다
					var markerday2_1 = new kakao.maps.Marker({
					    position: markerPositionday2_1,
					    image: markerImageday2,
					    clickable: true
					});
					
					var iwContentday2_1 = '<div style="padding:5px;">${dtplan.getDay2_1_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_1_latitude())+0.003} , ${dtplan.getDay2_1_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday2_1 = new kakao.maps.InfoWindow({
				        content : iwContentday2_1,
				    	removable : iwRemoveable
					});
				
				    markerday2_1.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday2_1, 'click', function() {
					      closeinfo();
					      infowindowday2_1.open(map, markerday2_1);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay2_2() !=null}">
					var markerPositionday2_2  = new kakao.maps.LatLng(${dtplan.getDay2_2_latitude()} , ${dtplan.getDay2_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday2_2 = new kakao.maps.Marker({
					    position: markerPositionday2_2,
					    image: markerImageday2,
					    clickable: true
					});
					
					var iwContentday2_2 = '<div style="padding:5px;">${dtplan.getDay2_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_2_latitude())+0.003} , ${dtplan.getDay2_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday2_2 = new kakao.maps.InfoWindow({
				        content : iwContentday2_2,
				    	removable : iwRemoveable
					});
				
				    markerday2_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday2_2, 'click', function() {
					      closeinfo();
					      infowindowday2_2.open(map, markerday2_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay2_3() !=null}">
					var markerPositionday2_3  = new kakao.maps.LatLng(${dtplan.getDay2_3_latitude()} , ${dtplan.getDay2_3_longitude()}); 
 
					// 마커를 생성합니다
					var markerday2_3 = new kakao.maps.Marker({
					    position: markerPositionday2_3,
					    image: markerImageday2,
					    clickable: true
					});
					
					var iwContentday2_3 = '<div style="padding:5px;">${dtplan.getDay2_3_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_3_latitude())+0.003} , ${dtplan.getDay2_3_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday2_3 = new kakao.maps.InfoWindow({
				        content : iwContentday2_3,
				    	removable : iwRemoveable
					});
				
				    markerday2_3.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday2_3, 'click', function() {
					      closeinfo();
					      infowindowday2_3.open(map, markerday2_3);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay3_0() !=null}">
					var markerPositionday3_0  = new kakao.maps.LatLng(${dtplan.getDay3_0_latitude()} , ${dtplan.getDay3_0_longitude()}); 
 
					// 마커를 생성합니다
					var markerday3_0 = new kakao.maps.Marker({
					    position: markerPositionday3_0,
					    image: markerImageday3,
					    clickable: true
					});
					
					var iwContentday3_0 = '<div style="padding:5px;">${dtplan.getDay3_0_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_0_latitude())+0.003} , ${dtplan.getDay3_0_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday3_0 = new kakao.maps.InfoWindow({
				        content : iwContentday3_0,
				    	removable : iwRemoveable
					});
				
				    markerday3_0.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday3_0, 'click', function() {
					      closeinfo();
					      infowindowday3_0.open(map, markerday3_0);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay3_1() !=null}">
					var markerPositionday3_1  = new kakao.maps.LatLng(${dtplan.getDay3_1_latitude()} , ${dtplan.getDay3_1_longitude()}); 
 
					// 마커를 생성합니다
					var markerday3_1 = new kakao.maps.Marker({
					    position: markerPositionday3_1,
					    image: markerImageday3,
					    clickable: true
					});
					
					var iwContentday3_1 = '<div style="padding:5px;">${dtplan.getDay3_1_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_1_latitude())+0.003} , ${dtplan.getDay3_1_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday3_1 = new kakao.maps.InfoWindow({
				        content : iwContentday3_1,
				    	removable : iwRemoveable
					});
				
				    markerday3_1.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday3_1, 'click', function() {
					      closeinfo();
					      infowindowday3_1.open(map, markerday3_1);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay3_2() !=null}">
					var markerPositionday3_2  = new kakao.maps.LatLng(${dtplan.getDay3_2_latitude()} , ${dtplan.getDay3_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday3_2 = new kakao.maps.Marker({
					    position: markerPositionday3_2,
					    image: markerImageday3,
					    clickable: true
					});
					
					var iwContentday3_2 = '<div style="padding:5px;">${dtplan.getDay3_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_2_latitude())+0.003} , ${dtplan.getDay3_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday3_2 = new kakao.maps.InfoWindow({
				        content : iwContentday3_2,
				    	removable : iwRemoveable
					});
				
				    markerday3_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday3_2, 'click', function() {
					      closeinfo();
					      infowindowday3_2.open(map, markerday3_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay3_3() !=null}">
					var markerPositionday3_3  = new kakao.maps.LatLng(${dtplan.getDay3_3_latitude()} , ${dtplan.getDay3_3_longitude()}); 
 
					// 마커를 생성합니다
					var markerday3_3 = new kakao.maps.Marker({
					    position: markerPositionday3_3,
					    image: markerImageday3,
					    clickable: true
					});
					
					var iwContentday3_3 = '<div style="padding:5px;">${dtplan.getDay3_3_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_3_latitude())+0.003} , ${dtplan.getDay3_3_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday3_3 = new kakao.maps.InfoWindow({
				        content : iwContentday3_3,
				    	removable : iwRemoveable
					});
				
				    markerday3_3.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday3_3, 'click', function() {
					      closeinfo();
					      infowindowday3_3.open(map, markerday3_3);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay4_0() !=null}">
					var markerPositionday4_0  = new kakao.maps.LatLng(${dtplan.getDay4_0_latitude()} , ${dtplan.getDay4_0_longitude()}); 
 
					// 마커를 생성합니다
					var markerday4_0 = new kakao.maps.Marker({
					    position: markerPositionday4_0,
					    image: markerImageday4,
					    clickable: true
					});
					
					var iwContentday4_0 = '<div style="padding:5px;">${dtplan.getDay4_0_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_0_latitude())+0.003} , ${dtplan.getDay4_0_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday4_0 = new kakao.maps.InfoWindow({
				        content : iwContentday4_0,
				    	removable : iwRemoveable
					});
				
				    markerday4_0.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday4_0, 'click', function() {
					      closeinfo();
					      infowindowday4_0.open(map, markerday4_0);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay4_1() !=null}">
					var markerPositionday4_1  = new kakao.maps.LatLng(${dtplan.getDay4_1_latitude()} , ${dtplan.getDay4_1_longitude()}); 
 
					// 마커를 생성합니다
					var markerday4_1 = new kakao.maps.Marker({
					    position: markerPositionday4_1,
					    image: markerImageday4,
					    clickable: true
					});
					
					var iwContentday4_1 = '<div style="padding:5px;">${dtplan.getDay4_1_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_1_latitude())+0.003} , ${dtplan.getDay4_1_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday4_1 = new kakao.maps.InfoWindow({
				        content : iwContentday4_1,
				    	removable : iwRemoveable
					});
				
				    markerday4_1.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday4_1, 'click', function() {
					      closeinfo();
					      infowindowday4_1.open(map, markerday4_1);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay4_2() !=null}">
					var markerPositionday4_2  = new kakao.maps.LatLng(${dtplan.getDay4_2_latitude()} , ${dtplan.getDay4_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday4_2 = new kakao.maps.Marker({
					    position: markerPositionday4_2,
					    image: markerImageday4,
					    clickable: true
					});
					
					var iwContentday4_2 = '<div style="padding:5px;">${dtplan.getDay4_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_2_latitude())+0.003} , ${dtplan.getDay4_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday4_2 = new kakao.maps.InfoWindow({
				        content : iwContentday4_2,
				    	removable : iwRemoveable
					});
				
				    markerday4_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday4_2, 'click', function() {
					      closeinfo();
					      infowindowday4_2.open(map, markerday4_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay4_3() !=null}">
					var markerPositionday4_3  = new kakao.maps.LatLng(${dtplan.getDay4_3_latitude()} , ${dtplan.getDay4_3_longitude()}); 
 
					// 마커를 생성합니다
					var markerday4_3 = new kakao.maps.Marker({
					    position: markerPositionday4_3,
					    image: markerImageday4,
					    clickable: true
					});
					
					var iwContentday4_3 = '<div style="padding:5px;">${dtplan.getDay4_3_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_3_latitude())+0.003} , ${dtplan.getDay4_3_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday4_3 = new kakao.maps.InfoWindow({
				        content : iwContentday4_3,
				    	removable : iwRemoveable
					});
				
				    markerday4_3.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday4_3, 'click', function() {
					      closeinfo();
					      infowindowday4_3.open(map, markerday4_3);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay5_0() !=null}">
					var markerPositionday5_0  = new kakao.maps.LatLng(${dtplan.getDay5_0_latitude()} , ${dtplan.getDay5_0_longitude()}); 
 
					// 마커를 생성합니다
					var markerday5_0 = new kakao.maps.Marker({
					    position: markerPositionday5_0,
					    image: markerImageday5,
					    clickable: true
					});
					
					var iwContentday5_0 = '<div style="padding:5px;">${dtplan.getDay5_0_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_0_latitude())+0.003} , ${dtplan.getDay5_0_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday5_0 = new kakao.maps.InfoWindow({
				        content : iwContentday5_0,
				    	removable : iwRemoveable
					});
				
				    markerday5_0.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday5_0, 'click', function() {
					      closeinfo();
					      infowindowday5_0.open(map, markerday5_0);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay5_1() !=null}">
					var markerPositionday5_1  = new kakao.maps.LatLng(${dtplan.getDay5_1_latitude()} , ${dtplan.getDay5_1_longitude()}); 
 
					// 마커를 생성합니다
					var markerday5_1 = new kakao.maps.Marker({
					    position: markerPositionday5_1,
					    image: markerImageday5,
					    clickable: true
					});
					
					var iwContentday5_1 = '<div style="padding:5px;">${dtplan.getDay5_1_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_1_latitude())+0.003} , ${dtplan.getDay5_1_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday5_1 = new kakao.maps.InfoWindow({
				        content : iwContentday5_1,
				    	removable : iwRemoveable
					});
				
				    markerday5_1.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday5_1, 'click', function() {
					      closeinfo();
					      infowindowday5_1.open(map, markerday5_1);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay5_2() !=null}">
					var markerPositionday5_2  = new kakao.maps.LatLng(${dtplan.getDay5_2_latitude()} , ${dtplan.getDay5_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday5_2 = new kakao.maps.Marker({
					    position: markerPositionday5_2,
					    image: markerImageday5,
					    clickable: true
					});
					
					var iwContentday5_2 = '<div style="padding:5px;">${dtplan.getDay5_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_2_latitude())+0.003} , ${dtplan.getDay5_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday5_2 = new kakao.maps.InfoWindow({
				        content : iwContentday5_2,
				    	removable : iwRemoveable
					});
				
				    markerday5_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday5_2, 'click', function() {
					      closeinfo();
					      infowindowday5_2.open(map, markerday5_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay5_3() !=null}">
					var markerPositionday5_3  = new kakao.maps.LatLng(${dtplan.getDay5_3_latitude()} , ${dtplan.getDay5_3_longitude()}); 
 
					// 마커를 생성합니다
					var markerday5_3 = new kakao.maps.Marker({
					    position: markerPositionday5_3,
					    image: markerImageday5,
					    clickable: true
					});
					
					var iwContentday5_3 = '<div style="padding:5px;">${dtplan.getDay5_3_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_3_latitude())+0.003} , ${dtplan.getDay5_3_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday5_3 = new kakao.maps.InfoWindow({
				        content : iwContentday5_3,
				    	removable : iwRemoveable
					});
				
				    markerday5_3.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday5_3, 'click', function() {
					      closeinfo();
					      infowindowday5_3.open(map, markerday5_3);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay6_0() !=null}">
					var markerPositionday6_0  = new kakao.maps.LatLng(${dtplan.getDay6_0_latitude()} , ${dtplan.getDay6_0_longitude()}); 
 
					// 마커를 생성합니다
					var markerday6_0 = new kakao.maps.Marker({
					    position: markerPositionday6_0,
					    image: markerImageday6,
					    clickable: true
					});
					
					var iwContentday6_0 = '<div style="padding:5px;">${dtplan.getDay6_0_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_0_latitude())+0.003} , ${dtplan.getDay6_0_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday6_0 = new kakao.maps.InfoWindow({
				        content : iwContentday6_0,
				    	removable : iwRemoveable
					});
				
				    markerday6_0.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday6_0, 'click', function() {
					      closeinfo();
					      infowindowday6_0.open(map, markerday6_0);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay6_1() !=null}">
					var markerPositionday6_1  = new kakao.maps.LatLng(${dtplan.getDay6_1_latitude()} , ${dtplan.getDay6_1_longitude()}); 
 
					// 마커를 생성합니다
					var markerday6_1 = new kakao.maps.Marker({
					    position: markerPositionday6_1,
					    image: markerImageday6,
					    clickable: true
					});
					
					var iwContentday6_1 = '<div style="padding:5px;">${dtplan.getDay6_1_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_1_latitude())+0.003} , ${dtplan.getDay6_1_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday6_1 = new kakao.maps.InfoWindow({
				        content : iwContentday6_1,
				    	removable : iwRemoveable
					});
				
				    markerday6_1.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday6_1, 'click', function() {
					      closeinfo();
					      infowindowday6_1.open(map, markerday6_1);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay6_2() !=null}">
					var markerPositionday6_2  = new kakao.maps.LatLng(${dtplan.getDay6_2_latitude()} , ${dtplan.getDay6_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday6_2 = new kakao.maps.Marker({
					    position: markerPositionday6_2,
					    image: markerImageday6,
					    clickable: true
					});
					
					var iwContentday6_2 = '<div style="padding:5px;">${dtplan.getDay6_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_2_latitude())+0.003} , ${dtplan.getDay6_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday6_2 = new kakao.maps.InfoWindow({
				        content : iwContentday6_2,
				    	removable : iwRemoveable
					});
				
				    markerday6_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday6_2, 'click', function() {
					      closeinfo();
					      infowindowday6_2.open(map, markerday6_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay6_3() !=null}">
					var markerPositionday6_3  = new kakao.maps.LatLng(${dtplan.getDay6_3_latitude()} , ${dtplan.getDay6_3_longitude()}); 
 
					// 마커를 생성합니다
					var markerday6_3 = new kakao.maps.Marker({
					    position: markerPositionday6_3,
					    image: markerImageday6,
					    clickable: true
					});
					
					var iwContentday6_3 = '<div style="padding:5px;">${dtplan.getDay6_3_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_3_latitude())+0.003} , ${dtplan.getDay6_3_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday6_3 = new kakao.maps.InfoWindow({
				        content : iwContentday6_3,
				    	removable : iwRemoveable
					});
				
				    markerday6_3.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday6_3, 'click', function() {
					      closeinfo();
					      infowindowday6_3.open(map, markerday6_3);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay7_0() !=null}">
					var markerPositionday7_0  = new kakao.maps.LatLng(${dtplan.getDay7_0_latitude()} , ${dtplan.getDay7_0_longitude()}); 
 
					// 마커를 생성합니다
					var markerday7_0 = new kakao.maps.Marker({
					    position: markerPositionday7_0,
					    image: markerImageday7,
					    clickable: true
					});
					
					var iwContentday7_0 = '<div style="padding:5px;">${dtplan.getDay7_0_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_0_latitude())+0.003} , ${dtplan.getDay7_0_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday7_0 = new kakao.maps.InfoWindow({
				        content : iwContentday7_0,
				    	removable : iwRemoveable
					});
				
				    markerday7_0.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday7_0, 'click', function() {
					      closeinfo();
					      infowindowday7_0.open(map, markerday7_0);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay7_1() !=null}">
					var markerPositionday7_1  = new kakao.maps.LatLng(${dtplan.getDay7_1_latitude()} , ${dtplan.getDay7_1_longitude()}); 
 
					// 마커를 생성합니다
					var markerday7_1 = new kakao.maps.Marker({
					    position: markerPositionday7_1,
					    image: markerImageday7,
					    clickable: true
					});
					
					var iwContentday7_1 = '<div style="padding:5px;">${dtplan.getDay7_1_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_1_latitude())+0.003} , ${dtplan.getDay7_1_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday7_1 = new kakao.maps.InfoWindow({
				        content : iwContentday7_1,
				    	removable : iwRemoveable
					});
				
				    markerday7_1.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday7_1, 'click', function() {
					      closeinfo();
					      infowindowday7_1.open(map, markerday7_1);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay7_2() !=null}">
					var markerPositionday7_2  = new kakao.maps.LatLng(${dtplan.getDay7_2_latitude()} , ${dtplan.getDay7_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday7_2 = new kakao.maps.Marker({
					    position: markerPositionday7_2,
					    image: markerImageday7,
					    clickable: true
					});
					
					var iwContentday7_2 = '<div style="padding:5px;">${dtplan.getDay7_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_2_latitude())+0.003} , ${dtplan.getDay7_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday7_2 = new kakao.maps.InfoWindow({
				        content : iwContentday7_2,
				    	removable : iwRemoveable
					});
				
				    markerday7_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday7_2, 'click', function() {
					      closeinfo();
					      infowindowday7_2.open(map, markerday7_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay7_2() !=null}">
					var markerPositionday7_2  = new kakao.maps.LatLng(${dtplan.getDay7_2_latitude()} , ${dtplan.getDay7_2_longitude()}); 
 
					// 마커를 생성합니다
					var markerday7_2 = new kakao.maps.Marker({
					    position: markerPositionday7_2,
					    image: markerImageday7,
					    clickable: true
					});
					
					var iwContentday7_2 = '<div style="padding:5px;">${dtplan.getDay7_2_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_2_latitude())+0.003} , ${dtplan.getDay7_2_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday7_2 = new kakao.maps.InfoWindow({
				        content : iwContentday7_2,
				    	removable : iwRemoveable
					});
				
				    markerday7_2.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday7_2, 'click', function() {
					      closeinfo();
					      infowindowday7_2.open(map, markerday7_2);  
			        });	
					</c:if>
					
					<c:if test="${dtplan.getDay7_3() !=null}">
					var markerPositionday7_3  = new kakao.maps.LatLng(${dtplan.getDay7_3_latitude()} , ${dtplan.getDay7_3_longitude()}); 
 
					// 마커를 생성합니다
					var markerday7_3 = new kakao.maps.Marker({
					    position: markerPositionday7_3,
					    image: markerImageday7,
					    clickable: true
					});
					
					var iwContentday7_3 = '<div style="padding:5px;">${dtplan.getDay7_3_name()}</div>', 
				    iwPosition = new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_3_latitude())+0.003} , ${dtplan.getDay7_3_longitude()}),
				    iwRemoveable = true; 

				   
				    var infowindowday7_3 = new kakao.maps.InfoWindow({
				        content : iwContentday7_3,
				    	removable : iwRemoveable
					});
				
				    markerday7_3.setMap(map); 
					
					
					kakao.maps.event.addListener(markerday7_3, 'click', function() {
					      closeinfo();
					      infowindowday7_3.open(map, markerday7_3);  
			        });
					</c:if>
					
					 function closeinfo() {
						 <c:if test="${dtplan.getDay1_0() !=null}">
				    	  infowindowday1_0.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay1_1() !=null}">
				    	  infowindowday1_1.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay1_2() !=null}">
				    	  infowindowday1_2.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay1_3() !=null}">
				    	  infowindowday1_3.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay2_0() !=null}">
				    	  infowindowday2_0.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay2_1() !=null}">
				    	  infowindowday2_1.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay2_2() !=null}">
				    	  infowindowday2_2.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay2_3() !=null}">
				    	  infowindowday2_3.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay3_0() !=null}">
				    	  infowindowday3_0.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay3_1() !=null}">
				    	  infowindowday3_1.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay3_2() !=null}">
				    	  infowindowday3_2.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay3_3() !=null}">
				    	  infowindowday3_3.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay4_0() !=null}">
				    	  infowindowday4_0.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay4_1() !=null}">
				    	  infowindowday4_1.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay4_2() !=null}">
				    	  infowindowday4_2.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay4_3() !=null}">
				    	  infowindowday4_3.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay5_0() !=null}">
				    	  infowindowday5_0.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay5_1() !=null}">
				    	  infowindowday5_1.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay5_2() !=null}">
				    	  infowindowday5_2.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay5_3() !=null}">
				    	  infowindowday5_3.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay6_0() !=null}">
				    	  infowindowday6_0.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay6_1() !=null}">
				    	  infowindowday6_1.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay6_2() !=null}">
				    	  infowindowday6_2.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay6_3() !=null}">
				    	  infowindowday6_3.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay7_0() !=null}">
				    	  infowindowday7_0.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay7_1() !=null}">
				    	  infowindowday7_1.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay7_2() !=null}">
				    	  infowindowday7_2.close(); 
				    	  </c:if>
						 <c:if test="${dtplan.getDay7_3() !=null}">
				    	  infowindowday7_3.close(); 
				    	  </c:if>
					}
					 <c:if test="${dtplan.getDay1_0() !=null}">
					var linePathday1 = [
						 <c:if test="${dtplan.getDay1_0() !=null}">
						    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_0_latitude())+0.008}, ${Float.valueOf(dtplan.getDay1_0_longitude())-0.004}),
						    </c:if>
						 <c:if test="${dtplan.getDay1_1() !=null}">
						    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_1_latitude())+0.008}, ${Float.valueOf(dtplan.getDay1_1_longitude())-0.004}),
						    </c:if>
						 <c:if test="${dtplan.getDay1_2() !=null}">
						    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_2_latitude())+0.008}, ${Float.valueOf(dtplan.getDay1_2_longitude())-0.004}),
						    </c:if>
						 <c:if test="${dtplan.getDay1_3() !=null}">
						    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay1_3_latitude())+0.008}, ${Float.valueOf(dtplan.getDay1_3_longitude())-0.004}),
						    </c:if>
						];

						// 지도에 표시할 선을 생성합니다
					
					var polylineday1 = new kakao.maps.Polyline({
						    path: linePathday1, // 선을 구성하는 좌표배열 입니다
						    strokeWeight: 5, // 선의 두께 입니다
						    strokeColor: '#D23F36', // 선의 색깔입니다
						    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
						    strokeStyle: 'solid' // 선의 스타일입니다
					});
						
					polylineday1.setMap(map);
					</c:if>
					
				
					<c:if test="${dtplan.getDay2_0() !=null}">
					var linePathday2 = [
						<c:if test="${dtplan.getDay2_0() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_0_latitude())+0.008}, ${Float.valueOf(dtplan.getDay2_0_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay2_1() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_1_latitude())+0.008}, ${Float.valueOf(dtplan.getDay2_1_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay2_2() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_2_latitude())+0.008}, ${Float.valueOf(dtplan.getDay2_2_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay2_3() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay2_3_latitude())+0.008}, ${Float.valueOf(dtplan.getDay2_3_longitude())-0.004}),
					    </c:if>

					];

					// 지도에 표시할 선을 생성합니다
					
					var polylineday2 = new kakao.maps.Polyline({
					    path: linePathday2, // 선을 구성하는 좌표배열 입니다
					    strokeWeight: 5, // 선의 두께 입니다
					    strokeColor: '#238CFA', // 선의 색깔입니다
					    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
					    strokeStyle: 'solid' // 선의 스타일입니다
					});
					
					polylineday2.setMap(map);  
					
					</c:if>

					<c:if test="${dtplan.getDay3_0() !=null}">
					var linePathday3 = [
						
				
						<c:if test="${dtplan.getDay3_0() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_0_latitude())+0.008}, ${Float.valueOf(dtplan.getDay3_0_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay3_1() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_1_latitude())+0.008}, ${Float.valueOf(dtplan.getDay3_1_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay3_2() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_2_latitude())+0.008}, ${Float.valueOf(dtplan.getDay3_2_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay3_3() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay3_3_latitude())+0.008}, ${Float.valueOf(dtplan.getDay3_3_longitude())-0.004}),
					    </c:if>
					];

					// 지도에 표시할 선을 생성합니다
					
					var polylineday3 = new kakao.maps.Polyline({
					    path: linePathday3, // 선을 구성하는 좌표배열 입니다
					    strokeWeight: 5, // 선의 두께 입니다
					    strokeColor: '#3E9F62', // 선의 색깔입니다
					    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
					    strokeStyle: 'solid' // 선의 스타일입니다
					});
					
					polylineday3.setMap(map);  
					</c:if>
					
					<c:if test="${dtplan.getDay4_0() !=null}">
					var linePathday4 = [
						<c:if test="${dtplan.getDay4_0() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_0_latitude())+0.008}, ${Float.valueOf(dtplan.getDay4_0_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay4_1() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_1_latitude())+0.008}, ${Float.valueOf(dtplan.getDay4_1_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay4_2() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_2_latitude())+0.008}, ${Float.valueOf(dtplan.getDay4_2_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay4_3() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay4_3_latitude())+0.008}, ${Float.valueOf(dtplan.getDay4_3_longitude())-0.004}),
					    </c:if>
					];

					// 지도에 표시할 선을 생성합니다
					
				
					var polylineday4 = new kakao.maps.Polyline({
					    path: linePathday4, // 선을 구성하는 좌표배열 입니다
					    strokeWeight: 5, // 선의 두께 입니다
					    strokeColor: '#E6F000', // 선의 색깔입니다
					    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
					    strokeStyle: 'solid' // 선의 스타일입니다
					});
					
					polylineday4.setMap(map);
					
					</c:if>

					<c:if test="${dtplan.getDay5_0() !=null}">
					var linePathday5 = [
						<c:if test="${dtplan.getDay5_0() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_0_latitude())+0.008}, ${Float.valueOf(dtplan.getDay5_0_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay5_1() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_1_latitude())+0.008}, ${Float.valueOf(dtplan.getDay5_1_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay5_2() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_2_latitude())+0.008}, ${Float.valueOf(dtplan.getDay5_2_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay5_3() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay5_3_latitude())+0.008}, ${Float.valueOf(dtplan.getDay5_3_longitude())-0.004}),
					    </c:if>
					];

					// 지도에 표시할 선을 생성합니다
					
					
					var polylineday5 = new kakao.maps.Polyline({
					    path: linePathday5, // 선을 구성하는 좌표배열 입니다
					    strokeWeight: 5, // 선의 두께 입니다
					    strokeColor: '#F06400',
					    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
					    strokeStyle: 'solid' // 선의 스타일입니다
					});
					
					polylineday5.setMap(map);  
					
					</c:if>

					<c:if test="${dtplan.getDay6_0() !=null}">
					var linePathday6 = [
						<c:if test="${dtplan.getDay6_0() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_0_latitude())+0.008}, ${Float.valueOf(dtplan.getDay6_0_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay6_1() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_1_latitude())+0.008}, ${Float.valueOf(dtplan.getDay6_1_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay6_2() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_2_latitude())+0.008}, ${Float.valueOf(dtplan.getDay6_2_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay6_3() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay6_3_latitude())+0.008}, ${Float.valueOf(dtplan.getDay6_3_longitude())-0.004}),
					    </c:if>
					];

					// 지도에 표시할 선을 생성합니다
					
					
					var polylineday6 = new kakao.maps.Polyline({
					    path: linePathday6, // 선을 구성하는 좌표배열 입니다
					    strokeWeight: 5, // 선의 두께 입니다
					    strokeColor: '#6D00F9', // 선의 색깔입니다
					    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
					    strokeStyle: 'solid' // 선의 스타일입니다
					});
					
					polylineday6.setMap(map);  
					
					</c:if>
					
					<c:if test="${dtplan.getDay7_0() !=null}">
					var linePathday7 = [
						<c:if test="${dtplan.getDay7_0() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_0_latitude())+0.008}, ${Float.valueOf(dtplan.getDay7_0_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay7_1() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_1_latitude())+0.008}, ${Float.valueOf(dtplan.getDay7_1_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay7_2() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_2_latitude())+0.008}, ${Float.valueOf(dtplan.getDay7_2_longitude())-0.004}),
					    </c:if>
						<c:if test="${dtplan.getDay7_3() !=null}">
					    new kakao.maps.LatLng(${Float.valueOf(dtplan.getDay7_3_latitude())+0.008}, ${Float.valueOf(dtplan.getDay7_3_longitude())-0.004}),
					    </c:if>
					];

					// 지도에 표시할 선을 생성합니다
					
					var polylineday7 = new kakao.maps.Polyline({
					    path: linePathday7, // 선을 구성하는 좌표배열 입니다
					    strokeWeight: 5, // 선의 두께 입니다
					    strokeColor: '#E200F7', // 선의 색깔입니다
					    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
					    strokeStyle: 'solid' // 선의 스타일입니다
					});
					
					polylineday7.setMap(map);  
					
					</c:if>

					
					
		
					
				</script>
			</div>
		</section>
	</div>
	<%@ include file="../footer.jsp"%>
</body>
</html>