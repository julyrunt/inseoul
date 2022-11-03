package com.inseoul.action.album;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumDetailService;
import com.inseoul.svc.album.AlbumRecommendProService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.AlbumBean;

public class AlbumRecommendProAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    String aid = request.getParameter("aid");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    AlbumRecommendProService albumRecommendProService = new AlbumRecommendProService();
    boolean isRecommendSuccess = albumRecommendProService.recommendAlbum(Integer.parseInt(aid), Integer.parseInt(uid));
    if(!isRecommendSuccess){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else{
      AlbumDetailService albumDetailService = new AlbumDetailService();
      AlbumBean album = albumDetailService.getAlbum(Integer.parseInt(aid), Integer.parseInt(uid));
      request.setAttribute("favorite", album.getFavorite());
      forward = new ActionForward();
      forward.setPath("../recommend.jsp");
    }
    return forward;
  }
}