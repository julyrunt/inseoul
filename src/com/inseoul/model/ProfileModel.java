package com.inseoul.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.UserBean;

public class ProfileModel {
  static ProfileModel model = new ProfileModel();
  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
  
  public static ProfileModel instance() {
    return model;
  }

  public int updateBackground(UserBean user) {
    int result = 0;
    SqlSession sqlSession = factory.openSession();
    result = sqlSession.update("profile.upBackground", user);
    sqlSession.commit();
    sqlSession.close();
    return result;
  }
  
}
