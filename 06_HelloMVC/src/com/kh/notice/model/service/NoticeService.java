package com.kh.notice.model.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;
import static common.template.JDBCTemplate.getConnection;//내가 연결을 import따로 해줘야 해
import static common.template.JDBCTemplate.close;

import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

public class NoticeService {
	
	private NoticeDao dao=new NoticeDao();//얘를 계속 이용할 거라서 미리 변수로 만드는 것
	
	
	public int selectCountList() {
		Connection conn=getConnection();
		int result=dao.selectCountList(conn);
		close(conn);
		return result;
	
	}
	

	public List<Notice> selectNoticeList(int cPage,int numPerPage) {
		Connection conn=getConnection();
		List<Notice> list = dao.selectNoticeList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}


	public Notice selectClick(int notice_no) {
		Connection conn=getConnection();
		Notice n=dao.selectClick(conn,notice_no);//매개변수로 conn과 notice_no를 만들어야함
		//그리고 dao에 넘겨주기
		close(conn);
		return n;
	}


	public int insertNotice(Notice n) {
		Connection conn=getConnection();
		int result=dao.insertNotice(conn,n);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		int result = dao.updateNotice(conn,n);
		System.out.println(result);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
