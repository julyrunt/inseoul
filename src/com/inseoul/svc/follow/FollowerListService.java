package com.inseoul.svc.follow;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.UserDAO;
import com.inseoul.vo.FollowBean;

public class FollowerListService {
  /* ----------------------------------------------------------------------------------------------------
   * 팔로워의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int pid) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    UserDAO userDAO = UserDAO.getInstance();
    userDAO.setConnection(con);
    listCount = userDAO.selectFollowerListCount(pid);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 팔로워 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<FollowBean> getFollowerList(int pid, int uid, int page, int limit) throws Exception{
    ArrayList<FollowBean> followerList = null;
    Connection con = getConnection();
    UserDAO userDAO = UserDAO.getInstance();
    userDAO.setConnection(con);
    followerList = userDAO.selectFollowerList(pid, uid, page, limit);
    close(con);
    return followerList;
  }
}
