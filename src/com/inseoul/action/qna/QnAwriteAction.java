package com.inseoul.action.qna;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.qna.QnAWriteProService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.QnABoardBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnAwriteAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward forward=null;
		QnABoardBean qnaboardBean = null;
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   		
		MultipartRequest multi=new MultipartRequest(request,realFolder,fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		System.out.println("1");
		qnaboardBean = new QnABoardBean();
		System.out.println("2");
		qnaboardBean.setUid(Integer.parseInt(multi.getParameter("uid")));
		qnaboardBean.setNick(multi.getParameter("nick"));
		qnaboardBean.setTitle(multi.getParameter("title"));
		qnaboardBean.setContents(multi.getParameter("contents"));
		qnaboardBean.setPhotos(
		multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		QnAWriteProService qnaWriteProService = new QnAWriteProService();
		boolean isWriteSuccess = qnaWriteProService.registArticle(qnaboardBean);
		System.out.println(isWriteSuccess);
		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('QnAwriteAction fail')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("qnaList.qa");
		}

		return forward;
		
	}  	
}
