package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;
import com.inseoul.vo.AlbumBean;
public class AlbumWriteProService {

  public boolean registAlbum(AlbumBean albumBean) throws Exception{
    boolean isWriteSuccess = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int insertCount = albumDAO.insertAlbum(albumBean);
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
