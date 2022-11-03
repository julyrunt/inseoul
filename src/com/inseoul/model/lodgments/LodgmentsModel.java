package com.inseoul.model.lodgments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inseoul.mybatis.SqlMapConfig;
import com.inseoul.vo.LodgmentsBean;

public class LodgmentsModel {
	static LodgmentsModel model = new LodgmentsModel();

	public static LodgmentsModel instance() {
		return model;
	}

	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	/* 숙박 전체 리스트 */
	public List<LodgmentsBean> selectAllLodgments() {
		System.out.println("LodgmentsModel selectAllLodgments 도착~");
		List<LodgmentsBean> list = null;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("lodgmentsList");
		sqlSession.close();
		return list;
	}
	/* 숙박 필터 적용 검색 */
	public List<LodgmentsBean> selectFilterLodgments(int rating, String[] selects, String bedtype) {
		System.out.println("LodgmentsModel selectFilterLodgments 도착~");
		Map<String, Object> map = new HashMap<String, Object>();
		List<LodgmentsBean> list = null;
		SqlSession sqlSession = factory.openSession();
		
		String bedtypeStr;
		switch (bedtype) {
		case "s":
			bedtypeStr = "싱글";
			break;
		case "d":
			bedtypeStr = "더블";
			break;
		case "t":
			bedtypeStr = "트윈";
			break;
		case "o":
			bedtypeStr = "온돌";
			break;
		default:
			bedtypeStr = "";
			break;
		}
		
			map.put("selects", selects);
			System.out.println("a:" + map);
		
		
		map.put("bedtype", bedtypeStr);
		
		switch (rating) {
		case 4:
			list = sqlSession.selectList("lodgments_filter4", map);

			break;
		case 5:
			list = sqlSession.selectList("lodgments_filter5", selects);

			break;
		case 6:
			list = sqlSession.selectList("lodgments_filter6", selects);

			break;
		case 7:
			list = sqlSession.selectList("lodgments_filter7", selects);

			break;
		}
		
		System.out.println("모델나간다~ : "+list);
		return list;
	}
	
}
