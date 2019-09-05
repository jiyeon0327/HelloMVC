package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.memeber.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		int result = new MemberService().deleteMember(userId);
		
		String msg = "";
		String loc = "";
		
		if(result>0) {
			msg = "탈퇴성공! 잘가~~";
			loc = "/logout";
		}else {
			msg = "회원탈퇴 실패~ 다시 시도하세요.";
			loc = "/member/memberView?userId="+userId;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	
	//암호화 필기
	//단방향은 암호화만 가능.풀수 없음 ex)비밀번호(대칭만 되면됨)
	//양방향은 암호화,복호화 가능.키(대칭키,공개키 등등)를 갖고있음 복호화,암호화 가능 ,공개키로 키 뿌리고 암호화갖고 있는 사람만
	//~~변경 가능ex)다~~적용가능
	//md5 권장 x sha512(경우의수 ↑)/256(권장) 비트
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
