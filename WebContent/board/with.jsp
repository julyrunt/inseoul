<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.inseoul.vo.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<!-- favicon.ico 오류 해결 링크 -->
<link rel="icon" href="data:;base64, iVBORw0KGgo=">
<!-- 슬라이더 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/top-header.css">
<link rel="stylesheet" type="text/css" href="../css/slick.css">
<link rel="stylesheet" type="text/css" href="../css/slick-theme.css">
<link rel="stylesheet" type="text/css" href="../css/with.css">
<link rel="stylesheet" type="text/css" href="../css/board-common.css">

<!-- 자바스크립트 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script src="../js/common.js"></script>


</head>
<body>
	<%
		String uid = (String)session.getAttribute("uid");
		ArrayList<WithBoardBean> withBoardBean = (ArrayList<WithBoardBean>)request.getAttribute("articleList");
		ArrayList<Integer> d_Day = (ArrayList<Integer>)request.getAttribute("d_Day");
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
	%>
	<jsp:include page="../header.jsp"/>
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="with-bigbox">
		<section>
		<nav class="with-toplist">
			<%if(uid==null){ %>
					<span><input type="button" class="buttons" value="글쓰기" onclick="alert('회원만 작성할 수 있습니다')"></span>
					<%
					}else{
					%>
					<span>
					<a href="../board/with_write.jsp"><input type="button" class="buttons" value="글쓰기"></a>
					</span>
					<%
					}
					%>
		</nav>
			<div class="with-midbox">
				<jsp:include page="board-sidebox.jsp"/>
<!-- 				<div class="with-topsection"> -->
					
<!-- 				</div> -->
				
				<div id="with-contentsAll">
				<%
				if(withBoardBean != null && listCount > 0){
					for(int x=0 ; x<withBoardBean.size() ; x++){ 
				%>
				<div class="with-contentsbox">
					
					<%if (withBoardBean.get(x).getPhotos() == null ){ %>
					<div class="with-contents-img">
					<a href="withDetail.with?wid=<%=withBoardBean.get(x).getWid()%>&page=<%=nowPage%>">
					<img alt="photos1" src="../images/logo2.png"></a></div>
					<%}else{ %>
					<div class="with-contents-img">
					<a href="withDetail.with?wid=<%=withBoardBean.get(x).getWid()%>&page=<%=nowPage%>">
					<img alt="photos" src="../boardUpload/<%=withBoardBean.get(x).getPhotos()%>"></a></div>
					<%} %>
					
					<div class="with-contents-text">
						
						<div class="with-contents-lefttext">
						
						<h4>그룹여행모집</h4>
						<a href="withDetail.with?wid=<%=withBoardBean.get(x).getWid()%>&page=<%=nowPage%>">
							<span>
							<%=withBoardBean.get(x).getTitle()%></span></a>
						<div class="with-contents-long">
							<div class="with-contents-long-date">모집기한: <%=withBoardBean.get(x).getMojib_limit() %></div>
							<div class="with-contents-long-date">여행날짜: <%=withBoardBean.get(x).getTrip_start() %> ~ <%=withBoardBean.get(x).getTrip_end() %></div>
						    <a href="withDetail.with?wid=<%=withBoardBean.get(x).getWid()%>&page=<%=nowPage%>">
						    <span><%=withBoardBean.get(x).getContents() %></span></a>
						</div>
						</div>
							<div class="with-contents-date"><%=withBoardBean.get(x).getWritedate() %></div>
<!-- 							Date mojibdate = simpleDateFormat.parse(withBoardBean.get(x).getMojib_limit()); -->
							<% 
							if (nowDate1.before(withBoardBean.get(x).getMojib_limit()) || nowDate1.equals(withBoardBean.get(x).getMojib_limit())){ 
								if(d_Day.get(x) > 1){
							%> 
								<div class="mojib_d-Day">
									 <span>D - <%=d_Day.get(x) %></span>
								</div>
							<%	}else{ %>
								<div class="mojib_d-Day_immi">
								 	<span>D - <%=d_Day.get(x) %></span>
								</div>
							<%	}
							}else{ %>
								<div class="mojib_complet">
									<span>모집기한 만료</span>
								</div>
							<%}%>
					</div>
						<div class="with-contents-info">
							<div class="with-contents-writer"><span><%=withBoardBean.get(x).getNick() %>님의 모집글</span></div>
							<div class="with-contents-readcount">
								<ul>
									<li><span class="material-icons">visibility</span></li>
									<li><%=withBoardBean.get(x).getReadcount() %> &nbsp; &nbsp;</li>
									<li><span class="material-icons">comment</span></li>
									<li><%=withBoardBean.get(x).getReplyCount() %></li>
								</ul>
							</div>
						</div>
				</div>
				<% }
					}%>
				</div>
			</div>
		
		</section>
		<section id="pageList">
			<%if(nowPage<=1){ %>
			[이전]&nbsp;
			<%}else{ %>
			<a href="boardList.with?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
			<%} %>

			<%for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
			[<%=a %>]&nbsp;
			<%}else{ %>
			<a href="boardList.with?page=<%=a %>">[<%=a %>]
			</a>&nbsp;
			<%} 
			}%>

			<%if(nowPage>=maxPage){ %>
			[다음]
			<%}else{ %>
			<a href="boardList.with?page=<%=nowPage+1 %>">[다음]</a>
			<%
			}
			%>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>