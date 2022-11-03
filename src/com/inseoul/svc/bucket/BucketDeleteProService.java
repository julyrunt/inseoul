package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;

public class BucketDeleteProService {

  public boolean isBucketWriter(int bid, int uid) throws Exception {
    boolean isBucketWriter = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    isBucketWriter = bucketDAO.isBucketWriter(bid, uid);
    close(con);
    return isBucketWriter;
  }
  
  public boolean removeBucket(int bid) throws Exception{
    boolean isRemoveSuccess = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    int deleteCount = bucketDAO.deleteBucket(bid);
    if(deleteCount > 0){
      commit(con);
      isRemoveSuccess = true;
    } else{
      rollback(con);
    }
    close(con);
    return isRemoveSuccess;
  }

}
