package com.kh.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

import static common.template.JDBCTemplate.close;

public class BoardDao {

	private Properties prop=new Properties();
	
	public BoardDao() {
		String path=BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectCountBoard(Connection conn) {
		PreparedStatement pstmt =null;
		
		int result=0;
		ResultSet rs= null;
		String sql=prop.getProperty("selectCountBoard");
		try {
			pstmt =conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);//컬럼명 첫번째 를 뜻함, 이런경우 컬럼명이 길거나 쓰기 힘들어서 간단하게 불러오기 위해서씀
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}

	public List<Board> selectBoardList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		String sql=prop.getProperty("selectBoardList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
			
				b.setBoard_title(rs.getString("Board_title"));
				b.setBoard_writer(rs.getString("Board_writer"));
				b.setBoard_content(rs.getString("Board_content"));
				b.setBoard_original_filename(rs.getString("Board_original_filename"));
				b.setBoard_rename_filename(rs.getString("Board_renamed_filename"));
				b.setBoard_date(rs.getDate("Board_date"));
				b.setBoard_readcount(rs.getInt("Board_readcount"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace(); 
		}finally {
			close(rs);
			close(pstmt);
		}return list;
		
	}

	public Board selectBoardView(Connection conn, int board_no) {
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectBoardView");
		Board b=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new Board();
				b.setBoard_no(rs.getInt("Board_no"));
				b.setBoard_title(rs.getString("Board_title"));
				b.setBoard_writer(rs.getString("Board_writer"));
				b.setBoard_content(rs.getString("Board_content"));
				b.setBoard_original_filename(rs.getString("Board_original_filename"));
				b.setBoard_rename_filename(rs.getString("Board_renamed_filename"));
				b.setBoard_date(rs.getDate("Board_date"));
				b.setBoard_readcount(rs.getInt("Board_readcount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
	}

	public int updateReadCount(Connection conn, int board_no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateReadCount");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,board_no);
			result=pstmt.executeUpdate();
			
			}catch(SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(pstmt);
				
			}return result;
		}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoard_title());
			pstmt.setString(2, b.getBoard_writer());
			pstmt.setString(3, b.getBoard_content());
			pstmt.setString(4, b.getBoard_original_filename());
			pstmt.setString(5, b.getBoard_rename_filename());
		
			result=pstmt.executeUpdate();

			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}finally {
				
				close(pstmt);
				
			}return result;
		}

	public int seletBoardNo(Connection conn) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="select seq_board_no.currval from dual";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				result=rs.getInt(1);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}return result;
	}

	public int insertBoardComment(Connection conn, BoardComment bc) {
			PreparedStatement pstmt =null;
			int result = 0;
			String sql=prop.getProperty("insertBoardComment");
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, bc.getBoardCommentLevel());
				pstmt.setString(2, bc.getBoardCommentWriter());
				pstmt.setString(3, bc.getBoardCommentContent());
				pstmt.setInt(4, bc.getBoardRef());
				//board와 comment 자체를 참조함 
				//comment_no에 0번은 안들어감. 부모의 참조할 수 없는 게 없어서 /foreign에 null은 들어갈수 있어
				//int형 자료형에 자료가 없어 null값을 넣을 때는
				//int를 string으로 바꾼다
//				pstmt.setInt(5, bc.getBoardCommentRef());
				//자동형변환으로 써서 변환시키기
				pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef()));
				result=pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}return result;
	}

	
	public List<BoardComment> selectBoardComment(Connection conn, int board_no) {
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		List<BoardComment> list=new ArrayList();
		String sql=prop.getProperty("selectBoardComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,board_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardComment bc = new BoardComment();
				bc.setBoardCommentNo(rs.getInt("board_comment_no"));
				bc.setBoardCommentLevel(rs.getInt("board_comment_level"));
				bc.setBoardCommentWriter(rs.getString("board_comment_writer"));
				bc.setBoardCommentContent(rs.getString("board_comment_content"));
				bc.setBoardCommentRef(rs.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rs.getDate("board_comment_date"));
				list.add(bc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	
	}

	public int deleteComment(Connection conn, int boardCommentNo, int boardRef) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql =prop.getProperty("deleteComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,boardCommentNo );
			pstmt.setInt(2, boardRef);
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}return result;
		
	}
}
/*
  조회 수 클릭 한 아이디당 한번만 가능하도록 로직짜기
  :쿠키 에 저장 하는 식으로
  board_no는 기본키니까 쿠키에 기본키를 저장할 것임
  
 */
