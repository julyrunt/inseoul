package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.inseoul.vo.WithBoardBean;

public class WithBoardDAO {
	DataSource ds;
	Connection con;
	private static WithBoardDAO withBoardDAO;

	private WithBoardDAO() {
	}

	public static WithBoardDAO getInstance() {
		if (withBoardDAO == null) {
			withBoardDAO = new WithBoardDAO();
		}
		return withBoardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	/*
	 * 전체 게시글 숫자 페이징을 하기 위한 게시글 갯수 출력 where seq=0을 해주면 본문 갯수만 카운팅
	 */
	public int selectListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("select count(*) from with_board where seq=0");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {

		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;

	}

	/*
	 * 게시판 리스트와 페이징 seq=0인 본문들을 작성시간 내림차순으로 한 페이지에 5개씩 나눠서 페이징
	 */
	public ArrayList<WithBoardBean> selectArticleList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql = "select *, (select count(*) from with_board where seq > 0) replyCount, users.nick from with_board wb join users on wb.uid=users.uid where seq=0 order by wid desc limit ?, 5";
		ArrayList<WithBoardBean> articleList = new ArrayList<WithBoardBean>();
		WithBoardBean withboard = null;	
		int startrow = (page - 1) * 5;
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				withboard = new WithBoardBean();
				withboard.setWid(rs.getInt("wid"));
				withboard.setUid(rs.getInt("uid"));
				withboard.setPhotos(rs.getString("photos"));
				withboard.setTitle(rs.getString("title"));
				withboard.setContents(rs.getString("contents"));
				withboard.setRef(rs.getInt("ref"));
				withboard.setSeq(rs.getInt("seq"));
				withboard.setReadcount(rs.getInt("readcount"));
				withboard.setWritedate(rs.getDate("writedate"));
				withboard.setNick(rs.getString("nick"));
				withboard.setReplyCount(rs.getInt("replyCount"));
				withboard.setMojib_limit(rs.getDate("mojib_limit"));
				withboard.setTrip_start(rs.getDate("trip_start"));
				withboard.setTrip_end(rs.getDate("trip_end"));
				articleList.add(withboard);

			}
		} catch (Exception ex) {
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	/* 댓글 페이징 */
	public int selectReplyListCount(int wid) {

		int replylistCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("select count(*) from with_board where ref=? and seq>0");
			pstmt.setInt(1, wid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				replylistCount = rs.getInt(1);
			}
		} catch (Exception ex) {

		} finally {
			close(rs);
			close(pstmt);
		}

		return replylistCount;
	}

	/* 모집기한 오름차순 정렬 */
