package com.kh.board.model.service;

import java.sql.Connection;


import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

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

	public Board selectBoardView(int Board_no, boolean hasRead) {
		Connection conn=getConnection();
		Board b= dao.selectBoardView(conn,Board_no);
		if(!hasRead &&  b != null) {
			//증가시키기
			int result = dao.updateReadCount(conn,Board_no);
			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}
		
		close(conn);
		return b;
	}

	public int insertBoard(Board b) {
		
		Connection conn=getConnection();
		int result = dao.insertBoard(conn,b);//
		if(result>0) {
			result=dao.seletBoardNo(conn);//nextval에서 나온 값이 currVal이 됨. 그리고 그 값을 중간에서 디비에서 가져올 수 있게 처리하기 위해서 쓰임
			commit(conn);
		}else {
			rollback(conn);
		}return result;
	}

	public int insertBoardComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn, bc);
		if(result>0){
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
			
		}

	public List<BoardComment> selectBoardComment(int board_no) {
		Connection conn=getConnection();
		List<BoardComment> list = dao.selectBoardComment(conn,board_no);
		close(conn);
		return list;

	}

	public int deleteComment(int boardCommentNo, int boardRef) {
		Connection conn=getConnection();
		int result=dao.deleteComment(conn,boardCommentNo,boardRef);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	
	
		

}
