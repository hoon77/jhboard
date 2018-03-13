<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="semantic/semantic.js"></script>
<style type="text/css">
body {
	background-color: #F7F7F7;
}
</style>

<title>Insert title here</title>
</head>
<body>

	<div id="navigationbar">
		<div>
			<div class="ui stackable menu">
				<div class="item">
					<i class="smile icon"></i>
					<c:choose>
						<c:when test="${!empty loginUser}">
   ${loginUser.name } 님 반갑습니다!
 </c:when>
					</c:choose>
				</div>

				<c:choose>
					<c:when test="${empty loginUser}">
						<a class="item" href="loginForm.do">Login</a>
						<a class="item" href="joinForm.do">Sign in</a>
					</c:when>
					<c:otherwise>
						<a class="item" href="logout.do">Logout</a>
					</c:otherwise>
				</c:choose>
				<a class="item" href="main.do?mId=${loginUser.memId }">Home</a> <a
					class="item" href="boardList.do">Board</a>
			</div>
		</div>
	</div>
</body>
</html>