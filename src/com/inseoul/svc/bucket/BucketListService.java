package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.BucketDAO;
import com.inseoul.vo.BucketBean;

public class BucketListService {
  /* ----------------------------------------------------------------------------------------------------
   * 게시판 글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int pid, String category, String search, String keywords) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    listCount = bucketDAO.selectBucketListCount(pid, category, search, keywords);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 게시판 글 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<BucketBean> getBucketList(int page, int limit, int pid, int uid, String category, String search, String keywords) throws Exception{
    ArrayList<BucketBean> bucketList = null;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    bucketList = bucketDAO.selectBucketList(page, limit, pid, uid, category, search, keywords);
    close(con);
    return bucketList;
  }
}
