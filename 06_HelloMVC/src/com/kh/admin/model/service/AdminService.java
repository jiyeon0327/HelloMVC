package com.kh.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.admin.model.dao.AdminDao;
import com.kh.member.model.vo.Member;

import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.close;

public class AdminService {
	private AdminDao dao=new AdminDao();
	public List<Member> selectMemberList(int cPage,int numPerPage){
		Connection conn=getConnection();
		List<Member> list=dao.selectMemberList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	public int selectCountMember() {
		Connection conn=getConnection();
		int result=dao.selectCountMember(conn);
		close(conn);
		return result;
	}
	//검색된 결과 중 numPerPage갯수만큼만 조회
	public List<Member> selectSearch(int cPage,int numPerPage,String type, String keyword) {
		Connection conn=getConnection();
		List<Member> list=dao.selectSearch(conn,cPage,numPerPage,type,keyword);	
		close(conn);
		return list;
		
		
	}
	//검색에 대한 전체 자료수
	public int selectSearchCount(String type,String keyword) {
		Connection conn=getConnection();
		int result=dao.selectSearchCount(conn, type, keyword);
		close(conn);
		return result;
	}
	//갯수에 많는 자료수를 출력
		
	
	
	
	
	
	
	
	
	
	
	
	
}
