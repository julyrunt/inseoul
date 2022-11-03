<%@page import="java.util.ArrayList"%>
<%@page import="portfolio_1.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>리뷰 작성</title>
	<!-- 폰트 스타일시트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<!-- 아이콘 폰트 스타일시트 -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<!-- 슬라이더 스타일시트 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	<!-- 스타일시트 -->
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/lodgment-detail.css">
	<!-- 자바스크립트 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	<script src="../js/common.js"></script>
	<!-- DB 연결 -->
	<jsp:useBean id="review" class="portfolio_1.DB_Controler"></jsp:useBean>
	<%
		ArrayList<Lodgment_Review_GS> db_notice =  review.db_ReviewAll();
		String uid = (String)session.getAttribute("uid");
	%>
	<script type="text/javascript">
	
		function title_check(){
			var title_val = document.getElementById("title").value;
			if(title_val==""){
				alert("제목은 필수입니다");
				return false;
			}
		}
	</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div id="mainbox1">
		<div id="mainbox1_in1" style="">
			<img src="../images/hotel.jpg" width="600px" height="550px">
			<div style="width: 600px; height: 120px; margin: 0px auto; margin-left: 45px;">
				
				<img src="../images/hotel_room1.jpg" width="120px" height="120px">
				<img src="../images/hotel_room2.jpg" width="120px" height="120px">
				<img src="../images/hotel_room3.jpg" width="120px" height="120px">
				<img src="../images/hotel_room4.jpg" width="120px" height="120px">
			</div>
		</div>
		<div id="mainbox1_in2" style="margin-left: 200px; font-family: 돋움; ">
			<h1 style="font-size: 30px; ">르네블루 바이 워커힐</h1>
			<pre style="font-size: 15px; font-weight: bold;">강원 고성군 죽왕면 오호리 산 2-22</pre><br>
			<pre style="font-weight: bold;">사장님의 한마디</pre>
			<div style="background-color: #EAEAEA; width: 490px; height: 100px;">언제나 누구와 함께 이용하더라도 편안함과 만족감을 느낄 수 있도록 최상의 시설로 운영 중인 호텔입니다.</div>
		</div>
	</div>
	
	<div class="tabmenu">
  	<ul>
    <li id="tab1" class="btnCon"> 
      <input type="radio" name="tabmenu" id="tabmenu1">
      <label for="tabmenu1">객실정보&예약</label>
      <div class="tabCon" >
      		<div class="roombox">
				<div class="room_img_box">
					<img src="../images/hotel_room1.jpg" width="400px" height="300px">
				</div>
				<div class="room_text_box" style="font-family: 돋움;">
					<h1 style=" font-size: 20px;">[룸서비스 패키지] 패밀리 스위트</h1><br><br>
					<div style="width: 350px; height: 50px; float: left; font-size: 20px; border-bottom: ridge; font-weight: bold;">가격</div>
					<div style="width: 350px; height: 50px; float: left; text-align: right; font-size: 20px; border-bottom: ridge; font-weight: bold;">1,000,000원</div>
					<button style="border: none; width: 700px; height: 40px; margin: 10px auto; font-size: 15pt;">객실이용 안내</button>
					<button style="border: none; width: 700px; height: 50px; margin: 17px 0 0 0 ; font-size: 15pt; background: crimson; color: white;">예약하기</button>
				</div>
				</div>
			
      </div>
    </li>
    <li id="tab2" class="btnCon"><input type="radio" name="tabmenu" id="tabmenu2">
      <label for="tabmenu2">숙소안내</label>
      <div class="tabCon" >
      		내용2
      </div>
      
    </li>    
    <li id="tab3" class="btnCon"><input type="radio" name="tabmenu" id="tabmenu3" checked>
      <label for="tabmenu3">리뷰</label>
      <div class="tabCon">
					<form action="writing.jsp" onsubmit="return title_check()" style="text-align: center;">
					<%
					
					if(uid==null){%>
					<div style="margin-top: 50px;">
						<h2>로그인을 해야 글쓰기가 가능합니다</h2>
						<a href="log-in.jsp"><button type="button">로그인 하러가기</button></a>
					</div>
					<%}else{%>
						<div class="reviewbox">
							<h2 style="text-align: center;">리뷰 작성</h2>
							<div style="text-align: center;">
								<textarea rows="1" cols="100" name="review_title" id="title"
									placeholder="제목은 필수입니다"></textarea>
							</div>
							<div style='text-align: center;'>
								<textarea rows='30' cols='100' name="review_contents" id="contents"></textarea>
							</div><div style="text-align: center;">
							<button type="submit">리뷰 올리기</button>
							<button type="reset">초기화</button></div>
						</div>
					<%} %>
					</form>
				</div>
    </li>
  </ul>
</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>