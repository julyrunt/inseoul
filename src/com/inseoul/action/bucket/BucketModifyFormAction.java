package com.inseoul.action.bucket;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketDetailService;
import com.inseoul.svc.bucket.BucketWriteFormService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.BucketBean;
import com.inseoul.vo.LocationBean;

public class BucketModifyFormAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{         
    ActionForward forward = new ActionForward();
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String bid = request.getParameter("bid");
    String nowPage = request.getParameter("page");
    /*게시물 정보 취득*/
    BucketDetailService bucketDetailService = new BucketDetailService(); 
    BucketBean bucket = bucketDetailService.getBucket(Integer.parseInt(bid), Integer.parseInt(uid));
    /*관광지 정보 취득*/
    BucketWriteFormService bucketWriteFormService = new BucketWriteFormService(); 
    ArrayList<LocationBean> locationList = bucketWriteFormService.getLocationList();
    
    request.setAttribute("bucket", bucket);
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
    String url = "modify.jsp?category=" + category + "&aid=" + bid;
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