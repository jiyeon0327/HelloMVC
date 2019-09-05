package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;
import com.kh.memeber.model.service.MemberService;

/**
 * Servlet implementation class MemberEnrollEndServlet
 */
@WebServlet(name="memberEnrollEnd",urlPatterns= "/memberEnrollEnd")
//위에 name은 filter의 이름과 같아야하고,
//member.jsp에 src를 urlpatterns로 받아온것임
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
		//filter적용 방식 2개
		//1.어노테이션 방식=>
//			@WebFilter("맵핑url") servletNames= {}
//			class dfff{
//			}
		//2.web.xml 직접등록 방식
//		<filter>
//			<filter-name>임의의 명칭</filter-name>
//			<filter-class>패키지명까지 다 작성을 해줘야함</filter-class>
//		</filter>
//		<filter-mapping>
//			<filter-name>임의의 명칭</filter-name>
//			<url-pattern></url-pattern>
//		</filter-mapping>
		
		//client가 보낸 값 받기
		String id=request.getParameter("userId");//키는 name값 받음
		String pw=request.getParameter("password");
		String name=request.getParameter("userName");
		char gender=request.getParameter("gender").charAt(0);
		int age=Integer.parseInt(request.getParameter("age"));
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String[] hobby=request.getParameterValues("hobby");
		
		//vo객체를 이용해서 DB에 전송
		//받아온 값 처리
		String hobbys=String.join(",",hobby);
		Member m=new Member(id,pw,name,gender,age,email,phone,address,hobbys,null);
		
		//비즈니스 로직
		//사용자가 보내준 데이터를 db에 저장하고 결과를 반환
		int result=new MemberService().insertMember(m);
		//service로 이동
		//
		String msg=result>0?"가입완료!":"가입실패!";
		String loc="/";
		request.setAttribute("msg",msg);
		request.setAttribute("loc",loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
