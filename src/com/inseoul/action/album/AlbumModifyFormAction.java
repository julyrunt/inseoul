package com.inseoul.action.album;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumDetailService;
import com.inseoul.svc.album.AlbumWriteFormService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.AlbumBean;
import com.inseoul.vo.LocationBean;

public class AlbumModifyFormAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{         
    ActionForward forward = new ActionForward();
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String aid = request.getParameter("aid");
    String nowPage = request.getParameter("page");
    /*앨범 게시물 정보 취득*/
    AlbumDetailService albumDetailService = new AlbumDetailService(); 
    AlbumBean album = albumDetailService.getAlbum(Integer.parseInt(aid), Integer.parseInt(uid));
    /*관광지 정보 취득*/
    AlbumWriteFormService albumWriteFormService = new AlbumWriteFormService(); 
    ArrayList<LocationBean> locationList = albumWriteFormService.getLocationList();
    
    request.setAttribute("album", album);
    request.setAttribute("nowPage", nowPage);
    request.setAttribute("locationList", locationList);
    
    String pid = request.getParameter("pid");
    if (pid == null) {
      pid = uid;
    }
    String category = request.getParameter("category");
    if (category == null) {
      category = "my";
    }
    String url = "modify.jsp?category=" + category + "&aid=" + aid;
    if (nowPage != null) {
      url += "&page=" + nowPage;
    }
    if (request.getParameter("search") != null) {
      url += "&search=" + request.getParameter("search");
    }
    if (request.getParameter("keywords") != null) {
      url += "&keywords=" + request.getParameter("keywords");
    }
    forward.setPath(url);
    return forward;
  }

}