package com.inseoul.svc.routemap;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.RoutemapListDAO;
import com.inseoul.vo.RoutemapListBean;

public class RoutemapListService {
  /* ----------------------------------------------------------------------------------------------------
   * 게시판 글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int pid, String category, String search, String keywords) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    RoutemapListDAO routemapListDAO = RoutemapListDAO.getInstance();
    routemapListDAO.setConnection(con);
    listCount = routemapListDAO.selectRoutemapListCount(pid, category, search, keywords);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 게시판 글 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<RoutemapListBean> getRoutemapList(int page, int limit, int pid, int uid, String category, String search, String keywords) throws Exception{
    ArrayList<RoutemapListBean> routemapList = null;
    Connection con = getConnection();
    RoutemapListDAO routemapListDAO = RoutemapListDAO.getInstance();
    routemapListDAO.setConnection(con);
    routemapList = routemapListDAO.selectRoutemapList(page, limit, pid, uid, category, search, keywords);
    close(con);
    return routemapList;
  }
}
