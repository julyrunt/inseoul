<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.inseoul.vo.UserBean"%>
<%@page import="com.inseoul.util.ValidationUtil"%>
<c:if test="${uid != null}">
	<c:redirect url="../" />
</c:if>
<c:if test="${uid == null}">
	<%
		int fileSize = 5 * 1024 * 1024;
		String realFolder = "", saveFolder = "/profileUpload";
		
		UserBean user = new UserBean();
		ValidationUtil util = new ValidationUtil();
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		user.setPhoto(multi.getFilesystemName("fil-photo"));
		String email = String.format("%s@%s", multi.getParameter("txt-email"),
			multi.getParameter("txt-domain"));
		user.setEmail(email);
		user.setPw(multi.getParameter("pw-input"));
		user.setName(multi.getParameter("txt-name"));
		user.setNick(multi.getParameter("txt-nick"));
		String birth = String.format("%04d-%s-%02d", new Integer(multi.getParameter("num-year")),
			multi.getParameter("sel-month"), new Integer(multi.getParameter("num-day")));
		user.setBirth(birth);
		user.setNation(multi.getParameter("nationNo"));
		user.setPhone(multi.getParameter("num-phone"));
		user.setAddr(multi.getParameter("txt-addr"));
		user.setDetail(multi.getParameter("txt-detail"));
		user.setQuestion(multi.getParameter("txt-question"));
		user.setAnswer(multi.getParameter("txt-answer"));
		
		boolean isInsertable = util.isInsertable(user);
		if (!isInsertable) {
			response.sendRedirect("./");
		} else {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				/* DB 연결시 문자 인코딩 속성 값을 함께 보낸다. */
				conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/inseoul?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false",
					"root", "1234");
				String sql =
					"insert into `users` (`photo`, `email`, `pw`, `name`, `nick`, `birth`, `nation`, `phone`, `addr`, `detail`, `question`, `answer`) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getPhoto());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getPw());
				pstmt.setString(4, user.getName());
				pstmt.setString(5, user.getNick());
				pstmt.setString(6, user.getBirth());
				pstmt.setString(7, user.getNation());
				pstmt.setString(8, user.getPhone());
				pstmt.setString(9, user.getAddr());
				pstmt.setString(10, user.getDetail());
				pstmt.setString(11, user.getQuestion());
				pstmt.setString(12, user.getAnswer());
	
				int rowNum = pstmt.executeUpdate();
				if (rowNum > 0) {
					response.sendRedirect("../log-in");
				} else {
					response.sendRedirect("./");
				}
			} finally {
				try {
					conn.close();
				} catch (Exception e) {
	
				}
				try {
					pstmt.close();
				} catch (Exception e) {
	
				}
			}
		}
	%>
</c:if>