package com.kh.notice.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

import oracle.jdbc.proxy.annotation.GetDelegate;

/**
 * Servlet implementation class NoticeClickServelt
 */
@WebServlet("/notice/noticeClick")
public class NoticeClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeClickServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int notice_no=Integer.parseInt(request.getParameter("notice_No"));
		//이제 service로 넘어가서 작성
		Notice n=new NoticeService().selectClick(notice_no);//기본키는 notice_no 단 하나이기 때문에 리스트로 굳이 안받고 단일객체로 받는다.
		
		request.setAttribute("notice", n);
		request.getRequestDispatcher("/views/notice/noticeView.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
