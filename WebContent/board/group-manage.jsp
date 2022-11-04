<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.inseoul.vo.*"%>
<!-- <html> -->
<!-- <head> -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/with-detail.css" />
<!-- 자바스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/slick.min.js"></script>
<script type="text/javascript" src="../js/with-detail.js"></script>
<table>
	<tr>
		<th width="165px">닉네임</th>
		<th width="280px">이메일</th>
		<th width="180px">연락처</th>
		<th width="140px">신청일</th>
		<th width="75px">상태</th>
		<th width="60px">관리</th>
	</tr>
	<c:forEach var="gr" items="${groups}" varStatus="status">
		<c:if test="${gr.state eq 0}">
			<tr>
				<td>${gr.nick}</td>
				<td>${gr.email}</td>
				<td>${gr.phone}</td>
				<td>${gr.apply_date}</td>
				<c:if test="${gr.state eq 0}">
					<td>신청</td>
				</c:if>
				<c:if test="${gr.state eq 1}">
					<td>수락</td>
				</c:if>
				<td><input type="button" id="group-appro" value="수락"
					onclick="appro('${gr.uid}')"></td>
			</tr>
		</c:if>
		<c:if test="${gr.state eq 1}">
			<tr style="background-color: navy; color: white;">
				<td>${gr.nick}</td>
				<td>${gr.email}</td>
				<td>${gr.phone}</td>
				<td>${gr.apply_date}</td>
				<c:if test="${gr.state eq 0}">
					<td>신청</td>
				</c:if>
				<c:if test="${gr.state eq 1}">
					<td>수락</td>
				</c:if>
				<td><input type="button" id="group-ban" value="추방" onclick="banned('${gr.uid}')"></td>
			</tr>
		</c:if>
	</c:forEach>
</table>
<div id="manage-btn-box">
	<input type="button" value="뒤로가기" onclick="javascript:history.back()">
</div>

<input type="hidden" id="wid" value="${wid}">