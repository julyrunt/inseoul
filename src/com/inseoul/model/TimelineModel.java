package com.inseoul.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.TimelineBean;

public class TimelineModel {
  static TimelineModel model = new TimelineModel();
  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
  
  public static TimelineModel instance() {
    return model;
  }

  public int getListCount(PageInfo pageInfo) {
    int listCount = 0;
    SqlSession sqlSession = factory.openSession();
    listCount = sqlSession.selectOne("profile.selTimelineCount", pageInfo);
    sqlSession.close();
    return listCount;
  }
  
  public List<TimelineBean> selectTimeline(PageInfo pageInfo) {
    List<TimelineBean> list = null;
    SqlSession sqlSession = factory.openSession();
    list = sqlSession.selectList("profile.selTimelineList", pageInfo);
    sqlSession.close();
    return list;
  }
  
}



