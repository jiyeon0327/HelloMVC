package com.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.admin.model.service.AdminService;
import com.kh.member.model.vo.Member;
import com.kh.memeber.model.service.MemberService;

/**
 * Servlet implementation class MemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//검색한 값을 받아오기
		//getParameter는 name으로만 값을 받아서 갖고 올수 있음
		//request.setAttribute에서 name으로 썻던 값을 getAttribute에서 name을 받음
		String type=request.getParameter("searchType");//member.jsp의 name값
		String keyword=request.getParameter("searchKeyword");
		
		System.out.println(type+" "+keyword);
		
	
		
		int cPage;//현재 페이지 
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;//처리내용
		}
		int numPerPage=request.getParameter("numPerPage")!=null?Integer.parseInt(request.getParameter("numPerPage")):5;
		//cPage,numPerPage로 구현 가능
		
		//데이터 불러오기
		AdminService service=new AdminService();//예를 들어 여자 데이터의 총 개수에서 나눠서 필터링 하는거니까 매개변수에 type,keyword(where절)를 쓴것
		//필터링 된 애들을 기준으로 나눠야 함
		List<Member> list=service.selectSearch(cPage, numPerPage,type,keyword);
		//페이징 처리할때 보여줄 갯수를 정하기 위해서 cPage,numPerPage를 쓴것임(5개씩 잘라서 가져와야 하니까) 
		
		
		int totalData=service.selectSearchCount(type, keyword);
		
		
		//먼저 셋팅해주기
		String pageBar="";
		int totalPage=(int)Math.ceil((double)totalData/numPerPage);
		int pageBarSize=10;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		//pageBar소스 코드 작성
		//이전 만들기
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/admin/memberFinder?cPage="+(pageNo-1)
					+"&searchType="+type+"&searchKeyword="+keyword+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		//페이지 링크 구하기
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/admin/memberFinder?cPage="+pageNo
						+"&searchType="+type+"&searchKeyword="+keyword+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
				
			}
			pageNo++;
		}
		//다음 만들기
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+=pageBar+="<a href='"+request.getContextPath()+"/admin/memberFinder?cPage="+pageNo
					+"&searchType="+type+"&searchKeyword="+keyword+"&numPerage="+numPerPage+"'>[다음]</a>";
			
		}
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.setAttribute("keyword", keyword);//memberlist.jsp에 다시 보낸거//값이 남아 있께 하려고
		request.setAttribute("type", type);
		request.setAttribute("numPerPage", numPerPage);
		
		request.getRequestDispatcher("/views/admin/memberList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
