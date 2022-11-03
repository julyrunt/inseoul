package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.*;

import java.sql.Connection;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class ReplyDeleteService {
	
	public boolean removeArticle(int wid) throws Exception{
		System.out.println("ReplyDeleteService removeArticle 도착 ");
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
	
}
