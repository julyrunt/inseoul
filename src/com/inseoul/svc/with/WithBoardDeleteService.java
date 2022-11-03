package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.*;

import java.sql.Connection;

import com.inseoul.dao.WithBoardDAO;

public class WithBoardDeleteService {
	
	public boolean removeArticle(int wid) throws Exception{
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		int deleteCount = withBoardDAO.deleteArticle(wid);
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}
	
//	public boolean isArticleWriter(int wid) throws Exception {
//		
//		boolean isArticleWriter = false;
//		Connection con = getConnection();
//		WithBoardDAO withBoardDAO = withBoardDAO.getInstance();
//		withBoardDAO.setConnection(con);
//		isArticleWriter = withBoardDAO.isArticleBoardWriter(wid);
//		close(con);
//		return isArticleWriter;
//		
//	}
}
