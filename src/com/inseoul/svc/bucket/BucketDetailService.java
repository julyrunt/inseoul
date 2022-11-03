package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;
import com.inseoul.vo.BucketBean;

public class BucketDetailService {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public BucketBean getBucket(int bid, int uid) throws Exception{
    BucketBean bucket = null;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    bucket = bucketDAO.selectBucket(bid, uid);
    close(con);
    return bucket;

  }

}