//	public ArrayList<WithBoardBean> selectArticleList_mojib_asc(int page,int limit){
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String board_list_sql="select *, count(ref)-1 as replyCount from with_board where mojib_limit >= current_date() order by mojib_limit asc limit ?, 5";
//		ArrayList<WithBoardBean> articleList = new ArrayList<WithBoardBean>();
//		WithBoardBean withboard = null;
//		int startrow=(page-1)*5; 
//		try{
//			pstmt = con.prepareStatement(board_list_sql);
//			pstmt.setInt(1, startrow);
//			rs = pstmt.executeQuery();
//			while(rs.next()){
//				withboard = new WithBoardBean();
//				withboard.setWid(rs.getInt("wid"));
//				withboard.setUid(rs.getInt("uid"));
//				withboard.setPhotos(rs.getString("photos"));
//				withboard.setTitle(rs.getString("title"));
//				withboard.setContents(rs.getString("contents"));
//				withboard.setRef(rs.getInt("ref"));
//				withboard.setSeq(rs.getInt("seq"));
//				withboard.setReadcount(rs.getInt("readcount"));
//				withboard.setWritedate(rs.getDate("writedate"));
//				withboard.setNick(rs.getString("nick"));
//				withboard.setReplyCount(rs.getInt("replyCount"));
//				withboard.setMojib_limit(rs.getString("mojib_limit"));
//				withboard.setTrip_start(rs.getDate("trip_start"));
//				withboard.setTrip_end(rs.getDate("trip_end"));
//				articleList.add(withboard);
//				
//			}
//		}catch(Exception ex){
//		}finally{
//			close(rs);
//			close(pstmt);
//		}
//		
//		return articleList;
//
//	}
	/*
	 * 댓글 출력 댓글 참조번호로 찾아서 seq=0인 본문을 제외한 댓글만 출력
	 */
	public ArrayList<WithBoardBean> selectReply(int wid, int replypage, int replylimit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String replyList_sql = "select with_board.*, users.photo, users.nick from with_board join users on with_board.uid=users.uid where ref=? and seq > 0 limit ?,10";
		WithBoardBean withBoardBean = null;
		ArrayList<WithBoardBean> replyList = new ArrayList<WithBoardBean>();
		int startrow = (replypage - 1) * 10;
		try {
			pstmt = con.prepareStatement(replyList_sql);
			pstmt.setInt(1, wid);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				withBoardBean = new WithBoardBean();
				withBoardBean.setWid(rs.getInt("wid"));
				withBoardBean.setUid(rs.getInt("uid"));
				withBoardBean.setPf(rs.getString("photo"));
				withBoardBean.setTitle(rs.getString("title"));
				withBoardBean.setContents(rs.getString("contents"));
				withBoardBean.setRef(rs.getInt("ref"));
				withBoardBean.setSeq(rs.getInt("seq"));
				withBoardBean.setReadcount(rs.getInt("readcount"));
				withBoardBean.setWritedate(rs.getDate("writedate"));
				withBoardBean.setNick(rs.getString("nick"));
				replyList.add(withBoardBean);
			}

		} catch (Exception ex) {
		} finally {
			close(rs);
			close(pstmt);
		}

		return replyList;

	}

	/*
	 * 게시글 출력 게시글 이너조인 유저DB 닉네임 가져와서 출력
	 */
	public WithBoardBean selectArticle(int uid, int wid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WithBoardBean withBoardBean = null;

		try {
			pstmt = con.prepareStatement("select with_board.*, users.nick, users.photo, "
					+ "(select state from with_application where uid = " + uid + " && wid = " + wid + ") state "
					+ "from with_board join users on " + "with_board.uid = users.uid where wid=?");
			pstmt.setInt(1, wid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				withBoardBean = new WithBoardBean();
				withBoardBean.setWid(rs.getInt("wid"));
				withBoardBean.setUid(rs.getInt("uid"));
				withBoardBean.setPhotos(rs.getString("photos"));
				withBoardBean.setPf(rs.getString("photo"));
				withBoardBean.setTitle(rs.getString("title"));
				withBoardBean.setContents(rs.getString("contents"));
				withBoardBean.setRef(rs.getInt("ref"));
				withBoardBean.setSeq(rs.getInt("seq"));
				withBoardBean.setReadcount(rs.getInt("readcount"));
				withBoardBean.setWritedate(rs.getDate("writedate"));
				withBoardBean.setNick(rs.getString("nick"));
				withBoardBean.setMojib_limit(rs.getDate("mojib_limit"));
				withBoardBean.setTrip_start(rs.getDate("trip_start"));
				withBoardBean.setTrip_end(rs.getDate("trip_end"));
				withBoardBean.setDues(rs.getInt("dues"));
				withBoardBean.setMojib_person(rs.getString("mojib_person"));
				withBoardBean.setState(rs.getInt("state"));
			}

		} catch (Exception ex) {
		} finally {
			close(rs);
			close(pstmt);
		}

		return withBoardBean;

	}

	public WithBoardBean selectArticle(int wid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WithBoardBean withBoardBean = null;

		try {
			pstmt = con.prepareStatement("select with_board.*, users.nick, users.photo "
					+ "from with_board join users on " + "with_board.uid = users.uid where wid=?");
			pstmt.setInt(1, wid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				withBoardBean = new WithBoardBean();
				withBoardBean.setWid(rs.getInt("wid"));
				withBoardBean.setUid(rs.getInt("uid"));
				withBoardBean.setPhotos(rs.getString("photos"));
				withBoardBean.setPf(rs.getString("photo"));
				withBoardBean.setTitle(rs.getString("title"));
				withBoardBean.setContents(rs.getString("contents"));
				withBoardBean.setRef(rs.getInt("ref"));
				withBoardBean.setSeq(rs.getInt("seq"));
				withBoardBean.setReadcount(rs.getInt("readcount"));
				withBoardBean.setWritedate(rs.getDate("writedate"));
				withBoardBean.setNick(rs.getString("nick"));
				withBoardBean.setMojib_limit(rs.getDate("mojib_limit"));
				withBoardBean.setTrip_start(rs.getDate("trip_start"));
				withBoardBean.setTrip_end(rs.getDate("trip_end"));
				withBoardBean.setDues(rs.getInt("dues"));
				withBoardBean.setMojib_person(rs.getString("mojib_person"));
			}

		} catch (Exception ex) {
		} finally {
			close(rs);
			close(pstmt);
		}

		return withBoardBean;

	}

	/*
	 * 게시글 입력 게시글 인설트
	 */
	public int insertArticle(WithBoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount = 0;
		try {
			pstmt = con.prepareStatement("select max(wid) from with_board");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;
			
			
			sql = "insert into with_board (wid, uid, photos, title, contents, ref, seq, readcount, writedate, mojib_limit, trip_start, trip_end, dues, mojib_person)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getUid());
			pstmt.setString(3, article.getPhotos());
			pstmt.setString(4, article.getTitle());
			pstmt.setString(5, article.getContents());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setDate(9, article.getMojib_limit());
			pstmt.setDate(10, article.getTrip_start());
			pstmt.setDate(11, article.getTrip_end());
			pstmt.setInt(12, article.getDues());
			pstmt.setString(13, article.getMojib_person());

			System.out.println("유저번호: " + article.getUid());
			insertCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;

	}

	/*
	 * 댓글 인설트 댓글로 입력시 seq에 +1 해줌으로써 본문이 아닌 댓글로 만듦
	 */
	public int insertReplyArticle(WithBoardBean article) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql = "select max(wid) from with_board";
		String sql = "";
		int num = 0;
		int insertCount = 0;
		int ref = article.getRef();
		int seq = article.getSeq();

		try {
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;
			sql = "update with_board set seq = seq+1 where ref=? ";
			sql += "and seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, seq);
			int updateCount = pstmt.executeUpdate();

			if (updateCount > 0) {
				commit(con);
			}

			seq = seq + 1;
			sql = "insert into with_board (wid,uid,";
			sql += "contents, ref, seq, writedate) ";
			sql += "values(?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getUid());
			pstmt.setString(3, article.getContents());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, seq);

			insertCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
		} finally {
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	/*
	 * 본문 수정 글 번호로 찾아 제목과 내용을 업데이트
	 */
	public int updateArticle(WithBoardBean article) {

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update with_board set title=?,contents=?,mojib_limit=?,trip_start=?,trip_end=?,dues=?,mojib_person=? where wid=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContents());
			pstmt.setDate(3, article.getMojib_limit());
			pstmt.setDate(4, article.getTrip_start());
			pstmt.setDate(5, article.getTrip_end());
			pstmt.setInt(6, article.getDues());
			pstmt.setString(7, article.getMojib_person());
			pstmt.setInt(8, article.getWid());
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
		} finally {
			close(pstmt);
		}

		return updateCount;

	}

	/*
	 * 본문 삭제 글 번호로 찾아 DB에서 삭제
	 */
	public int deleteArticle(int wid) {

		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from with_board where wid=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, wid);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
		} finally {
			close(pstmt);
		}

		return deleteCount;

	}

	/*
	 * 조회수 증가 게시판 리스트에서 글 클릭시 리드카운트를 1씩 증가 시켜 조회수 업데이트
	 */
	public int updateReadCount(int wid) {

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update with_board set readcount = " + "readcount+1 where wid = " + wid;

		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
		} finally {
			close(pstmt);

		}

		return updateCount;

	}

	/* 그룹 업데이트 신청, 취소 */
	public int updateGroup(int uid, int wid) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from with_application where uid = ? && wid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, wid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sql = "delete from with_application where uid = ? && wid = ?";
			} else {
				sql = "insert into with_application (uid, wid) values(?, ?)";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, wid);
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			close(pstmt);
		}
		System.out.println("그룹업댓:" + updateCount);
		return updateCount;
	}

	/* 그룹신청 여부 확인 */
	public int selectState(int uid, int wid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int stateCount = 0;
		try {
			pstmt = con.prepareStatement("select count(*) count from with_application where uid = ? && wid = ?");
			pstmt.setInt(1, uid);
			pstmt.setInt(2, wid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				stateCount = rs.getInt("count");
			}

		} catch (Exception ex) {
		} finally {
			close(rs);
			close(pstmt);
		}
		return stateCount;
	}
	
	/* 그룹 관리창 출력 */
	public ArrayList<WithBoardBean> selectGroupManage(int wid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String group_list = "select wa.*, us.nick, us.phone, us.photo pf , us.email "
				+ "FROM with_application wa join users us on wa.uid=us.uid where wid = ?";
		ArrayList<WithBoardBean> groupList = new ArrayList<WithBoardBean>();
		WithBoardBean withboard = null;
		try {
			pstmt = con.prepareStatement(group_list);
			pstmt.setInt(1, wid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				withboard = new WithBoardBean();
				withboard.setUid(rs.getInt("uid"));
				withboard.setEmail(rs.getString("email"));
				withboard.setState(rs.getInt("state"));
				withboard.setApply_date(rs.getDate("apply_date"));
				withboard.setNick(rs.getString("nick"));
				withboard.setPhone(rs.getString("phone"));
				withboard.setPf(rs.getString("pf"));
				groupList.add(withboard);

			}
		} catch (Exception ex) {
		} finally {
			close(rs);
			close(pstmt);
		}

		return groupList;

	}
	
	public int groupAppro(int wid, int uid) {
		System.out.println("dao w e:"+wid+" "+ uid);
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update with_application set state = 1 where wid = " + wid +" and "
				+ " uid = "+uid+";";

		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
		} finally {
			close(pstmt);

		}

		return updateCount;

	}
	
	public int groupBanned(int wid, int uid) {
		System.out.println("dao w e:"+wid+" "+ uid);
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "delete from with_application where wid = " +wid+ " "
				+ " and uid = "+uid+";";

		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
		} finally {
			close(pstmt);

		}

		return updateCount;

	}

	/* 글 작성시 비밀번호를 입력 하는 게시판 만들때 사용 */
//	public boolean isArticleBoardWriter(int board_num,String pass){
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String board_sql="select * from board where BOARD_NUM=?";
//		boolean isWriter = false;
//
//		try{
//			pstmt=con.prepareStatement(board_sql);
//			pstmt.setInt(1, board_num);
//			rs=pstmt.executeQuery();
//			rs.next();
//
//			if(pass.equals(rs.getString("BOARD_PASS"))){
//				isWriter = true;
//			}
//		}catch(SQLException ex){
//		}
//		finally{
//			close(pstmt);
//		}
//
//		return isWriter;
//
//	}
	/* 로그인 할때 DB의 아이디와 비번 검사 */
//	public boolean login_check(String id, String pw) {
//		boolean users = false;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String login_sql = "select * from users where id=?";
//		try{
//			pstmt=con.prepareStatement(login_sql);
//			pstmt.setString(1, id);
//			rs=pstmt.executeQuery();
//			rs.next();
//			if(pw.equals(rs.getString("pw"))) {
//				users = true;
//			}
//		}catch(Exception e) {
//			
//		}finally {
//			close(pstmt);
//		}
//		return users;
//	}
}
