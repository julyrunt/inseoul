package com.inseoul.action.travels;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.TravelsModel;
import com.inseoul.model.travels.TravelsReplyModel;
import com.inseoul.vo.TravelsBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class TravelsUpdating implements CommandInter{
	
	static TravelsUpdating impl = new TravelsUpdating();

	public static TravelsUpdating instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("TravelsUpdating showData 옴~");
		TravelsBean article = new TravelsBean();
		TravelsModel model = new TravelsModel();
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder); 
		MultipartRequest multi=new MultipartRequest(request,realFolder,fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String tid = (String) multi.getParameter("tid");
		String title = (String) multi.getParameter("title");
		String contents = (String) multi.getParameter("contents");
		
		article.setTid(Integer.parseInt(tid));
		article.setTitle(title);
		article.setContents(contents);
		model.updatingArticle(article);
		
		return "travelsList.tv";
	}
}
