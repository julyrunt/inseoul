package com.inseoul.dao;

import java.sql.*;
import java.util.ArrayList;

import com.inseoul.vo.AlbumBean;
import com.inseoul.vo.BucketBean;
import com.inseoul.vo.FestivalPosterBean;
import com.inseoul.vo.LocationBean;
import com.inseoul.vo.ProfileBean;
import com.inseoul.vo.UserBean;

public class ConnectDB {
  Connection conn;
  Statement stmt;
  /* ----------------------------------------------------------------------------------------------------
   * database와 연결합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public void open() {
    conn = null;
    stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/inseoul?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false",
          "root", "1234");
      stmt = conn.createStatement();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  /* ----------------------------------------------------------------------------------------------------
   * database와 연결을 닫습니다.
   * ---------------------------------------------------------------------------------------------------- */
  public void close() {
    try {
      stmt.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    try {
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }    
  }
  /* ----------------------------------------------------------------------------------------------------
   * 유저 정보 취득
   * ---------------------------------------------------------------------------------------------------- */
  public UserBean userByUid(int uid) {
    UserBean user = new UserBean();
    try {
      open();
      String command = String.format("select * from users where uid = %d;", uid);
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        user.setUid(rs.getInt("uid"));
        user.setPhoto(rs.getString("photo"));
        user.setEmail(rs.getString("email"));
        user.setPw(rs.getString("pw"));
        user.setName(rs.getString("name"));
        user.setNick(rs.getString("nick"));
        user.setBirth(rs.getString("birth"));
        user.setNation(rs.getString("nation"));
        user.setPhone(rs.getString("phone"));
        user.setAddr(rs.getString("addr"));
        user.setDetail(rs.getString("detail"));
        user.setQuestion(rs.getString("question"));
        user.setAnswer(rs.getString("answer"));
        user.setJoindate(rs.getString("joindate"));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return user;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 이메일 중복 확인
   * ---------------------------------------------------------------------------------------------------- */
  public boolean checkEmail(String email) {
    boolean result = false;
    try {
      open();
      String command = String.format("select * from users where email = '%s';", email);
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        result = true;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return result;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 로그인 확인 : 이메일과 비밀번호를 확인하고 회원 정보를 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public UserBean checkLogIn(String email, String pw) {
    UserBean dto = new UserBean();
    try {
      open();
      String command = String.format("select uid, nick, photo from users where email = '%s' && pw = '%s';", email, pw);
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        dto.setUid(rs.getInt("uid"));
        dto.setNick(rs.getString("nick"));
        dto.setPhoto(rs.getString("photo"));
      } else {
        dto = null;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return dto;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 로그인된 사용자의 비밀번호를 확인하고 그 결과를 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean checkPassword(int uid, String pw) {
    boolean isCorrect = false;
    try {
      open();
      String command = String.format("select * where uid = %d && pw = '%s';", uid, pw);
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        isCorrect = true;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return isCorrect;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 이메일 찾기 : 이름과 연락처를 확인하고 이메일을 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public String findEmail(String name, String nation, String phone) {
    String email = "";
    try {
      open();
      String command = String.format("select email from users where name = '%s' && nation = '%s' && phone = '%s';", name, nation, phone);
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        email = rs.getString("email");
      } else {
        email = null;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return email;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 비밀번호 찾기 : 이메일과 질문 답변을 확인하고 비밀번호를 반환한다.
   * ---------------------------------------------------------------------------------------------------- */
  public String findPw(String email, String question, String answer) {
    String pw = "";
    try {
      open();
      String command = String.format("select pw from users where email = '%s' && question = '%s' && answer = '%s';", email, question, answer);
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        pw = rs.getString("pw");
      } else {
        pw = null;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return pw;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 관광지 목록 열람
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<LocationBean> getLocations() {
    ArrayList<LocationBean> list = new ArrayList<>();
    try {
      open();
      String command = "select * from locations;";
      ResultSet rs = stmt.executeQuery(command);
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
        location.setInfo(rs.getString("info"));
        list.add(location);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return list;
  }
  /* ----------------------------------------------------------------------------------------------------
   * Best 관광지
   * ---------------------------------------------------------------------------------------------------- */
  public LocationBean getHotplace() {
    LocationBean location = new LocationBean();
    try {
      open();
      String command = "select " + 
          "    l.lid," +
          "    l.name," +
          "    l.img," +
          "    (" +
          "        (select count(*) from `albums` a where a.lid = l.lid) + " + 
          "        (select count(*) from `album-recommend` ar left join `albums` a on a.aid = ar.aid where a.lid = l.lid) + " + 
          "        (select count(*) from `buckets` b where b.lid = l.lid) + " + 
          "        (select count(*) from `bucket-recommend` br left join `buckets` b on b.bid = br.bid where b.lid = l.lid) + " + 
          "        (select count(*) from `travels` t where t.lid = l.lid) + " + 
          "        (select count(*) from `travels-recommend` tr left join `travels` t on t.tid = tr.tid where t.lid = l.lid) + " + 
          "        (select count(*) from `routemaps` r where " + 
          "            (r.day1_0 = l.lid) or  (r.day1_1 = l.lid) or  (r.day1_2 = l.lid) or  (r.day1_3 = l.lid) or " + 
          "            (r.day2_0 = l.lid) or  (r.day2_1 = l.lid) or  (r.day2_2 = l.lid) or  (r.day2_3 = l.lid) or " + 
          "            (r.day3_0 = l.lid) or  (r.day3_1 = l.lid) or  (r.day3_2 = l.lid) or  (r.day3_3 = l.lid) or " + 
          "            (r.day4_0 = l.lid) or  (r.day4_1 = l.lid) or  (r.day4_2 = l.lid) or  (r.day4_3 = l.lid) or " + 
          "            (r.day5_0 = l.lid) or  (r.day5_1 = l.lid) or  (r.day5_2 = l.lid) or  (r.day5_3 = l.lid) or " + 
          "            (r.day6_0 = l.lid) or  (r.day6_1 = l.lid) or  (r.day6_2 = l.lid) or  (r.day6_3 = l.lid) or " + 
          "            (r.day7_0 = l.lid) or  (r.day7_1 = l.lid) or  (r.day7_2 = l.lid) or  (r.day7_3 = l.lid)" + 
          "        ) + " + 
          "        (select count(*) from `routemap-recommend` rr left join `routemaps` r on rr.mid = r.mid where " + 
          "            (r.day1_0 = l.lid) or  (r.day1_1 = l.lid) or  (r.day1_2 = l.lid) or  (r.day1_3 = l.lid) or " + 
          "            (r.day2_0 = l.lid) or  (r.day2_1 = l.lid) or  (r.day2_2 = l.lid) or  (r.day2_3 = l.lid) or " + 
          "            (r.day3_0 = l.lid) or  (r.day3_1 = l.lid) or  (r.day3_2 = l.lid) or  (r.day3_3 = l.lid) or " + 
          "            (r.day4_0 = l.lid) or  (r.day4_1 = l.lid) or  (r.day4_2 = l.lid) or  (r.day4_3 = l.lid) or " + 
          "            (r.day5_0 = l.lid) or  (r.day5_1 = l.lid) or  (r.day5_2 = l.lid) or  (r.day5_3 = l.lid) or " + 
          "            (r.day6_0 = l.lid) or  (r.day6_1 = l.lid) or  (r.day6_2 = l.lid) or  (r.day6_3 = l.lid) or " + 
          "            (r.day7_0 = l.lid) or  (r.day7_1 = l.lid) or  (r.day7_2 = l.lid) or  (r.day7_3 = l.lid)" + 
          "        )" +
          "    ) as hotscore " + 
          "from `locations` l " + 
          "order by hotscore desc " +
          "limit 0, 1;";
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        location.setLid(rs.getInt("lid"));
        location.setName(rs.getString("name"));
        location.setImg(rs.getString("img"));
        System.out.println(rs.getInt("lid")+"/"+rs.getString("name")+"/"+rs.getString("img"));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return location;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 올해의 축제 목록
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<FestivalPosterBean> getYearlyFestivalPosters() {
    ArrayList<FestivalPosterBean> list = new ArrayList<>();
    try {
      open();
      String command = "select fid, img from `festival` where year(start) = year(current_date()) or year(end) = year(current_date()) order by start desc limit 0, 4;";
      ResultSet rs = stmt.executeQuery(command);
      while (rs.next()) {
        FestivalPosterBean festivalPosterBean = new FestivalPosterBean();
        festivalPosterBean.setFid(rs.getInt("fid"));
        festivalPosterBean.setImg(rs.getString("img"));
        list.add(festivalPosterBean);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return list;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷리스트 게시물 작성
   * ---------------------------------------------------------------------------------------------------- */
  public void writeBucket(BucketBean bucket) {
    try {
      open();
      String command = String.format("insert into buckets (uid, lid, item, progress) values (%d, %d, '%s', %d);", bucket.getUid(), bucket.getLid(), bucket.getItem(), bucket.getProgress());
      stmt.executeUpdate(command);
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷리스트 게시글 열람
   * ---------------------------------------------------------------------------------------------------- */
  public BucketBean readBucket(int bid) {
    BucketBean bucket = new BucketBean();
    try {
      open();
      String sql = String.format("select b.*, l.name, l.img, u.nick "
          + "from buckets as b "
          + "left join users as u on u.uid = b.uid "
          + "left join locations as l on l.lid = b.lid "
          + "where bid = %s;", bid);
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        bucket.setBid(rs.getInt("bid"));
        bucket.setUid(rs.getInt("uid"));
        bucket.setLid(rs.getInt("lid"));
        bucket.setNick(rs.getString("nick"));
        bucket.setName(rs.getString("name"));
        bucket.setImg(rs.getString("img"));
        bucket.setItem(rs.getString("item"));
        bucket.setProgress(rs.getInt("progress"));
        bucket.setWritedate(rs.getDate("writedate"));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return bucket;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷리스트 게시물 수정
   * ---------------------------------------------------------------------------------------------------- */
  public void editBucket(BucketBean bucket) {
    try {
      open();
      String command = String.format("update buckets set lid = %d, item = '%s', progress = %d where bid = %d;", bucket.getLid(), bucket.getItem(), bucket.getProgress(), bucket.getBid());
      stmt.executeUpdate(command);
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷리스트 게시물 삭제
   * ---------------------------------------------------------------------------------------------------- */
  public void deleteBucket(int bid) {
    try {
      open();
      String command = String.format("delete from buckets where bid = %d;", bid);
      stmt.executeUpdate(command);
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷리스트 게시물 목록 열람
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<BucketBean> getBucket() {
    ArrayList<BucketBean> list = new ArrayList<>();
    try {
      open();
      String sql = "select b.*, l.name, l.img, u.nick "
          + "from buckets as b "
          + "left join users as u on u.uid = b.uid "
          + "left join locations as l on l.lid = b.lid;";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        BucketBean bucket = new BucketBean();
        bucket.setBid(rs.getInt("bid"));
        bucket.setUid(rs.getInt("uid"));
        bucket.setLid(rs.getInt("lid"));
        bucket.setNick(rs.getString("nick"));
        bucket.setName(rs.getString("name"));
        bucket.setImg(rs.getString("img"));
        bucket.setItem(rs.getString("item"));
        bucket.setProgress(rs.getInt("progress"));
        bucket.setWritedate(rs.getDate("writedate"));
        list.add(bucket);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return list;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 버킷리스트 게시글 검색
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<BucketBean> searchBucket(String search, String keywords) {
    ArrayList<BucketBean> list = new ArrayList<>();
    try {
      open();
      String command = "";
      if (keywords.equals("")) {
        return getBucket();
      }
      switch (search) {
        case "location":
          command = String.format("select b.*, l.name, l.img, u.nick from buckets as b inner join users as u on u.uid = b.uid inner join locations as l on l.lid = b.lid where l.name like '%%%s%%';", keywords);
          break;
        case "nick":
          command = String.format("select b.*, l.name, l.img, u.nick from buckets as b inner join users as u on u.uid = b.uid inner join locations as l on l.lid = b.lid where u.nick like '%%%s%%';", keywords);
          break;
        case "item":
          command = String.format("select b.*, l.name, l.img, u.nick from buckets as b inner join users as u on u.uid = b.uid inner join locations as l on l.lid = b.lid where item like '%%%s%%';", keywords);
          break;
        default:
          return getBucket();
      }
      ResultSet rs = stmt.executeQuery(command);
      while (rs.next()) {
        BucketBean bucket = new BucketBean();
        bucket.setBid(rs.getInt("bid"));
        bucket.setUid(rs.getInt("uid"));
        bucket.setLid(rs.getInt("lid"));
        bucket.setNick(rs.getString("nick"));
        bucket.setName(rs.getString("name"));
        bucket.setImg(rs.getString("img"));
        bucket.setItem(rs.getString("item"));
        bucket.setProgress(rs.getInt("progress"));
        bucket.setWritedate(rs.getDate("writedate"));
        list.add(bucket);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return list;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 닉네임 반환
   * ---------------------------------------------------------------------------------------------------- */
  public String getNickById(int uid) {
    String nick = "";
    try {
      open();
      String command = String.format("select nick from users where uid = %d;", uid);
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        nick = rs.getString("nick");
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return nick;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 마이페이지 정보 취득
   * ---------------------------------------------------------------------------------------------------- */
  public ProfileBean profileInfoById(String pid, String uid) {
    ProfileBean profile = new ProfileBean();
    try {
      open();
      String command = String.format("select *, "
          + "(select count(*) from friends where follower = %s && following = %s ) myfollower, "
          + "(select count(*) from friends where follower = %s && following = %s ) myfollowing, "
          + "(select count(*) from friends where following = %s) followerCount, "
          + "(select count(*) from friends where follower = %s) followingCount, "
          + "(select fdate from friends where follower = %s && following = %s ) followerDate, "
          + "(select fdate from friends where follower = %s && following = %s ) followingDate "
          + "from users "
          + "where uid = %s;", 
          pid, uid, //myfollower
          uid, pid, //myfollowing
          pid, //followerCount
          pid, //followingCount
          pid, uid, //followerDate
          pid, uid, //followingDate
          pid); //where
      ResultSet rs = stmt.executeQuery(command);
      if (rs.next()) {
        profile.setUid(rs.getInt("uid"));
        profile.setPhoto(rs.getString("photo"));
        profile.setNick(rs.getString("nick"));
        profile.setJoindate(rs.getString("joindate"));
        profile.setIntroduction(rs.getString("introduction"));
        profile.setMyfollower(rs.getInt("myfollower"));
        profile.setMyfollowing(rs.getInt("myfollowing"));
        profile.setFollowerCount(rs.getInt("followerCount"));
        profile.setFollowingCount(rs.getInt("followingCount"));
        profile.setFollowerDate(rs.getDate("followerDate"));
        profile.setFollowingDate(rs.getDate("followingDate"));
        profile.setBackground(rs.getString("background"));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return profile;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 자기소개 수정
   * ---------------------------------------------------------------------------------------------------- */
  public String updateIntroduction(String uid, String introduction) {
    String result = "";
    try {
      open();
      String sql = String.format("update users set introduction = '%s' where uid = %s;", introduction, uid);
      if (stmt.executeUpdate(sql) > 0) {
        result = introduction;
      } else {
        result = null;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      close();
    }
    return result;
  }
  
  /* ----------------------------------------------------------------------------------------------------
   * 인덱스 베스트5 출력
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<LocationBean> getBest5() {
	  	ArrayList<LocationBean> list = new ArrayList<LocationBean>();
	    try {
	      open();
	      String command = "select " + 
	          "    l.lid," +
	          "    l.name," +
	          "    l.img," +
	          "    l.info," +
	          "    (" +
	          "        (select count(*) from `albums` a where a.lid = l.lid) + " + 
	          "        (select count(*) from `album-recommend` ar left join `albums` a on a.aid = ar.aid where a.lid = l.lid) + " + 
	          "        (select count(*) from `buckets` b where b.lid = l.lid) + " + 
	          "        (select count(*) from `bucket-recommend` br left join `buckets` b on b.bid = br.bid where b.lid = l.lid) + " + 
	          "        (select count(*) from `travels` t where t.lid = l.lid) + " + 
	          "        (select count(*) from `travels-recommend` tr left join `travels` t on t.tid = tr.tid where t.lid = l.lid) + " + 
	          "        (select count(*) from `routemaps` r where " + 
	          "            (r.day1_0 = l.lid) or  (r.day1_1 = l.lid) or  (r.day1_2 = l.lid) or  (r.day1_3 = l.lid) or " + 
	          "            (r.day2_0 = l.lid) or  (r.day2_1 = l.lid) or  (r.day2_2 = l.lid) or  (r.day2_3 = l.lid) or " + 
	          "            (r.day3_0 = l.lid) or  (r.day3_1 = l.lid) or  (r.day3_2 = l.lid) or  (r.day3_3 = l.lid) or " + 
	          "            (r.day4_0 = l.lid) or  (r.day4_1 = l.lid) or  (r.day4_2 = l.lid) or  (r.day4_3 = l.lid) or " + 
	          "            (r.day5_0 = l.lid) or  (r.day5_1 = l.lid) or  (r.day5_2 = l.lid) or  (r.day5_3 = l.lid) or " + 
	          "            (r.day6_0 = l.lid) or  (r.day6_1 = l.lid) or  (r.day6_2 = l.lid) or  (r.day6_3 = l.lid) or " + 
	          "            (r.day7_0 = l.lid) or  (r.day7_1 = l.lid) or  (r.day7_2 = l.lid) or  (r.day7_3 = l.lid)" + 
	          "        ) + " + 
	          "        (select count(*) from `routemap-recommend` rr left join `routemaps` r on rr.mid = r.mid where " + 
	          "            (r.day1_0 = l.lid) or  (r.day1_1 = l.lid) or  (r.day1_2 = l.lid) or  (r.day1_3 = l.lid) or " + 
	          "            (r.day2_0 = l.lid) or  (r.day2_1 = l.lid) or  (r.day2_2 = l.lid) or  (r.day2_3 = l.lid) or " + 
	          "            (r.day3_0 = l.lid) or  (r.day3_1 = l.lid) or  (r.day3_2 = l.lid) or  (r.day3_3 = l.lid) or " + 
	          "            (r.day4_0 = l.lid) or  (r.day4_1 = l.lid) or  (r.day4_2 = l.lid) or  (r.day4_3 = l.lid) or " + 
	          "            (r.day5_0 = l.lid) or  (r.day5_1 = l.lid) or  (r.day5_2 = l.lid) or  (r.day5_3 = l.lid) or " + 
	          "            (r.day6_0 = l.lid) or  (r.day6_1 = l.lid) or  (r.day6_2 = l.lid) or  (r.day6_3 = l.lid) or " + 
	          "            (r.day7_0 = l.lid) or  (r.day7_1 = l.lid) or  (r.day7_2 = l.lid) or  (r.day7_3 = l.lid)" + 
	          "        )" +
	          "    ) as hotscore " + 
	          "from `locations` l " + 
	          "order by hotscore desc " +
	          "limit 5;";
	      ResultSet rs = stmt.executeQuery(command);
	      while (rs.next()) {
	    	LocationBean location = new LocationBean();
	        location.setLid(rs.getInt("lid"));
	        location.setName(rs.getString("name"));
	        location.setImg(rs.getString("img"));
	        location.setInfo(rs.getString("info"));
	        location.setHotscore(rs.getInt("hotscore"));
	        list.add(location);
	        
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    } finally {
	      close();
	    }
	    return list;
  }
  
  /* ----------------------------------------------------------------------------------------------------
   * 인덱스 베스트5 출력
   * ---------------------------------------------------------------------------------------------------- */
  public ArrayList<AlbumBean> getImgs() {
	  ArrayList<AlbumBean> imgs = new ArrayList<>();
	  try {
	      open();
	      String command = "SELECT img01, aid, uid FROM albums order by aid desc limit 21";
	      ResultSet rs = stmt.executeQuery(command);
	      while (rs.next()) {
	    	AlbumBean album = new AlbumBean();
	    	album.setImg01(rs.getString("img01"));
	    	album.setUid(rs.getInt("uid"));
	    	album.setAid(rs.getInt("aid"));
	        imgs.add(album);
	        
	      }
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}
	
	  
	  return imgs;
  }
}
