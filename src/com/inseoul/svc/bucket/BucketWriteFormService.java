package com.inseoul.svc.bucket;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;
import java.sql.Connection;
import java.util.ArrayList;
import com.inseoul.dao.LocationDAO;
import com.inseoul.vo.LocationBean;

public class BucketWriteFormService {
  /* ----------------------------------------------------------------------------------------------------
   * 관광지 선택을 위한 콜렉션을 취득하여 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<LocationBean> getLocationList() throws Exception{
    ArrayList<LocationBean> locationList = null;
    Connection con = getConnection();
    LocationDAO locationDAO = LocationDAO.getInstance();
    locationDAO.setConnection(con);
    locationList = locationDAO.selectLocationList();
    close(con);
    return locationList;
  }
}
