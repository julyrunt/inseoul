package com.inseoul.action.info;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.InfoModel;
import com.inseoul.util.ValidationUtil;
import com.inseoul.vo.InfoGeneralBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GeneralImpl implements ProfileCommandInter{

  static GeneralImpl impl = new GeneralImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static GeneralImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    InfoGeneralBean generalBean = new InfoGeneralBean();
    generalBean.setUid(Integer.parseInt(uid));
    InfoGeneralBean generalInfo = model.getGeneralInfo(generalBean);
    request.setAttribute("generalInfo", generalInfo);
    return "general.jsp";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    
    int fileSize = 5 * 1024 * 1024;
    String realFolder = "", saveFolder = "/profileUpload";
    InfoGeneralBean generalBean = new InfoGeneralBean();
    
    ServletContext context = request.getServletContext();
    realFolder = context.getRealPath(saveFolder);
    MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
    
    generalBean.setUid(Integer.parseInt(uid));
    generalBean.setPhoto(multi.getFilesystemName("photo"));
    generalBean.setNick(multi.getParameter("nick"));
    
    ValidationUtil util = new ValidationUtil();
    boolean isUpdatable = util.isUpdatable(generalBean);
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    if (uid == null || !isUpdatable) {
      out.println("alert('입력된 정보가 양식에 맞지 않아 반려되었습니다.');");
      out.println("history.back();");
    } else {
      int rowCount = model.upGeneralInfo(generalBean);
      if (rowCount > 0) {
        out.println("alert('회원 정보가 수정되었습니다.');");
        out.println("location.href = './general.pr';");
      } else {
        out.println("alert('회원 정보 수정에 실패하였습니다.');");
        out.println("history.back();");
      }
    }
    out.println("</script>");
    out.close();
    return null;
  }
}