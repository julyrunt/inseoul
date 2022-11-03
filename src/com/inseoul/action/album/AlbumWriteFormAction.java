package com.inseoul.action.album;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumWriteFormService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.LocationBean;

public class AlbumWriteFormAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 관광지 선택을 위한 콜렉션을 취득하여 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = new ActionForward();
    AlbumWriteFormService albumWriteFormService = new AlbumWriteFormService(); 
    ArrayList<LocationBean> locationList = albumWriteFormService.getLocationList();
    request.setAttribute("locationList", locationList);
    HttpSession session = request.getSession();
    String pid = (String) session.getAttribute("uid");
    forward.setPath("/gallery/write.jsp?pid=" + pid);
    return forward;
  }

}