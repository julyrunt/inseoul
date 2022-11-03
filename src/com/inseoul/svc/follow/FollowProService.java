package com.inseoul.svc.follow;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.UserDAO;

public class FollowProService {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public boolean updateFollow(int uid, int pid) throws Exception{
    boolean isFollowSuccess = false;
    Connection con = getConnection();
    UserDAO userDAO = UserDAO.getInstance();
    userDAO.setConnection(con);
    int followCount = userDAO.updateFollow(uid, pid);
    if(followCount > 0){
      commit(con);
      isFollowSuccess = true;
    } else{
      rollback(con);
    }
    close(con);
    return isFollowSuccess;
  }
}
