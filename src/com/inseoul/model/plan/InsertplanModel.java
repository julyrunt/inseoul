package com.inseoul.model.plan;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LocationBean;
import com.inseoul.vo.Routemaps;



public class InsertplanModel {
	
	static InsertplanModel model = new InsertplanModel();
	
	public static InsertplanModel instance(){
		return model;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
//	public void roundsq(int round , int uid){
//		plan list = new plan();
//		list.setRound(round);
//		list.setUid(uid);
//		list = sqlSession.selectList("marks");
//		sqlSession.close(); 
//		SqlSession sqlSession = factory.openSession();
//		
//		sqlSession.close(); //insert delete update 는 commit을 해야함
//	}
//	
	public void insertplan(Routemaps list){
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.insert("ins",list);
		sqlSession.commit();
		sqlSession.close(); //insert delete update 는 commit을 해야함
	}
}
