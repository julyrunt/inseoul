package com.inseoul.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.ReceiptBean;

public class ReceiptModel {
  static ReceiptModel model = new ReceiptModel();
  private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
  
  public static ReceiptModel instance() {
    return model;
  }

  public int getListCount(PageInfo pageInfo) {
    int listCount = 0;
    SqlSession sqlSession = factory.openSession();
    listCount = sqlSession.selectOne("profile.selReceiptCount", pageInfo);
    sqlSession.close();
    return listCount;
  }
  
  public List<ReceiptBean> selectReceiptList(PageInfo pageInfo) {
    List<ReceiptBean> list = null;
    SqlSession sqlSession = factory.openSession();
    list = sqlSession.selectList("profile.selReceiptList", pageInfo);
    sqlSession.close();
    return list;
  }
  
  public ReceiptBean selectReceipt(ReceiptBean receiptBean) {
    ReceiptBean receiptInfo = null;
    SqlSession sqlSession = factory.openSession();
    receiptInfo = sqlSession.selectOne("profile.selReceiptDetail", receiptBean);
    sqlSession.close();
    return receiptInfo;
  }
  
}
