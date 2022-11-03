package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class BoardListService {

	public int getListCount() throws Exception {

		int listCount = 0;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		listCount = withBoardDAO.selectListCount();
		close(con);
		return listCount;

	}


	public ArrayList<WithBoardBean> getArticleList(int page, int limit) throws Exception {

		ArrayList<WithBoardBean> articleList = null;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		articleList = withBoardDAO.selectArticleList(page, limit);
		close(con);
		return articleList;

	}

//	public ArrayList<WithBoardBean> getArticleList_mojib_asc(int page, int limit) throws Exception{
//		
//		ArrayList<WithBoardBean> articleList = null;
//		Connection con = getConnection();
//		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
//		withBoardDAO.setConnection(con);
//		articleList = withBoardDAO.selectArticleList_mojib_asc(page,limit);
//		close(con);
//		return articleList;
//		
//	}

}
