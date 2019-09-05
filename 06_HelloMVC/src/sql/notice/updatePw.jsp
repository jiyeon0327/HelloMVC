<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId=request.getParameter("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<!--반드시 절대경로로 제이쿼리 만들기-->
<script src='<%=request.getContextPath() %>/js/jquery-3.4.1.min.js'></script>
    <style>
    div#updatePassword-container{
        background:skyblue;
    }
    div#updatePassword-container table {
        margin:0 auto;
        border-spacing: 20px;
    }
    div#updatePassword-container table tr:last-of-type td {
        text-align:center;
    }
    </style>
</head>
<body>
	<div id="updatePassword-container">
         <form action="<%=request.getContextPath()%>/updatePasswordEnd" name="updatePwdFrm" method="post">
            <table>
                <tr>
                    <th>현재비밀번호</th>
                    <td><input type="password" name="cPw" id="cPw"></td>
                </tr>
                <tr>
                    <th>새비밀번호</th>
                    <td><input type="password" name="nPw" id="nPw"></td>
                </tr>
                <tr>
                    <th>비밀번호확인</th>
                    <td><input type="password" id="ckPw"></td>
                </tr>
                <tr>
              
                    <td colspan="2">
                        <input type="submit" onclick="return password_validate();" value="변경">
                        <input type="button" onclick="self.close();" value="닫기">
                    </td>
                </tr>
                
            </table>
                <input type="hidden" value="<%=userId%>" name="userId"/>
        </form>
	 	
	</div>
	<script>
		$(function(){
			$('#ckPw').blur(function(){
				var pw=$('#nPw').val().trim();
				var pwck=$(this).val().trim();
				if(pw!=pwck){
					alert("비밀번호가 일치하지 않습니다.");
					$(this).val("");
					$("#nPw").focus();
				}
			});
		});
		function password_validate(){
			var current=$("#cPw").val().trim();
			if(current.length<=0){
				alert("현재 password가 비어있습니다.");
				return false;
			}
			return true;
		}
	</script>

</body>
</html>