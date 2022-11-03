package com.inseoul.model.festival;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.FestivalBean;

public class FestivalListModel {
	
	static FestivalListModel model = new FestivalListModel();

	public static FestivalListModel instance() {
		return model;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	
	public int getListCount() throws Exception {

		int listCount = 0;
		SqlSession sqlSession = factory.openSession();
		listCount = sqlSession.selectOne("festival_listcount");
		sqlSession.close();
		return listCount;
	}
	public List<FestivalBean> selectAll(int page) {
		System.out.println("FestivalListModel selectAll 도착~");
		List<FestivalBean> list = null;
		int startrow=(page-1)*8;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("festival_All", startrow);
		sqlSession.close();
		return list;
	}
}
