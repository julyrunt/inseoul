package com.inseoul.action.follow;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.dao.ConnectDB;
import com.inseoul.svc.follow.FollowProService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.ProfileBean;

public class FollowProAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    String pid = request.getParameter("pid");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    FollowProService followProService = new FollowProService();
    boolean isFollowSuccess = followProService.updateFollow(Integer.parseInt(uid), Integer.parseInt(pid));
    if(!isFollowSuccess){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else{
      ConnectDB conn = new ConnectDB();
      ProfileBean profile = conn.profileInfoById(pid, uid);
      request.setAttribute("profile", profile);
      forward = new ActionForward();
      forward.setPath("follow-button.jsp");
    }
    return forward;
  }
}