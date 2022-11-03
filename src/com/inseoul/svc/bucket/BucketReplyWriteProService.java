package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;
import com.inseoul.vo.ReplyBean;
public class BucketReplyWriteProService {
  /* ----------------------------------------------------------------------------------------------------
   * 댓글을 vo 객체에 담아 DAO에 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean registArticle(ReplyBean replyBean) throws Exception{
    boolean isWriteSuccess = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    int insertCount = bucketDAO.insertReply(replyBean);
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
