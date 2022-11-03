package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.AlbumDAO;
import com.inseoul.vo.ReplyBean;

public class AlbumReplyListService {
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시물에 달린 댓글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int aid) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    listCount = albumDAO.selectReplyListCount(aid);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시물에 달린 댓글 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<ReplyBean> getReplyList(int aid, int page, int limit) throws Exception{
    ArrayList<ReplyBean> replyList = null;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    replyList = albumDAO.selectReplyList(aid, page, limit);
    close(con);
    return replyList;
  }
}
