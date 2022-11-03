package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.inseoul.vo.TravelsListBean;

public class TravelsListDAO {
  DataSource ds;
  Connection con;
  private static TravelsListDAO travelsListDAO;
  /* ----------------------------------------------------------------------------------------------------
   * 기본 생성자
   * ---------------------------------------------------------------------------------------------------- */
  private TravelsListDAO() {
  }
  /* ----------------------------------------------------------------------------------------------------
   * 싱글톤 패턴으로 작성하여 반복 생성됨을 방지한다.
   * ---------------------------------------------------------------------------------------------------- */
  public static TravelsListDAO getInstance(){
    if(travelsListDAO == null){
      travelsListDAO = new TravelsListDAO();
    }
    return travelsListDAO;
  }
  /* ----------------------------------------------------------------------------------------------------
   * JdbcUtil이 생성한 연결 객체를 받는다.
   * ---------------------------------------------------------------------------------------------------- */
  public void setConnection(Connection con){
    this.con = con;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 전체 여행기 게시물의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int selectTravelsListCount(int pid, String category, String search, String keywords) {
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
    
    String sql = "select count(*) from travels a "
        + "left join users u on u.uid = a.uid "
        + "left join locations l on l.lid = a.lid "
        + "where ("
          + "(? = 'my' and a.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `travels-recommend` r where r.tid = a.tid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and l.`name` like concat('%', ?, '%') ) or "
          + "(? = 'nick' and u.nick like concat('%', ?, '%') ) or "
          + "(? = 'title' and a.title like concat('%', ?, '%') ) "
        + ")";
    try{
      pstmt = con.prepareStatement(sql); 
      pstmt.setString(1, category);
      pstmt.setInt(2, pid);
      pstmt.setString(3, category);
      pstmt.setInt(4, pid);
      pstmt.setString(5, category);
      
      pstmt.setString(6, search);
      pstmt.setString(7, search);
      pstmt.setString(8, keywords);
      pstmt.setString(9, search);
      pstmt.setString(10, keywords);
      pstmt.setString(11, search);
      pstmt.setString(12, keywords);
      rs = pstmt.executeQuery();
      if(rs.next()){
        listCount = rs.getInt(1);
      }
    }catch(Exception ex){
      System.out.println("selectTravelsListCount:" + ex);
    }finally{
      close(rs);
      close(pstmt);
    }
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 한 페이지의 여행기 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<TravelsListBean> selectTravelsList(int page, int limit, int pid, int uid, String category, String search, String keywords){
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
          + "l.`name`, "
          + "(select count(*) from `travels-reply` p where p.tid = a.tid) replycount," 
          + "(select count(*) from `travels-recommend` r where r.tid = a.tid) recommendcount,"
          + "(select max(rdate) from `travels-recommend` r where r.tid = a.tid) recommenddate,"
          + "(select count(*) from `travels-recommend` r where r.tid = a.tid && r.uid = ?) favorite "
        + "from travels a "
        + "left join users u on u.uid = a.uid "
        + "left join locations l on l.lid = a.lid "
        + "where ("
          + "(? = 'my' and a.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `travels-recommend` r where r.tid = a.tid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and l.`name` like concat('%', ?, '%') ) or "
          + "(? = 'nick' and u.nick like concat('%', ?, '%') ) or "
          + "(? = 'title' and a.title like concat('%', ?, '%') ) "
        + ") "
        + "order by "
        + "if( ? = 'favorite', `recommenddate`, if( ? = 'recommend', `recommendcount`, a.tid)) desc, a.tid desc "
        + "limit ?,?";
    ArrayList<TravelsListBean> articleList = new ArrayList<TravelsListBean>();
    TravelsListBean travels = null;
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
      pstmt.setString(12, search);
      pstmt.setString(13, keywords);
      
      pstmt.setString(14, category);
      pstmt.setString(15, category);
      
      pstmt.setInt(16, startrow);
      pstmt.setInt(17, limit);
      rs = pstmt.executeQuery();
      while(rs.next()){
        travels = new TravelsListBean();
        travels.setTid(rs.getInt("tid"));
        travels.setUid(rs.getInt("uid"));
        travels.setLid(rs.getInt("lid"));
        travels.setNick(rs.getString("nick"));
        travels.setName(rs.getString("name"));
        travels.setTitle(rs.getString("title"));
        travels.setContents(rs.getString("contents"));
        travels.setImg01(rs.getString("img01"));
        travels.setImg02(rs.getString("img02"));
        travels.setImg03(rs.getString("img03"));
        travels.setDate(rs.getDate("date"));
        travels.setReadCount(rs.getInt("readcount"));
        travels.setReplyCount(rs.getInt("replycount"));
        travels.setRecommendCount(rs.getInt("recommendcount"));
        travels.setFavorite(rs.getInt("favorite"));
        articleList.add(travels);
      }
    } catch(Exception ex) {
      System.out.println("selectTravelsList:" + ex);
    } finally {
      close(rs);
      close(pstmt);
    }

    return articleList;
  }
  
}
