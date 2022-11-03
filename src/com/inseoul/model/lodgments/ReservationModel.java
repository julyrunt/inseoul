package com.inseoul.model.lodgments;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LodgmentRoomBean;
import com.inseoul.vo.Lodgment_ReservationBean;

public class ReservationModel {

	static ReservationModel model = new ReservationModel();

	public static ReservationModel instance() {
		return model;
	}
	
private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	/*  */
	public void insertReservation(Lodgment_ReservationBean reservation) {
		System.out.println("RoomReservaionModel insertReservation 도착~");
		SqlSession sqlSession = factory.openSession();
		sqlSession.selectOne("reservation", reservation);
		sqlSession.commit();
		sqlSession.close();
	}
	public int countRnum() {
		System.out.println("RoomReservaionModel selectRoomInfo 도착~");
		SqlSession sqlSession = factory.openSession();
		int rnum = sqlSession.selectOne("countRnum");
		sqlSession.close();
		return rnum;
	}
	
	public List<Lodgment_ReservationBean> selectReservaion(int rnum){
		System.out.println("RoomReservaionModel selectReservaion 도착~");
		List<Lodgment_ReservationBean> list = null;
		SqlSession sqlSession = factory.openSession();
		
//		list = sqlSession.selectOne("reservaion_info", rnum);
		list = sqlSession.selectList("reservaion_info", rnum);
		System.out.println("RoomReservaionModel selectReservaion list2:" + list);
		sqlSession.close();
		return list;
	}
}
