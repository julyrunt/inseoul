package com.inseoul.action.message;

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

public class MessageImpl implements ProfileCommandInter{

  static MessageImpl impl = new MessageImpl();

  public static MessageImpl instance(){
    return impl;
  }

  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    return "./";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
}