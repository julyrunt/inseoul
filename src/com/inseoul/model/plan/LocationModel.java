package com.inseoul.model.plan;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.dao.QnaDAO;
import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LocationBean;
import com.inseoul.vo.QnABoardBean;


public class LocationModel {
	  static LocationModel model = new LocationModel();
	  
	  public static LocationModel instance() {
	    return model;
	  }
	  
	  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	  
	  public List<LocationBean> selectLocation(){
			List<LocationBean> list = null;
			SqlSession sqlSession = factory.openSession();
			list = sqlSession.selectList("marks");
			sqlSession.close(); 
			return list;
		}
	  
	  public int getListCount() throws Exception{
		  System.out.println("4");
			int listCount = 0;
			SqlSession sqlSession = factory.openSession();
			listCount = sqlSession.selectOne("sidelistcount");
			System.out.println("1"+listCount);
			sqlSession.close();
			return listCount;

		}

		public List<LocationBean> getArticleList(int page) throws Exception{
			System.out.println("3");
			List<LocationBean> articleList = null;
			int startrow=(page-1)*15; 
			System.out.println("q");
			SqlSession sqlSession = factory.openSession();
			articleList = sqlSession.selectList("sidelist",startrow);
	         System.out.println("2"+articleList.size());
			sqlSession.close();
			return articleList;
		}
}
