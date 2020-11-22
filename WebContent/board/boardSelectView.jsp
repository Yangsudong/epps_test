<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>boardAll</title>

<script>

function btnSelect() {
	var company_name = $("#company_list option:selected").val();
	if(company_name == ""){
		alert("회사선택 후 조회");
	}
	else if( confirm(company_name + " 조회 하시겠습니까?")) {
		location.href="boardSelect.do?company_name="+company_name;
	}
}

function btnInsert() {
	if( confirm("등록페이지로 이동하시겠습니까?")) {
		location.href="/forntWeb/board/boardInsert.jsp";
	}
}
	
function btnUpdate() {
	var board_id = $("input[name='checkbox']:checked").val();
	var check_num = $("input[name='checkbox']:checked").length;
	if(board_id == null){
		alert("체크 후 수정");
	}
	else if(check_num>1){
		alert("하나만 체크");
	}
	else if( confirm("수정페이지로 이동하시겠습니까?")) {
		location.href="boardUpdatePage.do?board_id="+board_id;
	}
}

function btnDelete() {
	var board_id = $("input[name='checkbox']:checked").val();
	var check_num = $("input[name='checkbox']:checked").length;
	if(board_id == null){
		alert("체크 후 삭제");
	}
	else if(check_num>1){
		alert("하나만 체크");
	}
	else if( confirm("정말 삭제 하시겠습니까?")) {
		location.href="boardDelete.do?board_id="+board_id;
		alert("삭제 되었습니다.");
	} else {
		alert("취소 되었습니다.");
	}
}

function btnListHome() {
	location.href="boardList.do";
}

</script>
<style>
.pagination li {
		display : inline-block;
		border : 15px solid white;
	}
.pagination .active {
	border : 10px solid lightgray;
	background-color : lightgray;
}
#head{
  height : 75px;
  postion : fixed;
  width : 99%;
  border: 1px solid lightgray;
  margin-top: 10px;
  margin-right: 10px;
  margin-bottom: 10px;
  margin-left: 10px;
}
#head_company{
  margin-top: 15px;
  margin-left: 15px;
}
#body{
  height : 400px;
  postion : fixed;
  width : 99%;
  border: 1px solid lightgray;
  margin-top: 10px;
  margin-right: 10px;
  margin-bottom: 10px;
  margin-left: 10px;
}

#btn{
float : right;
}

 .company_list {
  /* 생략 */
  font-family: "Noto Sansf KR", sans-serif;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;

  color: #444;
  background-color: #fff;

  padding: 0.6em 1.4em 0.5em 0.8em;
  margin: 0;

  border: 1px solid #aaa;
  border-radius: 0.5em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
}
</style>
</head>
<body>
<div id="head">
	<div id="head_company">
		<form name="form">
			<p>회사 &nbsp;&nbsp;
			<select name="company_list" id="company_list" class="company_list">
				<option id="company_list" value="">선택 
				<c:forEach items="${company_list}" var="company_list">
					<option value="${company_list.company_name}">${company_list.company_name}
				</c:forEach>	
			</select>
			</p>
		</form>
	</div>
</div>

<div id="btn">
<button type="button" class="btn btn-success" onclick="btnSelect()">조회</button> 
<button type="button" class="btn btn-success" onclick="btnListHome()">전체목록</button> 
</div>
<br><br>

	<div id="body" class="col-md-15 col-12">
	<br><br>
		<table border="1" class="table table-hover">
		<form name="page">
		<input type="hidden" name="p" value="1">
		</from>
			<thead align="center" >
				<tr>
					<th width="5%"> 선택</th>
					<th width="10%">회사</th>
					<th>제목</th>
					<th width="10%">첨부파일</th>
					<th width="5%">조회수</th>
					<th width="10%">작성자</th>
					<th width="10%">등록일</th>
				</tr>
			</thead>
			<tbody>
			 <c:forEach items="${selectView}" var="board">		
				<tr>
					<td align="center"><input type="checkbox" name="checkbox" value="${board.board_id}"></td>
					<td>${board.company_name}</td>
					<td><a href="boardUpdatePage.do?board_id=${board.board_id}">${board.title}</a></td>
					<td>${board.upload_file}</td>  
					<td>${board.views}</td>  
					<td>${board.writer}</td>
					<td>
					<fmt:parseDate value="${board.write_date}" pattern="yyyy-MM-dd" var="day" />
					<fmt:formatDate value="${day}" pattern="yyyy/MM/dd"/>					
					</td>				
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<p><my:paging paging="${paging}" jsfunc="gopage" /></p>
	<div id="btn">
	<button type="button" class="btn btn-outline-primary" onclick="btnInsert()">등록</button> 
	<button type="button" class="btn btn-outline-primary" onclick="btnUpdate()">수정</button> 
	<button type="button" class="btn btn-outline-primary" onclick="btnDelete()">삭제</button> 
	</div>
	
<script>
	function gopage(p) {
		page.p.value=p;
		page.submit();
		location.href = "boardList.do?p=" + p;
	}
</script>
</body>
</html>