<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/board.css">
<link rel="stylesheet" type="text/css" href="semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="semantic/semantic.js"></script>

<title>Insert title here</title>
<style type="text/css">
#searchDiv {
	margin-top: 30px;
}
</style>


<script type="text/javascript">

$(document)
.ready(function() {
	
	if('${mode}' == 1) {
		 $('.upBoard').addClass('active').siblings().removeClass('active');	
	}
	
	
	
  $('.ui.menu .ui.dropdown').dropdown({
    on: 'hover'
  });
  
  
  $('.ui.menu a.item').on('click', function() {
	 
       if ( $(this).attr('data-id') ==='bestUp') {  //추천 게시물
    	  
    	   window.location.href = 'boardList.do?mode=1';
       }
      
       else { //전체보기 
    	   
    	   window.location.href = 'boardList.do';
       }
       
  
      
    })
  ;
})
;


</script>

</head>
<body>

	<div id="header">
		<jsp:include page="navigationbar.jsp" />
	</div>



	<div id="wrap" align="center">
		<h1></h1>

		<div class="ui tabular menu">
			<a class="item active allBoard" data-id="all">전체</a> <a
				class="item upBoard" data-id="bestUp">Best추천글</a>

		</div>
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
					<td width="65%"><a href="boardView.do?mode=1&bId=${board.bId}">
							<c:if test="${board.fId != 0 }">
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
				<td width="1000px" colspan="5" align="center"><c:choose>

						<c:when test="${mode==0 }">
							<c:if test="${start != 1 }">
								<a href="boardList.do?page=1">[처음]</a>
								<a href="boardList.do?page=${start-1 }">[이전]</a>
							</c:if>
						</c:when>

						<c:when test="${mode==1 }">
							<c:if test="${start != 1 }">
								<a href="boardList.do?page=1&mode=1">[처음]</a>
								<a href="boardList.do?page=${start-1 }&mode=1">[이전]</a>
							</c:if>
						</c:when>

						<c:otherwise>
							<c:if test="${start != 1 }">
								<a
									href="boardList.do?page=1&mode=2&searchSel=${sp.searchSel }&searchInput=${sp.searchInput }">[처음]</a>
								<a
									href="boardList.do?page=${start-1 }&mode=2&searchSel=${sp.searchSel }&searchInput=${sp.searchInput }">[이전]</a>
							</c:if>
						</c:otherwise>

					</c:choose> <c:choose>
						<c:when test="${mode==0 }">
							<c:forEach begin="${start }" end="${end }" var="i">
								<c:choose>
									<c:when test="${i == current }">
								[${i }]
							</c:when>
									<c:otherwise>
										<a href="boardList.do?page=${i }">[${i }]</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>

						<c:when test="${mode==1 }">
							<c:forEach begin="${start }" end="${end }" var="i">
								<c:choose>
									<c:when test="${i == current }">
								[${i }]
							</c:when>
									<c:otherwise>
										<a href="boardList.do?page=${i }&mode=1">[${i }]</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>


						<c:otherwise>
							<c:forEach begin="${start }" end="${end }" var="i">
								<c:choose>
									<c:when test="${i == current }">
								[${i }]
							</c:when>
									<c:otherwise>
										<a
											href="boardList.do?page=${i }&mode=2&searchSel=${sp.searchSel }&searchInput=${sp.searchInput }">[${i }]</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${mode==0 }">
							<c:if test="${end != last }">
								<a href="boardList.do?page=${end+1 }">[다음]</a>
								<a href="boardList.do?page=${last }">[끝]</a>
							</c:if>
						</c:when>

						<c:when test="${mode==1 }">
							<c:if test="${end != last }">
								<a href="boardList.do?page=${end+1 }&mode=1">[다음]</a>
								<a href="boardList.do?page=${last }&mode=1">[끝]</a>
							</c:if>
						</c:when>

						<c:otherwise>

							<c:if test="${end != last }">
								<a
									href="boardList.do?page=${end+1 }&mode=2&searchSel=${sp.searchSel }&searchInput=${sp.searchInput }">[다음]</a>
								<a
									href="boardList.do?page=${last }&mode=2&searchSel=${sp.searchSel }&searchInput=${sp.searchInput }">[끝]</a>
							</c:if>
						</c:otherwise>

					</c:choose></td>

				<td colspan="5" align="right"><input type="button"
					class="ui basic button writeBtn" value="글쓰기"
					onclick="location.href='writeBoardForm.do?parentId=0'"></td>
			</tr>


		</table>  

		<div id="searchDiv">
			<form id="searchForm" action="boardList.do">
				<input type="hidden" value="2" name="mode"> <select size=1
					name="searchSel">
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">제목+내용</option>
					<option value="4">글쓴이</option>
				</select>

				<div class="ui small icon input">
					<input type="text" size="40" id="searchInput" name="searchInput">
					<i class="search icon"></i>
				</div>

				<button type="submit" class="ui primary basic button">검색</button>

			</form>

		</div>
	</div>
</body>
</html>