<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>


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
	<table id="big-tbl" style="border: 1px solid;">
		<tr><!--1  -->
			<td><!--1행1열  -->
				<table id="sml-tbl">
					<tr>
						<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/마부육전.jpg"></td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td>평점</td>
					</tr>
				</table>
				<span class="foodTag">
					<a href="#">#육회비빔밥</a> 
					<a href="#">#역삼역</a> 
					<a href="#">#회</a>
				</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
			<td><!--1행2열  -->
				<table id="sml-tbl">
					<tr>
						<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/산들애.jpg"></td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td>평점</td>
					</tr>
				</table>
				<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
			<td><!--2행1열  -->
			<table id="sml-tbl">
				<tr>
					<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/전주이씨24시콩나물국밥.jpg"></td>
				</tr>
				<tr>
					<td>이름</td>
				</tr>
				<tr>
					<td>평점</td>
				</tr>
			</table>
				<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
			<td><!--2행1열  -->
			<table id="sml-tbl">
				<tr>
					<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/전주이씨24시콩나물국밥.jpg"></td>
				</tr>
				<tr>
					<td>이름</td>
				</tr>
				<tr>
					<td>평점</td>
				</tr>
			</table>
				<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
		</tr>
		<tr>
			<td><!--2행1열  -->
			<table id="sml-tbl">
				<tr>
					<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/전주이씨24시콩나물국밥.jpg"></td>
				</tr>
				<tr>
					<td>이름</td>
				</tr>
				<tr>
					<td>평점</td>
				</tr>
			</table>
				<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
			
			<td><!--2행2열  -->
				<table id="sml-tbl">
					<tr>
						<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/이태리부대찌개.jpg"></td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td>평점</td>
					</tr>
				</table>
				<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
			<td><!--3행 1열 -->
				<table id="sml-tbl">
					<tr>
						<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/이화수육개장.jpg"></td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td>평점</td>
					</tr>
				</table>
				<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
			<td><!--3행 2열  -->
				<table id="sml-tbl">
					<tr>
						<td><img class="foodimg" alt="" src="<%=request.getContextPath()%>/images/food/성북동뼈해장국.jpg"></td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td>평점</td>
					</tr>
				</table>
				<span class="foodTag">#육회비빔밥 #역삼역 #회</span>
				<p class="viewCount">조회 : 104회</p>
			</td>
		</tr>	
	</table>
</section>

<%@ include file="/views/common/footer.jsp"%>	