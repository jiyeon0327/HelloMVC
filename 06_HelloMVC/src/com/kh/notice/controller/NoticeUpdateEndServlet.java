package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/notice/noticeUpdateEnd")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!(ServletFileUpload.isMultipartContent(request))){
			request.setAttribute("msg", "공지사항작성오류![form:entype 관리자에게 문의하세요]");
			request.setAttribute("loc", "/");//msg로 연결시켜주기
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/");
		
		String saveDir = root+"/upload/notice";
		int maxSize = 1024*1024*10;
		
		MultipartRequest mr = new MultipartRequest(request,saveDir,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String cotent = mr.getParameter("content");
		int notice_No = Integer.parseInt(mr.getParameter("notice_No")); 
		String fileName = mr.getFilesystemName("up_file");
		
		Notice n = new Notice();
		n.setNotice_Title(title);
		n.setNotice_WRITER(writer);
		n.setNotice_contenet(cotent);
		n.setFilepath(fileName);
		n.setNotice_No(notice_No);
		
		int result= new NoticeService().updateNotice(n);
		
		if(result>0) {
			request.getRequestDispatcher("/notice/noticeClick?notice_No="+notice_No).forward(request, response);
		}else {
			request.setAttribute("msg", "글 등록을 실패하였습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
