package com.inseoul.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.InfoAddressBean;
import com.inseoul.vo.InfoGeneralBean;
import com.inseoul.vo.InfoPasswordBean;
import com.inseoul.vo.InfoQuestionBean;
import com.inseoul.vo.UserBean;

public class InfoModel {
  static InfoModel model = new InfoModel();
  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static InfoModel instance() {
    return model;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public InfoGeneralBean getGeneralInfo(InfoGeneralBean generalBean) {
    SqlSession sqlSession = factory.openSession();
    InfoGeneralBean generalInfo = sqlSession.selectOne("profile.selGeneralInfo", generalBean);
    sqlSession.close();
    return generalInfo;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public int upGeneralInfo(InfoGeneralBean generalBean) {
    SqlSession sqlSession = factory.openSession();
    int rowCount = sqlSession.update("profile.upGeneralInfo", generalBean);
    sqlSession.commit();
    sqlSession.close();
    return rowCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public int checkPasswordInfo(InfoPasswordBean passwordBean) {
    SqlSession sqlSession = factory.openSession();
    int count = sqlSession.update("profile.selPasswordCheck", passwordBean);
    sqlSession.close();
    return count;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public int upPasswordInfo(InfoPasswordBean passwordBean) {
    SqlSession sqlSession = factory.openSession();
    int rowCount = sqlSession.update("profile.upPasswordInfo", passwordBean);
    sqlSession.commit();
    sqlSession.close();
    return rowCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public InfoAddressBean getAddressInfo(InfoAddressBean addressBean) {
    SqlSession sqlSession = factory.openSession();
    InfoAddressBean addressInfo = sqlSession.selectOne("profile.selAddressInfo", addressBean);
    sqlSession.close();
    return addressInfo;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public int upAddressInfo(InfoAddressBean addressBean) {
    SqlSession sqlSession = factory.openSession();
    int rowCount = sqlSession.update("profile.upAddressInfo", addressBean);
    sqlSession.commit();
    sqlSession.close();
    return rowCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public InfoQuestionBean getQuestionInfo(InfoQuestionBean questionBean) {
    SqlSession sqlSession = factory.openSession();
    InfoQuestionBean questionInfo = sqlSession.selectOne("profile.selQuestionInfo", questionBean);
    sqlSession.close();
    return questionInfo;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public int upQuestionInfo(InfoQuestionBean questionBean) {
    SqlSession sqlSession = factory.openSession();
    int rowCount = sqlSession.update("profile.upQuestionInfo", questionBean);
    sqlSession.commit();
    sqlSession.close();
    return rowCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public int delUserInfo(UserBean user) {
    SqlSession sqlSession = factory.openSession();
    int rowCount = sqlSession.update("profile.delUserInfo", user);
    sqlSession.commit();
    sqlSession.close();
    return rowCount;
  }
}



