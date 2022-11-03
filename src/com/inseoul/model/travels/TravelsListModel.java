package com.inseoul.model.travels;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;

public class TravelsListModel {
	
	static TravelsListModel model = new TravelsListModel();

	public static TravelsListModel instance() {
		return model;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public int getListCount() throws Exception {

		int listCount = 0;
		SqlSession sqlSession = factory.openSession();
		listCount = sqlSession.selectOne("travels_listcount");
		sqlSession.close();
		return listCount;
	}
	
	public List<TravelsBean> selectAllTravels(int page) {
		System.out.println("TravelsListModel selectAllTravels 도착~");
		List<TravelsBean> list = null;
		int startrow=(page-1)*5;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("travels_all", startrow);
		sqlSession.close();
		return list;
	}
}
