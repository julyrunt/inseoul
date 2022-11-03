package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class ReplyListService {
	
	public WithBoardBean getArticle(int wid) throws Exception{
		System.out.println("ReplyListService 도착 wid : "+ wid);
		
		WithBoardBean article = null;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		
//		article = withBoardDAO.selectArticle(wid);
		close(con);
		return article;
		
	}
	
	public int getReplyListCount(int ref) throws Exception {

		int listCount = 0;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		listCount = withBoardDAO.selectReplyListCount(ref);
		close(con);
		return listCount;

	}


	public ArrayList<WithBoardBean> getReplyList(int ref, int page, int limit) throws Exception {

		ArrayList<WithBoardBean> replyList = null;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		replyList = withBoardDAO.selectReply(ref, page, limit);
		close(con);
		return replyList;

	}
}
