<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<center>
		<table>
			<tr>
				<td width="1250px" colspan="5" bgcolor="pink"></td>
			</tr>
			<tr>
				<th width="100px" align="center">글 번호</th>
				<th width="700px" align="center">제 목</th>
				<th width="200px" align="center">작성일</th>
				<th width="100px" align="center">조회수</th>
				<th width="150px" align="center">작성자</th>
			</tr>
			<tr>
				<td width="1250px" colspan="5" bgcolor="pink"></td>
			</tr>
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td align="center">${board.num }</td>
					<td><a href="view.do?num=${board.num }">${board.title }</a></td>
					<td align="center"><fmt:formatDate value="${board.writedate }"
							pattern="yyyy-MM-dd" /></td>
					<td align="center">${board.readcount }</td>
					<td align="center">${board.name }</td>
				</tr>
			</c:forEach>
			<tr>
				<td width="1250px" colspan="5" bgcolor="pink"></td>
			</tr>
			<tr>
				<td width="1250px" colspan="5"><c:if test="${start != 1 }">
						<a href="list.do?page=1">[처음]</a>
						<a href="list.do?page=${start-1 }">[이전]</a>
					</c:if> <c:forEach begin="${start }" end="${end }" var="i">
						<c:choose>
							<c:when test="${i == current }">
								[${i }]
							</c:when>
							<c:otherwise>
								<a href="list.do?page=${i }">[${i }]</a>
							</c:otherwise>
						</c:choose>
					</c:forEach> <c:if test="${end != last }">
						<a href="list.do?page=${end+1 }">[다음]</a>
						<a href="list.do?page=${last }">[끝]</a>
					</c:if></td>
				<td colspan="5" align="right"><input type="button" value="글쓰기"
					onclick="location.href='writeForm.do'"></td>
			</tr>
		</table>
		<%-- 	start : 	${start }<br> --%>
		<%-- 	end :	${end }<br> --%>
		<%-- 	first :	${first }<br> --%>
		<%-- 	last :	${last }<br> --%>
		<%-- 	current : 	${current } --%>
	</center>
</body>
</html>













