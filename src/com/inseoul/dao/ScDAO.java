package com.inseoul.dao;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


import com.inseoul.vo.Servicecenter;

public class ScDAO {
	DataSource ds;
	Connection con;
	private static ScDAO ScDAO;

	public ScDAO() {
	}

	public static ScDAO getInstance(){ //싱글턴 패턴
		if(ScDAO == null){
			ScDAO = new ScDAO();
		}
		return ScDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}
	public int selectListCount() {

		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt=con.prepareStatement("select count(*) from inseoul.servicecenter where seq=0");
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
	
	public ArrayList<Servicecenter> selectArticleList(int page,int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from inseoul.servicecenter where seq = 0 order by ref desc,seq asc limit ?,10";
		ArrayList<Servicecenter> articleList = new ArrayList<Servicecenter>();
		Servicecenter servicecenter = null;
		int startrow=(page-1)*10; 

		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				servicecenter = new Servicecenter();
				servicecenter.setSid(rs.getInt("sid"));
				servicecenter.setUid(rs.getInt("uid"));
				servicecenter.setNick(rs.getString("nick"));
				servicecenter.setTitle(rs.getString("title"));
				servicecenter.setContents(rs.getString("contents"));
				servicecenter.setPhoto(rs.getString("photo"));
				servicecenter.setRef(rs.getInt("ref"));
				servicecenter.setSeq(rs.getInt("seq"));
				servicecenter.setWritedate(rs.getDate("writedate"));
				articleList.add(servicecenter);
			}

		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}
	
	public Servicecenter selectArticle(int sid){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Servicecenter servicecenter = null;

		try{
			pstmt = con.prepareStatement(
					"select * from inseoul.servicecenter where sid = ?");
			pstmt.setInt(1, sid);
			rs= pstmt.executeQuery();

			if(rs.next()){
				servicecenter = new Servicecenter();
				servicecenter.setSid(rs.getInt("sid"));
				servicecenter.setUid(rs.getInt("uid"));
				servicecenter.setNick(rs.getString("nick"));
				servicecenter.setTitle(rs.getString("title"));
				servicecenter.setContents(rs.getString("contents"));
				servicecenter.setPhoto(rs.getString("photo"));
				servicecenter.setRef(rs.getInt("ref"));
				servicecenter.setSeq(rs.getInt("seq"));
				servicecenter.setWritedate(rs.getDate("Writedate"));
			}
		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}

		return servicecenter;

	}
	
	public ArrayList<Servicecenter> selectReply(){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Servicecenter> reply = new ArrayList<Servicecenter>();
		Servicecenter servicecenter = null;
		try{
			pstmt = con.prepareStatement(
					"select * from inseoul.servicecenter where  seq >0;");
			rs= pstmt.executeQuery();

			if(rs.next()){
				servicecenter = new Servicecenter();
				servicecenter.setSid(rs.getInt("sid"));
				servicecenter.setUid(rs.getInt("uid"));
				servicecenter.setNick(rs.getString("nick"));
				servicecenter.setTitle(rs.getString("title"));
				servicecenter.setContents(rs.getString("contents"));
				servicecenter.setPhoto(rs.getString("photo"));
				servicecenter.setRef(rs.getInt("ref"));
				servicecenter.setSeq(rs.getInt("seq"));
				servicecenter.setWritedate(rs.getDate("Writedate"));
				reply.add(servicecenter);
			}
		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}

		return reply;
	}
	
	public int insertArticle(Servicecenter article){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(sid) from Servicecenter"); //prepareStatement = 형변환까지 해줌 '%s' 's' 처럼 구분 안해줘도됨
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into inseoul.Servicecenter (sid,uid,nick,photo,title,";
			sql+="contents,  writedate,"+
					"ref,seq) values(?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getUid());
			pstmt.setString(3, article.getNick());
			pstmt.setString(4, article.getPhoto());
			pstmt.setString(5, article.getTitle());
			pstmt.setString(6, article.getContents());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);

			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	public int insertReplyArticle(Servicecenter article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql="select max(sid) from inseoul.servicecenter";
		String sql="";
		int num=0;
		int insertCount=0;
		int re_ref=article.getRef();
		int re_seq=article.getSeq();

		try{
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1;
			else num=1;
			sql="update inseoul.servicecenter set seq=seq+1 where ref=? ";
			sql+="and seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			int updateCount=pstmt.executeUpdate();

			if(updateCount > 0){
				commit(con);
			}

			re_seq = re_seq + 1;
			sql="insert into inseoul.servicecenter (sid,uid,nick,title,";
			sql+="contents, photo,ref,seq,";
			sql+="writedate) values(?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getUid());
			pstmt.setString(3, article.getNick());
			pstmt.setString(4, article.getTitle());
			pstmt.setString(5, article.getContents());
			pstmt.setString(6, article.getPhoto()); //���忡�� ������ ���ε����� ����.
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_seq);
			insertCount = pstmt.executeUpdate();
		}catch(SQLException ex){
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
	
	
	public int updateArticle(Servicecenter article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update inseoul.servicecenter set title=?,contents=? where sid=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContents());
			pstmt.setInt(3, article.getSid());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
		}finally{
			close(pstmt);
		}
		System.out.println(article.getTitle()+"1");
		System.out.println(article.getContents()+"2");
		System.out.println(article.getSid()+"3");
		
		System.out.println(updateCount);
		return updateCount;

	}
	
	public int deleteArticle(int sid){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from inseoul.servicecenter where sid=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, sid);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}
	
}
