package com.inseoul.action.bucket;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketModifyProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.BucketBean;

public class BucketModifyProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    boolean isModifySuccess = false;
    BucketBean bucketBean = null;
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String bid = (String) request.getParameter("bid");
    BucketModifyProService bucketModifyProService = new BucketModifyProService();
    boolean isRightUser = bucketModifyProService.isBucketWriter(Integer.parseInt(bid), Integer.parseInt(uid));
    if(!isRightUser){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out=response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
    } else {
      bucketBean = new BucketBean();
      bucketBean.setBid(Integer.parseInt(bid));
      bucketBean.setLid(Integer.parseInt(request.getParameter("location")));
      bucketBean.setItem(SafeHtml.htmlEscape(request.getParameter("item")));
      bucketBean.setProgress(Integer.parseInt(request.getParameter("progress")));
      isModifySuccess = bucketModifyProService.modifyBucket(bucketBean);
      if(!isModifySuccess){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<script>");
        out.println("alert('버킷 리스트 수정에 실패했습니다.');");
        out.println("history.back()");
        out.println("</script>");
      } else{
        forward = new ActionForward();
        forward.setRedirect(true);
        String pid = request.getParameter("pid");
        if (pid == null) {
          pid = uid;
        }
        String category = request.getParameter("category");
        if (category == null) {
          category = "my";
        }
        String url = "view.do?pid=" + pid + "&category=" + category + "&bid=" + bid;
        if (request.getParameter("page") != null) {
          url += "&page=" + request.getParameter("page");
        }
        if (request.getParameter("search") != null) {
          url += "&search=" + request.getParameter("search");
        }
        if (request.getParameter("keywords") != null) {
          url += "&keywords=" + request.getParameter("keywords");
        }
        forward.setPath(url); 
      }
    }
    return forward;
  }
}