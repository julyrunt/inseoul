<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${param.pid != null}">
	<c:set var="pid" value="${param.pid}" />
</c:if>
<c:if test="${param.pid == null}">
	<c:set var="pid" value="${uid}" />
</c:if>
<nav class="board-nav">
	<ul>
		<li>
			<a href="../profile/timeline.pr?pid=${pid}">
				<span>
					타임라인
				</span>
			</a>
		</li>
		<li>
			<a href="../routemap/list.do?pid=${pid}&category=my">
				<span>
					루트맵
				</span>
			</a>
		</li>
		<li>
			<a href="../travels/list.do?pid=${pid}&category=my">
				<span>
					여행기
				</span>
			</a>
		</li>
		<li>
			<a href="../gallery/list.do?pid=${pid}&category=my">
				<span>
					갤러리
				</span>
			</a>
		</li>
		<li>
			<a href="../bucket-list/list.do?pid=${pid}&category=my">
				<span>
					버킷리스트
				</span>
			</a>
		</li>
	</ul>
</nav>