<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<!-- Page Heading -->
<div class="heading">
	<h1 class="h3 mb-4 text-gray-800 regtext border-left-primary">글쓰기</h1>
</div>

<div class="modal" id='registerModal' tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">등록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>등록이 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="movePage()" >목록으로</button>
      </div>
    </div>
  </div>
</div>

<div class="twContainer">
	<div class="input-group mb-3  titlebox">
		<input type="text" class="form-control" name='title'
			placeholder='제목을 입력하세요' value='<c:out value=''/>'
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default">
	</div>
	<div class="input-group mb-3  writerbox">
		<input type="text" class="form-control" name='writer'
			placeholder='작성자를 입력하세요' value='<c:out value=''/>'
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default">
	</div>
</div>
<div class="input-group mb-3 inputbox">
	<textarea class="contentbox form-control" name='content' placeholder="내용을 입력하세요" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"><c:out value=""></c:out></textarea>
</div>

<div class="d-grid gap-2 d-md-block buttonbox">
  <button class="btn btn-success registerBtn" type="button">등록</button>
  <button class="btn btn-primary listBtn" type="button">목록</button>
</div>

<form class="actionForm" action="/board/list" method="get">
	<input type='hidden' name='page' value='1'>
</form>

<script>
function movePage(){
	self.location = "/board/list";
}

document.querySelector(".registerBtn").addEventListener("click",function(e){
	
const title = document.querySelector("input[name='title']").value;
/* console.log(title) */
const writer = document.querySelector("input[name='writer']").value;
/* console.log(writer) */
const content = document.querySelector(".contentbox[name='content']").value;
/* console.log(content) */
	
	e.preventDefault();
	function sendAjax(data){
		/* console.log("sendAjax....", data) */
		
		return fetch("/board/register", {method:"post",
			headers: {'Content-Type' : 'application/json'},
			body: JSON.stringify(data)})
			.then(res => {
				if(!res.ok){
					throw new Error(res);
				}
				return res.text();
			})
			.catch(error => {
				console.log("catch....")
				console.log(error)
				return error
			})
	}

	const data = {title: title, content: content, writer: writer}
	/* console.log(data) */
	const fnResult = sendAjax(data);

	fnResult.then(result => {
		/* console.log("result: "+ result) */
		$("#registerModal").modal('show')
	})
},false);

document.querySelector(".listBtn").addEventListener("click", function(e){
	document.querySelector(".actionForm").submit();
},false);
</script>

<%@include file="../includes/footer.jsp" %>            