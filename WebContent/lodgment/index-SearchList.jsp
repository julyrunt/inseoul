<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div id="lodgment-title">
	<h3>숙소 리스트</h3>
</div>
<ul>
	<c:forEach var="i" begin="0" end="${list.size() - 1}">
		<a href="lodgment-detail.lm?hid=${list.get(i).getHid()}">
			<li>
				<div class="lodgment-contents"
					style="background-image: url('images/${list.get(i).getMain_photo()}');">
					<div class="contents-info">
						<div class="ratingbox">
							<c:set var="businessRating" value="7" />
							<c:choose>
								<c:when test="${businessRating eq list.get(i).getRating()}">
									<span>비지니스</span>
								</c:when>
								<c:otherwise>
									<span>${list.get(i).getRating()}성급</span>
								</c:otherwise>
							</c:choose>
						</div>
						<h2>${list.get(i).getName()}</h2>
						<h3>${list.get(i).getRegion()}</h3>
					</div>
					<div class="pricebox">
						<h2>
							<fmt:formatNumber value="${list.get(i).getMain_price()}"
								pattern="#,###,###" />
							원
						</h2>
					</div>
					<div class="img-brightbox"></div>
				</div>
		</li>
		</a>
	</c:forEach>
</ul>