<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>


<%
	Member loginMember=(Member)session.getAttribute("loginMember");
	Cookie[] cookies=request.getCookies();//쿠키에 저장된거 다른 변수에 저장시킴
	String saveId=null;
	if(cookies!=null){
		for(Cookie c:cookies){
			//쿠키는 자동으로 c에 getName()-->키값 /getValue()-->들어있는 값  를 갖고 있음
			System.out.println("Key : "+c.getName());
			System.out.println("value :"+c.getValue());
			if(c.getName().equals("saveId")){
					saveId=c.getValue();
			} 
			
		}
	 } 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMVC</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" type="text/css">
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<!--header  -->
	<header>
	<h1>Hello MVC</h1>
	
	<%if(loginMember==null) {%>
	
	<div class="login-container">
		<!-- 호출할 페이지 또는 참고하는 파일의 위치를 path를 절대 경로로 주는 경우에 contextpath가 변경될 수도 있다는 가정하에..
		직접적으로 명시하지 않고 request 객체에 저장된 contextpath()를 참조하여 경로 명시하는 방법 -->
		<form action="<%=request.getContextPath()%>/login" method="post" onsubmit="return validate();">
		<!--validate():유효성검사  -->
		<table>
			<tr>
				<td><input type="text" name="userId" id="userId" placeholder="아이디입력"
				value='<%=saveId!=null?saveId:"" %>'></td>
				<td></td>
			</tr>
			<tr>
				<td><input type="password" name="password" id="password" placeholder="비밀번호입력"></td>
				<td><input type="submit" value="로그인"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="saveId" id="saveId"<%=saveId!=null?"checked" :"" %>>아이디저장<label for="saveId">
					</label>&nbsp;&nbsp;
					<input type="button" value="회원가입" onclick="location.href='<%=request.getContextPath()%>/memberEnroll';">
					<!-- memberEnroll이라는 서블릿 주소로 이동할거야. -->
				</td>

			</tr>
		</table>
		</form>
	</div>
	
	<%} else { %>
	<div class="login-container">
		<table id="logged-in">
		<tr>
			<td><%=loginMember.getUserName() %>님, 어서오세요.</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="내 정보 보기" onclick="location.href='<%=request.getContextPath()%>/myPage?userId=<%=loginMember.getUserId()%>'"/>
				<input type="button" value="로그아웃" onclick="location.href='<%= request.getContextPath()%>/logout';"/>
				<!--href='절대경로를 쓸것!(project~부터 시작하는 곳부터)'  -->
			
			</td>
		</tr>
		
		</table>
	</div>
	<% } %>
	
	<!-- 메인메뉴 -->
	<nav>
		<ul class="main-nav">
			<li class="home">
				<a href="<%=request.getContextPath() %>/">HOME</a>
			</li>
			<li id="notice">
				<a href="<%=request.getContextPath()%>/notice/noticeList">공지사항</a>
			</li>
			<li id="board">
				<a href="#">게시판</a>
			</li>
			<li id="gallary">
				<a href="#">사진게시판</a>
			</li>
			<%if(loginMember!=null&&loginMember.getUserId().equals("admin")) {%>
			<li id="admin-member">
				<a href="<%=request.getContextPath()%>/admin/memberList">회원관리
				<span class="animate_Line"></span>
				</a>
			</li>
			<%} %>
		</ul>

	</nav>

	</header>
	
	<script>
		function validate(){
			if($("#userId").val().trim().length<4){
				alert("아이디는 4글자 이상 입력하세요.");
				$("#userId").focus();
				return false;
			}
			if($("#password").val().trim().length==0){
				alert("비밀번호를 입력하세요!");
				$("#password").focus();
				return false;
			}
			return true;
			
		}
		
		
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	