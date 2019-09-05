<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*서버가 갖고온거  */
	boolean unable=(boolean)request.getAttribute("unable");
	String userId=request.getParameter("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복 아이디체크</title>
	<style>
		div#checkid-container{
			text-align:center;
			padding-top:50px;
		}
		span#duplicated{
			color:red;font-weight:bold;
		}
	</style>
</head>
<body>
	<div id="checkid-container">
		<%if(unable) {%>
			<!--아이디가 중복된다면 여기로 넘어옴  -->
			[<span><%=userId %></span>]는 이미 사용중입니다.
			<br><br>
			<form action='<%=request.getContextPath() %>/checkIdDuplicate' 
			name="checkIdDuplicate" placeholder="아이디중복검사" method="post">
				<input type="text" name="userId">
			</form>
				<button onclick="checkId();">중복검사</button>
		<%}else{ %>
			[<span><%=userId %></span>]는 사용가능합니다.
			<br><br>
			<!-- <button onclick='self.close();'>닫기</button>-->	
			<button onclick='fn_close();'>닫기</button>
		<%} %>
	</div>
	<script>
		function checkId(){
			var id=checkIdDuplicate.userId.value;
			if(!id || id.trim().length<4){
				alert("아이디 4글자 이상 입력");
				return;
			}				
			checkIdDuplicate.submit();
		}
		//자식창에서 부모창에 
		function fn_close(){
			opener.document.getElementById("userId_").value='<%=userId%>';
			opener.document.getElementById("password_").focus();
			self.close();
		}
	</script>

</body>
</html>