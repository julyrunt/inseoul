package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;

public class AlbumReplyDeleteProService {

  public boolean isReplyWriter(int rid, int uid) throws Exception {
    boolean isReplyWriter = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    isReplyWriter = albumDAO.isReplyWriter(rid, uid);
    close(con);
    return isReplyWriter;
  }

  public boolean removeReply(int rid) throws Exception{
    boolean isRemoveSuccess = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int deleteCount = albumDAO.deleteReply(rid);
    if(deleteCount > 0){
      commit(con);
      isRemoveSuccess=true;
    } else {
      rollback(con);
    }
    close(con);
    return isRemoveSuccess;
  }
}
