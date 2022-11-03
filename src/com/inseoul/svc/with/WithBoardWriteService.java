package com.inseoul.svc.with;

import java.sql.Connection;
import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;
import static com.inseoul.db.JdbcUtil.*;

public class WithBoardWriteService {
	
public boolean registArticle(WithBoardBean withBoardBean) throws Exception{
		System.out.println("WithBoardWriteService 도착");
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		int insertCount = withBoardDAO.insertArticle(withBoardBean);
		
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
