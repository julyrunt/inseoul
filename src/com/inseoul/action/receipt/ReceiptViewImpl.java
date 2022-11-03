package com.inseoul.action.receipt;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.ReceiptModel;
import com.inseoul.vo.ReceiptBean;

public class ReceiptViewImpl implements ProfileCommandInter{

  static ReceiptViewImpl impl = new ReceiptViewImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static ReceiptViewImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String url = null;
    ReceiptModel model = ReceiptModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    String rid = request.getParameter("rid");
    
    if (uid != null && rid != null) {
      ReceiptBean receiptBean = new ReceiptBean();
      receiptBean.setReservation_uid(Integer.parseInt(uid));
      receiptBean.setReservation_num(Integer.parseInt(rid));
      ReceiptBean receiptInfo = model.selectReceipt(receiptBean);
      if (receiptInfo != null) {
        request.setAttribute("receiptInfo", receiptInfo);
        url = "view.jsp";
      }
    }
    if (url == null) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('해당 예약 확인 페이지를 열람할 수 없습니다.');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
    }
    return url;
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return null;
  }
}