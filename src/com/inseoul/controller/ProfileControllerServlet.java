package com.inseoul.controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.action.group.*;
import com.inseoul.action.info.*;
import com.inseoul.action.profile.*;
import com.inseoul.action.receipt.*;
import com.inseoul.action.timeline.*;

public class ProfileControllerServlet extends HttpServlet{

  /**
   * 
   */
  private static final long serialVersionUID = 6771948519106116685L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    String RequestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String command = RequestURI.substring(contextPath.length());
    /* ----------------------------------------------------------------------------------------------------
     * 로그인 상태에 한하여 페이지 이동을 진행한다.
     * ----------------------------------------------------------------------------------------------------
     * 비로그인 상태인 경우 로그인 페이지로 이동한다.
     * ---------------------------------------------------------------------------------------------------- */
    if (uid == null) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('로그인 페이지로 이동합니다.');");
      out.println("location.href = '../log-in';");
      out.println("</script>");
      out.close();
    } else {
      ProfileCommandInter inter = null;
      String viewName = "";
      try {
        if(command.equals("/profile/timeline.pr")){
          inter = TimelineImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/receipt/list.pr")) {
          inter = ReceiptListImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/receipt/view.pr")) {
          inter = ReceiptViewImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/group/list.pr")) {
          inter = GroupListImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/group/manage.pr")) {
          inter = GroupManageImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/group/participation.pr")) {
          inter = GroupParticipationImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/group/apply.pr")) {
          inter = GroupApplyImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/group/manageCancel.pr")) { 
          inter = GroupListImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/group/participationCancel.pr")) { 
          inter = GroupParticipationImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/group/applyCancel.pr")) { 
          inter = GroupApplyImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/info/general.pr")) {
          inter = GeneralImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/info/generalPro.pr")) {
          inter = GeneralImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/info/address.pr")) {
          inter = AddressImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/info/addressPro.pr")) {
          inter = AddressImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/info/password.pr")) {
          viewName = "password.jsp";
        } else if(command.equals("/info/passwordPro.pr")) {
          inter = PasswordImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/info/question.pr")) {
          inter = QuestionImpl.instance();
          viewName = inter.showData(request, response);
        } else if(command.equals("/info/questionPro.pr")) {
          inter = QuestionImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/info/withdrawal.pr")) {
          viewName = "withdrawal.jsp";
        } else if(command.equals("/info/withdrawalPro.pr")) {
          inter = WithdrawalImpl.instance();
          viewName = inter.runData(request, response);
        } else if(command.equals("/backgroundEditPro.pr")) {
          inter = BackgroundEditProImpl.instance();
          viewName = inter.showData(request, response);
        } else {
          viewName = "../error.jsp";
        }
        /* ----------------------------------------------------------------------------------------------------
         * 이동할 주소를 반환한 경우에 한하여 실행한다.
         * ---------------------------------------------------------------------------------------------------- */
        if (viewName != null) {
          request.getRequestDispatcher(viewName).forward(request, response);
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }
}



