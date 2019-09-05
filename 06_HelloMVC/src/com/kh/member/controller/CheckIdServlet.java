package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.memeber.model.service.MemberService;

/**
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/checkIdDuplicate")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//client한테 받아온값
		String userId=request.getParameter("userId");
		System.out.println(userId);
		
		//비즈니스로직처리
		//문제)DB에 USERid와 일치하는 id가 member table에 있는지
		//확인하고 있으면 true,없으면 false반환하기!
		
		//첫번째 방식
		//boolean tf=new MemberService().checkId(userId);
		//System.out.println(userId?"중복아이디":"사용가능아이디");
		
		//다른방식
		boolean unable=new MemberService().selectOne(userId)!=null?true:false;
		request.setAttribute("unable",unable);
		
		//view선택!
		RequestDispatcher rd=request.getRequestDispatcher("/views/member/checkId.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
