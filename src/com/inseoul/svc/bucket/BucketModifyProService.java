package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;
import com.inseoul.vo.BucketBean;

public class BucketModifyProService {

  public boolean isBucketWriter(int bid, int uid) throws Exception {
    boolean isBucketWriter = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    isBucketWriter = bucketDAO.isBucketWriter(bid, uid);
    close(con);
    return isBucketWriter;
  }

  public boolean modifyBucket(BucketBean bucket) throws Exception {
    boolean isModifySuccess = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    int updateCount = bucketDAO.updateBucket(bucket);
    if(updateCount > 0){
      commit(con);
      isModifySuccess=true;
    } else {
      rollback(con);
    }
    close(con);
    return isModifySuccess;
  }
}
