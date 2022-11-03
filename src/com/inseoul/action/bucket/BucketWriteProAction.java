package com.inseoul.action.bucket;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketWriteProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.BucketBean;

public class BucketWriteProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    BucketBean bucketBean = null;
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    bucketBean = new BucketBean();
    bucketBean.setUid(Integer.parseInt(uid));
    bucketBean.setLid(Integer.valueOf(request.getParameter("location")));
    bucketBean.setItem(SafeHtml.htmlEscape(request.getParameter("item")));
    
    BucketWriteProService bucketWriteProService = new BucketWriteProService();
    boolean isWriteSuccess = bucketWriteProService.registBucket(bucketBean);
    System.out.println(isWriteSuccess);
    if(!isWriteSuccess){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else {
      String pid = request.getParameter("pid");
      if (pid == null) {
        pid = uid;
      }
      String url = "list.do?pid=" + pid + "&category=my";
      forward = new ActionForward();
      forward.setRedirect(true);
      forward.setPath(url);
    }
    return forward;
  }   
}