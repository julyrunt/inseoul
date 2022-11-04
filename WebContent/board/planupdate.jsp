<%@page import="com.inseoul.vo.LocationBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.inseoul.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%ArrayList<LocationBean> marks =  (ArrayList<LocationBean>)request.getAttribute("articleList");
ArrayList<LocationBean> locations =  (ArrayList<LocationBean>)request.getAttribute("location");
int day = ((Integer)request.getAttribute("day")).intValue();



// PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
// int listCount=pageInfo.getListCount();
// int nowPage=pageInfo.getPage();
// int maxPage=pageInfo.getMaxPage();
// int startPage=pageInfo.getStartPage();
// int endPage=pageInfo.getEndPage();
// int day = (Integer)request.getAttribute("day");
// System.out.println(day);

%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%System.out.println(marks); %> --%>
<%-- <%System.out.println(marks); %> --%>
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
<script src="../js/plan-update.js"></script>
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
				<div id="pl-mapbox">
<!-- 					<div id="menu_wrap" class="bg_white"> -->
<!-- 						<div class="option"> -->
<!-- 							<div> -->
<!-- <!-- 								<form onsubmit="searchPlaces(); return false;"> --> -->
<!-- <!-- 									키워드 : <input type="text" value="경복궁" id="keyword" size="15"> --> -->
<!-- <!-- 									<button type="submit">검색하기</button> --> -->
<!-- <!-- 								</form> --> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<hr> -->
<!-- 						<ul id="placesList"> -->
<%-- 							<%for(int x = 0 ; x < marks.size(); x++){ %> --%>
<%-- 							<li class="item"><%="<span class='markerbg marker_"+ (x+1) + "'></span>"%> --%>
<!-- 								<div class="sideinfo"> -->
<%-- 									<h5><%=marks.get(x).getName() %></h5> --%>
<%-- 									<span><%=marks.get(x).getAddr() %></span> <span class="tel"><%=marks.get(x).getTel() %></span> --%>
<!-- 								</div></li> -->
<%-- 							<%} %> --%>
<!-- 						</ul> -->
<!-- 						<div id="pageList"> -->
<%-- 							<% --%>
// 								if (nowPage <= 1) {
<%-- 							%> --%>
<!-- 							《&nbsp; -->
<%-- 							<% --%>
// 								} else {
<%-- 							%> --%>
<%-- 							<a href="markerList.mk?page=<%=nowPage - 1%>">《</a>&nbsp; --%>
<%-- 							<% --%>
// 								}
<%-- 							%> --%>

<%-- 							<% --%>
// 								for (int a = startPage; a <= endPage; a++) {
// 									if (a == nowPage) {
<%-- 							%> --%>
<%-- 							[<%=a%>]&nbsp; --%>
<%-- 							<% --%>
// 								} else {
<%-- 							%> --%>
<%-- 							<a href="markerList.mk?page=<%=a%>">[<%=a%>] --%>
<!-- 							</a>&nbsp; -->
<%-- 							<% --%>
// 								}
// 								}
<%-- 							%> --%>

<%-- 							<% --%>
// 								if (nowPage >= maxPage) {
<%-- 							%> --%>
<!-- 							》 -->
<%-- 							<% --%>
// 								} else {
<%-- 							%> --%>
<%-- 							<a href="markerList.mk?page=<%=nowPage + 1%>">》</a> --%>
<%-- 							<% --%>
// 								}
<%-- 							%> --%>
<!-- 						</div> -->
<!-- 						<div id="pagination"></div> -->
<!-- 					</div> -->
				</div>
				<div id="bottom-box">
				<div id="select-date">
						여행시작 : <input type="date" id="start_date" readonly="readonly"
							value="<fm:formatDate value='${dtplan.getPlan_start()}' pattern='yyyy-MM-dd'/>"
							 />&nbsp;&nbsp;~ &nbsp;&nbsp; 여행종료 : <input type="date" readonly="readonly"
							id="end_date"
							value="<fm:formatDate value='${dtplan.getPlan_end()}' pattern='yyyy-MM-dd'/>"
							 />
						<!-- 				<input type="button" id="add" value="적용하기"> -->
					</div>
					<div id="title-box">
