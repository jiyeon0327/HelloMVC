package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.memeber.model.service.MemberService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet",urlPatterns ="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//client가 보낸 값을 받아옴!
		String id=request.getParameter("userId");//header input name을 매개변수에 쓴것임
		String pw=request.getParameter("password");
		//비즈니스 로직을 짜기
		//로그인 성공,실패시
		
		//로그인 저장 관리하기
		//check했을 때  on, 안했을 때 null이 나오게 분기처리함
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		
		
		
		//id와 pw를 가지고 가서 DB에 일치하는 값이 있는지 확인하고 결과를 가져옴
		MemberService service=new MemberService();
		Member m=service.selectId(id,pw);
		
		//view에서 보여줄 메시지
		String msg="";
	
		//view에서 다른 뷰로 이동하는 주소
		String loc="/"; //index.jsp
		String view="";
		//기준!
		if(m!=null) {
			//msg="로그인 성공";
			//로그인이 되면 session에 로그인결과에 대한 값을 유지시킴=>http통신 web stateless(상태유지안됨_
			//특정값을 유지되는 곳에 저장을 하고 확인
				HttpSession session=request.getSession();
				session.setAttribute("loginMember",m);
			//session 속성값 설정!
			//setMaxInactiveInterval():session생존주기 관리
			//*매개변수의 second만큼만 session을 유지
			//getCreationTime():생성된 시간
			//getLastAccessedTime():마지막 요청시간
			//invalidate():세션종료
			
			//session.setMaxInactiveInterval(60);//1분지나면 로그아웃,자동으로 세션을 끊기게 함(세션유지)
			
			//아이디를 Cookie를 이용해서 저장하기
			//Cookie는 클라이언트 측에서 저장하는 데이터를 말하고 서블릿에서 cookie객체를 가지고 있음
				if(saveId != null) {
					//체크가 된 경우 쿠키 생성
					Cookie c=new Cookie("saveId",id);//키:값 형식으로
					c.setMaxAge(3*24*60*60);//=>3일간 유지 (완료기간.초단위계산을 함)
					response.addCookie(c);
				}else {
					//로그인저장 체크 안된경우
					Cookie c=new Cookie("saveId",id);
					c.setMaxAge(0);//유지기간 0, 쿠키 삭제됨
					response.addCookie(c);
				}
	
		}else {
			//로그인 실패했을 때,안되는 상태일때
			msg="아이디나 패스워드가 일치하지 않습니다.";
			view="/views/common/msg.jsp";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		}		
		
		//request.setAttribute("loginMember", m);
		RequestDispatcher rs=request.getRequestDispatcher(view);//알람 띄워주는 용
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
