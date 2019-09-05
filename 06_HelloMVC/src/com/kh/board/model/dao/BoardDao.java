package com.kh.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;

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
				result = rs.getInt(1);//컬럼명
				
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
			pstmt.setInt(1, (cPage-1)*numPerPage);
			pstmt.setInt(2, cPage*numPerPage+1);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoard_no(rs.getInt("Board_no"));
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

}
