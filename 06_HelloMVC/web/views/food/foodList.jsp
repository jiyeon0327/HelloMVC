<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<%@ page import="java.util.List, com.kh.food.model.vo.Food"%>

<% 
	List<Food> list=(List<Food>)request.getAttribute("list");
	int count = 0;
%>

<style>
h2{color:gold}
table#big-tbl{ margin-left: auto; margin-right: auto; border-spacing: 50px 20px}
table#sml-tbl{ border: 1px solid red;}
img.foodimg{width: 300px; height: 200px;}
p.viewCount{ text-align: right; margin-top: -2px}
span.foodTag{ font-style:italic; }

</style>
	<section id="food-container">
	<MARQUEE behavior=alternate><h2 style="width:100; height:50;">FOOD ZONE</h2></MARQUEE>
	
	<table id="big-tbl">
		<% for(Food f:list)  {%>
			<%if(count== 0 || count == 4) { %><tr> <% } %>
				<td>
					<table id="sml-tbl">
						<tr>
							<td><img class="foodimg" src="<%=request.getContextPath()%>/images/food/<%=f.getBoard_Thumbnail()%>"></td>
						</tr>  
						<tr>
							<td>상호명 : <%=f.getBoard_Title()%></td>
						</tr>
						<tr>
							<td>평점 : <%=f.getBoard_Grade()%></td>
						</tr>
					</table>
					<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
					<p class="viewCount">조회 : <%= f.getBoard_Count() %>회</p>
				</td>
				<% count++; %>
			<%if(count== 4 || count == 8) { %><tr> <% } %>
		<% } %>
	</table>
	
	</section>

<%@ include file="/views/common/footer.jsp"%>	