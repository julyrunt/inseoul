package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.BucketDAO;

public class BucketRecommendProService {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public boolean recommendBucket(int bid, int uid) throws Exception{
    boolean isRecommendSuccess = false;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    int recommendCount = bucketDAO.updateRecommend(bid, uid);
    if(recommendCount > 0){
      commit(con);
      isRecommendSuccess = true;
    } else{
      rollback(con);
    }
    close(con);
    return isRecommendSuccess;
  }
}
