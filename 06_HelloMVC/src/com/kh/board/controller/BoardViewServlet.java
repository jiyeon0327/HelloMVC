package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int Board_no =Integer.parseInt(request.getParameter("Board_no"));
		
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}

		//쿠키 값 확인하기
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = "";
		boolean hasRead = false;//읽었는지 안읽었는지 구분하는 기준
		
		if(cookies != null) {
			//값이 있다는 말
			for(Cookie c: cookies) {
				String name=c.getName();//이건 key값
				String value=c.getValue();//value값
				//확인하기
				if("boardCookie".equals(name)) {
					boardCookieVal = value;//이전값을 보관해야함 =?본 쿠키를 확인할수 있게 만들기 이해서 저장함
					if(value.contains("|" + Board_no + "|")) {
						//포함(읽음)되어있으면 true, 안읽었음 false
						//번호가 있다는 말은 읽었단 말
						//기준값은 다음 과 같이
							hasRead=true;
							break;
					}
				}
			}
		}
		//안읽었을 때 조회수를 추가하고 cookie에 
		//현재 Board_no를 기록하자
		if(!hasRead) {
			Cookie c = new Cookie("boardCookie",boardCookieVal + "|" + Board_no + "|");
				c.setMaxAge(-1);//브라우저가 close되거나 로그아웃했을 때 삭제
				response.addCookie(c);//응답자한테 쿠키데이터를 넣어 준것임
			}
		
		Board b = new BoardService().selectBoardView(Board_no, hasRead);
		request.setAttribute("board", b);
		request.setAttribute("cPage", cPage);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
		
		}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
