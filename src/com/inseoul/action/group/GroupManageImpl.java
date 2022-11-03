package com.inseoul.action.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.GroupModel;
import com.inseoul.vo.GroupListBean;
import com.inseoul.vo.PageInfo;

public class GroupManageImpl implements ProfileCommandInter{

  static GroupManageImpl impl = new GroupManageImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static GroupManageImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    GroupModel model = GroupModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    String wid = request.getParameter("wid");
    if (uid != null && wid != null) {
    	PageInfo pageInfo = new PageInfo();
        pageInfo.setId(Integer.parseInt(uid));
        pageInfo.setGroup(Integer.parseInt(wid));
        
        GroupListBean group = model.selGroupById(pageInfo);
        if (group.getUid() == Integer.parseInt(uid)) {
        	request.setAttribute("data", group);
        	request.getRequestDispatcher("manage.jsp").forward(request, response);
        } else {
        	response.sendRedirect("list.pr");
        }
    } else {
    	response.sendRedirect("list.pr");
    }
    return null;
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    return null;
  }
}