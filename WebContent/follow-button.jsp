<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${profile.getUid() == uid}">
	<span class="timeline">
		<span class="material-symbols-outlined">
			person
		</span>
		<span class="symbol-label">
			프로필
		</span>
	</span>
</c:if>
<c:if test="${profile.getUid() != uid}">
	<c:if test="${profile.getMyfollowing() > 0}">
		<span class="follow-remove">
			<span class="material-symbols-outlined">
				person
			</span>
			<span class="symbol-label">
				팔로잉
			</span>
		</span>
	</c:if>
	<c:if test="${profile.getMyfollowing() == 0}">
		<span class="follow-add">
			<span class="material-symbols-outlined">
				person_add
			</span>
			<span class="symbol-label">
				팔로우
			</span>
		</span>
	</c:if>
</c:if>