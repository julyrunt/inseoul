package com.inseoul.model.plan;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.Routemaps;

public class DetailplanModel {
	  static DetailplanModel model = new DetailplanModel();
	  
	  public static DetailplanModel instance() {
	    return model;
	  }
	  
	  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	  
	  
	  public Routemaps selectdtplan(int mid){
		  System.out.println("모델옴");
			Routemaps plan = null;
			SqlSession sqlSession = factory.openSession();
			plan = sqlSession.selectOne("dtplans",mid);
			sqlSession.close(); 
			System.out.println("모델감");
			return plan;
	  }
}
