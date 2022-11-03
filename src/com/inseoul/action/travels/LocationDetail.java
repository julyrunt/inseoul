package com.inseoul.action.travels;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.LocationDetailModel;
import com.inseoul.vo.AlbumBean;
import com.inseoul.vo.BucketBean;
import com.inseoul.vo.Routemaps;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;

public class LocationDetail implements CommandInter {

	static LocationDetail impl = new LocationDetail();

	public static LocationDetail instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LocationDetail showData 옴~");
		LocationDetailModel model = LocationDetailModel.instance();
		Tourist_SpotBean article = new Tourist_SpotBean();
		List<TravelsBean> travels = null;
		List<BucketBean> bucket = null;
		List<AlbumBean> album = null;
		List<Routemaps> route = null;
		String lid = (String) request.getParameter("lid");
		
		article = model.getArticle(Integer.parseInt(lid));
		travels = model.getTravels(Integer.parseInt(lid));
		bucket = model.getBucket(Integer.parseInt(lid));
		album = model.getAlbum(Integer.parseInt(lid));
		route = model.getRoute(Integer.parseInt(lid));
		
		request.setAttribute("article", article);
		request.setAttribute("travels", travels);
		request.setAttribute("bucket", bucket);
		request.setAttribute("album", album);
		request.setAttribute("route", route);
		
		return "location-detail.jsp";
	}
}
