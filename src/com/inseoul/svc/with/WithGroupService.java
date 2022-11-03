package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;

import java.sql.Connection;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class WithGroupService {
	
	public boolean updateGroup(int uid, int wid) throws Exception{
		System.out.println("updateGroup 도착! ");
		boolean isGroupSuccess = false;
		int updateCount = 0;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		updateCount = withBoardDAO.updateGroup(uid, wid);
		if(updateCount > 0){
			commit(con);
			isGroupSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isGroupSuccess;	
	}
	
//	public boolean deleteGroup(int uid, int wid) throws Exception{
//		System.out.println("deleteGroup 도착! ");
//		boolean isReplySuccess = false;
//		int insertCount = 0;
//		Connection con = getConnection();
//		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
//		withBoardDAO.setConnection(con);
//		insertCount = withBoardDAO.deleteGroup(uid, wid);
//		if(insertCount > 0){
//			commit(con);
//			isReplySuccess = true;
//		}
//		else{
//			rollback(con);
//		}
//		
//		close(con);
//		return isReplySuccess;
//		
//	}
	
	public int stateGroup(int uid, int wid) throws Exception{
		System.out.println("stateGroup 도착! ");
		
		int stateCount = 0;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		stateCount = withBoardDAO.selectState(uid, wid);
		if(stateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		close(con);
		return stateCount;	
	}
}
