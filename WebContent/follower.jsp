<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img src="../profileUpload/${profile.getPhoto()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" />
<div class="follow-contents">
	<span class="follow-nick">${profile.getNick()}</span>
	<span class="follow-date">
		${profile.getFollowerDate()}
	</span>
	<div class="follow-introduction">${profile.getIntroduction()}</div>
	<c:if test="${profile.getMyfollowing() > 0}">
		<span class="follow-info">
			${profile.getNick()}님은 내가 팔로우하는 사용자 입니다.
		</span>
	</c:if>
</div>
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