package com.inseoul.mybatis;

import java.io.Reader;



import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class SqlMapConfig {
  private static SqlSessionFactory sqlSession;
  public static SqlSessionFactory getSqlSession() {
    return sqlSession;
  }

  static{
    try {
      /* String resource = "패키지명/파일명.xml"; */
      String resource = "com/inseoul/mybatis/Configuration.xml";
      Reader reader = Resources.getResourceAsReader(resource);
      SqlSessionFactoryBuilder factory = new SqlSessionFactoryBuilder();
      sqlSession = factory.build(reader);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}




