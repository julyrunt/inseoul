package com.inseoul.model.plan;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.Routemaps;

public class UpdateplanModel {
static UpdateplanModel model = new UpdateplanModel();
	
	public static UpdateplanModel instance(){
		return model;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public void updateplan(Routemaps list){
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.update("up",list);
		sqlSession.commit();
		sqlSession.close(); //insert delete update 는 commit을 해야함
	}
}
