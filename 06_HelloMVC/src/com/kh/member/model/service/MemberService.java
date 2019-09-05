package com.kh.member.model.service;

import java.sql.Connection;
import java.util.List;

import static common.template.JDBCTemplate.getConnection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

public class MemberService {

	private MemberDao dao=new MemberDao();
	
	public Member selectId(String id,String pw) {
		Connection conn=getConnection();
		Member m= dao.selectId(conn,id,pw);
		close(conn);
		return m;
		
	}
	//servlet에서 여기로 처리
	public int insertMember(Member m) {
		//selectOne
		Connection conn=getConnection();
		int result=dao.insertMember(conn,m);
		//dao에서 결과 값이 성공하면 1/실패하면 0
		if(result>0) {commit(conn);}
		else{rollback (conn);}
		close(conn);
		return result;
	}

	public Member selectOne(String userId) {
		Connection conn=getConnection();
		Member m=dao.selectOne(conn,userId);
		close(conn);
		
		return m;
	}
	public int updateMember(Member m) {
		Connection conn=getConnection();
		int result=dao.updateMember(conn,m);
		if(result>0) {commit(conn);}
		else {rollback(conn);}
		
		close(conn);
		return result;
	}
	public int deleteMember(String userId) {
		Connection conn = getConnection();
		int result = dao.deleteMember(conn,userId);
			if(result>0) { commit(conn); }
			else {rollback(conn); }
		return result;
	}
	public int updatePassword(String userId, String cPw, String nPw) {
		Connection conn=getConnection();
		//현재 비밀번호가 일치하는 지확인
		//필요한 값 가져오기
		Member m=dao.selectId(conn, userId, cPw);

		int result=0;
		if(m==null) {
			//-1일 경우는 현재 비밀번호를 모르고 있을 때, 이녀석 누구야
			//0일경우는 로직상,구문상,네트워크상에 문제가 생겨서 update문이 실행이 잘 안되어서 나온 값.
			result= -1;
		}else {
			//m이 있다는 건 현재비밀번호 일치한다는 것
		result=dao.updatePassword(conn,userId,nPw);
			
		if(result>0) {commit(conn);}
		else {rollback(conn);}
		
		}
		close(conn);
		return result;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
