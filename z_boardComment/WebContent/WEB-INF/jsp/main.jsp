<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mainBoard.css">
<link rel="stylesheet" type="text/css" href="semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<style>
#wrap {
	width: 1200px;
}

#profile {
	margin: 15px 0 0 30px;
	font-size: 15px;
	width: 150px;
}

#myBoard {
	width: 800px;
	height: 250px;
	margin-left: 100px;
	margin-top: 20px;
	margin-bottom: 50px;
}

#myComment {
	width: 800px;
	height: 250px;
	clear: both;
	margin-left: 280px;
	margin-top: 100px;
	table-layout: fixed
}

#myComment td {
	text-overflow: ellipsis;
	overflow: hidden;
}

.homeDiv {
	float: left;
}

.textOverflow {
	width: 600px;
	display: block;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="navigationbar.jsp" />
	</div>
	<div id="wrap">

		<div id="profile" class="ui card homeDiv">
			<a class="image" href="#"> <img
				src="http://cfile8.uf.tistory.com/image/266EDA35573F5C181B0C5A">
			</a>
			<div class="content">
				<center>
					<a class="header profileLabel" href="#">${loginUser.id}(${loginUser.name }
						님)</a>
					<div class="meta"></div>
				</center>
			</div>
		</div>



		<div id="myBoard" class="homeDiv">
			<h3 class="ui dividing header">내가 쓴 글</h3>
			<table class="list">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회</th>
					<th>추천</th>
				</tr>
				<c:forEach items="${boardList }" var="board" varStatus="i">
					<tr>
						<td width="5%" class="center">${board.bId }</td>
						<td width="65%"><a
							href="boardView.do?mode=1&bId=${board.bId}"> <c:if
									test="${board.fId != 0 }">
									<i class="save icon"></i>
								</c:if> <c:forEach var="cnt" begin="1" end="${board.groupLv }" step="1"> RE:</c:forEach>
								${board.title } [${countCommentList[i.index]}]
						</a></td>
						<td width="5%" class="center">${board.writer }</td>
						<td width="15%" class="center"><fmt:formatDate
								value="${board.regDate }" type="date" /></td>
						<td width="5%" class="center">${board.hit }</td>
						<td width="5%" class="center">${board.up }</td>
					</tr>
				</c:forEach>

				<!-- paging -->

				<tr>
					<td colspan="7"><c:if test="${start != 1 }">
							<a href="main.do?boardPage=1">[처음]</a>
							<a href="main.do?boardPage=${start-1 }">[이전]</a>
						</c:if> <c:forEach begin="${start }" end="${end }" var="i">
							<c:choose>
								<c:when test="${i == current }">
								[${i }]
							</c:when>
								<c:otherwise>
									<a href="main.do?boardPage=${i }">[${i }]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach> <c:if test="${end != last }">
							<a href="main.do?boardPage=${end+1 }">[다음]</a>
							<a href="main.do?boardPage=${last }">[끝]</a>
						</c:if></td>


				</tr>


			</table>


		</div>



		<div id="myComment">
			<h3 class="ui dividing header">내가 쓴 댓글</h3>
			<table class="list">
				<tr>
					<th>번호</th>
					<th>내용</th>
					<th>날짜</th>

				</tr>
				<c:forEach items="${commentList }" var="comment" varStatus="i">
					<tr>
						<td width="10%" class="center">${comment.cId }</td>
						<td width="75%"><a class="textOverflow"
							href="boardView.do?mode=1&bId=${comment.bId}">
								${comment.comment } </a></td>
						<td width="15%" class="center"><fmt:formatDate
								value="${comment.regDate }" type="date" /></td>
					</tr>
				</c:forEach>

				<!-- paging -->

				<tr>
					<td colspan="7"><c:if test="${c_start != 1 }">
							<a href="main.do?commentPage=1">[처음]</a>
							<a href="main.do?commentPage=${c_start-1 }">[이전]</a>
						</c:if> <c:forEach begin="${c_start }" end="${c_end }" var="i">
							<c:choose>
								<c:when test="${i == c_current }">
								[${i }]
							</c:when>
								<c:otherwise>
									<a href="main.do?commentPage=${i }">[${i }]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach> <c:if test="${c_end != c_last }">
							<a href="main.do?commentPage=${c_end+1 }">[다음]</a>
							<a href="main.do?commentPage=${c_last }">[끝]</a>
						</c:if></td>


				</tr>


			</table>



		</div>


	</div>



</body>
</html>