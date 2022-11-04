<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.inseoul.vo.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.inseoul.vo.WithBoardBean"%>
<%
	String uid = (String) session.getAttribute("uid");
	String nowPage = (String) request.getAttribute("page");
	ArrayList<WithBoardBean> replyList = (ArrayList<WithBoardBean>) request.getAttribute("replyList");
	WithBoardBean article = (WithBoardBean) request.getAttribute("article");
	PageInfo replypageInfo = (PageInfo) request.getAttribute("replypageInfo");
	int replylistCount = replypageInfo.getListCount();
	int replynowPage = replypageInfo.getPage();
	int replymaxPage = replypageInfo.getMaxPage();
	int replystartPage = replypageInfo.getStartPage();
	int replyendPage = replypageInfo.getEndPage();
%>
<%
	for (int i = 0; i < replyList.size(); i++) {
%>
<div class="with-detail-replybox-one">
	<div class="with-detail-replybox-photo">
		<img alt="프로필사진" src="../profileUpload/<%=replyList.get(i).getPf()%>">
	</div>
	<div class="with-detail-replybox-contents">
		<p>
			작성자 &nbsp;<%=replyList.get(i).getNick()%></p>
		<span><%=replyList.get(i).getWritedate()%></span>
		<textarea readonly><%=replyList.get(i).getContents()%></textarea>
		<%
			if (uid != null) {
					if (uid.equals(String.valueOf(replyList.get(i).getUid()))) {
		%>
		<div class="with-detail-replydeletebox">
			<span class="clear-box" rewid=<%=replyList.get(i).getWid()%>>
			<span class="material-icons" title="삭제"> clear </span></span>
		</div>
		<%
			} else {
		%>
		<div class="with-detail-replydeletebox"></div>
		<%
			}
				} else {
		%>
		<div class="with-detail-replydeletebox"></div>
		<%
			}
		%>
	</div>
</div>
<%
	}
%>
<section id="pageList">
	<%
		if (replynowPage <= 1) {
	%>
	[이전]&nbsp;
	<%
		} else {
	%>
	<a
		href="withDetail.with?replypage=<%=replynowPage - 1%>&ref=<%=article.getRef()%>&wid=<%=article.getWid()%>&page=<%=nowPage%>">[이전]</a>&nbsp;
	<%
		}
	%>

	<%
		for (int a = replystartPage; a <= replyendPage; a++) {
			if (a == replynowPage) {
	%>
	[<%=a%>]&nbsp;
	<%
		} else {
	%>
	<a
		href="withDetail.with?replypage=<%=a%>&ref=<%=article.getRef()%>&wid=<%=article.getWid()%>&page=<%=nowPage%>">[<%=a%>]
	</a>&nbsp;
	<%
		}
		}
	%>

	<%
		if (replynowPage >= replymaxPage) {
	%>
	[다음]
	<%
		} else {
	%>
	<a
		href="withDetail.with?replypage=<%=replynowPage + 1%>&ref=<%=article.getRef()%>&wid=<%=article.getWid()%>&page=<%=nowPage%>">[다음]</a>
	<%
		}
	%>
</section>
