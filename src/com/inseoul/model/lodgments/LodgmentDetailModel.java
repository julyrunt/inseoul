package com.inseoul.model.lodgments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LodgmentRoomBean;
import com.inseoul.vo.Lodgment_ReviewBean;
import com.inseoul.vo.LodgmentsBean;

public class LodgmentDetailModel {
	static LodgmentDetailModel model = new LodgmentDetailModel();

	public static LodgmentDetailModel instance() {
		return model;
	}
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	/* 숙박 리스트에서 클릭 시 숙박시설 상세정보 */
	public LodgmentsBean selectDetail(int hid){
		System.out.println("selectDetail 도착");
		LodgmentsBean list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectOne("lodgment_detail", hid);
		sqlSession.close();	
		return list;
	}
	/* 숙박 상세보기 숙소안내 */
//	public Lodgment_infoBean selectInfo(int hi_id) {
//		System.out.println("selectInfo 도착");
//		Lodgment_infoBean infolist = null;
//		
//		SqlSession sqlSession = factory.openSession();
//		infolist = sqlSession.selectOne("lodgment_detail_info", hi_id);
//		sqlSession.close();
//		return infolist;
//	}
	/* 숙박 상세보기 룸정보 */
	public List <LodgmentRoomBean> selectRoom(int hid) {
		System.out.println("selectRoom 도착");
		List <LodgmentRoomBean> roominfo = null;
		System.out.println("selectRoom hid :"+hid);
		SqlSession sqlSession = factory.openSession();
		roominfo = sqlSession.selectList("lodgment_room_info", hid);
		sqlSession.close();
		return roominfo;
	}
	/* 숙박 리뷰  */
	public List <Lodgment_ReviewBean> getReview(int hid) {
		System.out.println("getReview 도착");
		List <Lodgment_ReviewBean> review = null;
		
		SqlSession sqlSession = factory.openSession();
		review = sqlSession.selectList("lodgment_review", hid);
		sqlSession.close();
		return review;
	}
	/* 리뷰 작성  */
	public void writeReivew(Lodgment_ReviewBean article) {
		System.out.println("writeReivew 도착");
		SqlSession sqlSession = factory.openSession();
		sqlSession.insert("review_write", article);
		sqlSession.commit();
		sqlSession.close();
	}
	
}
