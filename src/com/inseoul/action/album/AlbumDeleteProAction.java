package com.inseoul.action.album;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumDeleteProService;
import com.inseoul.vo.ActionForward;

public class AlbumDeleteProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{    
    ActionForward forward = null;
    String aid = request.getParameter("aid");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String nowPage = request.getParameter("page");
    AlbumDeleteProService albumDeleteProService = new AlbumDeleteProService();
    boolean isArticleWriter = albumDeleteProService.isAlbumWriter(Integer.parseInt(aid), Integer.valueOf(uid));
    if(!isArticleWriter){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out=response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
    } else {
      boolean isDeleteSuccess = albumDeleteProService.removeAlbum(Integer.parseInt(aid));
      if(!isDeleteSuccess){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<script>");
        out.println("alert('fail');");
        out.println("history.back();");
        out.println("</script>");
        out.close();
      } else{
        String pid = request.getParameter("pid");
        if (pid == null) {
          pid = uid;
        }
        String category = request.getParameter("category");
        if (category == null) {
          category = "my";
        }
        String url = "list.do?pid=" + pid + "&category=" + category;
        if (nowPage != null) {
          url += "&page=" + nowPage;
        }
        if (request.getParameter("search") != null) {
          url += "&search=" + request.getParameter("search");
        }
        if (request.getParameter("keywords") != null) {
          url += "&keywords=" + request.getParameter("keywords");
        }
        forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath(url);
      }
    }
    return forward;
  }

}