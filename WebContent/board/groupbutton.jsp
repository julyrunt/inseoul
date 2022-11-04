<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/with-detail.js"></script>


<c:choose>
	<c:when test="${stateCount == 0}">
		<div id="cancled">
			<img src="../images/goodbye.png">
			<span>그룹 취소</span>
		</div>
	</c:when>
	<c:when test="${stateCount > 0}">
		<div id="wait-btn">
			<img src="../images/group_wait.png">
			<span>신청대기중</span>
		</div>
	</c:when>
</c:choose>

