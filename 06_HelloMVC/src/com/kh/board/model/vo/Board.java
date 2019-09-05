package com.kh.board.model.vo;

import java.sql.Date;

public class Board {

	private int Board_no;
	private String Board_title;
	private String Board_writer;
	private String Board_content;
	private String Board_original_filename;
	private String Board_rename_filename;
	private Date Board_date;
	private int board_readcount;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int board_no, String board_title, String board_writer, String board_content,
			String board_original_filename, String board_rename_filename, Date board_date, int board_readcount) {
		super();
		Board_no = board_no;
		Board_title = board_title;
		Board_writer = board_writer;
		Board_content = board_content;
		Board_original_filename = board_original_filename;
		Board_rename_filename = board_rename_filename;
		Board_date = board_date;
		this.board_readcount = board_readcount;
	}

	public int getBoard_no() {
		return Board_no;
	}

	public void setBoard_no(int board_no) {
		Board_no = board_no;
	}

	public String getBoard_title() {
		return Board_title;
	}

	public void setBoard_title(String board_title) {
		Board_title = board_title;
	}

	public String getBoard_writer() {
		return Board_writer;
	}

	public void setBoard_writer(String board_writer) {
		Board_writer = board_writer;
	}

	public String getBoard_content() {
		return Board_content;
	}

	public void setBoard_content(String board_content) {
		Board_content = board_content;
	}

	public String getBoard_original_filename() {
		return Board_original_filename;
	}

	public void setBoard_original_filename(String board_original_filename) {
		Board_original_filename = board_original_filename;
	}

	public String getBoard_rename_filename() {
		return Board_rename_filename;
	}

	public void setBoard_rename_filename(String board_rename_filename) {
		Board_rename_filename = board_rename_filename;
	}

	public Date getBoard_date() {
		return Board_date;
	}

	public void setBoard_date(Date board_date) {
		Board_date = board_date;
	}

	public int getBoard_readcount() {
		return board_readcount;
	}

	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}

	@Override
	public String toString() {
		return "Board [Board_no=" + Board_no + ", Board_title=" + Board_title + ", Board_writer=" + Board_writer
				+ ", Board_content=" + Board_content + ", Board_original_filename=" + Board_original_filename
				+ ", Board_rename_filename=" + Board_rename_filename + ", Board_date=" + Board_date
				+ ", board_readcount=" + board_readcount + "]";
	}
	
	
	
	
	
	
	
	
}
