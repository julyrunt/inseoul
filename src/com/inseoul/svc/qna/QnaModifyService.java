package com.inseoul.svc.qna;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.inseoul.dao.QnaDAO;
import com.inseoul.vo.QnABoardBean;

public class QnaModifyService {
	public boolean modifyArticle(QnABoardBean article) throws Exception {
		System.out.println("WithBoardModifyService 도착! ");
		boolean isModifySuccess = false;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		int updateCount = qnaDAO.updateArticle(article);
		
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		System.out.println("WithBoardModifyService 떠남! ");
		return isModifySuccess;
		
	}
}
