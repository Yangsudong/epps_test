<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdn.ckeditor.com/4.15.0/standard/ckeditor.js"></script>
<title>memberUpdate.jsp</title>
<script>
function inputCheck() {
	var w_pass = $("input[name='password']").val();
	var o_pass = $("input[name='o_password']").val();
	if(w_pass == o_pass){
		return true;
	}
	
	if( w_pass != o_pass) {
		alert("패스워드가 틀림");
		$("input[name='password']").val("");
		frm.password.focus();
		return false;
	}
	
	if(frm.company_name.value == ""){
		window.alert("회사 입력");
		frm.company_name.focus();
		return false; 
	}
} 
function btnListHome() {
	location.href="boardList.do";
}
</script>
</head>
<body>

<div class="container" role="main">
				<br><br>
			<form id="frm" name="frm" method="post" onsubmit="return inputCheck()" action="boardUpdate.do">
				<div class="mb-3">
				<input type="hidden" name="board_id" value="${board.board_id}">
				<label>회사</label> &nbsp; <input type="text" name="company_name" value="${board.company_name}" > 
				<label>파일첨부</label> &nbsp; <input type="file" id="upload_file" name="upload_file" value="${board.upload_file}"> 
				<label>비밀번호</label> &nbsp; <input type="text" name="password" id="password"><br> 
				<input type="hidden" name="o_password" value="${board.password}">
				<label>제목</label><input class="form-control" type="text" name="title" id="title" value="${board.title}"><br>
				</div>

				<div class="mb-3">
					<textarea  rows="10" name="content" id="content">${board.content}</textarea>
				</div>
			<p align="right">
				<button type="submit" class="btn btn-sm btn-primary" >저장</button>  
				<button type="button" class="btn btn-sm btn-primary" onclick="btnListHome()">취소</button>
			</p>
			</form>					
			</div>
<script>CKEDITOR.replace('content');</script>
</body>
</html>