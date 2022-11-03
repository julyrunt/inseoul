package com.inseoul.svc.travels;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.TravelsListDAO;
import com.inseoul.vo.TravelsListBean;

public class TravelsListService {
  /* ----------------------------------------------------------------------------------------------------
   * 게시판 글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int pid, String category, String search, String keywords) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    TravelsListDAO travelsListDAO = TravelsListDAO.getInstance();
    travelsListDAO.setConnection(con);
    listCount = travelsListDAO.selectTravelsListCount(pid, category, search, keywords);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 게시판 글 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<TravelsListBean> getTravelsList(int page, int limit, int pid, int uid, String category, String search, String keywords) throws Exception{
    ArrayList<TravelsListBean> travelsList = null;
    Connection con = getConnection();
    TravelsListDAO travelsListDAO = TravelsListDAO.getInstance();
    travelsListDAO.setConnection(con);
    travelsList = travelsListDAO.selectTravelsList(page, limit, pid, uid, category, search, keywords);
    close(con);
    return travelsList;
  }
}
