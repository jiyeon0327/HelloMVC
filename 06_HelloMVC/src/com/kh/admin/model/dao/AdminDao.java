package com.kh.admin.model.dao;

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

import com.kh.member.model.vo.Member;

import static common.template.JDBCTemplate.close;

public class AdminDao {
	private Properties prop=new Properties();
	
	public AdminDao() {

		String path=AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public List<Member> selectMemberList(Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		String sql=prop.getProperty("selectMemberList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);//현재페이지 3이면 11이 나올수 있게 공식을 사용한것.//여기가 1이면
			pstmt.setInt(2, cPage*numPerPage);//끝에 값 범위를 구하기 위해 계산식 쓴것.//여기는 5
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Member m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setAge(rs.getInt("age"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrolldate(rs.getDate("enrolldate"));
				list.add(m);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return list;
	}

	public int selectCountMember(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;//컬럼으로 불러오기 때문에 필요함
		int result=0;
		String sql=prop.getProperty("selectCountMember");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
//				result=rs.getInt("cnt");
				//컬럼명을 하는 방법외에도 인덱스를 기준으로 불러올 수 도 있음(ex:1)
				//select count(*) from member;하면 각각 인덱스 값이 부여되는데 그 것을 갖고 와도된다는 말!
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}

	public List<Member> selectSearch(Connection conn, int cPage,int numPerPage, String type, String keyword) {
	
		//PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		//PreparedStatement을 사용하면 컬럼명,유저네임,테이블명은 sql에서 ?를 쓸 수 없다.
		//나눠야 함
		String sql="";
//		switch (type) {
		//컬럼명을 지정해서 쓸 수 없기 때문에 이렇게 나눠서야 한다.
//		case "userId":sql=prop.getProperty("selectSearchUsrtId");break;
//		case "userName":sql=prop.getProperty("selectSearchUsrtName");break;
//		case "gender":sql=prop.getProperty("selectSearchgender");break;
//		}
		//statement방식(''와 띄어 쓰기를 조심해서 사용할것)
		int start=(cPage-1)*numPerPage+1;
		int end=cPage*numPerPage;
		
        sql="select * from (select rownum as rnum,a.* "
        		+ "from  (select * "
        			+ "from member "
        			+" where "+type+" like '%"+keyword+"%')a)"
        					+ "where rnum between "+start+" and "+end;
		
        try {
        	stmt=conn.createStatement();
        	rs=stmt.executeQuery(sql);
        	while(rs.next()) {
        		Member m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setUserName(rs.getString("username"));
				m.setAge(rs.getInt("age"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrolldate(rs.getDate("enrolldate"));
				list.add(m);
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }finally{
        	close(rs);
        	close(stmt);
        }return list;
	}

	public int selectSearchCount(Connection conn, String type, String keyword) {
		//PreparedStatement pstmt=null;
		ResultSet rs=null;//select할 떄 무조건 받아야해
		Statement stmt=null;
		int result=0;
		//String sql=prop.getProperty("selectSearchCount");
		String sql="";
		//switch (type) {
		//컬럼명을 지정해서 쓸 수 없기 때문에 이렇게 나눠서야 한다.
//		case "userId":sql=prop.getProperty("selectSearchCountUserId");break;
//		case "userName":sql=prop.getProperty("selectSearchCountUserName");break;
//		case "gender":sql=prop.getProperty("selectSearchCountgender");break;
//		}
		sql="select count(*) from member "+ "where "+type+" like '%"+keyword+"%'";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				result=rs.getInt(1);//컬럼 인덱스명
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return result;
	
	}
	
	
	
}
