package com.inseoul.action.album;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumWriteProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.util.ValidationUtil;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.AlbumBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AlbumWriteProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    AlbumBean album = null;
    ValidationUtil util = new ValidationUtil();
    String realFolder = "";
    String saveFolder = "/albumUpload";
    int fileSize = 5 * 1024 * 1024 * 10;
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    
    ServletContext context = request.getServletContext();
    realFolder = context.getRealPath(saveFolder);
    MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,
        "UTF-8",
        new DefaultFileRenamePolicy());
    
    boolean isNumericString = util.isNumericString(multi.getParameter("location"), true);
    
    if (uid == null || !isNumericString) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else {
      album = new AlbumBean();
      album.setUid(Integer.parseInt(uid));
      album.setLid(Integer.parseInt(multi.getParameter("location")));
      album.setTitle(SafeHtml.htmlEscape(multi.getParameter("title")));
      album.setImg01(multi.getFilesystemName("img01"));
      album.setImg02(multi.getFilesystemName("img02"));
      album.setImg03(multi.getFilesystemName("img03"));
      album.setImg04(multi.getFilesystemName("img04"));
      album.setImg05(multi.getFilesystemName("img05"));
      album.setImg06(multi.getFilesystemName("img06"));
      album.setImg07(multi.getFilesystemName("img07"));
      album.setImg08(multi.getFilesystemName("img08"));
      album.setImg09(multi.getFilesystemName("img09"));
      album.setImg10(multi.getFilesystemName("img10"));
      album.setContents(SafeHtml.htmlEscape(multi.getParameter("contents")));
      
      boolean isInsertable = util.isInsertable(album);
      if (!isInsertable) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('fail')");
        out.println("history.back();");
        out.println("</script>");
      } else {
        AlbumWriteProService albumWriteProService = new AlbumWriteProService();
        boolean isWriteSuccess = albumWriteProService.registAlbum(album);
        System.out.println(isWriteSuccess);
        if(!isWriteSuccess){
          response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
          out.println("<script>");
          out.println("alert('fail')");
          out.println("history.back();");
          out.println("</script>");
        } else {
          String pid = multi.getParameter("pid");
          if (pid == null) {
            pid = uid;
          }
          String url = "list.do?pid=" + pid + "&category=my";
          forward = new ActionForward();
          forward.setRedirect(true);
          forward.setPath(url);
        }
      }
    }
    return forward;
  }   
}