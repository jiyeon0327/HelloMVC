package com.kh.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;

public class BoardService {

	private BoardDao dao=new BoardDao();
	
	public int selectCountBoard() {
		Connection conn=getConnection();
		int result=dao.selectCountBoard(conn);
		close(conn);
		return result;
		
	}

	public List<Board> selectBoardList(int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<Board> list=dao.selectBoardList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public Board selectBoardView(int Board_no) {
		Connection conn=getConnection();
		Board b= dao.selectBoardView(conn,Board_no);
		close(conn);
		return b;
	}

}
