package com.inseoul.model.travels;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.ReplyBean;
import com.inseoul.vo.TravelsBean;

public class TravelsReplyModel {
	static TravelsReplyModel model = new TravelsReplyModel();

	public static TravelsReplyModel instance() {
		return model;
	}
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public void insertArticle(ReplyBean reply) throws Exception {
		System.out.println("TravelsReplyWriteModel insertArticle 도착~");
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.insert("travels_replyWrite", reply);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	public void deleteArticle(int rid) throws Exception {
		System.out.println("TravelsReplyWriteModel deleteArticle 도착~");
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.delete("travels_replyDelete", rid);
		sqlSession.commit();
		sqlSession.close();
		
	}
}
