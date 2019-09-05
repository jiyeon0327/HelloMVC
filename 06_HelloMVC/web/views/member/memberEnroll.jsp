<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
    <section id="enroll-container">
        <h2>회 원 가 입</h2>
        <form action="<%=request.getContextPath()%>/memberEnrollEnd" method="post" onsubmit="return enroll_validate();">
            <table>
                <tr>
                    <th>아이디</th>
                    <td>
                        <input type="text" placeholder="4글자 이상" name="userId" id="userId_" required/>
						<input type="button" value="중복검사" onclick="checkIdDuplicate();"/>
						<span id="result"></span>
                    </td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td>
                        <input type="password" name="password" id="password_" required>
                    </td>
                </tr>
                <tr>
                    <th>비밀번호 확인</th>
                    <td>
                        <input type="password" id="passwordck" required/>     
                    </td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>
                        <input type="text" name="userName" id="userName" required/>
                    </td>
                </tr>
                <tr>
                    <th>나이</th>
                    <td>
                        <input type="number" name="age" id="age" />
                    </td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>
                        <input type="email" name="email" id="email" placeholder="abc@xyz.com"/>
                    </td>
                </tr>
                <tr>
                    <th>핸드폰</th>
                    <td>
                        <input type="tel" placeholder="(-제외)01012345678" name="phone" id="phone" maxlength="11" required/>
                    </td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>
                        <input type="text" name="address" id="address"> 
                    </td>
                </tr>
                <tr>
                    <th>성별</th>
                    <td>
                        <input type="radio" name="gender" id="gender0" value="M">
                        <label for="gender0">남</label>
                        <input type="radio" name="gender" id="gender1" value="F">
                        <label for="gender1">여</label>
                    </td>
                </tr>
                <tr>
                    <th>취미</th>
                    <td>
                        <input type="checkbox" name="hobby" id="hobby0" value="운동">
                        <label for="hobby0">운동</label><br/>
                        <input type="checkbox" name="hobby" id="hobby1" value="등산">
                        <label for="hobby1">등산</label><br/>
                        <input type="checkbox" name="hobby" id="hobby2" value="독서">
                        <label for="hobby2">독서</label><br/>
                        <input type="checkbox" name="hobby" id="hobby3" value="게임">
                        <label for="hobby3">게임</label><br/>
                        <input type="checkbox" name="hobby" id="hobby4" value="여행">
                        <label for="hobby4">여행</label><br/>
                    </td>
                </tr>
            </table>
            <input type="submit" value="가입"/>
            <input type="reset" value="취소"/>

        </form>
        <script>
       
       	function enroll_validate(){
       		//정규표현식을 적용해서 유효성 검사(아이디나 비밀번호)
       		//아이디가 4글자이상 되면 넘길예정
       		var id=$('#userId_').val().trim();
       		if(id.length<4){
       			alert("아이디는 4글자 이상 입력!");
       			$('#userId_').focus();
       			return false;
       		}
       		return true;
       	}
       	
       	$(function(){
       		$('#userId_').keyup(function(){
       			if($(this).val().trim().length>=4){
       				$.ajax({
       					url:"<%=request.getContextPath()%>/checkAjaxId",
  					data:{userId:$(this).val()},
  					success:function(data){
  					console.log(data);	
	  					if(!data){
	  						$("#result").html("사용할수 있는 아이디입니다.").css("color","green");
	  					}else{
	  						$("#result").html("사용할수 없는 아이디입니다.").css("color","red");
	  					}
  					}
       			});
       		}
       			
       	});
   	});
       	
       	
       	
       	
       	$(function(){
       		$("#passwordck").blur(function(){
       			var pw=$("#password_").val().trim();
       			var pwck=$(this).val().trim();
       			if(pw!=pwck){
       				alert("패스워드가 일치하지 않습니다.");
       				$("#password_").focus();
       				$(this).val("");
       				$("#password_").val("");
       			}
       		});
       	});
        	
       	function checkIdDuplicate() {
       		if($('#userId_').val().trim().length>=4){
       			var url="<%=request.getContextPath()%>/checkIdDuplicate?userId="+$('#userId_').val();
				//여기서는 인풋태그이용=>이게 키 userId 뒤에는 value값
        		var title ="checkId";
        		var status="left=500px,top=100px,width=300px,height=300px,scrollbars=yes";
        		var popup=open(url,title,status);
        		//아이디 중복된 값이 있는지 확인하기 위해서
        		//데이터를 다른페이지에 넘기는 방법:quertstring방식 (?키=value),form방식(input태그방식으로)
        		
			}else{
				alert("아이디 4글자 이상 입력");
			}
       	}
      

       	
        	
        </script>

    </section>
<%@ include file="/views/common/footer.jsp" %>