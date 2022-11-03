package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.inseoul.vo.FollowBean;

public class UserDAO {
    DataSource ds;
    Connection con;
    private static UserDAO userDAO;
    /* ----------------------------------------------------------------------------------------------------
     * 기본 생성자
     * ---------------------------------------------------------------------------------------------------- */
    private UserDAO() {
    }
    /* ----------------------------------------------------------------------------------------------------
     * 싱글톤 패턴으로 작성하여 반복 생성됨을 방지한다.
     * ---------------------------------------------------------------------------------------------------- */
    public static UserDAO getInstance(){
        if(userDAO == null){
          userDAO = new UserDAO();
        }
        return userDAO;
    }
    /* ----------------------------------------------------------------------------------------------------
     * JdbcUtil이 생성한 연결 객체를 받는다.
     * ---------------------------------------------------------------------------------------------------- */
    public void setConnection(Connection con){
        this.con = con;
    }
    /* ----------------------------------------------------------------------------------------------------
     * 팔로우 내역을 수정합니다.
     * ---------------------------------------------------------------------------------------------------- */
    public int updateFollow(int followerUserId, int followingUserId) {
      int updateCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select * from `friends` where follower = ? && following = ?";
      try{
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, followerUserId);
        pstmt.setInt(2, followingUserId);
        rs = pstmt.executeQuery();
        if (rs.next()) {
          sql = "delete from `friends` where follower = ? && following = ?";
        } else {
          sql = "insert into `friends` (follower, following) values(?, ?)";
        }
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, followerUserId);
        pstmt.setInt(2, followingUserId);
        updateCount = pstmt.executeUpdate();
      }catch(SQLException ex){
      }
      finally{
        close(rs);
        close(pstmt);
      }
      return updateCount;
    }
    /* ----------------------------------------------------------------------------------------------------
     * 특정 사용자를 팔로잉하는 팔로워 목록의 총합을 반환한다.
     * ---------------------------------------------------------------------------------------------------- */
    public int selectFollowerListCount(int followingUserId) {
      int listCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try{
        pstmt = con.prepareStatement("select count(*) from `friends` where following = ?");
        pstmt.setInt(1, followingUserId);
        rs = pstmt.executeQuery();
        if(rs.next()){
          listCount=rs.getInt(1);
        }
      }catch(Exception ex){

      }finally{
        close(rs);
        close(pstmt);
      }
      return listCount;
    }
    /* ----------------------------------------------------------------------------------------------------
     * 특정 사용자가 팔로잉하는 팔로잉 목록의 총합을 반환한다.
     * ---------------------------------------------------------------------------------------------------- */
    public int selectFollowingListCount(int followerUserId) {
      int listCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try{
        pstmt = con.prepareStatement("select count(*) from `friends` where follower = ?");
        pstmt.setInt(1, followerUserId);
        rs = pstmt.executeQuery();
        if(rs.next()){
          listCount=rs.getInt(1);
        }
      }catch(Exception ex){

      }finally{
        close(rs);
        close(pstmt);
      }
      return listCount;
    }
    /* ----------------------------------------------------------------------------------------------------
     * 특정 사용자를 팔로잉하는 팔로워 목록을 반환한다.
     * ---------------------------------------------------------------------------------------------------- */
    public ArrayList<FollowBean> selectFollowerList(int followingUserId, int userId, int page, int limit){
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql="select f.*, u.photo, u.nick, u.Introduction, "
          + "(select count(*) from `friends` sub where sub.following = f.follower && sub.follower = ?) myfollowing, "
          + "(select count(*) from `friends` sub where sub.follower = f.follower && sub.following = ?) myfollower "
          + "from `friends` f "
          + "left join users u on u.uid = f.follower "
          + "where f.following = ? "
          + "order by f.fdate desc "
          + "limit ?, ?";
      ArrayList<FollowBean> followerList = new ArrayList<FollowBean>();
      FollowBean follow = null;
      int startrow = (page - 1) * limit;
      try{
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        pstmt.setInt(2, userId);
        pstmt.setInt(3, followingUserId);
        pstmt.setInt(4, startrow);
        pstmt.setInt(5, limit);
        rs = pstmt.executeQuery();
        while(rs.next()){
          follow = new FollowBean();
          follow.setFollower(rs.getInt("follower"));
          follow.setMyfollowing(rs.getInt("myfollowing"));
          follow.setMyfollower(rs.getInt("myfollower"));
          follow.setPhoto(rs.getString("photo"));
          follow.setNick(rs.getString("nick"));
          follow.setIntroduction(rs.getString("Introduction"));
          follow.setFdate(rs.getDate("fdate"));
          followerList.add(follow);
        }
      }catch(Exception ex){
      }finally{
        close(rs);
        close(pstmt);
      }
      return followerList;
    }
    /* ----------------------------------------------------------------------------------------------------
     * 특정 사용자가 팔로워하는 팔로잉 목록을 반환한다.
     * ---------------------------------------------------------------------------------------------------- */
    public ArrayList<FollowBean> selectFollowingList(int followerUserId, int userId, int page, int limit){
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql="select f.*, u.photo, u.nick, u.Introduction, "
          + "(select count(*) from `friends` sub where sub.following = f.following && sub.follower = ?) myfollowing, "
          + "(select count(*) from `friends` sub where sub.follower = f.following && sub.following = ?) myfollower "
          + "from `friends` f "
          + "left join users u on u.uid = f.following "
          + "where f.follower = ? "
          + "order by f.fdate desc "
          + "limit ?, ?";
      ArrayList<FollowBean> followingList = new ArrayList<FollowBean>();
      FollowBean follow = null;
      int startrow = (page - 1) * limit;
      try{
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        pstmt.setInt(2, userId);
        pstmt.setInt(3, followerUserId);
        pstmt.setInt(4, startrow);
        pstmt.setInt(5, limit);
        rs = pstmt.executeQuery();
        while(rs.next()){
          follow = new FollowBean();
          follow.setFollowing(rs.getInt("following"));
          follow.setMyfollowing(rs.getInt("myfollowing"));
          follow.setMyfollower(rs.getInt("myfollower"));
          follow.setPhoto(rs.getString("photo"));
          follow.setNick(rs.getString("nick"));
          follow.setIntroduction(rs.getString("Introduction"));
          follow.setFdate(rs.getDate("fdate"));
          followingList.add(follow);
        }
      }catch(Exception ex){
      }finally{
        close(rs);
        close(pstmt);
      }
      return followingList;
    }
}
