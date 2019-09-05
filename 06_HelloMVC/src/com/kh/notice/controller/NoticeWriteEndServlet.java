package com.kh.notice.controller;

import java.io.File;
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
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/notice/noticeWriterEnd")
public class NoticeWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//멀티 파트 폼:제일 먼저 해야할 일은 파일을 저장하는 로직을 처리해야 하기 때문에 멀티파트 폼 데이터로 들어왔는지 확인부터 할것
		//multipart로 전송되었는지  request를 확인!!!시작
		//방법:ServletFileUpload객체를 이용한다.
		//이객체에 isMultipartContent(request)  ->multipart인지 확인가능함
		if(!ServletFileUpload.isMultipartContent(request)) {
			//맞으면 true고 처리하면 됨
			//예외면 바같으로 빼기
			request.setAttribute("msg","공지사항작성오류![form:enctype 관리자에게 문의하세요]");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common.msg.jsp").forward(request, response);
			return;
		}
		//파일업로드 작성진행 순서
		//1.파일을 저장할 서버의 실제경로(파일시스템상 경로)를 불러온다.
		//2.파일 저장 최대 크기를 결정해야한다.
		//3.cos.jar에서 지원하는 MultipartRequest객체를 생성한다.
		//끝!
		
		//실제경로 갖고오기
		String root=getServletContext().getRealPath("/");//가장 최상이 되는 root경로에서 context경로까지 불러온다.(web)까지
		String saveDir=root+"/upload/notice";//실제로 파일을 저장할 위치경로를 지정해준다.
		
		
		
		//만약 위에서 저장할 파일이 없다면 
		//mkdir는 폴더 아래 하나만
		//mkdirs은 아래 자식이 있어도 다 만들어도 가능함
		
		//하지만 우리는 강제로 폴더를 만들거임(upload/notice폴더에서 )
		String saveDir2=root+File.separator+"upload"+File.separator+"notice";
		
		//업로드 파일크기 설정
		int maxSize=1024*1024*10;//10메가까지 받을거임
		
		//MultipartRequest객체생성
		//객체 생성 시 매개변수가 있는 생성자를 이용
		//매개변수를 보면
		//1 : request값
		//2 : 파일 저장 경로를 받기
		//3 : 파일 최대 크기 
		//4 : 인코딩 값 (UTF-8)
		//5 : rename정책(파일이름) 중복되면 안되기 때문!에 rename정책을 통해 이름을 다시 정하게 하기!왜냐면 같은이름이면 덮어쓰여지기 때문
		MultipartRequest mr=new MultipartRequest(
							request,
							saveDir,
							maxSize,
							"UTF-8",
							new DefaultFileRenamePolicy()
					);
		
		//
		String title=mr.getParameter("title");
		String writer=mr.getParameter("writer");
		String content=mr.getParameter("content");
		String fileName=mr.getFilesystemName("up_file");//실질적으로 저장된 파일명
		//제대로 불러오는지 확인해보기
		System.out.println("title : "+title);
		System.out.println("writer : "+writer);
		System.out.println("content : "+content);
		System.out.println("up_file : "+fileName);
		
		
		Notice n=new Notice();
		n.setNotice_Title(title);
		n.setNotice_WRITER(writer);
		n.setNotice_contenet(content);
		n.setFilepath(fileName);
		int result=new NoticeService().insertNotice(n);
		
		
		if(result>0) {
			request.getRequestDispatcher("/notice/noticeList").forward(request, response);
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
