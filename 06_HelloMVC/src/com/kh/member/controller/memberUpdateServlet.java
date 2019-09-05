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
 * Servlet implementation class memberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class memberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//회원정보를 수정하는 서블릿 만들기(매핑)
		Member m=new Member(request.getParameter("userId"),
				"",
				request.getParameter("userName"),
				request.getParameter("gender").charAt(0),
				Integer.parseInt(request.getParameter("age")),
				request.getParameter("email"),
				request.getParameter("phone"),
				request.getParameter("address"),
				String.join(",",request.getParameterValues("hobby")),
				null);
		//비즈니스 로직 수행
		int result =new MemberService().updateMember(m);
		String msg="";
		String loc="";
		if(result>0) {
			msg="회원정보 수정이 완료되었습니다.";
			loc="/";//main페이지로 이동
		}else {
			msg="회원정보 수정 실패 다시 수정해주세요.";
			loc="/member/memberView?userId="+m.getUserId();//마이페이지뷰로 다시 이동
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
