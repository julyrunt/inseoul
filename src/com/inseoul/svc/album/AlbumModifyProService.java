package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;
import com.inseoul.vo.AlbumBean;

public class AlbumModifyProService {

  public boolean isAlbumWriter(int aid, int uid) throws Exception {
    boolean isAlbumWriter = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    isAlbumWriter = albumDAO.isAlbumWriter(aid, uid);
    close(con);
    return isAlbumWriter;
  }

  public boolean modifyAlbum(AlbumBean album) throws Exception {
    boolean isModifySuccess = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int updateCount = albumDAO.updateAlbum(album);
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
