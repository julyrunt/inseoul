<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${favorite > 0}">
	<span class='material-symbols-outlined'> favorite </span>
	<br />
	추천됨
</c:if>
<c:if test="${favorite == 0}">
	<span class='material-symbols-outlined'> heart_plus </span>
	<br />
	추천하기
</c:if>