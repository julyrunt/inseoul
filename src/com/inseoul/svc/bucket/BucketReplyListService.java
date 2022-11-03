package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.BucketDAO;
import com.inseoul.vo.ReplyBean;

public class BucketReplyListService {
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시물에 달린 댓글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int bid) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    listCount = bucketDAO.selectReplyListCount(bid);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시물에 달린 댓글 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<ReplyBean> getReplyList(int bid, int page, int limit) throws Exception{
    ArrayList<ReplyBean> replyList = null;
    Connection con = getConnection();
    BucketDAO bucketDAO = BucketDAO.getInstance();
    bucketDAO.setConnection(con);
    replyList = bucketDAO.selectReplyList(bid, page, limit);
    close(con);
    return replyList;
  }
}
