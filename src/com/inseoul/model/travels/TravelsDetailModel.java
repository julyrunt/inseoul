package com.inseoul.model.travels;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.dao.WithBoardDAO;
import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.ReplyBean;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;
import com.inseoul.vo.WithBoardBean;

public class TravelsDetailModel {
	
	static TravelsDetailModel model = new TravelsDetailModel();

	public static TravelsDetailModel instance() {
		return model;
	}
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public TravelsBean getArticle(int tid) throws Exception {
		System.out.println("TravelsDetailModel getArticle 도착~");
		TravelsBean article = null;
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.update("travels_readcount", tid);
		sqlSession.commit();
		article = sqlSession.selectOne("travels_one", tid);
		sqlSession.close();
		return article;
	}
	
	public TravelsBean getArticle(int uid, int tid) throws Exception {
		System.out.println("TravelsDetailModel getArticle 도착~");
		TravelsBean article = null;
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.update("travels_readcount", tid);
		sqlSession.commit();
		article = sqlSession.selectOne("travels_one", tid);
		sqlSession.close();
		return article;
	}
	
	public int getLike(int uid, int tid) {
		System.out.println("TravelsDetailModel getLike 도착 ");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("tid", tid);
		SqlSession sqlSession = factory.openSession();
		int likeCount = sqlSession.selectOne("travels_likeCount", map);
		sqlSession.close();
		return likeCount;
	}
	
	public int getReplyListCount(int tid) throws Exception {

		int replylistCount = 0;
		
		SqlSession sqlSession = factory.openSession();
		replylistCount = sqlSession.selectOne("travels_replyCount", tid);
		sqlSession.close();
		return replylistCount;
	}
	
	public List<ReplyBean> getReplyList(int tid, int replypage) throws Exception{
		System.out.println("TravelsDetailModel getReplyList 도착 ");
		
		List<ReplyBean> replyList = null;
		int startrow = (replypage - 1 ) * 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		map.put("startrow", startrow);
		
		SqlSession sqlSession = factory.openSession();
		replyList = sqlSession.selectList("travels_replyList", map);
		sqlSession.close();
		return replyList;
	}
}
