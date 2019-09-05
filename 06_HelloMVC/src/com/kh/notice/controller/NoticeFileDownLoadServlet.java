package com.kh.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDownLoadServlet
 */
@WebServlet("/notice/fileDown")
public class NoticeFileDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일 네임 받아오기
		String fileName =request.getParameter("fileName");
		System.out.println(fileName);
		
		//서버에서 저장된 파일이름 client한테 전송!!!
		//1.실제 저장경로에서 파일 불러오기(inputStream)
		//2.파일입출력을 위한 스트림 생성
		//  파일을 불러올때: 서버에서 inputStream을 생성
		//  파일을 보낼때: client=>response에서 스트림 받아서 처리하기
		//3.파일에 대한 인코딩 처리를 한다.(브라우저에 따른 인코딩 처리임)
		//4. 해더 환경설정
		//   설정1.응답 contentType설정(application / octet-stream)
		//   설정2.Content-Disposition설정 : 파일을 어떻게 받을 것인지 설정, 파일명  설정
		//   파일을 어떻게 받을 건지 attachment(다운로드 팝업 open)/inline(브라우저가 열수 있는 확장자면 브라우저에서 open)
		//   filename = 파일명
		//5.outputStream으로 해당 파일 보내기!==>write()!!
		
		String root = getServletContext().getRealPath("/");
		String saveDir = root+"/upload/notice";
		
		//inputStream 생성->파일연결 ,램상으로 갖고올 수 있도록 만드는 것
		File f=new File(saveDir +"/" +fileName);
		BufferedInputStream bi =new BufferedInputStream (new FileInputStream(f));
		
		//보낼 outputStream 생성
		//response에서 받아오기
		//BufferedOutputStream는 전송 속도가 더 빠름, 그 다음 cpu 공간 확보
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		
		//브라우저에 맞춰서 인코딩처리, 파일명
		String reNameFile="";
		boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1 
				|| request.getHeader("user-agent").indexOf("Trident")!= -1;
		//user-agent 브라우저 정보
		//indexof 앞에 있는지(true) 찾고 번호 매개주기
		if(isMSIE) {
			reNameFile=URLEncoder.encode(fileName,"UTF-8").replace("\\+", "%20");
		}else {
			reNameFile=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");//한글파일로 열어도 안깨지게 바이트로 쪼개고, 다시 ISO로 처리		
		}
		//response해더 설정
		response.setHeader("Content-Disposition", "attachment;fileName="+reNameFile);
		//content파일 설정
		response.setContentType("application/octet-stream");//바이너리로 넘어가는 값이 니까 그냥 이렇게 하는거
		
		
		//이제 파일전송
		int read=-1;
		while((read=bi.read())!=-1){
			bos.write(read);
		}
		bos.close();
		bi.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
