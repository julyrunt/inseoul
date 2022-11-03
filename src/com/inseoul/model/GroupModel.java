package com.inseoul.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.GroupListBean;
import com.inseoul.vo.PageInfo;

/**
 * 사용자가 개설 또는 가입한 여행 그룹 관리를 위한 통신을 수행하는 클래스.
 * 
 * @author julyrunt
 * @version 1.0.0
 * @param model 싱클톤 패턴에 사용되는 객체
 * @param factory SqlSessionFactory
 */
public class GroupModel {
  static GroupModel model = new GroupModel();
  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
  
  public static GroupModel instance() {
    return model;
  }
  
  /**
   * 사용자가 개설한 여행 그룹의 총합을 취득합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 사용자가 개설한 그룹의 총합
   */
  public int selOwnGroupCount(PageInfo pageInfo) {
    int listCount = 0;
    SqlSession sqlSession = factory.openSession();
    listCount = sqlSession.selectOne("profile.selOwnGroupCount", pageInfo);
    sqlSession.close();
    return listCount;
  }
  /**
   * 사용자가 가입한 여행 그룹 목록의 한 페이지를 취득합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 사용자가 개설한 그룹 목록의 한 페이지 리스트
   */
  public List<GroupListBean> selOwnGroupList(PageInfo pageInfo) {
    List<GroupListBean> list = null;
    SqlSession sqlSession = factory.openSession();
    list = sqlSession.selectList("profile.selOwnGroupList", pageInfo);
    sqlSession.close();
    return list;
  }
  /**
   * 사용자가 가입한 여행 그룹의 총합을 취득합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 사용자가 가입한 그룹의 총합
   */
  public int selMyGroupCount(PageInfo pageInfo) {
    int listCount = 0;
    SqlSession sqlSession = factory.openSession();
    listCount = sqlSession.selectOne("profile.selMyGroupCount", pageInfo);
    sqlSession.close();
    return listCount;
  }
  /**
   * 사용자가 가입한 여행 그룹 목록의 한 페이지를 취득합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 사용자가 가입한 그룹 목록의 한 페이지 리스트
   */
  public List<GroupListBean> selMyGroupList(PageInfo pageInfo) {
    List<GroupListBean> list = null;
    SqlSession sqlSession = factory.openSession();
    list = sqlSession.selectList("profile.selMyGroupList", pageInfo);
    sqlSession.close();
    return list;
  }
  /**
   * 사용자가 가입 신청한 여행 그룹의 총합을 취득합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 사용자가 가입 신청한 그룹의 총합
   */
  public int selApplyGroupCount(PageInfo pageInfo) {
    int listCount = 0;
    SqlSession sqlSession = factory.openSession();
    listCount = sqlSession.selectOne("profile.selApplyGroupCount", pageInfo);
    sqlSession.close();
    return listCount;
  }
  /**
   * 사용자가 가입 신청한 여행 그룹 목록의 한 페이지를 취득합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 사용자가 가입 신청한 그룹 목록의 한 페이지 리스트
   */
  public List<GroupListBean> selApplyGroupList(PageInfo pageInfo) {
    List<GroupListBean> list = null;
    SqlSession sqlSession = factory.openSession();
    list = sqlSession.selectList("profile.selApplyGroupList", pageInfo);
    sqlSession.close();
    return list;
  }
  /**
   * 사용자를 그룹의 가입 및 신청 멤버 목록에서 삭제합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 목록에서 삭제되었는지 결과
   */
  public boolean delGroupMember(PageInfo pageInfo) {
    boolean isDeleted = false;
    SqlSession sqlSession = factory.openSession();
    int rowCount = sqlSession.delete("profile.delGroupMember", pageInfo);
    sqlSession.commit();
    sqlSession.close();
    if (rowCount > 0) {
      isDeleted = true;
    }
    return isDeleted;
  }
  /**
   * 특정 ID의 그룹 정보를 취득합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 특정 ID의 그룹 정보
   */
  public GroupListBean selGroupById(PageInfo pageInfo) {
    GroupListBean group = null;
    SqlSession sqlSession = factory.openSession();
    group = sqlSession.selectOne("profile.selGroupById", pageInfo);
    sqlSession.close();
    return group;
  }
  /**
   * 그룹을 삭제합니다.
   * @param pageInfo 요청 게시판 페이지 정보 
   * @return 목록에서 삭제되었는지 결과
   */
  public boolean delGroup(PageInfo pageInfo) {
    boolean isDeleted = false;
    SqlSession sqlSession = factory.openSession();
    int rowCount = sqlSession.delete("profile.delGroup", pageInfo);
    sqlSession.commit();
    sqlSession.close();
    if (rowCount > 0) {
      isDeleted = true;
    }
    return isDeleted;
  }
}



