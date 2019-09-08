<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<style>
input#btn-add{top:10%; float:right;}

div#text-first{float: left; padding: 2%; width: 33%;}
div#text-second{float: left; padding: 2%; width: 33%;}
div#text-third{float: left; padding: 2%; width: 33%;}

div#text-first-F{float: Right; padding: 2%; width: 33%;}
div#text-second-S{float: Right; padding: 2%; width: 33%;}
div#text-third-T{float: Right; padding: 2%; width: 33%;}



</style>

	<section id="board-container">
		<h2>푸드코너 </h2>
			<input type="button" value="글쓰기" id="btn-add" onclick="fn_boardForm()">
		<script>
		function fn_boardForm(){
			location.href='<%=request.getContextPath()%>/board/foodForm'
		}
		</script>
	<table id="tbl-board" >
	<div id="text-first">
		<tr>
			<th colspan="2" text-align="left">번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
	</div>
	<div id="text-second">
		<tr>
			<th colspan="2" text-align="left">번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
	</div>
	<div id="text-third">
		<tr>
			<th colspan="2" text-align="left">번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
	<div id="text-first-F">
		<tr>
			<th colspan="2" text-align="left">번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
	</div>
	<div id="text-second-S">
		<tr>
			<th colspan="2" text-align="left">번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
	</div>
	<div id="text-third-T">
		<tr>
			<th colspan="2" text-align="left">번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
	</div>
	
	
	
	
	
	
	
	
	</table>
		
		<!--pageBar도 있어야함-->
	
	</section>

<%@ include file="/views/common/footer.jsp"%>	