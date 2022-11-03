package com.inseoul.svc.with;

import static com.inseoul.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.vo.WithBoardBean;

public class WithGroupManageService {
	
	public static ArrayList<WithBoardBean> getGroup(int wid){
		System.out.println("WithGroupManageService getGroup 도착");
		ArrayList<WithBoardBean> groups = null;
		Connection con = getConnection();
		WithBoardDAO withBoardDAO = WithBoardDAO.getInstance();
		withBoardDAO.setConnection(con);
		groups = withBoardDAO.selectGroupManage(wid);
		return groups;
	}
}
