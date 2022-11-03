package com.inseoul.model.festival;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.FestivalBean;

public class FestivalDetailModel {
	
	static FestivalDetailModel model = new FestivalDetailModel();

	public static FestivalDetailModel instance() {
		return model;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public FestivalBean selectOne(int fid) {
		System.out.println("FestivalDetailModel selectOne 도착~");
		FestivalBean list = null;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectOne("festival_one", fid);
		sqlSession.close();
		return list;
	}
}
