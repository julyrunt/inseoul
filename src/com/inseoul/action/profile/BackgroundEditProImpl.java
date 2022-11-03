package com.inseoul.action.profile;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.ProfileModel;
import com.inseoul.vo.UserBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BackgroundEditProImpl implements ProfileCommandInter{

  static BackgroundEditProImpl impl = new BackgroundEditProImpl();

  public static BackgroundEditProImpl instance(){
    return impl;
  }

  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String realFolder = "";
    String saveFolder = "/profileUpload";
    int fileSize = 5 * 1024 * 1024 * 1;
    ProfileModel model = ProfileModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    ServletContext context = request.getServletContext();
    realFolder = context.getRealPath(saveFolder);
    MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,
        "UTF-8",
        new DefaultFileRenamePolicy());
    UserBean user = new UserBean();
    user.setUid(Integer.parseInt(uid));
    user.setBackground(multi.getFilesystemName("image"));
    int result = model.updateBackground(user);
    if (result > 0) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out=response.getWriter();
      out.println("<script>");
      out.println("alert('프로필 백그라운드 이미지가 성공적으로 변경되었습니다.');");
      out.println("opener.parent.location.reload();");
      out.println("window.close();");
      out.println("</script>");
      out.close();
    } else {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out=response.getWriter();
      out.println("<script>");
      out.println("alert('프로필 백그라운드 이미지 업로드에 실패했습니다.');");
      out.println("window.close();");
      out.println("</script>");
    }
    return "./";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
}