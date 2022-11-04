<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
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
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/lodgment-detail.css">
<!-- 자바스크립트 -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/lodgment-detail.js"></script>
<%
	String uid = (String) session.getAttribute("uid");
%>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">숙박 정보</div>
	</div>
	<div id="mainbox1">
		<div id="mainbox1_in1">
			<img src="images/${list.getMain_photo()}">
			<div id="thumbnail_big">
				<img src="images/${list.getPhotos1()}"> <img
					src="images/${list.getPhotos2()}"> <img
					src="images/${list.getPhotos3()}"> <img
					src="images/${list.getPhotos4()}">
			</div>
		</div>
		<div id="mainbox1_in2">
			<c:set var="businessRating" value="7" />
			<c:choose>
				<c:when test="${businessRating eq list.getRating()}">
					<span id="ratingbox_b">비지니스</span>
				</c:when>
				<c:otherwise>
					<span id="ratingbox">${list.getRating()}성급</span>
				</c:otherwise>
			</c:choose>
			<h1>${list.getName()}</h1>
			<br> <br>
			<p id="addressbox">${list.getAddress()}</p>
			<br> <br>
			<!-- 			<span>사장님의 한마디</span> -->
			<div id="owner_comment">
				글로벌 공인 인증 (GBAC STAR Facility) 획득 한국의 아름다움을 현대적인 감각으로 풀어낸 객실에서 서울의
				파노라믹한 스카이라인과 환상적인 야경이 있습니다 <br> <br>시그니엘 서울은 럭셔리를 넘어, 서비스를
				넘어, 기대를 넘어, 상상하는 모든 것 그 이상의 가치를 제공합니다
			</div>
		</div>
	</div>
	<section>
		<div class="tabmenu">
			<ul>
				<li id="tab1" class="btnCon">
					<c:if test="${param.category == 'list' || param.category == null}" >
						<input type="radio" name="tabmenu" id="tabmenu1" checked="checked">
					</c:if>
					<c:if test="${param.category != 'list' && param.category != null}" >
						<input type="radio" name="tabmenu" id="tabmenu1">
					</c:if>
					<label for="tabmenu1">객실정보&예약</label>
					<div class="tabCon">
						<div id="room_bigbox">
							<div id="date_sel">
								체&nbsp;크&nbsp;인 &nbsp;: <input type="date" id="start_date"
									onchange="start_date_change()"><br> 체크아웃 : <input
									type="date" id="end_date" onchange="end_date_check()">
							</div>
							<c:forEach var="rooms" items="${roominfo}" varStatus="status">
								<div class="roombox">
									<div class="room_img_box">
										<img src="images/${rooms.roomphoto1}">
									</div>
									<div class="room_info_box">
										<h1>${rooms.roomname}</h1>
										<br> <br>
										<div class="room_info_price">
											<span>가격</span>
											<div class="pricebox">
												<fmt:formatNumber value="${rooms.roomprice}"
													pattern="#,###,###" />
												원
											</div>
										</div>
										<input type="button" class="room_info_button" value="객실이용안내">
										<c:if test="${uid == null}">
											<input type="button" class="room_reservation_btn"
												value="예약하기" onclick="alert('로그인 해야 예약이 가능합니다')">
										</c:if>
										<c:if test="${uid != null}">
											<input type="hidden" class="hid" value="${list.getHid()}">
											<input type="button" class="room_reservation_btn"
												value="예약하기"
												onclick="reserv(${rooms.roomid}, ${rooms.roomprice}, ${capacity})">
										</c:if>
									</div>
								</div>
							</c:forEach>
							<input type="hidden" id="capacity" value="${capacity}">
						</div>
					</div></li>
				<li id="tab2" class="btnCon">
					<c:if test="${param.category == 'info' }" >
						<input type="radio" name="tabmenu" id="tabmenu2" checked="checked">
					</c:if>
					<c:if test="${param.category != 'info' }" >
						<input type="radio" name="tabmenu" id="tabmenu2">
					</c:if>
					<label for="tabmenu2">숙소안내</label>
					<div class="tabCon">
						<div id="lodgment_infobox">
							<h3>주변정보</h3>
							<ul>
								<li>${list.getAround_info1()}</li>
								<li>${list.getAround_info2()}</li>
								<li>${list.getAround_info3()}</li>
							</ul>
							<br> <br>
							<h3>기본 정보</h3>
							<ul>
								<li>${list.getBasic_info1()}</li>
								<li>${list.getBasic_info2()}</li>
								<li>${list.getBasic_info3()}</li>
								<li>${list.getBasic_info4()}</li>
								<li>${list.getBasic_info5()}</li>
							</ul>
							<br> <br>
							<h3>인원 추가 정보</h3>
							<ul>
								<li>${list.getPersonadd_info1()}</li>
								<li>${list.getPersonadd_info2()}</li>
								<li>${list.getPersonadd_info3()}</li>
								<li>${list.getPersonadd_info4()}</li>
								<li>${list.getPersonadd_info5()}</li>
								<li>${list.getPersonadd_info6()}</li>
							</ul>
							<br> <br>
							<c:if test="${list.getParking_info1() != ''}">
								<h3>주차 정보</h3>
								<ul>
									<li>${list.getParking_info1()}</li>
									<li>${list.getParking_info2()}</li>
									<li>${list.getParking_info3()}</li>
									<li>${list.getParking_info4()}</li>
									<li>${list.getParking_info5()}</li>
									<li>${list.getParking_info6()}</li>
								</ul>
								<br>
								<br>
							</c:if>
							<c:if test="${list.getBenefit_info1() != ''}">
								<h3>투숙객 혜택</h3>
								<ul>
									<li>${list.getBenefit_info1()}</li>
									<li>${list.getBenefit_info2()}</li>
									<li>${list.getBenefit_info3()}</li>
								</ul>
								<br>
								<br>
							</c:if>
							<h3>부대시설 정보</h3>
							<ul>
								<li>${list.getAdditional_info1()}</li>
								<li>${list.getAdditional_info2()}</li>
								<li>${list.getAdditional_info3()}</li>
								<li>${list.getAdditional_info4()}</li>
								<li>${list.getAdditional_info5()}</li>
								<li>${list.getAdditional_info6()}</li>
								<li>${list.getAdditional_info7()}</li>
								<li>${list.getAdditional_info8()}</li>
								<li>${list.getAdditional_info9()}</li>
								<li>${list.getAdditional_info10()}</li>
							</ul>
							<br> <br>
							<c:if test="${list.getBreakfast_info1() != ''}">
								<h3>조식 정보</h3>
								<ul>
									<li>${list.getBreakfast_info1()}</li>
									<li>${list.getBreakfast_info2()}</li>
									<li>${list.getBreakfast_info3()}</li>
									<li>${list.getBreakfast_info4()}</li>
									<li>${list.getBreakfast_info5()}</li>
								</ul>
								<br>
								<br>
							</c:if>
							<div id="last_info">
								<h3>취소 및 환불 규정</h3>
								<ul>
									<li>체크인일 기준 3일전 17시까지 : 100% 환불</li>
									<li>체크인일 기준 3일전 17시이후~당일 및 No-show : 환불불가</li>
									<li>취소, 환불시 수수료가 발생할 수 있습니다</li>
									<li>아래 객실은 예약 후 취소, 변경, 환불 불가합니다</li>
									<li>[한정특가] 객실 예약 후 취소, 변경, 환불 불가</li>
								</ul>
								<br> <br>
								<h3>확인사항 및 기타</h3>
								<ul>
									<li>최대인원 초과시 입실 불가합니다</li>
									<li>미성년자는 보호자 동반없이 이용이 불가합니다</li>
									<li>위의 정보는 호텔의 사정에 따라 변경될 수 있습니다</li>
									<li>해당 이미지는 실제와 상이 할 수 있습니다</li>
									<li>체크인 시 배정 또는 베드타입 미기재 상품은 특정객실과 베드타입을 보장하지 않습니다</li>
									<li>해당 객실가는 세금, 봉사료가 포함된 금액입니다</li>
								</ul>
							</div>
						</div>
					</div></li>
				<li id="tab3" class="btnCon">
					<c:if test="${param.category == 'review' }" >
						<input type="radio" name="tabmenu" id="tabmenu3" checked="checked" />
					</c:if>
					<c:if test="${param.category != 'review' }" >
						<input type="radio" name="tabmenu" id="tabmenu3" />
					</c:if>
					<label for="tabmenu3">리뷰</label>
					<div class="tabCon">
						<article>
								<div class="reviewbox">
									<table cellspacing=0 class="tb">
										<tr>
											<th width="50px">번호</th>
											<th width="800px">제목</th>
											<th width="100px">닉네임</th>
											<th width="100px">작성시간</th>
										</tr>
										<c:forEach var="review" items="${review}" varStatus="status">
											<tr class='item'>
												<td>${review.review_num}</td>
												<td class='title_td'><span>${review.review_title}</span></td>
												<td><span>${review.nick}</span></td>
												<td><div class='date_box'>${review.review_date}</div></td>
											</tr>
											<c:if test="${review.review_uid eq  uid}">
												<tr class='hide'>
													<td colspan='4'><textarea rows='15' cols='105'
															id='contents' readonly>
																			${review.review_contents}
																			</textarea>
														<div id='fu_button_box'>
															<a href="">
																<input type="button" class='fu_button' value="수정하기">
															</a> 
															<a href="">
																<input type="button" class='fu_button' value="삭제하기">
															</a>
														</div></td>
												</tr>
											</c:if>
											<c:if test="${review.review_uid != uid}">
												<tr class='hide'>
													<td colspan='4'><textarea rows='15' cols='100'
															id='contents' readonly>
																			${review.review_contents}</textarea></td>
												</tr>
											</c:if>
										</c:forEach>
									</table>
									<div>
										<c:if test="${uid != null}">
											<input type="button" value="리뷰쓰기" style="margin-left: 1065px;" onclick="write_review()">
										</c:if>
										<c:if test="${uid == null}">
											<input type="button" value="리뷰쓰기" style="margin-left: 1065px;" onclick="login_()">
										</c:if>
									</div>
								</div>
						</article>
					</div></li>
			</ul>
		</div>
	</section>
	<div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h2>리뷰 작성</h2>
            </div>
            <div class="close-area" onclick="modaloff()">X</div>
            <div class="content">
                <input type="text" id="re_title">
                <textarea id="re_contents" maxlength="500"></textarea>
                <label id="warning"></label>
                <input type="button" value="작성완료" id="review_write" onclick="reviewWrite(${uid})">
            </div>
        </div>
    </div>
	<%@ include file="../footer.jsp"%>
</body>
</html>