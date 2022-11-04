<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.inseoul.vo.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>그룹여행 모집</title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/top-header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/slick.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/slick-theme.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/plan.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board-common.css">

<!-- 자바스크립트 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.bxslider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>


</head>
<body>
	<%
		String uid = (String)session.getAttribute("uid");
			String nick = (String)session.getAttribute("nick");
// 			ArrayList<Routemaps> plan = (ArrayList<Routemaps>)request.getAttribute("planList");
			PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
			int listCount=pageInfo.getListCount();
			int nowPage=pageInfo.getPage();
			int maxPage=pageInfo.getMaxPage();
			int startPage=pageInfo.getStartPage();
			int endPage=pageInfo.getEndPage(); 
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = simpleDateFormat.format(date);
			Date nowDate1 = simpleDateFormat.parse(nowDate);
			ArrayList<Routemaps> lids = (ArrayList<Routemaps>)request.getAttribute("lids");
	%>
	
	<jsp:include page="../header.jsp"/>
	<div class="top-title">
		<img src="${pageContext.request.contextPath}/images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="with-bigbox">
		<section>
		<nav class="with-toplist">
		</nav>
			
			<div class="with-midbox">
				<jsp:include page="board-sidebox.jsp"/>
<!-- 				<div class="with-topsection"> -->
<!-- 					<p>여행계획 공유해요</p>  -->
<!-- 				</div> -->
				
				<div id="with-contentsAll">
				<c:forEach var="x" begin="0" end="${lids.size() -1}">
				<div class="plan-contentsbox">
				<a href="plandetail.mk?mid=${lids.get(x).getMid()}">
<%-- 					<%if (lids.get(x).getPhotos() == null ){ %> --%>
<!-- 					<div class="with-contents-img"><img alt="photos1" src="images/logo2.png"></div> -->
<%-- 					<%}else{ %> --%>
					<div class="plan-contents-img"><img alt="photos" src="../images/${lids.get(x).getImg() }"></div>
<%-- 					<%} %> --%>
					<div class="plan-contents-text">
						<div class="with-contents-lefttext">
						<ul>
						<li class="txt_hide"> <h4>${lids.get(x).getNick() }</h4> <li>
						<li class="tit"> ${lids.get(x).getTitle()} <fm:formatDate value="${lids.get(x).getWriteday()}" pattern="yyyy-MM-dd"/> </li>
						<li class="schedule"><span class="ng-star-inserted">${lids.get(x).getDay1_0_name()} </span> 
						<c:if test="${lids.get(x).getDay1_1() !=null}">
							<span class="ng-star-inserted">${lids.get(x).getDay1_1_name()}</span>
						</c:if>
						<c:if test="${lids.get(x).getDay1_2() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay1_2_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay1_3() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay1_3_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay2_0() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay2_0_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay2_1() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay2_1_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay2_2() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay2_2_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay2_3() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay2_3_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay3_0() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay3_0_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay3_1() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay3_1_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay3_2() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay3_2_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay3_3() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay3_3_name()}</span></c:if>
						<c:if test="${lids.get(x).getDay4_0() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay4_0_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay4_1() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay4_1_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay4_2() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay4_2_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay4_3() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay4_3_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay5_0() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay5_0_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay5_1() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay5_1_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay5_2() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay5_2_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay5_3() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay5_3_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay6_0() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay6_0_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay6_1() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay6_1_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay6_2() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay6_2_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay6_3() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay6_3_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay7_0() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay7_0_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay7_1() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay7_1_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay7_2() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay7_2_name() }</span></c:if>
						<c:if test="${lids.get(x).getDay7_3() !=null}"><span class="ng-star-inserted">${lids.get(x).getDay7_3_name() }</span></c:if>
						</li>
						<li class="date"><fm:formatDate value="${lids.get(x).getPlan_start()}" pattern="yyyy-MM-dd"/> ~ <fm:formatDate value="${lids.get(x).getPlan_end()}" pattern="yyyy-MM-dd"/> </li>
						<li>
							<ul class="ico_wrap">
							<li></li>
							</ul>
						</li>
						</ul>
						</div>
					</a>		
					</div>
<!-- 						<div class="with-contents-info"> -->
<%-- 							<div class="with-contents-writer"><span><%=withBoardBean.get(x).getNick() %>님의 모집글</span></div> --%>
<!-- 							<div class="with-contents-readcount"> -->
<!-- 								<ul> -->
<!-- 									<li><span class="material-icons">visibility</span></li> -->
<%-- 									<li><%=withBoardBean.get(x).getReadcount() %> &nbsp; &nbsp;</li> --%>
<!-- 									<li><span class="material-icons">comment</span></li> -->
<%-- 									<li><%=withBoardBean.get(x).getReplyCount() %></li> --%>
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 						</div> -->
				</div>
				</c:forEach>
				</div>
			</div>
		
		</section>
		<section id="pageList">
			<%if(nowPage<=1){ %>
			[이전]&nbsp;
			<%}else{ %>
			<a href="planselect.mk?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
			<%} %>

			<%for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
			[<%=a %>]&nbsp;
			<%}else{ %>
			<a href="planselect.mk?page=<%=a %>">[<%=a %>]
			</a>&nbsp;
			<%} 
			}%>

			<%if(nowPage>=maxPage){ %>
			[다음]
			<%}else{ %>
			<a href="planselect.mk?page=<%=nowPage+1 %>">[다음]</a>
			<%
			}
			%>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>