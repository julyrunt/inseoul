package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.AlbumDAO;
import com.inseoul.vo.AlbumBean;

public class AlbumListService {
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시판 글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int getListCount(int uid, String category, String search, String keywords) throws Exception{
    int listCount = 0;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    listCount = albumDAO.selectAlbumListCount(uid, category, search, keywords);
    close(con);
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시판 글 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<AlbumBean> getAlbumList(int page, int limit, int pid, int uid, String category, String search, String keywords) throws Exception{
    ArrayList<AlbumBean> albumList = null;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    albumList = albumDAO.selectAlbumList(page, limit, pid, uid, category, search, keywords);
    close(con);
    return albumList;
  }
}
