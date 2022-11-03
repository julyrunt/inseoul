package com.inseoul.svc.qna;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.inseoul.dao.QnaDAO;
import com.inseoul.vo.QnABoardBean;


public class QnaDetailService {
	public QnABoardBean getArticle(int qid, int ref) throws Exception{
		
		QnABoardBean article = null;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		int updateCount = qnaDAO.updateReadCount(qid);
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = qnaDAO.selectArticle(qid);
		close(con);
		return article;
		
	}
	public ArrayList<QnABoardBean> getReply(int ref) throws Exception{
		System.out.println("WithBoardDetailService getArticle1 도착 ");
		
		ArrayList<QnABoardBean> replyList = null;
		
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		replyList = qnaDAO.selectReply(ref);
		System.out.println("123"+replyList);
		close(con);
		return replyList;
		
	}
}
