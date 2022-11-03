<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 아이콘 폰트 스타일시트 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/location-detail.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-common.css">
<link rel="stylesheet" type="text/css" href="../css/bucket-index.css">
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/location.js"></script>

</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">관 광 지 정 보</div>
	</div>
	<form action="travelsWrite.tv" method="post">
		<section>
			<div id="location_">
				<div id="top_name">
					<h2>${article.getName()}</h2>
				</div>
				<div id="img_box">
					<img alt="img" src="../images2/${article.getImg()}">
				</div>
				<div id="location_info">
					<span>${article.getInfo()}</span>
				</div>
				<div id="useinfo">
					<h3>상세 정보</h3>
					<ul>
						<li><strong> 주 소 - </strong>&nbsp;<span>${article.getAddr()}</span></li>
						<li><strong>전화번호 - </strong>&nbsp;<span>${article.getTel()}</span></li>
						<c:if test="${article.getUsetime() != ''}">
							<li><strong>이용시간 - </strong>&nbsp;<span>${article.getUsetime()}</span></li>
						</c:if>
						<c:if test="${article.getUsetime() == ''}">
							<li><strong>이용시간 - </strong>&nbsp;<span>24시간 개방</span></li>
						</c:if>
						<c:if test="${article.getHoliday() != ''}">
							<li><strong> 휴 일 - </strong>&nbsp;<span>${article.getHoliday()}</span></li>
						</c:if>
						<c:if test="${article.getHoliday() == ''}">
							<li><strong> 휴 일 - </strong>&nbsp;<span>연중무휴</span></li>
						</c:if>
						<c:if test="${article.getUseinfo() != ''}">
							<li><dl>
									<dt>
										<strong>이용 정보</strong>
									</dt>
									<dd>
										<span>${article.getUseinfo()}</span>
									</dd>
								</dl></li>
						</c:if>
					</ul>
				</div>
			</div>
		</section>
		<section>
			<div id="travels_">
				<div id="travels_title">
					<h2>${article.getName()}- 여행기</h2>
				</div>
				<div id="travels_content_big">
					<ul id="travels-list">
						<c:if test="${travels != null && travels.size() > 0}">
							<c:forEach var="i" begin="0" end="${travels.size() - 1}">
								<li class="tavels"><a
									href="../board/travelsDetail.tv?tid=${travels.get(i).getTid()}">
										<dl>
											<dt>
												<div class="travels-title">
													${travels.get(i).getTitle()}</div>
											</dt>
											<dd>${travels.get(i).getContents()}</dd>
										</dl>
										<div class="count">
											<div class="group-01">
												<img alt=""
													src="../images/visibility_FILL0_wght400_GRAD0_opsz20.svg">
												${travels.get(i).getReadcount()} <img alt=""
													src="../images/comment_FILL0_wght400_GRAD0_opsz20.svg">
												${travels.get(i).getReplycount()} <img alt=""
													src="../images/favorite_FILL0_wght400_GRAD0_opsz20.svg">
												${travels.get(i).getRecommendCount()}
											</div>
											<div class="group-02">
												<span class="trip-id">${travels.get(i).getNick()}</span> <span
													class="trip-data">${travels.get(i).getDate()}</span>
											</div>
										</div>
								</a></li>
							</c:forEach>

						</c:if>
						<li class="tavels-more"><a href="../board/travelsList.tv">
								<div class="more">
									<span></span><span></span><span></span>
									<p>여행기 더 보기</p>
								</div>
						</a></li>
					</ul>
				</div>
				<div id="travels_btns">
					<c:if test="${uid != null}">
						<input type="submit" value="여행기 쓰기" id="travels_write">
					</c:if>
					<c:if test="${uid == null}">
						<input type="button" value="여행기 쓰기" id="travels_write"
							onclick="travels_write_login()">
					</c:if>
				</div>
			</div>
		</section>
		<input type="hidden" name="lid" value="${article.getLid()}">
	</form>

	<section>
		<div id="routemap_">
			<h2>${article.getName()}- 루트맵</h2>
			<c:if test="${route != null && route.size() > 0}">
				<c:forEach var="x" begin="0" end="${route.size() -1}">
					<div class="plan-contentsbox">
						<a> <%-- 				href="plandetail.mk?mid=${route.get(x).getMid()}"> --%>
							<%-- 					<%if (route.get(x).getPhotos() == null ){ %> --%> <!-- 					<div class="with-contents-img"><img alt="photos1" src="images/logo2.png"></div> -->
							<%-- 					<%}else{ %> --%>
							<div class="plan-contents-img">
								<img alt="photos" src="">
							</div> <%-- 					<%} %> --%>
	
							<div class="plan-contents-text">
								<div class="with-contents-lefttext">
									<ul>
										<li class="txt_hide">
											<h4>여행계획</h4>
										<li>
										<li class="tit">${route.get(x).getTitle()} <fm:formatDate
												value="${route.get(x).getWriteday()}" pattern="yyyy-MM-dd" />
										</li>
										<li class="schedule"><span class="ng-star-inserted">${route.get(x).getDay1_0_name()}
										</span> <c:if test="${route.get(x).getDay1_1() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay1_1_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay1_2() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay1_2_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay1_3() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay1_3_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay2_0() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay2_0_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay2_1() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay2_1_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay2_2() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay2_2_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay2_3() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay2_3_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay3_0() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay3_0_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay3_1() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay3_1_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay3_2() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay3_2_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay3_3() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay3_3_name()}</span>
											</c:if> <c:if test="${route.get(x).getDay4_0() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay4_0_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay4_1() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay4_1_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay4_2() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay4_2_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay4_3() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay4_3_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay5_0() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay5_0_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay5_1() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay5_1_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay5_2() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay5_2_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay5_3() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay5_3_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay6_0() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay6_0_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay6_1() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay6_1_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay6_2() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay6_2_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay6_3() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay6_3_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay7_0() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay7_0_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay7_1() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay7_1_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay7_2() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay7_2_name() }</span>
											</c:if> <c:if test="${route.get(x).getDay7_3() !=null}">
												<span class="ng-star-inserted">${route.get(x).getDay7_3_name() }</span>
											</c:if></li>
										<li class="date"><fm:formatDate
												value="${route.get(x).getPlan_start()}" pattern="yyyy-MM-dd" />
											~ <fm:formatDate value="${route.get(x).getPlan_end()}"
												pattern="yyyy-MM-dd" /></li>
										<li>
											<ul class="ico_wrap">
												<li></li>
											</ul>
										</li>
									</ul>
								</div>
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
			
				</c:forEach>
			</c:if>
			<div class="plan-contentsbox">
				<a href="../plan/planselect.mk">
					<div class="more">
						<span></span><span></span><span></span>
						<p>루트맵 더 보기</p>
					</div>
				</a>
			</div>
		</div>
	</section>

	<section>
		<div id="bucket_">
			<h2>${article.getName()}- 버킷리스트</h2>
			<div>
				<c:if test="${bucket != null && bucket.size() > 0}">
					<ul>
						<c:forEach var="i" begin="0" end="${bucket.size() - 1}">
							<li><a href="../bucket-list/view.do?pid=${bucket.get(i).getUid()}&category=my&bid=${bucket.get(i).getBid()}&page=1">
									<div class='bucket-ticket'
										style='background: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url("../images2/${bucket.get(i).getImg()}"), url("../images/image_not_supported_black.svg");background-size: 100%;background-position: center;'>
										<div class='left-semicircle'></div>
										<div class='bucket-no'>
											${bucket.get(i).getBid()} <span class="bucket-info"> <img
												alt=""
												src="../images/comment_FILL0_wght400_GRAD0_opsz20.svg">
												${bucket.get(i).getReplyCount()} <img alt=""
												src="../images/favorite_FILL0_wght400_GRAD0_opsz20.svg">
												${bucket.get(i).getRecommendCount()}
											</span>
										</div>
										<div class='location-name'>${bucket.get(i).getName()}</div>
										<div class='bucket-item'>${bucket.get(i).getItem()}</div>
									</div>
									<div>
										<div class='write-date'>${bucket.get(i).getWritedate()}</div>
										<div class='author'>${bucket.get(i).getNick()}</div>
										<c:if test="${bucket.get(i).getProgress() == 0}">
											<span class='material-symbols-outlined progress'>
												pending </span>
										</c:if>
										<c:if test="${bucket.get(i).getProgress() == 1}">
											<span class='material-symbols-outlined complete'>
												task_alt </span>
										</c:if>
										<div class='right-semicircle'></div>
									</div>
							</a></li>
						</c:forEach>
						<li style="background: white;">
							 
							
							<a style="width: 100%; height: 100%; display: block;" 
								href="../bucket-list/list.do?pid=${uid}&category=recommend">
								<div class="more" style="width: 450px; padding: 15px 0 0 0;">
									<span></span><span></span><span></span>
									<p>버킷리스트 더 보기</p>
								</div>
							</a>
						</li>
					</ul>
				</c:if>
			</div>
		</div>
	</section>
	<section>
		<div id="album_">
			<h2>${article.getName()}- 앨 범</h2>
			<div>
				<ul>
					<c:if test="${album != null && album.size() > 0}">
						<c:forEach var="i" begin="0" end="${album.size() - 1}">
							<li>
								<div class="background-image"
									style="background-image: url('../albumUpload/${album.get(i).getImg01()}'), url('../images/transparent.svg')">
								</div><a href="../gallery/view.do?pid=${album.get(i).getUid()}&category=my&aid=${album.get(i).getAid()}&page=1">
									<span class="album-title">${album.get(i).getName()} |
										${album.get(i).getTitle()}</span> <span class="album-writer">${album.get(i).getNick()}</span>
									<span class="album-write-date">${album.get(i).getWritedate()}</span>
							</a>
								<div class="album-bottom-info">
									<img alt=""
										src="../images/visibility_FILL0_wght400_GRAD0_opsz20.svg">
									${album.get(i).getReadCount()} <img alt=""
										src="../images/comment_FILL0_wght400_GRAD0_opsz20.svg">
									${album.get(i).getReplyCount()} <img alt=""
										src="../images/favorite_FILL0_wght400_GRAD0_opsz20.svg">
									${album.get(i).getRecommendCount()}
								</div>
							</li>
						</c:forEach>
					</c:if>
					<li>

						<div class="more">
							<a style="top: 0px; background-color: white;"
								href="../gallery/list.do?pid=${uid}&category=recommend"> 
							<span></span><span></span><span></span>
								<p>앨범 더 보기</p>
							</a>
						</div>

					</li>
				</ul>
			</div>
		</div>
	</section>
	<jsp:include page="../footer.jsp" />
</body>
</html>