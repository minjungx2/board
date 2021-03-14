<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<!-- Page Heading -->
<div class="heading">
	<h1 class="h3 mb-4 text-gray-800 regtext border-left-primary">게시글 수정</h1>
</div>

<div class="modal" id='deleteModal' tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>삭제가 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="movePage()" >목록으로</button>
      </div>
    </div>
  </div>
</div>

<div class="modal" id='modifyModal' tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>수정이 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="movePage()" >목록으로</button>
      </div>
    </div>
  </div>
</div>

<div class="twContainer">
	<div class="input-group mb-3">
		<input type="hidden" class="form-control" name='bno'
			value='<c:out value='${board.bno }'/>' aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default">
	</div>
	<div class="input-group mb-3  titlebox">
		<input type="text" class="form-control" name='title'
			placeholder='제목을 입력하세요' value='<c:out value='${board.title }'/>'
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default">
	</div>
	<div class="input-group mb-3  writerbox">
		<input type="text" class="form-control" name='writer'
			placeholder='작성자를 입력하세요' value='<c:out value='${board.writer }'/>'
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default" readonly>
	</div>
</div>
<div class="input-group mb-3 inputbox">
	<textarea class="contentbox form-control" name='content' placeholder="내용을 입력하세요" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"><c:out value="${board.content }"></c:out></textarea>
</div>

<div class="d-grid gap-2 d-md-block buttonbox">
  <button class="btn btn-primary listBtn" type="button">목록</button>
  <button class="btn btn-success modifyBtn" type="button">수정</button>
  <button class="btn btn-danger deleteBtn" type="button">삭제</button>
</div>

<form class="actionForm" action="/board/modify" method="get">
	<input type='hidden' name='page' value='${pageDTO.page }'>
	<input type='hidden' name='perSheet' value='${pageDTO.perSheet }'>
	<input type='hidden' name='type' value='${pageDTO.type }'>
	<input type='hidden' name='keyword' value='${pageDTO.keyword }'>
</form>

<script>
function movePage(){
	self.location = "/board/list";
}

const docQuery = document.querySelector.bind(document);
const actionForm = document.querySelector(".actionForm");

docQuery(".listBtn").addEventListener("click", function(e){
	e.preventDefault();
	actionForm.setAttribute("action", "/board/list");
	actionForm.submit();
},false);

docQuery(".deleteBtn").addEventListener("click",function(e){
	fetch("/board/remove", {method:"post",
		headers: {'Content-Type':'application/x-www-form-urlencoded'},
		body:"bno=${board.bno }"
		}).then(res => console.log(res))
		.then(res => {
			console.log("result: "+ res)
			$("#deleteModal").modal('show')
		})
},false);

docQuery(".modifyBtn").addEventListener("click", function(e){
	const title = document.querySelector("input[name='title']").value;
	/* console.log(title) */
	const writer = document.querySelector("input[name='writer']").value;
	/* console.log(writer) */
	const content = document.querySelector(".contentbox[name='content']").value;
	/* console.log(content) */
	const bno = document.querySelector("input[name='bno']").value;
	/* console.log(bno) */
	
	e.preventDefault();
	
	function sendAjax(data){
		/* console.log("sendBoardDTO: ", data) */
		
		return fetch("/board/modify",{method:"post",
			headers:{'Content-Type': 'application/json'},
			body:JSON.stringify(data)})
			.then(res => {
				if(!res.ok){
					throw new Error(res);
				}
				return res.text();
			})
			.catch(error => {
				console.log("catch.....")
				console.log(error)
				return error
			})
	}
	
	const data = {bno: bno, title: title, content: content, writer: writer}
	/* console.log("data: ", data) */
	const fnResult = sendAjax(data);
	
	fnResult.then(result => {
		/* console.log("result: "+ result) */
		$("#modifyModal").modal('show')
	})
	
},false);
</script>

<%@include file="../includes/footer.jsp" %>              