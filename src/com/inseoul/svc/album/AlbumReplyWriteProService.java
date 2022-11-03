package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;
import com.inseoul.vo.ReplyBean;
public class AlbumReplyWriteProService {
  /* ----------------------------------------------------------------------------------------------------
   * 댓글을 vo 객체에 담아 DAO에 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean registArticle(ReplyBean replyBean) throws Exception{
    boolean isWriteSuccess = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int insertCount = albumDAO.insertReply(replyBean);
    if(insertCount > 0){
      commit(con);
      isWriteSuccess = true;
    } else{
      rollback(con);
    }
    close(con);
    return isWriteSuccess;
  }
}
