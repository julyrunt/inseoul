package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.inseoul.vo.LocationBean;

public class LocationDAO {
  DataSource ds;
  Connection con;
  private static LocationDAO locationDAO;
  /* ----------------------------------------------------------------------------------------------------
   * 기본 생성자
   * ---------------------------------------------------------------------------------------------------- */
  private LocationDAO() {
  }
  /* ----------------------------------------------------------------------------------------------------
   * 싱글톤 패턴으로 작성하여 반복 생성됨을 방지한다.
   * ---------------------------------------------------------------------------------------------------- */
  public static LocationDAO getInstance(){
      if(locationDAO == null){
        locationDAO = new LocationDAO();
      }
      return locationDAO;
  }
  /* ----------------------------------------------------------------------------------------------------
   * JdbcUtil이 생성한 연결 객체를 받는다.
   * ---------------------------------------------------------------------------------------------------- */
  public void setConnection(Connection con){
      this.con = con;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 관광지 목록 열람
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<LocationBean> selectLocationList() {
    ArrayList<LocationBean> list = new ArrayList<>();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt=con.prepareStatement("select * from locations");
      rs = pstmt.executeQuery();
      while (rs.next()) {
        LocationBean location = new LocationBean();
        location.setLid(rs.getInt("lid"));
        location.setName(rs.getString("name"));
        location.setImg(rs.getString("img"));
        location.setTel(rs.getString("tel"));
        location.setSite(rs.getString("site"));
        location.setAddr(rs.getString("addr"));
        location.setUsetime(rs.getString("usetime"));
        location.setHoliday(rs.getString("holiday"));
        location.setUseinfo(rs.getString("useinfo"));
        location.setInfo(rs.getString("info"));
        list.add(location);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close(rs);
      close(pstmt);
    }
    return list;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 해당 ID값을 지닌 레코드가 존재하는지 확인한다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isLocationID(int lid) {
    boolean isLocationID = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = con.prepareStatement("select * `locations` where `lid` = ?");
      rs = pstmt.executeQuery();
      pstmt.setInt(1, lid);
      if (rs.next()) {
        isLocationID = true;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close(rs);
      close(pstmt);
    }
    return isLocationID;
  }
}