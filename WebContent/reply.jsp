<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="nowPage" value="${pageInfo.getPage()}" />
<c:set var="maxPage" value="${pageInfo.getMaxPage()}" />
<c:set var="startPage" value="${pageInfo.getStartPage()}" />
<c:set var="endPage" value="${pageInfo.getEndPage()}" />
<ul class="replies" rp="${nowPage}">
	<c:if test="${replyList != null && replyList.size() > 0}">
		<c:forEach var="i" begin="0" end="${replyList.size() - 1}">
			<li class="reply">
				<a href="../profile/timeline.pr?pid=${replyList.get(i).getUid()}"><img src="../profileUpload/${replyList.get(i).getPhoto()}" onerror="this.onerror=null; this.src='../images/transparent.svg';" /></a>
				<div class="reply-contents">
					<span class="reply-writer-nick"><a href="../profile/timeline.pr?pid=${replyList.get(i).getUid()}">${replyList.get(i).getNick()}</a></span>
					<span class="reply-write-date">
						${replyList.get(i).getWritedate()}
						<c:if test="${replyList.get(i).getModified() > 0}">
							&nbsp;(수정됨)
						</c:if>
					</span>
					<c:if test="${replyList.get(i).getUid() == uid}">
						<span class="reply-manage">
							<span class="reply-modify" rid="${replyList.get(i).getRid()}">
								<span class="material-symbols-outlined">
									edit
								</span>
							</span>
							<span class="reply-delete" rid="${replyList.get(i).getRid()}">
								<span class="material-symbols-outlined">
									delete
								</span>
							</span>
						</span>
					</c:if>
					<br />
					<div class="reply-text">${replyList.get(i).getContents()}</div>
				</div>
			</li>
		</c:forEach>
	</c:if>
</ul>
<div class="paging">
	<div class="paging-buttons"> 
		<c:if test="${nowPage <= 1}">
			<button type="button" value="1" disabled>처음</button>&nbsp;
			<button type="button" value="1" disabled>이전</button>&nbsp;
		</c:if>
		<c:if test="${nowPage > 1}">
			<button type="button" value="1">처음</button>&nbsp;
			<button type="button" value="${nowPage - 1}">이전</button>&nbsp;
		</c:if>
		<c:forEach var="a" begin="${startPage}" end="${endPage}">
			<c:if test="${a == nowPage}">
				<button type="button" value="${a}" disabled>${a}</button>&nbsp;
			</c:if>
			<c:if test="${a != nowPage}">
				<button type="button" value="${a}">${a}</button>&nbsp;
			</c:if>
		</c:forEach>
		<c:if test="${nowPage >= maxPage}">
			<button type="button" value="${maxPage}" disabled>다음</button>&nbsp;
			<button type="button" value="${maxPage}" disabled>마지막</button>
		</c:if>
		<c:if test="${nowPage < maxPage}">
			<button type="button" value="${nowPage + 1}">다음</button>&nbsp;
			<button type="button" value="${maxPage}">마지막</button>
		</c:if>
	</div>
</div>