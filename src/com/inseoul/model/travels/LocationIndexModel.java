package com.inseoul.model.travels;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.Tourist_SpotBean;

public class LocationIndexModel {

	static LocationIndexModel model = new LocationIndexModel();

	public static LocationIndexModel instance() {
		return model;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public int getListCount() throws Exception {

		int listCount = 0;
		SqlSession sqlSession = factory.openSession();
		listCount = sqlSession.selectOne("spot_listcount");
		sqlSession.close();
		return listCount;
	}
	
	public List<Tourist_SpotBean> selectAllSpot(int page) {
		System.out.println("LocationIndexModel selectAllSpot 도착~");
		List<Tourist_SpotBean> list = null;
		int startrow=(page-1)*8;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("spot_all", startrow);
		sqlSession.close();
		return list;
	}
}
