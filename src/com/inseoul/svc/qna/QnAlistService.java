package com.inseoul.svc.qna;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.inseoul.dao.QnaDAO;
import com.inseoul.vo.QnABoardBean;


public class QnAlistService {
	public int getListCount() throws Exception{
		int listCount = 0;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		listCount = qnaDAO.selectListCount();
		close(con);
		return listCount;

	}

	public ArrayList<QnABoardBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<QnABoardBean> articleList = null;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		articleList = qnaDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}
}
