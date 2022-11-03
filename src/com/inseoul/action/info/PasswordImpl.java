package com.inseoul.action.info;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.InfoModel;
import com.inseoul.util.ValidationUtil;
import com.inseoul.vo.InfoPasswordBean;

public class PasswordImpl implements ProfileCommandInter{

  static PasswordImpl impl = new PasswordImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static PasswordImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "password.jsp";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    InfoPasswordBean passwordBean = new InfoPasswordBean();
    passwordBean.setUid(Integer.parseInt(uid));
    passwordBean.setPwOld(request.getParameter("pwOld"));
    passwordBean.setPwNew(request.getParameter("pwNew"));
    passwordBean.setPwChk(request.getParameter("pwChk"));
    ValidationUtil util = new ValidationUtil();
    boolean isUpdatable = util.isUpdatable(passwordBean);
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    if (uid == null || !isUpdatable) {
      out.println("alert('입력된 정보가 양식에 맞지 않아 반려되었습니다.');");
    } else {
      int rowCount = model.upPasswordInfo(passwordBean);
      if (rowCount > 0) {
        out.println("alert('회원 정보가 수정되었습니다.');");
      } else {
        out.println("alert('회원 정보 수정에 실패하였습니다.');");
      }
    }
    out.println("history.back();");
    out.println("</script>");
    out.close();
    return null;
  }
}