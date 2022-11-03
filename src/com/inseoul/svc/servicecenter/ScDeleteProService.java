package com.inseoul.svc.servicecenter;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.inseoul.dao.ScDAO;
import com.inseoul.vo.Servicecenter;



public class ScDeleteProService {

	public boolean removeArticle(int sid) throws Exception{
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		int deleteCount = scDAO.deleteArticle(sid);
		
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
