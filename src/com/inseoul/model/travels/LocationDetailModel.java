package com.inseoul.model.travels;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.AlbumBean;
import com.inseoul.vo.BucketBean;
import com.inseoul.vo.Routemaps;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;

public class LocationDetailModel {
	static LocationDetailModel model = new LocationDetailModel();

	public static LocationDetailModel instance() {
		return model;
	}
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public Tourist_SpotBean getArticle(int lid) throws Exception {
		System.out.println("LocationDetailModel getArticle 도착~");
		Tourist_SpotBean article = null;
		
		SqlSession sqlSession = factory.openSession();
		article = sqlSession.selectOne("spot_one", lid);
		sqlSession.close();
		return article;
	}
	
	public List<TravelsBean> getTravels(int lid) throws Exception {
		System.out.println("LocationDetailModel getTravels 도착~");
		List<TravelsBean> travels = null;
		
		SqlSession sqlSession = factory.openSession();
		travels = sqlSession.selectList("travels_select", lid);
		sqlSession.close();
		return travels;
	}
	
	public List<BucketBean> getBucket(int lid) throws Exception {
		System.out.println("LocationDetailModel getBucket 도착~");
		List<BucketBean> bucket = null;
		
		SqlSession sqlSession = factory.openSession();
		bucket = sqlSession.selectList("bucket_select", lid);
		sqlSession.close();
		return bucket;
	}
	
	public List<AlbumBean> getAlbum(int lid) throws Exception {
		System.out.println("LocationDetailModel getAlbum 도착~");
		List<AlbumBean> album = null;
		
		SqlSession sqlSession = factory.openSession();
		album = sqlSession.selectList("album_select", lid);
		sqlSession.close();
		return album;
	}
	
	public List<Routemaps> getRoute(int lid) throws Exception {
		System.out.println("LocationDetailModel getRoute 도착~");
		List<Routemaps> route = null;
		
		SqlSession sqlSession = factory.openSession();
		route = sqlSession.selectList("route_select", lid);
		sqlSession.close();
		return route;
	}
}
