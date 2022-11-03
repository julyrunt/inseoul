package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.inseoul.dao.WithBoardDAO;

public class GroupUpdate {
	
	public boolean groupAppro(int wid, int uid) {
		System.out.println("GroupUpdate groupAppro 도착");
		boolean isApproSuccess = false;
		int updateCount = 0;
		
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		updateCount = withBoardDAO.groupAppro(wid, uid);
		if(updateCount > 0){
			commit(con);
			isApproSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isApproSuccess;	
	}
	
	public boolean groupBanned(int wid, int uid) {
		System.out.println("GroupUpdate groupBanned 도착");
		boolean isBannedSuccess = false;
		int updateCount = 0;
		
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		updateCount = withBoardDAO.groupBanned(wid, uid);
		if(updateCount > 0){
			commit(con);
			isBannedSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isBannedSuccess;	
	}
}
