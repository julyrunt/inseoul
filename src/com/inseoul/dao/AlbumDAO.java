package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.inseoul.vo.AlbumBean;
import com.inseoul.vo.ReplyBean;

public class AlbumDAO {
  DataSource ds;
  Connection con;
  private static AlbumDAO albumDAO;
  /* ----------------------------------------------------------------------------------------------------
   * 기본 생성자
   * ---------------------------------------------------------------------------------------------------- */
  private AlbumDAO() {
  }
  /* ----------------------------------------------------------------------------------------------------
   * 싱글톤 패턴으로 작성하여 반복 생성됨을 방지한다.
   * ---------------------------------------------------------------------------------------------------- */
  public static AlbumDAO getInstance(){
    if(albumDAO == null){
      albumDAO = new AlbumDAO();
    }
    return albumDAO;
  }
  /* ----------------------------------------------------------------------------------------------------
   * JdbcUtil이 생성한 연결 객체를 받는다.
   * ---------------------------------------------------------------------------------------------------- */
  public void setConnection(Connection con){
    this.con = con;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 전체 앨범 게시물의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int selectAlbumListCount(int pid, String category, String search, String keywords) {
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
    
    String sql = "select count(*) from albums a "
        + "left join users u on u.uid = a.uid "
        + "left join locations l on l.lid = a.lid "
        + "where ("
          + "(? = 'my' and a.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `album-recommend` r where r.aid = a.aid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and l.name like concat('%', ?, '%') ) or "
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

    }finally{
      close(rs);
      close(pstmt);
    }
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 특정 앨범 게시물에 달린 댓글의 총합을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int selectReplyListCount(int aid) {
    int listCount= 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
      pstmt=con.prepareStatement("select count(*) from `album-reply` where aid = ?");
      pstmt.setInt(1, aid);
      rs = pstmt.executeQuery();
      if(rs.next()){
        listCount = rs.getInt(1);
      }
    }catch(Exception ex){

    }finally{
      close(rs);
      close(pstmt);
    }
    return listCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 한 페이지의 앨범 목록을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<AlbumBean> selectAlbumList(int page, int limit, int pid, int uid, String category, String search, String keywords){
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
          + "l.name, "
          + "(select count(*) from `album-reply` p where p.aid = a.aid) replycount," 
          + "(select count(*) from `album-recommend` r where r.aid = a.aid) recommendcount,"
          + "(select max(rdate) from `album-recommend` r where r.aid = a.aid) recommenddate,"
          + "(select count(*) from `album-recommend` r where r.aid = a.aid && r.uid = ?) favorite "
        + "from albums a "
        + "left join users u on u.uid = a.uid "
        + "left join locations l on l.lid = a.lid "
        + "where ("
          + "(? = 'my' and a.uid = ?) or "
          + "(? = 'favorite' and (select count(*) from `album-recommend` r where r.aid = a.aid && r.uid = ?) > 0) or "
          + "? = 'recommend' "
        + ") and ("
          + "? = '' or "
          + "(? = 'location' and l.name like concat('%', ?, '%') ) or "
          + "(? = 'nick' and u.nick like concat('%', ?, '%') ) or "
          + "(? = 'title' and a.title like concat('%', ?, '%') ) "
        + ") "
        + "order by "
        + "if( ? = 'favorite', `recommenddate`, if( ? = 'recommend', `recommendcount`, a.aid)) desc, a.aid desc "
        + "limit ?,?";
    ArrayList<AlbumBean> articleList = new ArrayList<AlbumBean>();
    AlbumBean album = null;
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
        album = new AlbumBean();
        album.setAid(rs.getInt("aid"));
        album.setUid(rs.getInt("uid"));
        album.setLid(rs.getInt("lid"));
        album.setNick(rs.getString("nick"));
        album.setName(rs.getString("name"));
        album.setTitle(rs.getString("title"));
        album.setImg01(rs.getString("img01"));
        album.setWritedate(rs.getDate("writedate"));
        album.setReadCount(rs.getInt("readcount"));
        album.setReplyCount(rs.getInt("replycount"));
        album.setRecommendCount(rs.getInt("recommendcount"));
        album.setFavorite(rs.getInt("favorite"));
        articleList.add(album);
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
  public ArrayList<ReplyBean> selectReplyList(int aid, int page, int limit){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql="select r.*, u.photo, u.nick" + 
        " from `album-reply` r" + 
        " left join users u on u.uid = r.uid" +  
        " where r.aid = ?"+
        " order by r.rid desc" + 
        " limit ?, ?";
    ArrayList<ReplyBean> replyList = new ArrayList<ReplyBean>();
    ReplyBean reply = null;
    int startrow = (page - 1) * limit;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
      pstmt.setInt(2, startrow);
      pstmt.setInt(3, limit);
      rs = pstmt.executeQuery();
      while(rs.next()){
        reply = new ReplyBean();
        reply.setRid(rs.getInt("rid"));
        reply.setAid(rs.getInt("aid"));
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
   * 앨범 게시물을 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public AlbumBean selectAlbum(int aid, int uid){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    AlbumBean album = null;
    String sql="select a.*, u.nick, l.name"
        + ", (select count(*) from `album-reply` p where p.aid = a.aid) replycount" 
        + ", (select count(*) from `album-recommend` r where r.aid = a.aid) recommendcount"
        + ", (select count(*) from `album-recommend` r where r.aid = a.aid && r.uid = " + uid + ") favorite"
        + " from albums a"
        + " left join users u on u.uid = a.uid"
        + " left join locations l on l.lid = a.lid"
        + " where aid = ?";
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
      rs= pstmt.executeQuery();

      if (rs.next()) {
        album = new AlbumBean();
        album.setAid(rs.getInt("aid"));
        album.setUid(rs.getInt("uid"));
        album.setLid(rs.getInt("lid"));
        album.setNick(rs.getString("nick"));
        album.setName(rs.getString("name"));
        album.setTitle(rs.getString("title"));
        album.setImg01(rs.getString("img01"));
        album.setImg02(rs.getString("img02"));
        album.setImg03(rs.getString("img03"));
        album.setImg04(rs.getString("img04"));
        album.setImg05(rs.getString("img05"));
        album.setImg06(rs.getString("img06"));
        album.setImg07(rs.getString("img07"));
        album.setImg08(rs.getString("img08"));
        album.setImg09(rs.getString("img09"));
        album.setImg10(rs.getString("img10"));
        album.setContents(rs.getString("contents"));
        album.setWritedate(rs.getDate("writedate"));
        album.setReadCount(rs.getInt("readcount"));
        album.setReplyCount(rs.getInt("replycount"));
        album.setRecommendCount(rs.getInt("recommendcount"));
        album.setFavorite(rs.getInt("favorite"));
      }
    }catch(Exception ex){
    }finally{
      close(rs);
      close(pstmt);
    }
    return album;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시물을 DB에 작성하고 그 결과를 반환한다.
   * 댓글과 게시물을 하나의 테이블에서 작성하는 경우 Auto Increment를 사용키 어렵다는 단점이 있어 분리했다. 
   * ---------------------------------------------------------------------------------------------------- */
  public int insertAlbum(AlbumBean album){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "insert into albums" + 
        " (uid, lid, title, img01, img02, img03, img04, img05, img06, img07, img08, img09, img10, contents)" +
        " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    int insertCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, album.getUid());
      pstmt.setInt(2, album.getLid());
      pstmt.setString(3, album.getTitle());
      pstmt.setString(4, album.getImg01());
      pstmt.setString(5, album.getImg02());
      pstmt.setString(6, album.getImg03());
      pstmt.setString(7, album.getImg04());
      pstmt.setString(8, album.getImg05());
      pstmt.setString(9, album.getImg06());
      pstmt.setString(10, album.getImg07());
      pstmt.setString(11, album.getImg08());
      pstmt.setString(12, album.getImg09());
      pstmt.setString(13, album.getImg10());
      pstmt.setString(14, album.getContents());
      insertCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }finally{
      close(rs);
      close(pstmt);
    }
    return insertCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 해당 앨범에 댓글을 작성하고 그 결과를 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public int insertReply(ReplyBean reply){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql="insert into `album-reply` (aid, uid, contents) values(?, ?, ?)";
    int insertCount=0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, reply.getAid());
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
   * 앨범의 내용을 수정합니다.
   * aid 값을 기반으로 하여 게시물을 특정하고 그 제목, 내용, 첨부파일명을 변경한다. 
   * ---------------------------------------------------------------------------------------------------- */
  public int updateAlbum(AlbumBean album){
    int updateCount = 0;
    PreparedStatement pstmt = null;
    String sql = "update `albums` set " + 
        "    `lid` = ?, " + 
        "    `title` = ?, " + 
        "    `contents` = ?, " + 
        "    `img01` = if(? is null, `img01`, if(? != 'remove', ?, `img01`)), " + 
        "    `img02` = if(? is null, `img02`, if(? != 'remove', ?, null)), " + 
        "    `img03` = if(? is null, `img03`, if(? != 'remove', ?, null)), " +
        "    `img04` = if(? is null, `img04`, if(? != 'remove', ?, null)), " +
        "    `img05` = if(? is null, `img05`, if(? != 'remove', ?, null)), " + 
        "    `img06` = if(? is null, `img06`, if(? != 'remove', ?, null)), " +
        "    `img07` = if(? is null, `img07`, if(? != 'remove', ?, null)), " +
        "    `img08` = if(? is null, `img08`, if(? != 'remove', ?, null)), " + 
        "    `img09` = if(? is null, `img09`, if(? != 'remove', ?, null)), " +
        "    `img10` = if(? is null, `img10`, if(? != 'remove', ?, null)) " +
        "where `aid` = ?";
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, album.getLid());
      pstmt.setString(2, album.getTitle());
      pstmt.setString(3, album.getContents());
      
      pstmt.setString(4, album.getImg01());
      pstmt.setString(5, album.getImg01());
      pstmt.setString(6, album.getImg01());
      
      pstmt.setString(7, album.getImg02());
      pstmt.setString(8, album.getImg02());
      pstmt.setString(9, album.getImg02());
      
      pstmt.setString(10, album.getImg03());
      pstmt.setString(11, album.getImg03());
      pstmt.setString(12, album.getImg03());
      
      pstmt.setString(13, album.getImg04());
      pstmt.setString(14, album.getImg04());
      pstmt.setString(15, album.getImg04());
      
      pstmt.setString(16, album.getImg05());
      pstmt.setString(17, album.getImg05());
      pstmt.setString(18, album.getImg05());
      
      pstmt.setString(19, album.getImg06());
      pstmt.setString(20, album.getImg06());
      pstmt.setString(21, album.getImg06());
      
      pstmt.setString(22, album.getImg07());
      pstmt.setString(23, album.getImg07());
      pstmt.setString(24, album.getImg07());
      
      pstmt.setString(25, album.getImg08());
      pstmt.setString(26, album.getImg08());
      pstmt.setString(27, album.getImg08());
      
      pstmt.setString(28, album.getImg09());
      pstmt.setString(29, album.getImg09());
      pstmt.setString(30, album.getImg09());
      
      pstmt.setString(31, album.getImg10());
      pstmt.setString(32, album.getImg10());
      pstmt.setString(33, album.getImg10());
      
      pstmt.setInt(34, album.getAid());
      System.out.println(pstmt.toString());
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
    String sql="update `album-reply` set contents = ?, modified = 1 where rid = ?";
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
  public int updateRecommend(int aid, int uid) {
    int updateCount = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from `album-recommend` where aid = ? && uid = ?";
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
      pstmt.setInt(2, uid);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        sql = "delete from `album-recommend` where aid = ? && uid = ?";
      } else {
        sql = "insert into `album-recommend` (aid, uid) values(?, ?)";
      }
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
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
   * 앨범 조회수
   * ---------------------------------------------------------------------------------------------------- */
  public int updateReadCount(int aid){
    PreparedStatement pstmt = null;
    int updateCount = 0;
    String sql="update albums set readcount = "+
        "readcount + 1 where aid = ?";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
      updateCount = pstmt.executeUpdate();
    } catch(SQLException ex) {
    } finally{
      close(pstmt);
    }
    return updateCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 앨범을 삭제합니다.
   * 추천 및 댓글이 달려있는 게시물의 경우 추천 및 댓글 내역을 먼저 삭제합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int deleteAlbum(int aid){
    PreparedStatement pstmt = null;
    String sql = "delete from albums where aid = ?";
    int deleteCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
      deleteCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }   finally{
      close(pstmt);
    }
    return deleteCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 특정 앨범 게시물의 모든 댓글을 삭제합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int deleteReplyByAlbum(int aid){
    PreparedStatement pstmt = null;
    String sql = "delete from `album-reply` where aid = ?";
    int deleteCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
      deleteCount = pstmt.executeUpdate();
    }catch(Exception ex){
    }   finally{
      close(pstmt);
    }
    return deleteCount;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 특정 앨범 게시물의 모든 추천을 삭제합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public int deleteRecommandByAlbum(int aid){
    PreparedStatement pstmt = null;
    String sql = "delete from `album-recommend` where aid = ?";
    int deleteCount = 0;
    try{
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, aid);
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
    String sql = "delete from `album-reply` where rid = ?";
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
   * 사용자가 해당 앨범의 작성자가 맞는지 확인하고 그 결과를 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isAlbumWriter(int aid, int uid){
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from albums where aid = ? && uid = ?";
    boolean isWriter = false;
    try{
      pstmt=con.prepareStatement(sql);
      pstmt.setInt(1, aid);
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
    String sql = "select * from `album-reply` where rid = ? && uid = ?";
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
