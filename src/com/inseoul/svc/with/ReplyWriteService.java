package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.*;


import java.sql.Connection;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class ReplyWriteService {

public boolean replyArticle(WithBoardBean article) throws Exception{
		System.out.println("ReplyWriteService 도착! ");
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		insertCount = withBoardDAO.insertReplyArticle(article);
		System.out.println("WithBoardReplyService insertCount값 : "+ insertCount);
		if(insertCount > 0){
			commit(con);
			isReplySuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		System.out.println("WithBoardReplyService 떠남! ");
		return isReplySuccess;
		
	}
}
