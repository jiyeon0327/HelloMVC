package com.kh.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import com.kh.member.model.vo.Member;
import static common.template.JDBCTemplate.close;

public class MemberDao {
	
	private Properties prop=new Properties();
	
	
	public MemberDao() {
		String path=MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	//service에서 여기로 던짐
	public Member selectId(Connection conn,String id,String pw) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;//db결과를 저장하기 위한 데이터형식을 표현한 객체(2차원배열)
		Member m=null;
		String sql=prop.getProperty("selectId");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();//sql실행 끝
			
			if(rs.next()) {//검색결과가 있다면 검색결과를 셋팅(member객체의 m에)
				m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setAge(rs.getInt("age"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrolldate(rs.getDate("enrolldate"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
		
		
		
		
		
	}
	public int insertMember(Connection conn, Member m) {
		
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,m.getUserId());//여기서부터 값을 셋팅시작
			pstmt.setString(2,m.getPassword());
			pstmt.setString(3,m.getUserName());
			pstmt.setString(4,String.valueOf(m.getGender()));
			pstmt.setInt(5,m.getAge());
			pstmt.setString(6,m.getEmail());
			pstmt.setString(7,m.getPhone());
			pstmt.setString(8,m.getAddress());
			pstmt.setString(9,m.getHobby());
			result=pstmt.executeUpdate();//의 쿼리의 값들을 결과에 넣기
			
		}catch(SQLException e) {//sql예외처리
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		//순서대로 close시키기: pstmt->result->conn(dao로 넘어가기)
		
		
	}
	public Member selectOne(Connection conn,String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		//String sql=prop.getProperty("checkId");
		String sql=prop.getProperty("selectOne");
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userId);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setAge(rs.getInt("age"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrolldate(rs.getDate("enrolldate"));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstmt);
			}
			return m;
		}
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
//		Resultset필요없음
		int result=0;
		String sql="update member set username=?,gender=?,age=?,email=?,phone=?,address=?,hobby=? where userid=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2,String.valueOf(m.getGender()));
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getHobby());
			pstmt.setString(8, m.getUserId());
			
		
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int updatePassword(Connection conn, String userId, String pw) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		int result =0;
		String sql=prop.getProperty("updatePassword");
		System.out.println(sql);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
		
	
