<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	Member m=(Member)request.getAttribute("member");
	/* 1번째 방법 */
	/* String hobby=m.getHobby();
	String[] hobbys=new String[5];
	if(hobby!=null){
		for(String h:hobby.split(",")){
			switch(h){
			case "운동":hobbys[0]="checked" ;break;
			case "등산":hobbys[1]="checked" ;break;
			case "독서":hobbys[2]="checked" ;break; 
			case "게임":hobbys[3]="checked" ;break; 
			case "여행":hobbys[4]="checked" ;break; 
			}
		} 
	}*/
%>
<%@ include file="/views/common/header.jsp"%>
	<section id="enroll-container">
		<h2>회원정보수정</h2>
		<form name="updateMemberFrm" method="post" onsubmit="return update_validate();">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" id="userId" value="<%=m.getUserId()%>" name="userId"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" id="userName" value="<%=m.getUserName()%>" name="userName"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="number" id="age" value="<%=m.getAge()%>" name="age"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" id="email" value="<%=m.getEmail()%>" name="email"></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="tel" id="phone" value="<%=m.getPhone()%>" name="phone"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" id="address" value="<%=m.getAddress()%>" name="address"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<!-- 취미를 어떻게 체크할것인지 방법은 2가지임(1.배열 2.문자열을 찾을수 있는메소드 이용) -->
					<% %>
					<%if(m.getGender()=='M'){%>
					<input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
					<%}else {%>
						<input type="radio" name="gender" id="gender0" value="M" >
						<label for="gender0">남</label>
						<input type="radio" name="gender" id="gender1" value="F" checked>
						<label for="gender1">여</label>
					<%}%>
					</td>
			</tr>
			<%-- <tr>
				<th>취미</th>
				<td>
				<!-- 1번쨰 방법 배열을  이용하는 방법 -->
				<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=hobbys[0] %>>
				<label for="hobby0">운동</label>
				
				<input type="checkbox" name="hobby" id="hobby1"  value="등산" <%=hobbys[1] %>>
				<label for="hobby1">등산</label>

				<input type="checkbox" name="hobby" id="hobby2"  value="독서" <%=hobbys[2] %>>
				<label for="hobby2">독서</label>

				<input type="checkbox" name="hobby" id="hobby3"  value="게임" <%=hobbys[3] %>>
				<label for="hobby3">게임</label>

				<input type="checkbox" name="hobby" id="hobby4"  value="여행" <%=hobbys[4] %>>
				<label for="hobby4">여행</label>
				</td>
			</tr> --%>
			
			<tr>
				<th>취미</th>
				<td>
				<!-- 2번쨰 방법contains 이용하는 방법 //변수선언할 필요가 없고 for문을 돌릴 필요도 없다.-->
				<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=m.getHobby().contains("운동")?"checked":"" %>>
				<label for="hobby0">운동</label>
				
				<input type="checkbox" name="hobby" id="hobby1"  value="등산" <%=m.getHobby().contains("등산")?"checked":"" %>>
				<label for="hobby1">등산</label>

				<input type="checkbox" name="hobby" id="hobby2"  value="독서" <%=m.getHobby().contains("독서")?"checked":"" %>>
				<label for="hobby2">독서</label>

				<input type="checkbox" name="hobby" id="hobby3"  value="게임" <%=m.getHobby().contains("게임")?"checked":"" %>>
				<label for="hobby3">게임</label>

				<input type="checkbox" name="hobby" id="hobby4"  value="여행" <%=m.getHobby().contains("여행")?"checked":"" %>>
				<label for="hobby4">여행</label>
				</td>
			</tr>
		</table>
		
		<input type="button" onclick="fn_updatePassword();" value="비밀번호변경"/> 
		
		<input type="button" onclick="fn_updateMember();" value="정보수정"/> 
		
		<input type="button" onclick="fn_deleteMember();" value="탈퇴"/>
		
		
	</form>
	<script>
		function fn_updatePassword(){
			var url="<%=request.getContextPath()%>/member/updatePassword?userId=<%=m.getUserId()%>";
			var title = 'updatePw';
			var status="lefrt=500px, top=200px, width=400px, height=210px";
			var popup=open(url,title,status);
		}
	
		function update_validate(){
		//정규표현식 작성
			return true;
			
			
			
		}
		function fn_updateMember(){
		//수정된 정보를 서블릿에 전송하기
			updateMemberFrm.action="<%=request.getContextPath()%>/member/memberUpdate";//주소값
			console.log(updateMemberFrm.action);
			updateMemberFrm.submit();//데이터 전송(post방식으로)
			//주소와 맵핑되는 서블릿 만들기
		}
		
		function fn_deleteMember() {
			if(confirm("정말로 삭제하시겠습니까?")){
				updateMemberFrm.action="<%=request.getContextPath()%>/member/memberDelete?userId=<%=m.getUserId()%>";
				updateMemberFrm.submit();
			}
		}
		
	</script>
	</section>
	
	
	
<%@ include file="/views/common/footer.jsp"%>	