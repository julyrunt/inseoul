package com.inseoul.svc.servicecenter;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;
import java.sql.Connection;
import java.util.ArrayList;

import com.inseoul.dao.ScDAO;
import com.inseoul.vo.Servicecenter;

public class ScDetailService {
	public Servicecenter getArticle(int sid) throws Exception{
		
		Servicecenter article = null;
		Connection con = getConnection();
		ScDAO scDAO = ScDAO.getInstance();
		scDAO.setConnection(con);
		
		article = scDAO.selectArticle(sid);
		close(con);
		return article;
		
	}
	
	
}
