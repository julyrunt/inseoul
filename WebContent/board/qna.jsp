<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.inseoul.vo.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

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
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/slick.css">
<link rel="stylesheet" type="text/css" href="../css/slick-theme.css">
<link rel="stylesheet" type="text/css" href="../css/qna-list.css">
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
		String nick = (String)session.getAttribute("nick");
		ArrayList<QnABoardBean> articleList = (ArrayList<QnABoardBean>)request.getAttribute("articleList");
		PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
		int listCount=pageInfo.getListCount();
		int nowPage=pageInfo.getPage();
		int maxPage=pageInfo.getMaxPage();
		int startPage=pageInfo.getStartPage();
		int endPage=pageInfo.getEndPage(); 
		
	%>
	<jsp:include page="../header.jsp"/>
	<div class="top-title">
		<img src="../images/top-bg-001.png" />
		<div class="titleBox">커 뮤 니 티</div>
	</div>
	<div class="qna-list-bigbox">
		<section>
		<nav class="qna-list-toplist">
			<ul>
				<li><a href="">In서울</a></li>
				<li><span>></span></li>
				<li><a href="">커뮤니티</a></li>
			</ul>
		</nav>
		
			<div class="qna-list-midbox">
				<jsp:include page="board-sidebox.jsp"/>
				
				<div class="qna-list-topsection">
				
					<p>회원들간 의사소통 공간입니다</p>
					<%if(uid==null){ %>
					<span><input type="button" class="buttons" value="글쓰기" onclick="alert('회원만 작성할 수 있습니다')"></span>
					<%
					}else{
					%>
					<span> <a href="../board/qna-write.jsp"><input type="button" class="buttons" value="글쓰기"></a>
					</span>
					<%
					}
					%>
				</div>
				<table>
			<% if(articleList != null && listCount > 0){ %>

			<tr id="tr_top">
				<td class="">제목</td>
				<td class="td1">작성자</td>
				<td class="td1">날짜</td>
				<td class="td1">조회수</td>
			</tr>

			<% for(int i=0;i<articleList.size();i++){ %>
			<tr class="qna-index-List">

				<td class="tdtitle">
				<a href="qnaDetail.qa?qid=<%=articleList.get(i).getQid()%>&page=<%=nowPage%>&ref=<%=articleList.get(i).getRef()%>">
						<%=articleList.get(i).getTitle()%>
				</a>
				</td>

				<td><%=articleList.get(i).getNick() %></td>
				<td><%=articleList.get(i).getWritedate() %></td>
				<td><%=articleList.get(i).getReadcount() %></td>
			</tr>
			<%}
			}%>
		</table>
			</div>
		
		</section>
		<section id="pageList">
			<%if(nowPage<=1){ %>
			《&nbsp;
			<%}else{ %>
			<a href="qnaList.qa?page=<%=nowPage-1 %>">《</a>&nbsp;
			<%} %>

			<%for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
			[<%=a %>]&nbsp;
			<%}else{ %>
			<a href="qnaList.qa?page=<%=a %>">[<%=a %>]
			</a>&nbsp;
			<%} 
			}%>

			<%if(nowPage>=maxPage){ %>
			》
			<%}else{ %>
			<a href="qnaList.qa?page=<%=nowPage+1 %>">》</a>
			<%
			}
			%>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>