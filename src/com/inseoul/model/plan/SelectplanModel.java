package com.inseoul.model.plan;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LocationBean;
import com.inseoul.vo.Routemaps;

public class SelectplanModel {
	 static SelectplanModel model = new SelectplanModel();
	  
	  public static SelectplanModel instance() {
	    return model;
	  }
	  
	  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();

	  

	  
	  public int getListCount() throws Exception{
			int listCount = 0;
			SqlSession sqlSession = factory.openSession();
			listCount = sqlSession.selectOne("routemapslistcount");
			sqlSession.close();
			return listCount;

		}

		public List<Routemaps> selectname(int page) throws Exception{
			List<Routemaps> articleList = null;
			int startrow=(page-1)*5; 
			SqlSession sqlSession = factory.openSession();
			articleList = sqlSession.selectList("lcnames",startrow);
			sqlSession.close();
			return articleList;
		}
}
