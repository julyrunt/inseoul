package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;

public class BucketReplyDeleteProService {

  public boolean isReplyWriter(int rid, int uid) throws Exception {
    boolean isReplyWriter = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    isReplyWriter = bucketDAO.isReplyWriter(rid, uid);
    close(con);
    return isReplyWriter;
  }

  public boolean removeReply(int rid) throws Exception{
    boolean isRemoveSuccess = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    int deleteCount = bucketDAO.deleteReply(rid);
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
