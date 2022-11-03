package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;
import com.inseoul.vo.BucketBean;
public class BucketWriteProService {

  public boolean registBucket(BucketBean bucketBean) throws Exception{
    boolean isWriteSuccess = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    int insertCount = bucketDAO.insertBucket(bucketBean);
    if(insertCount > 0){
      commit(con);
      isWriteSuccess = true;
    } else {
      rollback(con);
    }
    close(con);
    return isWriteSuccess;
  }
}