<%-- 					<c:set value="${dtplan.getTitle()}" var="title"/> --%>
						<input type="text" name="plantitle" id="plantitle" value="${dtplan.getTitle()}" style="width: 640px; height: 50px; font-size: 18px; font-weight: bold">
					</div>
					<div id="ovx-box">
						<div id="plan-big-box">
							<c:if test="${dtplan.getDay1_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title'>
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-1))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay1_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle" lid="${dtplan.getDay1_0()}">${dtplan.getDay1_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay1_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
										<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
									</div>
									<c:if test="${dtplan.getDay1_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay1_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay1_1()}">${dtplan.getDay1_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay1_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									
									<c:if test="${dtplan.getDay1_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay1_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay1_2()}">${dtplan.getDay1_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay1_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay1_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay1_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay1_3()}">${dtplan.getDay1_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay1_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay2_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title'>
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-2))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay2_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle" lid="${dtplan.getDay2_0()}">${dtplan.getDay2_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay2_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
										<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
									</div>
									<c:if test="${dtplan.getDay2_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay2_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay2_1()}">${dtplan.getDay2_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay2_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay2_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay2_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay2_2()}">${dtplan.getDay2_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay2_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay2_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay2_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay2_3()}">${dtplan.getDay2_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay2_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${dtplan.getDay3_0() !=null}">
								<div class="plan-box bid">
									<div class='plan-title'>
										<p class='plandivp' style="text-shadow: black 1px 1px 1px;">
											Day<%=(day - (day-3))%></p>
									</div>
									<div class="addinfo">
										<div>
											<img src="../images2/${dtplan.getDay3_0_img() }"
												class="addimg">
										</div>
										<div class="addtitle" lid="${dtplan.getDay3_0()}">${dtplan.getDay3_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay3_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
										<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
									</div>
									<c:if test="${dtplan.getDay3_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay3_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay3_1()}">${dtplan.getDay3_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay3_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay3_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay3_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay3_2()}">${dtplan.getDay3_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay3_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay3_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay3_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay3_3()}">${dtplan.getDay3_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay3_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
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
										<div class="addtitle" lid="${dtplan.getDay4_0()}">${dtplan.getDay4_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay4_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
										<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
									</div>
									<c:if test="${dtplan.getDay4_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay4_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay4_1()}">${dtplan.getDay4_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay4_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay4_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay4_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay4_2()}">${dtplan.getDay4_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay4_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay4_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay4_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay4_3()}">${dtplan.getDay4_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay4_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
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
										<div class="addtitle" lid="${dtplan.getDay5_0()}">${dtplan.getDay5_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay5_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
										<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
									</div>
									<c:if test="${dtplan.getDay5_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay5_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay5_1()}">${dtplan.getDay5_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay5_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay5_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay5_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay5_2()}">${dtplan.getDay5_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay5_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay5_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay5_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay5_3()}">${dtplan.getDay5_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay5_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
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
										<div class="addtitle" lid="${dtplan.getDay6_0()}">${dtplan.getDay6_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay6_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
										<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
									</div>
									<c:if test="${dtplan.getDay6_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay6_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay6_1()}">${dtplan.getDay6_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay6_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay6_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay6_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay6_2()}">${dtplan.getDay6_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay6_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay6_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay6_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay6_3()}">${dtplan.getDay6_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay6_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
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
										<div class="addtitle" lid="${dtplan.getDay7_0()}">${dtplan.getDay7_0_name() }</div>
										<div class="addlink">
											<a href="${dtplan.getDay7_0_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
										</div>
										<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
									</div>
									<c:if test="${dtplan.getDay7_1() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay7_1_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay7_1()}">${dtplan.getDay7_1_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay7_1_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay7_2() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay7_2_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay7_2()}">${dtplan.getDay7_2_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay7_2_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
									<c:if test="${dtplan.getDay7_3() !=null}">
										<div class="addinfo">
											<div>
												<img src="../images2/${dtplan.getDay7_3_img() }"
													class="addimg">
											</div>
											<div class="addtitle" lid="${dtplan.getDay7_3()}">${dtplan.getDay7_3_name() }</div>
											<div class="addlink">
												<a href="${dtplan.getDay7_3_site() }" class="link"><input type="image" src="../images/home-link.svg" style="width:30px; height:35px; float:right; padding:5px;"></a>
											</div>
											<input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'>
										</div>
									</c:if>
								</div>
							</c:if>
						</div>
				</div>
				<div id="plan-btn">
<!-- 				<input type="submit" value="일정등록" id="okbtn"> -->
                <input type="hidden" value="${dtplan.getMid()}" id="mid">
				<input type="button" value="수정하기" id="okbtn">
				</div>
				</div>
				<script type="text/javascript">
					var overlays = [];
					var container = document.getElementById('pl-mapbox'); //지도를 담을 영역의 DOM 레퍼런스
					var options = { //지도를 생성할 때 필요한 기본 옵션
						center : new kakao.maps.LatLng(37.57759076 , 126.9768945), //지도의 중심좌표.
						level : 6
					//지도의 레벨(확대, 축소 정도)
					} 
					var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
					
					var clickedOverlay = null;
					
					<%for(int x = 0 ; x < locations.size() ; x++){ %>
					// 지도에 마커를 표시합니다 
					var marker<%= x %> = new kakao.maps.Marker({
					    map: map, 
					    position: new kakao.maps.LatLng(<%=locations.get(x).getLatitude() %>, <%=locations.get(x).getLongitude() %>)
					});
					
					// 커스텀 오버레이에 표시할 컨텐츠 입니다
					// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
					// 별도의 이벤트 메소드를 제공하지 않습니다 
					var content<%= x %> = 
						        '<div class="wrap">' + 
					            '    <div class="info">' + 
					            '        <div class="title">' + 
					            '            <%=locations.get(x).getName() %>' + 
					            '            <div class="close" onclick="closeOverlay<%= x %>()" title="닫기"></div>' + 
					            '        </div>' + 
					            '        <div class="body">' + 
					            '            <div class="img">' +
					            '                <img src="../images2/<%=locations.get(x).getImg() %>" width="73" height="70">' +
					            '           </div>' + 
					            '            <div class="desc">' + 
					            '                <div class="ellipsis"><%=locations.get(x).getAddr() %></div>' + 
					            '                <div><a href="<%=locations.get(x).getSite() %>" target="_blank" class="link">홈페이지</a></div>' +
					            '            <div class="addbox">' +
					            '   		    <input type="button" value="추가하기" class="adddiv">'+
					            '   		    <input type="hidden" value="<%=locations.get(x).getName() %>" class="lctitle">'+
					            '   		    <input type="hidden" value="<%=locations.get(x).getSite() %>" class="lclink">'+
					            '   		    <input type="hidden" value="<%=locations.get(x).getImg() %>" class="lcimg">'+
					            '   		    <input type="hidden" value="<%=locations.get(x).getLid() %>" class="lclid" id="planlid">'+
					            '            </div>' +
					            '            </div>' + 
					            '        </div>' + 
					            '    </div>' +    
					            '</div>';
					
					// 마커 위에 커스텀오버레이를 표시합니다
					// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
					var overlay<%=x%> = null;
					overlays.push(overlay<%=x%>);
					
					// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
					kakao.maps.event.addListener(marker<%=x%>, 'click', function() {
						if (clickedOverlay) {
					        clickedOverlay.setMap(null);
					    }
						if (overlay<%=x%> == null) {
							overlay<%=x%> = new kakao.maps.CustomOverlay({
							    content: content<%=x%>,
							    map: map,
							    position: marker<%=x%>.getPosition()       
							});
						}
						for (var overlay of overlays) {
							if (overlay != null) {
								overlay.setMap(null);
							}
						}
					    overlay<%=x%>.setMap(map);
					    clickedOverlay = overlay<%=x%>;
					});
					// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
					function closeOverlay<%=x%>() {
						overlay<%=x%>.setMap(null);
					}
					
				<%}%>
				
				$(document).on("click", ".adddiv", function() {
					var title=$(".lctitle").val();
					var img=$(".lcimg").val();
					var link=$(".lclink").val();
					var lid=$(".lclid").val();
					
					var html = "<div class='addinfo'><div><img src='../images2/"+img+"' class='addimg'></div><div class='addtitle' lid='"+lid+"' >"+title+"</div><div class='addlink'><a href="+link+" target='_blank' class='link'><input type='image' src='../images/home-link.svg' style='width:30px; height:35px; float:right; padding:5px;'></a></div><input type='image' src='../images/route-del.svg' style='width:25px; height:35px; float:right; padding:5px; margin : 1px 15px 0 0 ;'><div>";
					
					if ($(".slt .addinfo").length < 4){
						var check = true;
						for (var item of $(".slt .addinfo")) {
							if ($(item).find(".addtitle").html() == title){
								check = false;
							}
						}
						if (check) {
							$(".slt").append($(html));
						}else{
							alert("이미 선택한 관광지 입니다.");
						}
					}else{
						alert("경로추가는 4개까지만 가능합니다");
					}
				});
				
				
				
					
				</script>
			</div>
		</section>
	</div>
	<%@ include file="../footer.jsp"%>
</body>
</html>