package com.inseoul.svc.qna;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.inseoul.dao.QnaDAO;
import com.inseoul.vo.QnABoardBean;

public class QnaReplyService {
	public boolean replyArticle(QnABoardBean article) throws Exception{
		System.out.println("''''"+article);
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		insertCount = qnaDAO.insertReplyArticle(article);
		System.out.println("QnaReplyService insertCount값 : "+ insertCount);
		if(insertCount > 0){
			commit(con);
			isReplySuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		System.out.println("QnaReplyService 떠남! ");
		return isReplySuccess;
		
	}
}
