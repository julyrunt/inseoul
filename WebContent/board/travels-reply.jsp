<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="replylistCount" value="${replypageInfo.getListCount()}" />
<c:set var="replynowPage" value="${replypageInfo.getPage()}" />
<c:set var="replymaxPage" value="${replypageInfo.getMaxPage()}" />
<c:set var="replystartPage" value="${replypageInfo.getStartPage()}" />
<c:set var="replyendPage" value="${replypageInfo.getEndPage()}" />

<c:forEach var="reply" varStatus="status" items="${replylist}">

	<div class="travels-detail-replybox-one">
		<div class="travels-detail-replybox-photo">
			<img alt="프로필사진" src="../profileUpload/${reply.photo}">
		</div>
		<div class="travels-detail-replybox-contents">
			<p>작성자 &nbsp;${reply.nick}</p>
			<span>${reply.writedate}</span>
			<textarea readonly>${reply.contents}</textarea>
			<c:if test="${uid != null}">
				<c:if test="${uid eq reply.uid}">
					<div class="travels-detail-replydeletebox">
						<span class="clear-box" rerid="${reply.rid}"> <span
							class="material-icons" title="삭제"> clear </span>
						</span>
					</div>
				</c:if>
				<c:if test="${uid != reply.uid}">
					<div class="travels-detail-replydeletebox"></div>
				</c:if>
			</c:if>
			<c:if test="${uid eq null}">
				<div class="travels-detail-replydeletebox"></div>
			</c:if>
		</div>
	</div>
</c:forEach>
<section id="pageList">
	<c:if test="${replynowPage <= 1}">
								[처음]&nbsp;[이전]&nbsp;
							</c:if>
	<c:if test="${replynowPage > 1}">
		<a href="travelsDetail.tv?page=1">[처음]</a>&nbsp;
								<a href="travelsDetail.tv?page=${replynowPage - 1}">[이전]</a>&nbsp;
							</c:if>
	<c:forEach var="a" begin="${replystartPage}" end="${replyendPage}">
		<c:if test="${a eq replynowPage}">
									[${a}]&nbsp;
								</c:if>
		<c:if test="${a != replynowPage}">
			<a href="travelsDetail.tv?page=${a}">[${a}]</a>&nbsp;
								</c:if>
	</c:forEach>
	<c:if test="${replynowPage >= replymaxPage}">
								[다음]
							</c:if>
	<c:if test="${replynowPage < replymaxPage}">
		<a href="travelsDetail.tv?page=${replynowPage + 1}">[다음]</a>
		<a href="travelsDetail.tv?page=${replymaxPage}">[마지막]</a>
	</c:if>
</section>