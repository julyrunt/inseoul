package com.inseoul.action.receipt;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.ReceiptModel;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.ReceiptBean;

public class ReceiptListImpl implements ProfileCommandInter{

  static ReceiptListImpl impl = new ReceiptListImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static ReceiptListImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int page = 1;
    int limit = 20;
    int range = 10;
    String category = "all";
    ReceiptModel model = ReceiptModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    if (request.getParameter("page") != null) {
      page = Integer.parseInt(request.getParameter("page"));
    }
    if (request.getParameter("category") != null) {
      category = request.getParameter("category");
    }
    PageInfo pageInfo = new PageInfo();
    pageInfo.setId(Integer.parseInt(uid));
    pageInfo.setPage(page);
    pageInfo.setLimit(limit);
    pageInfo.setCategory(category);
    
    int listCount = model.getListCount(pageInfo);
    int maxPage = (int)((double)(listCount + limit - 1) / limit);
    int startPage = page - ((page - 1) % range);
    int endPage = Math.min(startPage + range - 1, maxPage);
    pageInfo.setEndPage(endPage);
    pageInfo.setListCount(listCount);
    pageInfo.setMaxPage(maxPage);
    pageInfo.setPage(page);
    pageInfo.setStartPage(startPage);   
    request.setAttribute("pageInfo", pageInfo);
    
    ArrayList<ReceiptBean> list = (ArrayList<ReceiptBean>) model.selectReceiptList(pageInfo);
    request.setAttribute("data", list);
    return "./";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return null;
  }
}