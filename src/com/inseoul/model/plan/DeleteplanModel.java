package com.inseoul.model.plan;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.Routemaps;

public class DeleteplanModel {
static DeleteplanModel model = new DeleteplanModel();
	
	public static DeleteplanModel instance(){
		return model;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
    public void deleteplan(int mid){
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.delete("del",mid);
		sqlSession.commit();
		sqlSession.close(); //insert delete update 는 commit을 해야함
	}
}
