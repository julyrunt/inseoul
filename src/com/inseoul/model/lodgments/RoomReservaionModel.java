package com.inseoul.model.lodgments;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LodgmentRoomBean;
import com.inseoul.vo.LodgmentsBean;
import com.inseoul.vo.UserBean;

public class RoomReservaionModel {
	
	static RoomReservaionModel model = new RoomReservaionModel();

	public static RoomReservaionModel instance() {
		return model;
	}
	
private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	/*  */
	public LodgmentRoomBean selectRoomInfo(int roomid) {
		System.out.println("RoomReservaionModel selectRoomInfo 도착~");
		LodgmentRoomBean roominfo = null;
		SqlSession sqlSession = factory.openSession();
		roominfo = sqlSession.selectOne("roomreservaion", roomid);
		sqlSession.close();
		return roominfo;
	}
	
	public UserBean selectUser(int uid) {
		System.out.println("RoomReservaionModel selectRoomInfo 도착~");
		UserBean user = null;
		SqlSession sqlSession = factory.openSession();
		user = sqlSession.selectOne("oneuser", uid);
		sqlSession.close();
		return user;
	}
}
