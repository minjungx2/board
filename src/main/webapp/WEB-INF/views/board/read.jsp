<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<div class="container">
	<div class="readbtnbox">
		<button class="btn btn-warning modifyBtn" type="button">수정/삭제</button>
		<button class="btn btn-primary listBtn" type="button">목록</button>
	</div>
	<div class="card postcard">
		<div class="card-header card-header-color">
			<h3>${board.title }</h3>
			<p class="card-text">작성자: ${board.writer }</p>
		</div>
		<div class="card-body postcard-body">
			<p class="card-text">${board.content }</p>
		</div>
	</div>
</div>


<form class="actionForm" action="/board/modify" method="get">
	<input type='hidden' name='page' value='${pageDTO.page }'>
	<input type='hidden' name='perSheet' value='${pageDTO.perSheet }'>
	<input type='hidden' name='type' value='${pageDTO.type }'> 
	<input type='hidden' name='keyword' value='${pageDTO.keyword }'>
</form>

<script>
const docQuery = document.querySelector.bind(document);
const actionForm = document.querySelector(".actionForm");

docQuery(".listBtn").addEventListener("click", function(e){
	e.preventDefault();
	actionForm.setAttribute("action", "/board/list");
	actionForm.submit();
},false);

docQuery(".modifyBtn").addEventListener("click",function(e){
	e.preventDefault();
	actionForm.innerHTML = actionForm.innerHTML + "<input type ='hidden' name='bno' value='"+${board.bno}+"'>";
	actionForm.submit();
},false);
</script>

<%@include file="../includes/footer.jsp"%>