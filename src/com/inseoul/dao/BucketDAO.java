package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.inseoul.vo.BucketBean;
import com.inseoul.vo.ReplyBean;

public class BucketDAO {
  DataSource ds;
  Connection con;
  private static BucketDAO bucketDAO;
  /* ----------------------------------------------------------------------------------------------------
   * 기본 생성자
   * ---------------------------------------------------------------------------------------------------- */
  private BucketDAO() {
  }
  /* ----------------------------------------------------------------------------------------------------
   * 싱글톤 패턴으로 작성하여 반복 생성됨을 방지한다.
   * ---------------------------------------------------------------------------------------------------- */
  public static BucketDAO getInstance(){
    if(bucketDAO == null){
      bucketDAO = new BucketDAO();
    }
    return bucketDAO;
  }
  /* ----------------------------------------------------------------------------------------------------
   * JdbcUtil이 생성한 연결 객체를 받는다.
   * ---------------------------------------------------------------------------------------------------- */
  public void setConnection(Connection con){
    this.con = con;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 전체 버킷 게시물의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int selectBucketListCount(int pid, String category, String search, String keywords) {
    int listCount= 0;
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
    
    String sql = "select count(*) from buckets b "
        + "left join users u on u.uid = b.uid "
        + "left join locations l on l.lid = b.lid "
        + "where ("
          + "(? = 'my' and b.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `bucket-recommend` r where r.bid = b.bid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and l.name like concat('%', ?, '%') ) or "
          + "(? = 'nick' and u.nick like concat('%', ?, '%') ) or "
          + "(? = 'item' and b.item like concat('%', ?, '%') ) "
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
   * 특정 버킷 게시물에 달린 댓글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int selectReplyListCount(int bid) {
    int listCount= 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
      pstmt=con.prepareStatement("select count(*) from `bucket-reply` where bid = ?");
      pstmt.setInt(1, bid);
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
   * 한 페이지의 버킷 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<BucketBean> selectBucketList(int page, int limit, int pid, int uid, String category, String search, String keywords){
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
        + "b.*, "
        + "u.nick, "
        + "l.name, "
        + "l.img, "
        + "(select count(*) from `bucket-reply` p where p.bid = b.bid) replycount," 
        + "(select count(*) from `bucket-recommend` r where r.bid = b.bid) recommendcount,"
        + "(select max(rdate) from `bucket-recommend` r where r.bid = b.bid) recommenddate,"
        + "(select count(*) from `bucket-recommend` r where r.bid = b.bid && r.uid = ?) favorite "
        + "from buckets b "
        + "left join users u on u.uid = b.uid "
        + "left join locations l on l.lid = b.lid "
        + "where ("
          + "(? = 'my' and b.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `bucket-recommend` r where r.bid = b.bid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and l.name like concat('%', ?, '%') ) or "
          + "(? = 'nick' and u.nick like concat('%', ?, '%') ) or "
          + "(? = 'item' and b.item like concat('%', ?, '%') ) "
        + ") "
        + "order by "
        + "if( ? = 'favorite', `recommenddate`, if( ? = 'recommend', `recommendcount`, b.bid)) desc, b.bid desc "
        + "limit ?,?";
    ArrayList<BucketBean> articleList = new ArrayList<BucketBean>();
    BucketBean bucket = null;
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
        bucket = new BucketBean();
        bucket.setBid(rs.getInt("bid"));
        bucket.setUid(rs.getInt("uid"));
        bucket.setLid(rs.getInt("lid"));
        bucket.setNick(rs.getString("nick"));
        bucket.setName(rs.getString("name"));
        bucket.setImg(rs.getString("img"));
        bucket.setItem(rs.getString("item"));
        bucket.setProgress(rs.getInt("progress"));
        bucket.setWritedate(rs.getDate("writedate"));
        bucket.setReplyCount(rs.getInt("replycount"));
        bucket.setRecommendCount(rs.getInt("recommendcount"));
        bucket.setFavorite(rs.getInt("favorite"));
        articleList.add(bucket);
      }
    } catch(Exception ex) {
    } finally {
      close(rs);
      close(pstmt);
    }
    return articleList;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 게시물의 댓글 목록을 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<ReplyBean> selectReplyList(int bid, int page, int limit){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql="select r.*, u.photo, u.nick" + 
        " from `bucket-reply` r" + 
        " left join users u on u.uid = r.uid" +  
        " where r.bid = ?"+
        " order by r.rid desc" + 
        " limit ?, ?";
    ArrayList<ReplyBean> replyList = new ArrayList<ReplyBean>();
    ReplyBean reply = null;
    int startrow = (page - 1) * limit;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      pstmt.setInt(2, startrow);
      pstmt.setInt(3, limit);
      rs = pstmt.executeQuery();
      while(rs.next()){
        reply = new ReplyBean();
        reply.setRid(rs.getInt("rid"));
        reply.setBid(rs.getInt("bid"));
        reply.setUid(rs.getInt("uid"));
        reply.setPhoto(rs.getString("photo"));
        reply.setNick(rs.getString("nick"));
        reply.setContents(rs.getString("contents"));
        reply.setWritedate(rs.getDate("writedate"));
        reply.setModified(rs.getInt("modified"));
        replyList.add(reply);
      }
    }catch(Exception ex){
    }finally{
      close(rs);
      close(pstmt);
    }
    return replyList;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷 게시물을 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public BucketBean selectBucket(int bid, int uid){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    BucketBean bucket = null;
    String sql = "select b.*, u.nick, l.name, l.img"
        + ", (select count(*) from `bucket-reply` p where p.bid = b.bid) replycount" 
        + ", (select count(*) from `bucket-recommend` r where r.bid = b.bid) recommendcount"
        + ", (select count(*) from `bucket-recommend` r where r.bid = b.bid && r.uid = " + uid + ") favorite"
        + " from buckets b"
        + " left join users u on u.uid = b.uid"
        + " left join locations l on l.lid = b.lid"
        + " where bid = ?";
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        bucket = new BucketBean();
        bucket.setBid(rs.getInt("bid"));
        bucket.setUid(rs.getInt("uid"));
        bucket.setLid(rs.getInt("lid"));
        bucket.setNick(rs.getString("nick"));
        bucket.setName(rs.getString("name"));
        bucket.setImg(rs.getString("img"));
        bucket.setItem(rs.getString("item"));
        bucket.setProgress(rs.getInt("progress"));
        bucket.setWritedate(rs.getDate("writedate"));
        bucket.setReplyCount(rs.getInt("replycount"));
        bucket.setRecommendCount(rs.getInt("recommendcount"));
        bucket.setFavorite(rs.getInt("favorite"));
      }
    }catch(Exception ex){
    }finally{
      close(rs);
      close(pstmt);
    }
    return bucket;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷 게시물을 DB에 작성하고 그 결과를 반환한다.
   * 댓글과 게시물을 하나의 테이블에서 작성하는 경우 Auto Increment를 사용키 어렵다는 단점이 있어 분리했다. 
   * ---------------------------------------------------------------------------------------------------- */
  public int insertBucket(BucketBean bucket){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "insert into buckets "
        + "(uid, lid, item) "
        + "values(?,?,?)";
    int insertCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bucket.getUid());
      pstmt.setInt(2, bucket.getLid());
      pstmt.setString(3, bucket.getItem());
      insertCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }finally{
      close(rs);
      close(pstmt);
    }
    return insertCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 해당 버킷에 댓글을 작성하고 그 결과를 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int insertReply(ReplyBean reply){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "insert into `bucket-reply` "
        + "(bid, uid, contents) "
        + "values(?, ?, ?)";
    int insertCount=0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, reply.getBid());
      pstmt.setInt(2, reply.getUid());
      pstmt.setString(3, reply.getContents());
      insertCount = pstmt.executeUpdate();
    }catch(SQLException ex){
    }finally{
      close(rs);
      close(pstmt);
    }
    return insertCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷의 내용을 수정합니다.
   * bid 값을 기반으로 하여 게시물을 특정하고 그 제목, 내용, 첨부파일명을 변경한다. 
   * ---------------------------------------------------------------------------------------------------- */
  public int updateBucket(BucketBean bucket){
    int updateCount = 0;
    PreparedStatement pstmt = null;
    String sql = "update buckets set lid = ?, item = ?, progress = ? where bid = ?";
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bucket.getLid());
      pstmt.setString(2, bucket.getItem());
      pstmt.setInt(3, bucket.getProgress());
      pstmt.setInt(4, bucket.getBid());
      updateCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }finally{
      close(pstmt);
    }
    return updateCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 댓글의 내용을 수정합니다. 
   * ---------------------------------------------------------------------------------------------------- */
  public int updateReply(ReplyBean reply){
    int updateCount = 0;
    PreparedStatement pstmt = null;
    String sql="update `bucket-reply` set contents = ?, modified = 1 where rid = ?";
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, reply.getContents());
      pstmt.setInt(2, reply.getRid());
      updateCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }finally{
      close(pstmt);
    }
    return updateCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 추천 내역을 수정합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int updateRecommend(int bid, int uid) {
    int updateCount = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from `bucket-recommend` where bid = ? && uid = ?";
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      pstmt.setInt(2, uid);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        sql = "delete from `bucket-recommend` where bid = ? && uid = ?";
      } else {
        sql = "insert into `bucket-recommend` (bid, uid) values(?, ?)";
      }
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      pstmt.setInt(2, uid);
      updateCount = pstmt.executeUpdate();
    }catch(SQLException ex){
    }
    finally{
      close(pstmt);
    }
    return updateCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷 조회수
   * ---------------------------------------------------------------------------------------------------- */
  public int updateReadCount(int bid){
    PreparedStatement pstmt = null;
    int updateCount = 0;
    String sql="update buckets set readcount = "+
        "readcount + 1 where bid = ?";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      updateCount = pstmt.executeUpdate();
    } catch(SQLException ex) {
    } finally{
      close(pstmt);
    }
    return updateCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷을 삭제합니다.
   * 추천 및 댓글이 달려있는 게시물의 경우 추천 및 댓글 내역을 먼저 삭제합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int deleteBucket(int bid){
    PreparedStatement pstmt = null;
    String sql = "delete from buckets where bid = ?";
    int deleteCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      deleteCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }   finally{
      close(pstmt);
    }
    return deleteCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 특정 버킷 게시물의 모든 댓글을 삭제합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int deleteReplyByBucket(int bid){
    PreparedStatement pstmt = null;
    String sql = "delete from `bucket-reply` where bid = ?";
    int deleteCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      deleteCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }   finally{
      close(pstmt);
    }
    return deleteCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 특정 버킷 게시물의 모든 추천을 삭제합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int deleteRecommandByBucket(int bid){
    PreparedStatement pstmt = null;
    String sql = "delete from `bucket-recommend` where bid = ?";
    int deleteCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      deleteCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }   finally{
      close(pstmt);
    }
    return deleteCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 댓글을 삭제합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int deleteReply(int rid){
    PreparedStatement pstmt = null;
    String sql = "delete from `bucket-reply` where rid = ?";
    int deleteCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, rid);
      deleteCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }   finally{
      close(pstmt);
    }
    return deleteCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 사용자가 해당 버킷의 작성자가 맞는지 확인하고 그 결과를 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isBucketWriter(int bid, int uid){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from buckets where bid = ? && uid = ?";
    boolean isWriter = false;
    try{
      pstmt=con.prepareStatement(sql);
      pstmt.setInt(1, bid);
      pstmt.setInt(2, uid);
      rs=pstmt.executeQuery();
      if (rs.next()) {
        isWriter = true;
      }
    }catch(SQLException ex){
    }
    finally{
      close(pstmt);
    }
    return isWriter;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 사용자가 해당 댓글의 작성자가 맞는지 확인하고 그 결과를 반환합니다. 
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isReplyWriter(int rid, int uid){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from `bucket-reply` where rid = ? && uid = ?";
    boolean isWriter = false;
    try{
      pstmt=con.prepareStatement(sql);
      pstmt.setInt(1, rid);
      pstmt.setInt(2, uid);
      rs=pstmt.executeQuery();
      if (rs.next()) {
        isWriter = true;
      }
    }catch(SQLException ex){
    }
    finally{
      close(pstmt);
    }
    return isWriter;
  }
}
