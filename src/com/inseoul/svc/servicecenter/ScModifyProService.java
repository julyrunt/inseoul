package com.inseoul.svc.servicecenter;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.inseoul.dao.ScDAO;
import com.inseoul.vo.Servicecenter;

public class ScModifyProService {

	public boolean modifyArticle(Servicecenter article) throws Exception {
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		int updateCount = scDAO.updateArticle(article);
		System.out.println("upup"+updateCount);
		
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
		
	}
}
