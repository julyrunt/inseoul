package com.inseoul.model.travels;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;

public class TravelsModel {
	static TravelsModel model = new TravelsModel();

	public static TravelsModel instance() {
		return model;
	}
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public Tourist_SpotBean getArticle(int lid) throws Exception {
		System.out.println("TravelsModel getArticle 도착~");
		Tourist_SpotBean article = null;
		
		SqlSession sqlSession = factory.openSession();
		article = sqlSession.selectOne("spot_one", lid);
		sqlSession.close();
		return article;
	}
	
	public void insertArticle(TravelsBean travels) throws Exception {
		System.out.println("TravelsModel insertArticle 도착~");
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.insert("travels_write", travels);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	public void deleteArticle(int tid) throws Exception {
		System.out.println("TravelsModel deleteArticle 도착~");
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.delete("travels_delete", tid);
		sqlSession.commit();
		sqlSession.close();
		
	}
	/* 수정하기 위해 원글 셀렉 */
	public TravelsBean updateArticle(int tid) throws Exception {
		System.out.println("TravelsModel updateArticle 도착~");
		TravelsBean article = null;
		SqlSession sqlSession = factory.openSession();
		article = sqlSession.selectOne("travels_update", tid);
		sqlSession.close();
		return article;
	}
	/* 수정 하기 완료 */
	public void updatingArticle(TravelsBean article) throws Exception {
		System.out.println("TravelsModel updatingArticle 도착~");
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.update("travels_updating", article);
		sqlSession.commit();
		sqlSession.close();
		
	}
}
