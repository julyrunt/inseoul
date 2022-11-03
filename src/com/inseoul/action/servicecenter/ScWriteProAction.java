package com.inseoul.action.servicecenter;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


import com.inseoul.svc.servicecenter.ScWriteProService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.Servicecenter;

public class ScWriteProAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward forward=null;
		Servicecenter servicecenter = null;
		String realFolder="";
		String saveFolder="/scUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartRequest multi=new MultipartRequest(request,realFolder,fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		servicecenter = new Servicecenter();
		servicecenter.setUid(Integer.parseInt(multi.getParameter("uid")));
		servicecenter.setNick(multi.getParameter("nick"));
		servicecenter.setTitle(multi.getParameter("title"));
		servicecenter.setContents(multi.getParameter("contents"));
		servicecenter.setPhoto(
		multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		ScWriteProService scWriteProService = new ScWriteProService();
		boolean isWriteSuccess = scWriteProService.scArticle(servicecenter);
		System.out.println(isWriteSuccess);
		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('fail')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("ScList.sc");
		}

		return forward;
		
	}  	
}
