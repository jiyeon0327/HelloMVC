<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/views/common/header.jsp" %>
<%@ page import="com.kh.notice.model.vo.Notice" %>

<% Notice n = (Notice)request.getAttribute("notice"); %>
 
 <style>
	section#notice-container{width:600px; margin:0 auto; text-align:center;}
	section#notice-container h2{margin:10px 0;}
	table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style> 

<section id="notice-container">
	<h2>공지사항</h2>
	<form action ="<%=request.getContextPath()%>/notice/noticeUpdateEnd?notice_No=<%=n.getNotice_No()%>" method="post" enctype="multipart/form-data">
	<table id="tbl-notice">
	
	<tr>
		<th>제 목</th>
			<td>
				<input type="text" name="title" value="<%= n.getNotice_Title() %>">
			</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="writer" value="<%= n.getNotice_WRITER() %>" readonly>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" name="up_file">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="5" cols="50" name="content"><%= n.getNotice_contenet() %></textarea>
			</td>
		</tr>
		<tr>
			<th colspan="2" style="text-align: center;">
			<input type="submit" value="수정완료">
			<input type="button" value="취소하기" onclick="location.href='<%=request.getContextPath() %>/notice/noticeView?notice_No=<%= n.getNotice_Title() %>'">
			</tr>
			</th>
		</tr>
		</table>
		</form>
	</section>

<%@ include file = "/views/common/footer.jsp" %>
	
	
	
	
	
	
	
	
	
	
	</table>
	
	
	
	
	
	
	
	
	
	
	</form>










</section>
