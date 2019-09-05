package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.policy.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardFormEndServlet
 */
@WebServlet("/board/boardFormEnd")
public class BoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			//있는지 없는 지 확인하기
			request.setAttribute("msg", "잘못된 요청입니다,.관리자한테 물어보세요");
			request.setAttribute("loc", "/board/boardForm");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		//가장 먼저 기본경로 갖고오기
		String saveDir = getServletContext().getRealPath("/");
		
		saveDir += "/upload/board";
		int maxSize=1024*1024*1024;
		
		MultipartRequest mr=new  MultipartRequest(request, saveDir,maxSize,"UTF-8",new MyFileRenamePolicy());
		
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String content =mr.getParameter("content");
		String rename=mr.getFilesystemName("up_file");
		String oriname=mr.getOriginalFileName("up_file");
		//내부적으로 orifile와 rename을 갖고 있는것
		
		Board b= new Board();
		b.setBoard_title(title);
		b.setBoard_writer(writer);
		b.setBoard_content(content);
		b.setBoard_rename_filename(rename);
		b.setBoard_original_filename(oriname);
		
		int result = new BoardService().insertBoard(b);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0) {
			 loc="/board/boardView?Board_no="+result;
			 msg="게시글 등록성공!";
		}else {
			loc="/board/boardForm";
			msg="게시글등록실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);	
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
