     package com.inseoul.action.info;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.InfoModel;
import com.inseoul.util.ValidationUtil;
import com.inseoul.vo.InfoQuestionBean;

public class QuestionImpl implements ProfileCommandInter{

  static QuestionImpl impl = new QuestionImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static QuestionImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    InfoQuestionBean questionBean = new InfoQuestionBean();
    questionBean.setUid(Integer.parseInt(uid));
    InfoQuestionBean questionInfo = model.getQuestionInfo(questionBean);
    request.setAttribute("questionInfo", questionInfo);
    return "question.jsp";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    InfoQuestionBean questionBean = new InfoQuestionBean();
    questionBean.setUid(Integer.parseInt(uid));
    questionBean.setQuestion(request.getParameter("question"));
    questionBean.setAnswer(request.getParameter("answer"));
    ValidationUtil util = new ValidationUtil();
    boolean isUpdatable = util.isUpdatable(questionBean);
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    if (uid == null || !isUpdatable) {
      out.println("alert('????????? ????????? ????????? ?????? ?????? ?????????????????????.');");
    } else {
      int rowCount = model.upQuestionInfo(questionBean);
      if (rowCount > 0) {
        out.println("alert('?????? ????????? ?????????????????????.');");
      } else {
        out.println("alert('?????? ?????? ????????? ?????????????????????.');");
      }
    }
    out.println("history.back();");
    out.println("</script>");
    out.close();
    return null;
  }
}