package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.inseoul.vo.QnABoardBean;


public class QnaDAO {
	DataSource ds;
	Connection con;
	private static QnaDAO qnaDAO;

	private QnaDAO() {
	}

	public static QnaDAO getInstance(){ //싱글턴 패턴
		if(qnaDAO == null){
			qnaDAO = new QnaDAO();
		}
		return qnaDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}

	public int selectListCount() {

		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt=con.prepareStatement("select count(*) from qnaboard where seq=0");
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
	
	public ArrayList<QnABoardBean> selectArticleList(int page,int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from qnaboard having seq=0 order by ref desc,seq asc limit ?,25";
		ArrayList<QnABoardBean> articleList = new ArrayList<QnABoardBean>();
		QnABoardBean board = null;
		int startrow=(page-1)*25; 

		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				board = new QnABoardBean();
				board.setQid(rs.getInt("qid"));
				board.setUid(rs.getInt("uid"));
				board.setNick(rs.getString("nick"));
				board.setTitle(rs.getString("title"));
				board.setContents(rs.getString("contents"));
				board.setPhotos(rs.getString("photos"));
				board.setRef(rs.getInt("ref"));
				board.setSeq(rs.getInt("seq"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWritedate(rs.getDate("writedate"));
				articleList.add(board);
			}

		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}
	
	public ArrayList<QnABoardBean> selectReply(int ref){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnABoardBean qnaBoardBean = null;
		ArrayList<QnABoardBean> replyList = new ArrayList<QnABoardBean>();
		try{
			pstmt = con.prepareStatement("select qnaboard.*, users.photo from qnaboard join users on qnaboard.uid=users.uid where ref=? and seq > 0 ");
			pstmt.setInt(1, ref);
			rs= pstmt.executeQuery();
			while(rs.next()){
				qnaBoardBean = new QnABoardBean();
				qnaBoardBean.setQid(rs.getInt("qid"));
				qnaBoardBean.setUid(rs.getInt("uid"));
				qnaBoardBean.setPf(rs.getString("photo"));
				qnaBoardBean.setTitle(rs.getString("title"));
				qnaBoardBean.setContents(rs.getString("contents"));
				qnaBoardBean.setRef(rs.getInt("ref"));
				qnaBoardBean.setSeq(rs.getInt("seq"));
				qnaBoardBean.setReadcount(rs.getInt("readcount"));
				qnaBoardBean.setWritedate(rs.getDate("writedate"));
				qnaBoardBean.setNick(rs.getString("nick"));
				replyList.add(qnaBoardBean);
			}
			
		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		System.out.println("321"+replyList);
		return replyList;

	} 

	public QnABoardBean selectArticle(int qid){
		System.out.println("aaa"+qid);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnABoardBean qnaBoardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select qnaboard.*, inseoul.users.nick, inseoul.users.photo from qnaboard join inseoul.users on "
							+"qnaboard.uid = inseoul.users.uid where qid=?");
			pstmt.setInt(1, qid);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				qnaBoardBean = new QnABoardBean();
				qnaBoardBean.setQid(rs.getInt("qid"));
				qnaBoardBean.setUid(rs.getInt("uid"));
				qnaBoardBean.setPhotos(rs.getString("photos"));
				qnaBoardBean.setPf(rs.getString("photo"));
				qnaBoardBean.setTitle(rs.getString("title"));
				qnaBoardBean.setContents(rs.getString("contents"));
				qnaBoardBean.setRef(rs.getInt("ref"));
				qnaBoardBean.setSeq(rs.getInt("seq"));
				qnaBoardBean.setReadcount(rs.getInt("readcount"));
				qnaBoardBean.setWritedate(rs.getDate("writedate"));
				qnaBoardBean.setNick(rs.getString("nick"));
				System.out.println("pho: "+rs.getString("photos"));
			}
		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		System.out.println("111: "+qnaBoardBean);
		return qnaBoardBean;

	}

	public int insertArticle(QnABoardBean article){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(qid) from qnaboard"); //prepareStatement = 형변환까지 해줌 '%s' 's' 처럼 구분 안해줘도됨
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into qnaboard (qid,uid,photos,title,";
			sql+="contents, ref, seq,"+
					"readcount,writedate,nick) values(?,?,?,?,?,?,?,?,now(),?)";
			System.out.println("dfsdf");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getUid());
			pstmt.setString(3, article.getPhotos());
			pstmt.setString(4, article.getTitle());
			pstmt.setString(5, article.getContents());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setString(9, article.getNick());

			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	public int insertReplyArticle(QnABoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql="select max(qid) from qnaboard";
		String sql="";
		int num=0;
		int insertCount=0;
		int ref=article.getRef();
		int seq=article.getSeq();

		try{
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1;
			else num=1;
			sql="update qnaboard set seq=seq+1 where ref=? ";
			sql+="and seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,ref);
			pstmt.setInt(2,seq);
			int updateCount=pstmt.executeUpdate();

			if(updateCount > 0){
				commit(con);
			}

			seq = seq + 1;
			sql="insert into qnaboard (qid,uid,photos,title,contents,ref,seq,readcount,nick,writedate) values(?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getUid());
			pstmt.setString(3, article.getPhotos());
			pstmt.setString(4, article.getTitle());
			pstmt.setString(5, article.getContents());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, seq);
			pstmt.setInt(8, 0);
			pstmt.setString(9, article.getNick());
			insertCount = pstmt.executeUpdate();
		}catch(SQLException ex){
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	public int updateArticle(QnABoardBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update qnaboard set title=?,contents=? where qid=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContents());
			pstmt.setInt(3, article.getQid());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	public int deleteArticle(int qid){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from qnaboard where qid=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, qid);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	public int updateReadCount(int qid){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update qnaboard set readcount = "+
				"readcount+1 where qid = "+qid;

		try{
			pstmt=con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(SQLException ex){
		}
		finally{
			close(pstmt);

		}

		return updateCount;

	}

	public boolean isArticleBoardWriter(int qid){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from qnaboard where qid=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, qid);
			rs=pstmt.executeQuery();
			rs.next();

		}catch(SQLException ex){
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}
	
	public boolean login(String id , String pw) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_sql="select * from users where id=?";
		boolean Users = false;
		try {
			pstmt=con.prepareStatement(user_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(pw.equals(rs.getString("pw"))) {
				Users=true;
			}
		} catch (Exception e) {
		}finally{
			close(rs);
			close(pstmt);
		}

		return Users;
	}

}