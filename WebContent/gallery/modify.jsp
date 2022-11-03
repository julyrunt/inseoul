<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-common.css">
<link rel="stylesheet" type="text/css" href="../css/profile-header.css">
<link rel="stylesheet" type="text/css" href="../css/profile-nav.css">
<link rel="stylesheet" type="text/css" href="../css/gallery-common.css">
<link rel="stylesheet" type="text/css" href="../css/gallery-modify.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/profile-header.js"></script>
<script type="text/javascript" src="../js/gallery-modify.js"></script>
</head>
<body>
	<c:if test="${uid == null}">
		<c:redirect url="../log-in" />
	</c:if>
	<c:if test="${uid != null}">
		<jsp:include page="../header.jsp" />
		<header id="main-header">
			<jsp:include page="../profile-header.jsp" />
		</header>
		<main>
			<section>
				<header id="article-header">
					<jsp:include page="../profile-article-header-nav.jsp" />
				</header>
				<article>
					<form action="albumModifyPro.do" method="post" enctype="multipart/form-data" name="boardform" onsubmit="return checkFileSize()">
						<input type="hidden" name="pid" value="${param.pid}"/>
						<input type="hidden" name="category" value="${param.category}"/>
						<input type="hidden" name="aid" value="${param.aid}"/>
						<input type="hidden" name="page" value="${param.page}"/>
						<input type="hidden" name="search" value="${param.search}"/>
						<input type="hidden" name="keywords" value="${param.keywords}"/>
						<header>
							<div class="ui-widget">
								<select id="location" name="location" required="required">
									<option value>Select one...</option>
									<c:if test="${locationList.size() > 0}">
										<c:forEach var="i" begin="0" end="${locationList.size() - 1}">
											<c:if test="${locationList.get(i).getLid() == album.getLid()}">
												<option value=${locationList.get(i).getLid()} bg="${locationList.get(i).getImg()}" selected>${locationList.get(i).getName()}</option>
											</c:if>
											<c:if test="${locationList.get(i).getLid() != album.getLid()}">
												<option value=${locationList.get(i).getLid()} bg="${locationList.get(i).getImg()}" >${locationList.get(i).getName()}</option>
											</c:if>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<input id="title" type="text" name="title" value="${album.getTitle()}" required="required"/>
						</header>
						<textarea name="contents" >${album.getContents()}</textarea>
						사진 (<span id="photo-count">1</span>/10)
						<c:if test="${album.getImg10() != null}">
							<input id="add" type="button" value="+" disabled />
						</c:if>
						<c:if test="${album.getImg10() == null}">
							<input id="add" type="button" value="+" />
						</c:if>
						<c:if test="${album.getImg02() != null}">
							<input id='remove' type='button' value='-' />
						</c:if>
						<c:if test="${album.getImg02() == null}">
							<input id='remove' type='button' value='-' disabled />
						</c:if>
						<span id="file-size">0MB / 50MB · 파일형식: png · 파일명: 공백문자 제외</span>
						<ul id="file-input-list">
							<c:if test="${album.getImg01() != null}">
								<li class="file-input">
									<div id="preview01" style="background-image: url('../albumUpload/${album.getImg01()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img01" type="file" id="img01" value="${album.getImg01()}" accept="image/png" />
									</span>
									<span id="file-name01">${album.getImg01()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg02() != null}">
								<li class="file-input">
									<div id="preview02" style="background-image: url('../albumUpload/${album.getImg02()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img02" type="file" id="img02" value="${album.getImg02()}" accept="image/png" />
									</span>
									<span id="file-name02">${album.getImg02()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg03() != null}">
								<li class="file-input">
									<div id="preview03" style="background-image: url('../albumUpload/${album.getImg03()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img03" type="file" id="img03" value="${album.getImg03()}" accept="image/png" />
									</span>
									<span id="file-name03">${album.getImg03()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg04() != null}">
								<li class="file-input">
									<div id="preview04" style="background-image: url('../albumUpload/${album.getImg04()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img04" type="file" id="img04" value="${album.getImg04()}" accept="image/png" />
									</span>
									<span id="file-name04">${album.getImg04()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg05() != null}">
								<li class="file-input">
									<div id="preview05" style="background-image: url('../albumUpload/${album.getImg05()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img05" type="file" id="img05" value="${album.getImg05()}" accept="image/png" />
									</span>
									<span id="file-name05">${album.getImg05()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg06() != null}">
								<li class="file-input">
									<div id="preview06" style="background-image: url('../albumUpload/${album.getImg06()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img06" type="file" id="img06" value="${album.getImg06()}" accept="image/png" />
									</span>
									<span id="file-name06">${album.getImg06()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg07() != null}">
								<li class="file-input">
									<div id="preview07" style="background-image: url('../albumUpload/${album.getImg07()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img07" type="file" id="img07" value="${album.getImg07()}" accept="image/png" />
									</span>
									<span id="file-name07">${album.getImg07()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg08() != null}">
								<li class="file-input">
									<div id="preview08" style="background-image: url('../albumUpload/${album.getImg08()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img08" type="file" id="img08" value="${album.getImg08()}" accept="image/png" />
									</span>
									<span id="file-name08">${album.getImg08()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg09() != null}">
								<li class="file-input">
									<div id="preview09" style="background-image: url('../albumUpload/${album.getImg09()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img09" type="file" id="img09" value="${album.getImg09()}" accept="image/png" />
									</span>
									<span id="file-name09">${album.getImg09()}</span>
								</li>
							</c:if>
							<c:if test="${album.getImg10() != null}">
								<li class="file-input">
									<div id="preview10" style="background-image: url('../albumUpload/${album.getImg10()}'), url('../images/transparent.svg');"></div>
									<span class="file-button">
										<input name="img10" type="file" id="img10" value="${album.getImg10()}" accept="image/png" />
									</span>
									<span id="file-name10">${album.getImg10()}</span>
								</li>
							</c:if>
						</ul>
						<footer>
							<input id="cancel" type="button" value="취소" />
							<input type="submit" value="완료" />
						</footer>
					</form>
				</article>
				<footer id="article-footer">
					
				</footer>
			</section>
			<nav id="section-nav">
				<jsp:include page="../profile-nav.jsp" />
			</nav>
		</main>
		<jsp:include page="../footer.jsp" />
	</c:if>
</body>
</html>