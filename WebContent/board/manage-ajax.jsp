<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.inseoul.vo.*"%>
<link rel="stylesheet" type="text/css" href="../css/with-detail.css" />
<script src="../js/with-detail.js"></script>

<table>
	<tr>
		<th width="165px">닉네임</th>
		<th width="280px">이메일</th>
		<th width="180px">연락처</th>
		<th width="140px">신청일</th>
		<th width="75px">상태</th>
		<th width="60px">관리</th>
	</tr>
	<c:forEach var="i" begin="0" end="${groups.size() - 1}">
		<c:if test="${groups.get(i).getState() eq 0}">
			<tr>
				<td>${groups.get(i).getNick()}</td>
				<td>${groups.get(i).getEmail()}</td>
				<td>${groups.get(i).getPhone()}</td>
				<td>${groups.get(i).getApply_date()}</td>
				<c:if test="${groups.get(i).getState() eq 0}">
					<td>신청</td>
				</c:if>
				<c:if test="${groups.get(i).getState() eq 1}">
					<td>수락</td>
				</c:if>
				<td><input type="button" id="group-appro" value="수락"></td>
			</tr>
		</c:if>
		<c:if test="${groups.get(i).getState() eq 1}">
			<tr style="background-color: skyblue;">
				<td>${groups.get(i).getNick()}</td>
				<td>${groups.get(i).getEmail()}</td>
				<td>${groups.get(i).getPhone()}</td>
				<td>${groups.get(i).getApply_date()}</td>
				<c:if test="${groups.get(i).getState() eq 0}">
					<td>신청</td>
				</c:if>
				<c:if test="${groups.get(i).getState() eq 1}">
					<td>수락</td>
				</c:if>
				<td><input type="button" id="group-ben" value="추방"></td>
			</tr>
		</c:if>
	</c:forEach>

</table>
