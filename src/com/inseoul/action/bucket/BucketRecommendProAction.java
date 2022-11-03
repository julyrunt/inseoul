package com.inseoul.action.bucket;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketDetailService;
import com.inseoul.svc.bucket.BucketRecommendProService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.BucketBean;

public class BucketRecommendProAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    int bid = Integer.parseInt(request.getParameter("bid"));
    HttpSession session = request.getSession();
    int uid = Integer.parseInt((String) session.getAttribute("uid"));
    BucketRecommendProService bucketRecommendProService = new BucketRecommendProService();
    boolean isRecommendSuccess = bucketRecommendProService.recommendBucket(bid, uid);
    if(!isRecommendSuccess){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else{
      BucketDetailService bucketDetailService = new BucketDetailService();
      BucketBean bucket = bucketDetailService.getBucket(bid, uid);
      request.setAttribute("favorite", bucket.getFavorite());
      forward = new ActionForward();
      forward.setPath("../recommend.jsp");
    }
    return forward;
  }
}