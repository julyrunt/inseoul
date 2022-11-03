package com.inseoul.action.group;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.GroupModel;
import com.inseoul.vo.GroupListBean;
import com.inseoul.vo.PageInfo;

public class GroupParticipationImpl implements ProfileCommandInter{

  static GroupParticipationImpl impl = new GroupParticipationImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static GroupParticipationImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int page = 1;
    int limit = 8;
    int range = 10;
    GroupModel model = GroupModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    if (request.getParameter("page") != null) {
      page = Integer.parseInt(request.getParameter("page"));
    }
    PageInfo pageInfo = new PageInfo();
    pageInfo.setId(Integer.parseInt(uid));
    pageInfo.setPage(page);
    pageInfo.setLimit(limit);
    
    int listCount = model.selMyGroupCount(pageInfo);
    int maxPage = (int)((double)(listCount + limit - 1) / limit);
    int startPage = page - ((page - 1) % range);
    int endPage = Math.min(startPage + range - 1, maxPage);
    pageInfo.setEndPage(endPage);
    pageInfo.setListCount(listCount);
    pageInfo.setMaxPage(maxPage);
    pageInfo.setPage(page);
    pageInfo.setStartPage(startPage);   
    request.setAttribute("pageInfo", pageInfo);
    
    ArrayList<GroupListBean> list = (ArrayList<GroupListBean>) model.selMyGroupList(pageInfo);
    request.setAttribute("data", list);
    return "participation.jsp";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    GroupModel model = GroupModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    String group = request.getParameter("wid");
    PageInfo pageInfo = new PageInfo();
    pageInfo.setId(Integer.parseInt(uid));
    pageInfo.setGroup(Integer.parseInt(group));
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    if (uid != null && group != null) {
      boolean isDeleted = model.delGroupMember(pageInfo);
      if (isDeleted) {
        out.print("1");
      } else {
        out.print("0");
      }
    } else {
      out.print("0");
    }
    return null;
  }
}