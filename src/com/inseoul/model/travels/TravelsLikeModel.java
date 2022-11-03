package com.inseoul.model.travels;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.Tourist_SpotBean;

public class TravelsLikeModel {
	static TravelsLikeModel model = new TravelsLikeModel();

	public static TravelsLikeModel instance() {
		return model;
	}
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public int getLike(int tid, int uid) throws Exception {
		System.out.println("TravelsLikeModel getArticle 도착~");
		int likeCount = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		map.put("uid", uid);
		SqlSession sqlSession = factory.openSession();
		likeCount = sqlSession.selectOne("travels_like_select", map);
		
		switch (likeCount) {
			case 0:
				sqlSession.insert("travels_like_insert", map);
				sqlSession.commit();
				break;
			case 1:
				sqlSession.delete("travels_like_delete", map);
				sqlSession.commit();
				break;
			default:
				break;
		}
		
		sqlSession.close();
		return likeCount;
	}
}
