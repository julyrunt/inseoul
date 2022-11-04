<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/travels-detail.js"></script>
<!DOCTYPE html>

<c:if test="${likeCount == 0}">
	<img alt="like" src="../images/like_a.png" class="likes">
</c:if>
<c:if test="${likeCount > 0}">
	<img alt="like" src="../images/like_b.png" class="likes">
</c:if>