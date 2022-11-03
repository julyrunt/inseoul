<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="conn" class="com.inseoul.dao.ConnectDB" />
<c:if test="${param.pid != null}">
	<c:set var="pid" value="${param.pid}" />
</c:if>
<c:if test="${param.pid == null}">
	<c:set var="pid" value="${uid}" />
</c:if>
<c:set var="profile" value="${conn.profileInfoById(pid, uid)}" />
<div class="profile-background" style="background-image: url('../profileUpload/${profile.getBackground()}')">
	<div class="profile-header" pid=${pid}>
		<div class="photo">
			<div style="background-image: url('../profileUpload/${profile.getPhoto()}'), url('../images/transparent.svg');" ></div>
			<div>
				<c:if test="${pid == uid}">
					<span class="timeline">
						<span class="material-symbols-outlined">
							person
						</span>
						<span class="symbol-label">
							타임라인
						</span>
					</span>
				</c:if>
				<c:if test="${pid != uid}">
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
			</div>
		</div>
		<div class="info">
			<ul>
				<li>
					<a href="../profile/timeline.pr?pid=${pid}">
						<span><b>${profile.getNick()}</b></span>
					</a>
				</li>
				<li class="introduction">
					<span>${profile.getIntroduction()}</span>
					<c:if test="${pid == uid}">
						<span id="introduction-edit" class="material-symbols-outlined">
							settings
						</span>
					</c:if>
				</li>
				<li>
					<span class="material-symbols-outlined">
						calendar_month
					</span>
					<span>가입일시 : ${profile.getJoindate()}</span>
				</li>
				<li>
					<ul class="follow">
						<li>
							<a href="../following/list.do?pid=${pid}"><b>${profile.getFollowingCount()}</b> 팔로우 중</a>
						</li>
						<li>
							<a href="../follower/list.do?pid=${pid}"><b>${profile.getFollowerCount()}</b> 팔로워</a>
						</li>
					</ul>
				</li>
<!-- 				<li>
					<ul class="badge">
						<li>뱃지</li>
						<li>뱃지</li>
						<li>뱃지</li>
						<li>뱃지</li>
					</ul>
				</li> -->
			</ul>
		</div>
		<c:if test="${pid == uid}">
			<div class="profile-header-manage">
				<span id="background-image-change" class="material-symbols-outlined">
					add_photo_alternate
				</span>
			</div>
		</c:if>
	</div>
</div>