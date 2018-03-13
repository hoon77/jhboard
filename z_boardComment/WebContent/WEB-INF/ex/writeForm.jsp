<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="write.do" method="post" enctype="multipart/form-data">
			제목 : <input type="text" name="title"> <br> 비번 : <input
				type="password" name="pass"> <br> 이름 : <input
				type="text" name="name"> <br> 이메일 : <input type="text"
				name="email"> <br> 첨부파일 : <input type="file"
				name="ufile"> <br> 내용 <br>
			<textarea rows="20" cols="50" name="content">
			
			</textarea>
			<input type="submit" value="글쓰기ㄱ">
		</form>
	</center>
</body>
</html>