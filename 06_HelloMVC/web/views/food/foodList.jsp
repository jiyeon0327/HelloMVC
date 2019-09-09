<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<style>


table#tbl-1{width:25%; margin:10%; border:1px solid gold; text-align:center; float: left;}
table#tbl-2{width:25%; margin:10%; border:1px solid gold; text-align:center; float: left;}
table#tbl-3{width:25%; margin:10%; border:1px solid gold; text-align:center; float: left;}
table#tbl-4{width:25%; margin:10%; border:1px solid gold; text-align:center; float: left;}
table#tbl-5{width:25%; margin:10%; border:1px solid gold; text-align:center; float: left;}
table#tbl-6{width:25%; margin:10%; border:1px solid gold; text-align:center; float: left;}

h2{background:gold;}
</style>

	<section id="board-container">
		<MARQUEE behavior=alternate><h2 width="100" height="50">FOOD ZONE</h2></MARQUEE>

			<input type="button" value="글쓰기" id="btn-add" onclick="fn_boardForm()">
		<script>
		function fn_boardForm(){
			location.href='<%=request.getContextPath()%>/board/foodForm'
		}
		</script>

	
		<table id="tbl-1" >
		<tr>
			<th >번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2" weight="200px" height="250px">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
		
		</table>
		<th>조회수</th>
		<th>리뷰</th>
		
		<table id="tbl-2">
		<tr>
			<th >번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2" weight="200px" height="250px">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
		
		</table>
		<table id="tbl-3">
		<tr>
			<th >번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2" weight="200px" height="250px">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
		</table>
		<table id="tbl-4">
		<tr>
			<th >번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2" weight="200px" height="250px">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
		</table>
		
		<table id="tbl-5">
		<tr>
			<th >번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2" weight="200px" height="250px">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
		</table>
		
		<table id="tbl-6">
		<tr>
			<th >번호</th>
   		</tr>
   		 <tr>
    		<td colspan="2" weight="200px" height="250px">그림</td>
   		</tr>
     	<tr>
			<td>제목</td>
			<td>평점</td>
    	</tr>
    	<tr>
			<td>조회수</td>
			<td>리뷰</td>
		</tr>
		</table>
		
		
	

		
		<!--pageBar도 있어야함-->
	
	</section>

<%@ include file="/views/common/footer.jsp"%>	