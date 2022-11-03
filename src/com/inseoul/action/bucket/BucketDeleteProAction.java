package com.inseoul.action.bucket;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketDeleteProService;
import com.inseoul.vo.ActionForward;

public class BucketDeleteProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{    
    ActionForward forward = null;
    int bid = Integer.parseInt(request.getParameter("bid"));
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String nowPage = request.getParameter("page");
    BucketDeleteProService bucketDeleteProService = new BucketDeleteProService();
    boolean isArticleWriter = bucketDeleteProService.isBucketWriter(bid, Integer.valueOf(uid));
    if(!isArticleWriter){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out=response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
    } else {
      boolean isDeleteSuccess = bucketDeleteProService.removeBucket(bid);
      if(!isDeleteSuccess){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<script>");
        out.println("alert('��������');");
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