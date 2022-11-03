package com.inseoul.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.action.album.*;
import com.inseoul.action.bucket.*;
import com.inseoul.action.follow.*;
import com.inseoul.action.routemap.RoutemapListAction;
import com.inseoul.action.travels.TravelsListAction;
import com.inseoul.vo.ActionForward;

@WebServlet("*.do")
public class ProfileFrontController extends javax.servlet.http.HttpServlet {
  /**
   * 
   */
  private static final long serialVersionUID = 3350449680983508044L;
  /**
   * 호출된 페이지 주소에 따라서 서블릿 페이지를 작성해 작업을 수행하고 그 결과에 따라 페이지를 이동합니다.
   */
  protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String RequestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String command = RequestURI.substring(contextPath.length());
    ActionForward forward = null;
    Action action = null;
    
    if (uid == null) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('로그인 페이지로 이동합니다.');");
      out.println("location.href = '../log-in';");
      out.println("</script>");
      out.close();
    } else {
      switch (command) {
        case "/followbutton.do":
          action = new FollowProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/follower/list.do":
          action = new FollowerListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/follower/follow.do":
          action = new FollowerProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/following/list.do":
          action = new FollowingListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/following/follow.do":
          action = new FollowingProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/list.do":
          action = new BucketListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/view.do":
          action = new BucketDetailAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/write.do":
          action = new BucketWriteFormAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/bucketWritePro.do":
          action = new BucketWriteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/modify.do":
          action = new BucketModifyFormAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/bucketModifyPro.do":
          action = new BucketModifyProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/delete.do":
          action = new BucketDeleteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/reply.do":
          action = new BucketReplyListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/replyWrite.do":
          action = new BucketReplyWriteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/replyModify.do":
          action = new BucketReplyModifyProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/replyDelete.do":
          action = new BucketReplyDeleteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/bucket-list/recommend.do":
          action = new BucketRecommendProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/list.do":
          action = new AlbumListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/view.do":
          action = new AlbumDetailAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/write.do":
          action = new AlbumWriteFormAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/albumWritePro.do":
          action = new AlbumWriteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/modify.do":
          action = new AlbumModifyFormAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/albumModifyPro.do":
          action = new AlbumModifyProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/delete.do":
          action = new AlbumDeleteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/reply.do":
          action = new AlbumReplyListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/replyWrite.do":
          action = new AlbumReplyWriteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/replyModify.do":
          action = new AlbumReplyModifyProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/replyDelete.do":
          action = new AlbumReplyDeleteProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/gallery/recommend.do":
          action = new AlbumRecommendProAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/routemap/list.do":
          action = new RoutemapListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/travels/list.do":
          action = new TravelsListAction();
          try {
            forward = action.execute(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        default :
          forward = new ActionForward();
          forward.setPath("../error.jsp");
          break;
      }
    }
    if(forward != null){
      if(forward.isRedirect()){
        response.sendRedirect(forward.getPath());
      }else{
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
        dispatcher.forward(request, response);
      }
    }
  }
  /* ----------------------------------------------------------------------------------------------------
   * do 메소드로 호출된 경우 doProcess()로 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request,response);
  }     
  /* ----------------------------------------------------------------------------------------------------
   * get 메소드로 호출된 경우 doProcess()로 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request,response);
  }   

}