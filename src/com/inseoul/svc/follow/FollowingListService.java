package com.inseoul.svc.follow;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.UserDAO;
import com.inseoul.vo.FollowBean;

public class FollowingListService {
  /* ----------------------------------------------------------------------------------------------------
   * 팔로잉의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int pid) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    UserDAO userDAO = UserDAO.getInstance();
    userDAO.setConnection(con);
    listCount = userDAO.selectFollowingListCount(pid);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 팔로잉 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<FollowBean> getFollowingList(int pid, int uid, int page, int limit) throws Exception{
    ArrayList<FollowBean> followingList = null;
    Connection con = getConnection();
    UserDAO userDAO = UserDAO.getInstance();
    userDAO.setConnection(con);
    followingList = userDAO.selectFollowingList(pid, uid, page, limit);
    close(con);
    return followingList;
  }
}
