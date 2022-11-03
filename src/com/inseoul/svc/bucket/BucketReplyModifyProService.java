package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;
import com.inseoul.vo.ReplyBean;

public class BucketReplyModifyProService {

  public boolean isReplyWriter(int rid, int uid) throws Exception {
    boolean isReplyWriter = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    isReplyWriter = bucketDAO.isReplyWriter(rid, uid);
    close(con);
    return isReplyWriter;
  }

  public boolean modifyReply(ReplyBean replyBean) throws Exception{
    boolean isModifySuccess = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    int modifyCount = bucketDAO.updateReply(replyBean);
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
