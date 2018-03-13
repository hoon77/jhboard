<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/board2.css">
<link rel="stylesheet" type="text/css" href="semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="semantic/semantic.js"></script>
<script type="text/javascript" src="js/board.js"></script>
<title>Insert title here</title>

</head>
<body>

	<div id="header">
		<jsp:include page="navigationbar.jsp" />
	</div>

	<div id="wrap" align="center">
		<form action="writeBoard.do" name="frm" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="parentId" value="${parentId }">
			<table>
				<tr>
					<th>작성자</th>
					<td>
						<div class="ui input">
							${loginUser.name} <input type="hidden" name="writer"
								value="${loginUser.name}"> <input type="hidden"
								name="mId" value="${loginUser.memId}"> <input
								type="hidden" name="pwd" value="${loginUser.pwd}">
						</div>
					</td>
				</tr>

				<tr>
					<th>제목</th>
					<td>
						<div class="ui input">
							<input type="text" size="70" name="title">&nbsp;&nbsp;*
							필수
						</div>
					</td>
				</tr>

				<tr>
					<th>파일첨부</th>
					<td><input type="file" name="ufile"></td>
				</tr>

				<tr>
					<th>내용</th>
					<td>
						<div class="ui form">
							<div class="field">
								<textarea cols="60" rows="15" name="content"></textarea>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<br> <br>
			<button class="ui basic button" type="submit"
				onclick="return writeBoardCheck()">등록</button>
			<button class="ui basic button" type="reset">취소</button>

		</form>

	</div>
</body>
</html>