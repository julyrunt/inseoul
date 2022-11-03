package com.inseoul.action.with;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.inseoul.svc.with.WithBoardWriteService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;

public class WithBoardWriteAction implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		System.out.println("WithBoardWriteAction 도착");
		ActionForward forward=null;
		WithBoardBean withBoardBean = null;
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=5*1024*1024;
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   		
		MultipartRequest multi=new MultipartRequest(request,realFolder,fileSize, "UTF-8", new DefaultFileRenamePolicy());
		String board_sel = multi.getParameter("board_sel");
				 
		String mojib_limitStr = multi.getParameter("mojib_limit").toString();
		java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(mojib_limitStr);
		Date mojib_limitDate = new Date(utilDate.getTime());
		
		String trip_startStr = multi.getParameter("trip_start").toString();
		java.util.Date tripS = new SimpleDateFormat("yyyy-MM-dd").parse(trip_startStr);
		Date trip_startDate = new Date(tripS.getTime());
		
		String trip_endStr = multi.getParameter("trip_end").toString();
		java.util.Date tripE = new SimpleDateFormat("yyyy-MM-dd").parse(trip_endStr);
		Date trip_endDate = new Date(tripE.getTime());
		
		
		withBoardBean = new WithBoardBean();
		withBoardBean.setUid(Integer.parseInt(multi.getParameter("uid")));
		withBoardBean.setTitle(multi.getParameter("title"));
		withBoardBean.setContents(multi.getParameter("contents"));
		withBoardBean.setMojib_limit(mojib_limitDate);
		withBoardBean.setTrip_start(trip_startDate);
		withBoardBean.setTrip_end(trip_endDate);
		withBoardBean.setDues(Integer.parseInt(multi.getParameter("dues")));
		withBoardBean.setMojib_person(multi.getParameter("mojib_person"));
		withBoardBean.setPhotos(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		WithBoardWriteService withBoardWriteService = new WithBoardWriteService();
		boolean isWriteSuccess = withBoardWriteService.registArticle(withBoardBean);
		System.out.println(isWriteSuccess);
		
		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('WithBoardWriteAction fail')");
			out.println("history.back();");
			out.println("</script>");
		}else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.with");
		}
		
		return forward;
	}
}
