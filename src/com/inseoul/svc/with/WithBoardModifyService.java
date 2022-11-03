package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.*;


import java.sql.Connection;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class WithBoardModifyService {

public boolean modifyArticle(WithBoardBean article) throws Exception {
		System.out.println("WithBoardModifyService 도착! ");
		boolean isModifySuccess = false;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		int updateCount = withBoardDAO.updateArticle(article);
		
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
