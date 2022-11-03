package com.inseoul.action.info;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.InfoModel;
import com.inseoul.vo.UserBean;

public class WithdrawalImpl implements ProfileCommandInter{

  static WithdrawalImpl impl = new WithdrawalImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static WithdrawalImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "withdrawal.jsp";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    UserBean user = new UserBean();
    user.setUid(Integer.parseInt(uid));
    user.setPw(request.getParameter("pw"));
    int rowCount = model.delUserInfo(user);
    if (rowCount > 0) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('회원 탈퇴가 완료되었습니다.');");
      out.println("location.href = '../';");
      out.println("</script>");
      out.close();
    } else {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
    }
    return null;
  }
}