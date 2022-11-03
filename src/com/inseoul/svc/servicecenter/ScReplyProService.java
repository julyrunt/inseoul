package com.inseoul.svc.servicecenter;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;


import com.inseoul.dao.ScDAO;
import com.inseoul.vo.Servicecenter;

public class ScReplyProService {
public boolean replyArticle(Servicecenter article) throws Exception{
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		insertCount = scDAO.insertReplyArticle(article);
		
		if(insertCount > 0){
			commit(con);
			isReplySuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isReplySuccess;
		
	}
}
