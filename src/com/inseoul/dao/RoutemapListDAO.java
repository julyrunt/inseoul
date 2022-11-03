package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.inseoul.vo.RoutemapListBean;

public class RoutemapListDAO {
  DataSource ds;
  Connection con;
  private static RoutemapListDAO routemapListDAO;
  /* ----------------------------------------------------------------------------------------------------
   * 기본 생성자
   * ---------------------------------------------------------------------------------------------------- */
  private RoutemapListDAO() {
  }
  /* ----------------------------------------------------------------------------------------------------
   * 싱글톤 패턴으로 작성하여 반복 생성됨을 방지한다.
   * ---------------------------------------------------------------------------------------------------- */
  public static RoutemapListDAO getInstance(){
    if(routemapListDAO == null){
      routemapListDAO = new RoutemapListDAO();
    }
    return routemapListDAO;
  }
  /* ----------------------------------------------------------------------------------------------------
   * JdbcUtil이 생성한 연결 객체를 받는다.
   * ---------------------------------------------------------------------------------------------------- */
  public void setConnection(Connection con){
    this.con = con;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 전체 루트맵 게시물의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int selectRoutemapListCount(int pid, String category, String search, String keywords) {
    int listCount = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    if (category == null) {
      category = "my";
    }
    if (search == null) {
      search = "";
    }
    if (keywords == null) {
      keywords = "";
    }
    
    String sql = "select count(*) from routemaps a "
        + "left join users u on u.uid = a.uid "
        + "where ("
          + "(? = 'my' and a.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `routemap-recommend` r where r.mid = a.mid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_3) like concat('%', ?, '%') ) or "
          + "(? = 'nick' and u.nick like concat('%', ?, '%') ) or "
          + "(? = 'title' and a.title like concat('%', ?, '%') ) "
        + ") ";
    try{
      pstmt = con.prepareStatement(sql); 
      pstmt.setString(1, category);
      pstmt.setInt(2, pid);
      pstmt.setString(3, category);
      pstmt.setInt(4, pid);
      pstmt.setString(5, category);
      
      pstmt.setString(6, search);
      
      //1_0
      pstmt.setString(7, search);
      pstmt.setString(8, keywords);
      //1_1
      pstmt.setString(9, search);
      pstmt.setString(10, keywords);
      //1_2
      pstmt.setString(11, search);
      pstmt.setString(12, keywords);
      //1_3
      pstmt.setString(13, search);
      pstmt.setString(14, keywords);
      //2_0
      pstmt.setString(15, search);
      pstmt.setString(16, keywords);
      //2_1
      pstmt.setString(17, search);
      pstmt.setString(18, keywords);
      //2_2
      pstmt.setString(19, search);
      pstmt.setString(20, keywords);
      //2_3
      pstmt.setString(21, search);
      pstmt.setString(22, keywords);
      //3_0
      pstmt.setString(23, search);
      pstmt.setString(24, keywords);
      //3_1
      pstmt.setString(25, search);
      pstmt.setString(26, keywords);
      //3_2
      pstmt.setString(27, search);
      pstmt.setString(28, keywords);
      //3_3
      pstmt.setString(29, search);
      pstmt.setString(30, keywords);
      //4_0
      pstmt.setString(31, search);
      pstmt.setString(32, keywords);
      //4_1
      pstmt.setString(33, search);
      pstmt.setString(34, keywords);
      //4_2
      pstmt.setString(35, search);
      pstmt.setString(36, keywords);
      //4_3
      pstmt.setString(37, search);
      pstmt.setString(38, keywords);
      //5_0
      pstmt.setString(39, search);
      pstmt.setString(40, keywords);
      //5_1
      pstmt.setString(41, search);
      pstmt.setString(42, keywords);
      //5_2
      pstmt.setString(43, search);
      pstmt.setString(44, keywords);
      //5_3
      pstmt.setString(45, search);
      pstmt.setString(46, keywords);
      //6_0
      pstmt.setString(47, search);
      pstmt.setString(48, keywords);
      //6_1
      pstmt.setString(49, search);
      pstmt.setString(50, keywords);
      //6_2
      pstmt.setString(51, search);
      pstmt.setString(52, keywords);
      //6_3
      pstmt.setString(53, search);
      pstmt.setString(54, keywords);
      //7_0
      pstmt.setString(55, search);
      pstmt.setString(56, keywords);
      //7_1
      pstmt.setString(57, search);
      pstmt.setString(58, keywords);
      //7_2
      pstmt.setString(59, search);
      pstmt.setString(60, keywords);
      //7_3
      pstmt.setString(61, search);
      pstmt.setString(62, keywords);
      
      pstmt.setString(63, search);
      pstmt.setString(64, keywords);
      pstmt.setString(65, search);
      pstmt.setString(66, keywords);

      rs = pstmt.executeQuery();
      if(rs.next()){
        listCount = rs.getInt(1);
      }
    }catch(Exception ex){
      System.out.println("selectRoutemapListCount:" + ex);
    }finally{
      close(rs);
      close(pstmt);
    }
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 한 페이지의 루트맵 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<RoutemapListBean> selectRoutemapList(int page, int limit, int pid, int uid, String category, String search, String keywords){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    if (category == null) {
      category = "my";
    }
    if (search == null) {
      search = "";
    }
    if (keywords == null) {
      keywords = "";
    }
    
    String sql = "select "
          + "a.*, "
          + "u.nick, "
          + "(select l.name from `locations` l where l.lid = a.day1_0) day1_0_name,"
          + "(select l.name from `locations` l where l.lid = a.day1_1) day1_1_name,"
          + "(select l.name from `locations` l where l.lid = a.day1_2) day1_2_name,"
          + "(select l.name from `locations` l where l.lid = a.day1_3) day1_3_name,"
          + "(select l.name from `locations` l where l.lid = a.day2_0) day2_0_name,"
          + "(select l.name from `locations` l where l.lid = a.day2_1) day2_1_name,"
          + "(select l.name from `locations` l where l.lid = a.day2_2) day2_2_name,"
          + "(select l.name from `locations` l where l.lid = a.day2_3) day2_3_name,"
          + "(select l.name from `locations` l where l.lid = a.day3_0) day3_0_name,"
          + "(select l.name from `locations` l where l.lid = a.day3_1) day3_1_name,"
          + "(select l.name from `locations` l where l.lid = a.day3_2) day3_2_name,"
          + "(select l.name from `locations` l where l.lid = a.day3_3) day3_3_name,"
          + "(select l.name from `locations` l where l.lid = a.day4_0) day4_0_name,"
          + "(select l.name from `locations` l where l.lid = a.day4_1) day4_1_name,"
          + "(select l.name from `locations` l where l.lid = a.day4_2) day4_2_name,"
          + "(select l.name from `locations` l where l.lid = a.day4_3) day4_3_name,"
          + "(select l.name from `locations` l where l.lid = a.day5_0) day5_0_name,"
          + "(select l.name from `locations` l where l.lid = a.day5_1) day5_1_name,"
          + "(select l.name from `locations` l where l.lid = a.day5_2) day5_2_name,"
          + "(select l.name from `locations` l where l.lid = a.day5_3) day5_3_name,"
          + "(select l.name from `locations` l where l.lid = a.day6_0) day6_0_name,"
          + "(select l.name from `locations` l where l.lid = a.day6_1) day6_1_name,"
          + "(select l.name from `locations` l where l.lid = a.day6_2) day6_2_name,"
          + "(select l.name from `locations` l where l.lid = a.day6_3) day6_3_name,"
          + "(select l.name from `locations` l where l.lid = a.day7_0) day7_0_name,"
          + "(select l.name from `locations` l where l.lid = a.day7_1) day7_1_name,"
          + "(select l.name from `locations` l where l.lid = a.day7_2) day7_2_name,"
          + "(select l.name from `locations` l where l.lid = a.day7_3) day7_3_name,"
          + "(select count(*) from `routemap-reply` p where p.mid = a.mid) replycount," 
          + "(select count(*) from `routemap-recommend` r where r.mid = a.mid) recommendcount,"
          + "(select max(rdate) from `routemap-recommend` r where r.mid = a.mid) recommenddate,"
          + "(select count(*) from `routemap-recommend` r where r.mid = a.mid && r.uid = ?) favorite "
        + "from routemaps a "
        + "left join users u on u.uid = a.uid "
        + "where ("
          + "(? = 'my' and a.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `routemap-recommend` r where r.mid = a.mid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day1_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day2_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day3_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day4_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day5_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day6_3) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_0) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_1) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_2) like concat('%', ?, '%') ) or "
          + "(? = 'location' and (select l.name from `locations` l where l.lid = a.day7_3) like concat('%', ?, '%') ) or "
          + "(? = 'nick' and u.nick like concat('%', ?, '%') ) or "
          + "(? = 'title' and a.title like concat('%', ?, '%') ) "
        + ") "
        + "order by "
        + "if( ? = 'favorite', `recommenddate`, if( ? = 'recommend', `recommendcount`, a.mid)) desc, a.mid desc "
        + "limit ?,?";
    ArrayList<RoutemapListBean> articleList = new ArrayList<RoutemapListBean>();
    RoutemapListBean routemap = null;
    int startrow = (page - 1) * limit;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, uid);
      pstmt.setString(2, category);
      pstmt.setInt(3, pid);
      pstmt.setString(4, category);
      pstmt.setInt(5, pid);
      pstmt.setString(6, category);
      
      pstmt.setString(7, search);
      pstmt.setString(8, search);
      pstmt.setString(9, keywords);
      pstmt.setString(10, search);
      pstmt.setString(11, keywords);
      //1_0
      pstmt.setString(12, search);
      pstmt.setString(13, keywords);
      //1_1
      pstmt.setString(14, search);
      pstmt.setString(15, keywords);
      //1_2
      pstmt.setString(16, search);
      pstmt.setString(17, keywords);
      //1_3
      pstmt.setString(18, search);
      pstmt.setString(19, keywords);
      //2_0
      pstmt.setString(20, search);
      pstmt.setString(21, keywords);
      //2_1
      pstmt.setString(22, search);
      pstmt.setString(23, keywords);
      //2_2
      pstmt.setString(24, search);
      pstmt.setString(25, keywords);
      //2_3
      pstmt.setString(26, search);
      pstmt.setString(27, keywords);
      //3_0
      pstmt.setString(28, search);
      pstmt.setString(29, keywords);
      //3_1
      pstmt.setString(30, search);
      pstmt.setString(31, keywords);
      //3_2
      pstmt.setString(32, search);
      pstmt.setString(33, keywords);
      //3_3
      pstmt.setString(34, search);
      pstmt.setString(35, keywords);
      //4_0
      pstmt.setString(36, search);
      pstmt.setString(37, keywords);
      //4_1
      pstmt.setString(38, search);
      pstmt.setString(39, keywords);
      //4_2
      pstmt.setString(40, search);
      pstmt.setString(41, keywords);
      //4_3
      pstmt.setString(42, search);
      pstmt.setString(43, keywords);
      //5_0
      pstmt.setString(44, search);
      pstmt.setString(45, keywords);
      //5_1
      pstmt.setString(46, search);
      pstmt.setString(47, keywords);
      //5_2
      pstmt.setString(48, search);
      pstmt.setString(49, keywords);
      //5_3
      pstmt.setString(50, search);
      pstmt.setString(51, keywords);
      //6_0
      pstmt.setString(52, search);
      pstmt.setString(53, keywords);
      //6_1
      pstmt.setString(54, search);
      pstmt.setString(55, keywords);
      //6_2
      pstmt.setString(56, search);
      pstmt.setString(57, keywords);
      //6_3
      pstmt.setString(58, search);
      pstmt.setString(59, keywords);
      //7_0
      pstmt.setString(60, search);
      pstmt.setString(61, keywords);
      //7_1
      pstmt.setString(62, search);
      pstmt.setString(63, keywords);
      //7_2
      pstmt.setString(64, search);
      pstmt.setString(65, keywords);
      //7_3
      pstmt.setString(66, search);
      pstmt.setString(67, keywords);
      
      pstmt.setString(68, category);
      pstmt.setString(69, category);
      
      pstmt.setInt(70, startrow);
      pstmt.setInt(71, limit);
      rs = pstmt.executeQuery();
      while(rs.next()){
        routemap = new RoutemapListBean();
        routemap.setMid(rs.getInt("mid"));
        routemap.setUid(rs.getInt("uid"));
        routemap.setNick(rs.getString("nick"));
        routemap.setTitle(rs.getString("title"));
        routemap.setImg(rs.getString("img"));
        routemap.setDay1_0(rs.getString("day1_0_name"));
        routemap.setDay1_1(rs.getString("day1_1_name"));
        routemap.setDay1_2(rs.getString("day1_2_name"));
        routemap.setDay1_3(rs.getString("day1_3_name"));
        routemap.setDay2_0(rs.getString("day2_0_name"));
        routemap.setDay2_1(rs.getString("day2_1_name"));
        routemap.setDay2_2(rs.getString("day2_2_name"));
        routemap.setDay2_3(rs.getString("day2_3_name"));
        routemap.setDay3_0(rs.getString("day3_0_name"));
        routemap.setDay3_1(rs.getString("day3_1_name"));
        routemap.setDay3_2(rs.getString("day3_2_name"));
        routemap.setDay3_3(rs.getString("day3_3_name"));
        routemap.setDay4_0(rs.getString("day4_0_name"));
        routemap.setDay4_1(rs.getString("day4_1_name"));
        routemap.setDay4_2(rs.getString("day4_2_name"));
        routemap.setDay4_3(rs.getString("day4_3_name"));
        routemap.setDay5_0(rs.getString("day5_0_name"));
        routemap.setDay5_1(rs.getString("day5_1_name"));
        routemap.setDay5_2(rs.getString("day5_2_name"));
        routemap.setDay5_3(rs.getString("day5_3_name"));
        routemap.setDay6_0(rs.getString("day6_0_name"));
        routemap.setDay6_1(rs.getString("day6_1_name"));
        routemap.setDay6_2(rs.getString("day6_2_name"));
        routemap.setDay6_3(rs.getString("day6_3_name"));
        routemap.setDay7_0(rs.getString("day7_0_name"));
        routemap.setDay7_1(rs.getString("day7_1_name"));
        routemap.setDay7_2(rs.getString("day7_2_name"));
        routemap.setDay7_3(rs.getString("day7_3_name"));
        routemap.setPlan_start(rs.getDate("plan_start"));
        routemap.setPlan_end(rs.getDate("plan_end"));
        routemap.setWriteday(rs.getDate("writeday"));
        routemap.setReadCount(rs.getInt("readcount"));
        routemap.setReplyCount(rs.getInt("replycount"));
        routemap.setRecommendCount(rs.getInt("recommendcount"));
        routemap.setFavorite(rs.getInt("favorite"));
        articleList.add(routemap);
      }
    } catch(Exception ex) {
      System.out.println("selectRoutemapList:" + ex);
    } finally {
      close(rs);
      close(pstmt);
    }

    return articleList;
  }
}
