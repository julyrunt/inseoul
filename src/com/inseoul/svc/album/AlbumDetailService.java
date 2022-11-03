package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;
import com.inseoul.vo.AlbumBean;

public class AlbumDetailService {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public AlbumBean getAlbum(int aid, int uid) throws Exception{
    AlbumBean article = null;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int updateCount = albumDAO.updateReadCount(aid);

    if(updateCount > 0){
      commit(con);
    }
    else{
      rollback(con);
    }

    article = albumDAO.selectAlbum(aid, uid);
    close(con);
    return article;

  }

}
