<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.inseoul.dao.ConnectDB"%>
<%@page import="com.inseoul.vo.ProfileBean"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
  request.setCharacterEncoding("UTF-8");
  String uid = (String) session.getAttribute("uid");
  String introduction = request.getParameter("introduction");
  ConnectDB conn = new ConnectDB();
  ProfileBean profile = conn.profileInfoById(uid, uid);
  /* 40자를 넘지 않는 경우에만 DB로 값을 전송한다. */
  if (introduction.length() <= 40) {
    String result = conn.updateIntroduction(uid, introduction);
    if (result != null) {
      out.print(result);
    } else {
      out.print(profile.getIntroduction());
    }
  } else {
    out.print(profile.getIntroduction());
  }
%>