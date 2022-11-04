<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="page-header">
	<div class="page-header-box" id="#top">
		<div class="top-logo">
			<a href="./" target="_self"> <img
				id="top-logo" src="images/logo.png" alt="IN서울">
			</a>
		</div>
		<div class="joinbox">
									
													 
			<c:if test="${uid == null}">
				<button id='btLogIn'>로그인</button>
				<button id='btSignUp'>회원가입</button>
			</c:if>
			<c:if test="${uid != null}">
				<button id='btLogOut'>로그아웃</button>
			</c:if>
		</div>
		<nav class="top-nav">
			<ul>
				<li><a href="travels/locationIndex.tv"> 정보 </a></li>
				<li><a href="plan/markerList.mk"> 여행계획 짜기 </a></li>
				<li><a href="plan/planselect.mk" target="_self"> 커뮤니티 </a></li>
				<li><a href="profile/timeline.pr" target="_self">마이페이지</a></li>
							   
			</ul>
		</nav>
		<nav class="gnb">
			<div class="gnb-box">
				<div class="gnb-items">
					<ul>
						<li><a href="travels/locationIndex.tv" target="_self"> 관광지 정보 </a></li>
						<li><a href="lodgment/lodgment.lm" target="_self"> 숙박 정보 </a></li>
						<li><a href="festival/festivalList.val" target="_self"> 축제 정보 </a></li>
					</ul>
					<ul>
						<li><a href="plan/markerList.mk" target="_self"> 여행계획짜기 </a></li>
					</ul>
					<ul>
						<li><a href="plan/planselect.mk" target="_self"> 여행일정공유 </a></li>
						<li><a href="board/boardList.with" target="_self"> 그룹여행모집 </a></li>
						<li><a href="board/travelsList.tv" target="_self"> 여행기 </a></li>
						<li><a href="board/qnaList.qa" target="_self"> 질답게시판 </a></li>
					</ul>
					<ul>
						<li><a href="profile/timeline.pr" target="_self">프로필</a></li>
						<li><a href="receipt/list.pr" target="_self">예약내역</a></li>
						<li><a href="group/manage.pr" target="_self">그룹관리</a></li>
						<li><a href="info/general.pr" target="_self">회원정보</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
</header>