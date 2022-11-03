package com.inseoul.model.lodgments;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LodgmentsBean;

public class LodgmentsSearchModel {

	static LodgmentsSearchModel model = new LodgmentsSearchModel();

	public static LodgmentsSearchModel instance() {
		return model;
	}

	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List<LodgmentsBean> selectSearch(String search) {
		System.out.println("LodgmentsSearchModel selectSearch 도착~");
		List<LodgmentsBean> list = null;
		SqlSession sqlSession = factory.openSession();
		System.out.println("모델 : "+ search);
		list = sqlSession.selectList("lodgments_search", search);
		sqlSession.close();
		
		return list;
	}
	
}
