package com.inseoul.svc.album;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import com.inseoul.dao.AlbumDAO;

public class AlbumRecommendProService {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public boolean recommendAlbum(int aid, int uid) throws Exception{
    boolean isRecommendSuccess = false;
    Connection con = getConnection();
    AlbumDAO albumDAO = AlbumDAO.getInstance();
    albumDAO.setConnection(con);
    int recommendCount = albumDAO.updateRecommend(aid, uid);
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
