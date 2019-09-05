package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {
	private int notice_No;
	private String notice_Title;
	private String notice_WRITER;
	private String notice_contenet;
	private Date notice_date;
	private String filepath;
	private char status;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int notice_No, String notice_Title, String notice_WRITER, String notice_contenet, Date notice_date,
			String filepath, char status) {
		super();
		this.notice_No = notice_No;
		this.notice_Title = notice_Title;
		this.notice_WRITER = notice_WRITER;
		this.notice_contenet = notice_contenet;
		this.notice_date = notice_date;
		this.filepath = filepath;
		this.status = status;
	}

	public int getNotice_No() {
		return notice_No;
	}

	public void setNotice_No(int notice_No) {
		this.notice_No = notice_No;
	}

	public String getNotice_Title() {
		return notice_Title;
	}

	public void setNotice_Title(String notice_Title) {
		this.notice_Title = notice_Title;
	}

	public String getNotice_WRITER() {
		return notice_WRITER;
	}

	public void setNotice_WRITER(String notice_WRITER) {
		this.notice_WRITER = notice_WRITER;
	}

	public String getNotice_contenet() {
		return notice_contenet;
	}

	public void setNotice_contenet(String notice_contenet) {
		this.notice_contenet = notice_contenet;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	

}
