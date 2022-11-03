package com.inseoul.svc.servicecenter;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.inseoul.dao.ScDAO;
import com.inseoul.vo.Servicecenter;

public class ScListService {
	public int getListCount() throws Exception{
		int listCount = 0;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		listCount = scDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<Servicecenter> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<Servicecenter> articleList = null;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		articleList = scDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}
	
	public ArrayList<Servicecenter> getReply() throws Exception{

		ArrayList<Servicecenter> replyList = null;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		replyList = scDAO.selectReply();
		close(con);
		return replyList;

	}


}
