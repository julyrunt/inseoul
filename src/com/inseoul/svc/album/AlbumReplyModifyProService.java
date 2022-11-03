package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;
import com.inseoul.vo.ReplyBean;

public class AlbumReplyModifyProService {

  public boolean isReplyWriter(int rid, int uid) throws Exception {
    boolean isReplyWriter = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    isReplyWriter = albumDAO.isReplyWriter(rid, uid);
    close(con);
    return isReplyWriter;
  }

  public boolean modifyReply(ReplyBean replyBean) throws Exception{
    boolean isModifySuccess = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int modifyCount = albumDAO.updateReply(replyBean);
    if(modifyCount > 0){
      commit(con);
      isModifySuccess=true;
    } else {
      rollback(con);
    }
    close(con);
    return isModifySuccess;
  }
}
