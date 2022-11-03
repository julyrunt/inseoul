package com.inseoul.action.album;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumModifyProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.AlbumBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AlbumModifyProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    boolean isModifySuccess = false;
    AlbumBean albumBean = null;
    String realFolder = "";
    String saveFolder = "/albumUpload";
    int fileSize = 5 * 1024 * 1024 * 10;
    ServletContext context = request.getServletContext();
    realFolder = context.getRealPath(saveFolder);
    MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize,
        "UTF-8",
        new DefaultFileRenamePolicy());
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String aid = (String) multi.getParameter("aid");
    AlbumModifyProService albumModifyProService = new AlbumModifyProService();
    boolean isRightUser = albumModifyProService.isAlbumWriter(Integer.parseInt(aid), Integer.parseInt(uid));
    if(!isRightUser){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out=response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
    } else {
      albumBean = new AlbumBean();
      albumBean.setAid(Integer.parseInt(aid));
      albumBean.setUid(Integer.parseInt(uid));
      albumBean.setLid(Integer.valueOf(multi.getParameter("location")));
      albumBean.setTitle(SafeHtml.htmlEscape(multi.getParameter("title")));
      Enumeration<String> files = multi.getFileNames();
      int size =  Collections.list(files).size();
      albumBean.setImg01(multi.getFilesystemName("img01"));
      if (size > 1) {
        albumBean.setImg02(multi.getFilesystemName("img02"));
      } else {
        albumBean.setImg02("remove");
      }
      if (size > 2) {
        albumBean.setImg03(multi.getFilesystemName("img03"));
      } else {
        albumBean.setImg03("remove");
      }
      if (size > 3) {
        albumBean.setImg04(multi.getFilesystemName("img04"));
      } else {
        albumBean.setImg04("remove");
      }
      if (size > 4) {
        albumBean.setImg05(multi.getFilesystemName("img05"));
      } else {
        albumBean.setImg05("remove");
      }
      if (size > 5) {
        albumBean.setImg06(multi.getFilesystemName("img06"));
      } else {
        albumBean.setImg06("remove");
      }
      if (size > 6) {
        albumBean.setImg07(multi.getFilesystemName("img07"));
      } else {
        albumBean.setImg07("remove");
      }
      if (size > 7) {
        albumBean.setImg08(multi.getFilesystemName("img08"));
      } else {
        albumBean.setImg08("remove");
      }
      if (size > 8) {
        albumBean.setImg09(multi.getFilesystemName("img09"));
      } else {
        albumBean.setImg09("remove");
      }
      if (size > 9) {
        albumBean.setImg10(multi.getFilesystemName("img10"));
      } else {
        albumBean.setImg10("remove");
      }
      albumBean.setContents(SafeHtml.htmlEscape(multi.getParameter("contents")));
      isModifySuccess = albumModifyProService.modifyAlbum(albumBean);
      if(!isModifySuccess){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('앨범 수정에 실패했습니다.');");
        out.println("history.back()");
        out.println("</script>");
      } else{
        forward = new ActionForward();
        forward.setRedirect(true);
        
        String pid = multi.getParameter("pid");
        if (pid == null) {
          pid = uid;
        }
        String category = multi.getParameter("category");
        if (category == null) {
          category = "my";
        }
        String url = "view.do?pid=" + pid + "&category=" + category + "&aid=" + aid;
        if (multi.getParameter("page") != null) {
          url += "&page=" + multi.getParameter("page");
        }
        if (multi.getParameter("search") != null) {
          url += "&search=" + multi.getParameter("search");
        }
        if (multi.getParameter("keywords") != null) {
          url += "&keywords=" + multi.getParameter("keywords");
        }
        forward.setPath(url); 
      }
    }
    return forward;
  }
}