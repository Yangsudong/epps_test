<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdn.ckeditor.com/4.15.0/standard/ckeditor.js"></script>
<title>Insert title here</title>
<script>
function inputCheck() {
	if(frm.company_name.value == ""){
		window.alert("회사 입력");
		frm.company_name.focus();
		return false; 
	}
	if(frm.password.value == ""){
		window.alert("패스워드 입력");
		frm.password.focus();
		return false;		
	}	
	if(frm.title.value == ""){
		window.alert("제목 입력");
		frm.title.focus();
		return false;		
	}	
	return true;
} 

function btnListHome() {
	location.href="boardList.do";
}
</script>
</head>
<body>

<div class="container" role="main">
				<br><br>
			<form id="frm" name="frm" onsubmit="return inputCheck()" method="post" action="${pageContext.request.contextPath}/boardInsert.do">
				<div class="mb-3">
				<label>회사</label> &nbsp; <input type="text" name="company_name" placeholder="회사명"> 
				<label>파일첨부</label> &nbsp; <input type="file" id="upload_file" name="upload_file"> 
				<label>비밀번호</label> &nbsp; <input type="password" name="password" placeholder="password"><br> 
				<label>제목</label><input class="form-control" type="text" name="title" id="title" placeholder="제목"><br>
				</div>

				<div class="mb-3">
					<textarea  rows="10" name="content" id="content" placeholder="내용을 입력하세요"></textarea>
				</div>
				
			<div>
			<p align="right">
				<button type="submit" class="btn btn-sm btn-primary" >저장</button>  
				<button type="button" class="btn btn-sm btn-primary" onclick="history.back(-1);">목록</button>
			</p>
			</div>
			</form>					
		</div>	
<script>CKEDITOR.replace('content');</script>
</body>
</html>