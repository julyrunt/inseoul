package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class WithBoardDetailService {

	public WithBoardBean getArticle(int uid, int wid) throws Exception{
		System.out.println("WithBoardDetailService 도착 ");
		
		WithBoardBean article = null;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		int updateCount = withBoardDAO.updateReadCount(wid);
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = withBoardDAO.selectArticle(uid, wid);
		close(con);
		return article;
		
	}
	
	public WithBoardBean getArticle(int wid) throws Exception{
		System.out.println("WithBoardDetailService 도착 ");
		
		WithBoardBean article = null;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		int updateCount = withBoardDAO.updateReadCount(wid);
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = withBoardDAO.selectArticle(wid);
		close(con);
		return article;
		
	}
	
	public int getReplyListCount(int wid) throws Exception {

		int replylistCount = 0;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		replylistCount = withBoardDAO.selectReplyListCount(wid);
		close(con);
		return replylistCount;
	}
	
	public ArrayList<WithBoardBean> getReplyList(int wid, int replypage, int replylimit) throws Exception{
		System.out.println("WithBoardDetailService getReplyList 도착 ");
		
		ArrayList<WithBoardBean> replyList = null;
		
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		replyList = withBoardDAO.selectReply(wid, replypage, replylimit);
		
		close(con);
		return replyList;
	}
	
	public int getGroup(int uid, int wid) {
		System.out.println("WithBoardDetailService getGroup 도착 ");
		
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		int stateCount = withBoardDAO.selectState(uid,wid);
		return stateCount;
	}
	
	

}
