package com.inseoul.svc.servicecenter;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.inseoul.dao.ScDAO;
import com.inseoul.vo.Servicecenter;

public class ScWriteProService {
public boolean scArticle(Servicecenter Servicecenter) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		int insertCount = scDAO.insertArticle(Servicecenter);
		System.out.println("ScWriteProService도착");
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
