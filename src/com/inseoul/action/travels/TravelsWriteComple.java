package com.inseoul.action.travels;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.TravelsModel;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class TravelsWriteComple implements CommandInter{
	
	static TravelsWriteComple impl = new TravelsWriteComple();

	public static TravelsWriteComple instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TravelsWriteComple showData 옴~");
		TravelsModel model = TravelsModel.instance();
		TravelsBean travels = new TravelsBean();
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   		
		MultipartRequest multi=new MultipartRequest(request,realFolder,fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		HttpSession session = request.getSession();
		int uid = Integer.parseInt((String) session.getAttribute("uid"));
		String lid = (String) multi.getParameter("lid");
		
		travels.setImg01(multi.getFilesystemName("file_01"));
		travels.setImg02(multi.getFilesystemName("file_02"));
		travels.setImg03(multi.getFilesystemName("file_03"));
		travels.setTitle(multi.getParameter("write_title"));
		travels.setContents(multi.getParameter("write_contents"));
		travels.setLid(Integer.parseInt(lid));
		travels.setUid(uid);
		
		model.insertArticle(travels);
		
		return "locationDetail.tv?lid="+lid;
	}
}
