<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<!-- Page Heading -->
<div class="heading">
	<h1 class="h3 mb-4 text-gray-800 regtext border-left-primary">게시판</h1>
</div>

<table class="table">
	<thead class="table-dark">
		<tr>
			<th scope="bno"></th>
			<th scope="title">제목</th>
			<th scope="writer">작성자</th>
			<th scope="regdate">작성일</th>
		</tr>
	</thead>
	<c:forEach items="${list }" var="board">
		<tbody>
			<tr>
				<td><c:out value="${board.bno }"></c:out></td>
				<td><a class='list' href="<c:out value="${board.bno }"/>"><c:out value="${board.title }" /></td>
				<td><c:out value="${board.writer }"></c:out></td>
				<td><c:out value="${board.regdate }"></c:out></td>
			</tr>
		</tbody>
	</c:forEach>
</table>

<div class='selectContainer'>
	<div class='selectDiv'>
		<select name="selectType" aria-controls="dataTable" class="selectType custom-select custom-select-sm form-control form-control-sm">
			<option value='t' ${pageDTO.type == "t"?"selected":"" }>제목</option>
			<option value='c' ${pageDTO.type == "c"?"selected":"" }>내용</option>
			<option value='w' ${pageDTO.type == "w"?"selected":"" }>작성자</option>
			<option value='tc' ${pageDTO.type == "tc"?"selected":"" }>제목+내용</option>
			<option value='tw' ${pageDTO.type == "tw"?"selected":"" }>제목+작성자</option>
			<option value='tcw' ${pageDTO.type == "tcw"?"selected":"" }>제목+내용+작성자</option>
		</select>
	</div>

	<div class="input-group searchBar">
		<input name='search' class="form-control search-keyword" type='text' placeholder='검색어를 입력하세요' value="<c:out value='${pageDTO.keyword }'/>">
		<button class='searchBtn btn btn-primary searchBtn'>검색</button>
	</div>

	<div class="register">
		<button class="btn btn-success registerBtn" type="button">글쓰기</button>
	</div>
</div>

<div class="pageContainer">
	<nav aria-label="...">
		<ul class="pagination">
			<c:if test="${pageMaker.prev }">
				<li class="page-item"><a class="page-link" href="${pageMaker.start-1 }" tabindex="-1">Previous</a></li>
			</c:if>
			<c:forEach begin="${pageMaker.start }" end="${pageMaker.end }"
				var="num">
				<li class="page-item ${num == pageMaker.pageDTO.page?"active":"" }"><a class="page-link" href="${num }">${num }</a></li>
			</c:forEach>
			<c:if test="${pageMaker.next }">
				<li class="page-item"><a class="page-link" href="${pageMaker.end+1 }">Next</a></li>
			</c:if>
		</ul>
	</nav>
</div>

<form class="actionForm" action="/board/list" method="get">
	<input type='hidden' name='page' value='${pageDTO.page }'>
	<input type='hidden' name='perSheet' value='${pageDTO.perSheet }'>
	<input type='hidden' name='type' value='${pageDTO.type }'>
	<input type='hidden' name='keyword' value='${pageDTO.keyword }'>
</form>

<script>
document.querySelector(".pagination").addEventListener("click",function(e){
	e.preventDefault();
	const target = e.target;
	const pageNum = target.getAttribute("href");
	/* console.log(pageNum); */
	document.querySelector(".actionForm input[name='page']").value = pageNum;
	document.querySelector(".actionForm").submit();
},false);

document.querySelector(".searchBtn").addEventListener("click",function(e){
	e.preventDefault();
	const stype = document.querySelector(".selectType");
	/* console.log(stype); */
	const idx = stype.selectedIndex;
	/* console.log(idx); */
	const type = stype[idx].value;
	/* console.log(type); */
	document.querySelector(".actionForm input[name='page']").value = 1;
	document.querySelector(".actionForm input[name='type']").value = type;
	document.querySelector(".actionForm input[name='keyword']").value = document.querySelector("input[name='search']").value;
	document.querySelector(".actionForm").submit();	
},false);

document.querySelector("input[name='search']").addEventListener("keypress",function(e){
	if (e.key === 'Enter') {  
	e.preventDefault();
	const stype = document.querySelector(".selectType");
	/* console.log(stype); */
	const idx = stype.selectedIndex;
	/* console.log(idx); */
	const type = stype[idx].value;
	/* console.log(type); */
	document.querySelector(".actionForm input[name='page']").value = 1;
	document.querySelector(".actionForm input[name='type']").value = type;
	document.querySelector(".actionForm input[name='keyword']").value = document.querySelector("input[name='search']").value;
	document.querySelector(".actionForm").submit();	
    }
},false);

document.querySelectorAll(".list").forEach(a => {
	a.addEventListener("click",function(e){
	e.preventDefault();
	const bno = e.target.getAttribute("href");
	/* console.log(bno); */
	const actionForm = document.querySelector(".actionForm");
	actionForm.setAttribute("action", "/board/read");
	actionForm.innerHTML += "<input type='hidden' name='bno' value='"+bno+"'>";
	actionForm.submit();
    },false);
});

document.querySelector(".registerBtn").addEventListener("click", function(e){
	const actionForm = document.querySelector(".actionForm");
	actionForm.setAttribute("action", "/board/register");
	actionForm.submit();
},false);
</script>

<%@include file="../includes/footer.jsp"%>