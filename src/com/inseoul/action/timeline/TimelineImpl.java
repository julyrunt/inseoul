package com.inseoul.action.timeline;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.TimelineModel;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.TimelineBean;

public class TimelineImpl implements ProfileCommandInter{

  static TimelineImpl impl = new TimelineImpl();

  public static TimelineImpl instance(){
    return impl;
  }

  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int page = 1;
    int limit = 12;
    int range = 10;
    TimelineModel model = TimelineModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    String pid = request.getParameter("pid");
    if (pid == null) {
      pid = uid;
    }
    if (request.getParameter("page") != null) {
      page = Integer.parseInt(request.getParameter("page"));
    }
    PageInfo pageInfo = new PageInfo();
    pageInfo.setId(Integer.parseInt(pid));
    pageInfo.setPage(page);
    pageInfo.setLimit(limit);
    
    int listCount = model.getListCount(pageInfo);
    int maxPage = (int)((double)(listCount + limit - 1) / limit);
    int startPage = page - ((page - 1) % range);
    int endPage = Math.min(startPage + range - 1, maxPage);
    pageInfo.setEndPage(endPage);
    pageInfo.setListCount(listCount);
    pageInfo.setMaxPage(maxPage);
    pageInfo.setStartPage(startPage);   
    request.setAttribute("pageInfo", pageInfo);
    
    ArrayList<TimelineBean> list = (ArrayList<TimelineBean>) model.selectTimeline(pageInfo);
    request.setAttribute("data", list);
    return "./";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
}