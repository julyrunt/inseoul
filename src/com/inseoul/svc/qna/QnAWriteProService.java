package com.inseoul.svc.qna;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;


import java.sql.Connection;

import com.inseoul.dao.QnaDAO;
import com.inseoul.vo.QnABoardBean;


public class QnAWriteProService {
public boolean registArticle(QnABoardBean qnaboardBean) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		QnaDAO qnaDAO = QnaDAO.getInstance();
		qnaDAO.setConnection(con);
		int insertCount = qnaDAO.insertArticle(qnaboardBean);
		
		if(insertCount > 0){
			commit(con);
			isWriteSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
		
	}
	
}
