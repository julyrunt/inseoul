package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;
import static com.inseoul.db.JdbcUtil.getConnection;
import static com.inseoul.db.JdbcUtil.rollback;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;

public class AlbumDeleteProService {

  public boolean isAlbumWriter(int aid, int uid) throws Exception {
    boolean isAlbumWriter = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    isAlbumWriter = albumDAO.isAlbumWriter(aid, uid);
    close(con);
    return isAlbumWriter;
  }
  
  public boolean removeAlbum(int aid) throws Exception{
    boolean isRemoveSuccess = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int deleteCount = albumDAO.deleteAlbum(aid);
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
