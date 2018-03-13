<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/board3.css">
<link rel="stylesheet" type="text/css" href="semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="semantic/semantic.js"></script>
<style type="text/css">
* {
	
}

#commentDiv {
	width: 970px;
	height: 600px;
	overflow: hidden;
}

#commentListTable {
	width: 100%;
	border-top: 1px solid #cccccc;
	border-collapse: collapse;
	font-size: 12px;
}

#commentListTable td {
	border-bottom: 1px solid #cccccc;
	padding: 2px;
}

.commentList {
	width: 100%;
}

.writeComment {
	width: 100%;
	height: 100px;
	border: 1px solid #cccccc;
	margin-top: 50px;
}

.commendInput {
	border: 1px solid #cccccc;
}

.block {
	margin-top: 25px;
	float: left;
	height: 70px;
}

.writeCommentS1 {
	width: 9%;
	font-size: 12px;
	text-align: center;
	margin-left: 9px;
	margin-top: 30px;
}

.writeCommentS2 {
	width: 70%;
}

.writeCommentS3 {
	width: 10%;
	margin-left: 85px;
}

.w_td {
	width: 10%;
}

.comm_td {
	width: 65%;
}

.date_td {
	width: 12%;
}

.re_td {
	width: 2px;
}

.del_td {
	width: 2px;
}

.re_comment_tr {
	display: none;
}

.re_w_td {
	width: 20%;
}

.re_comm_td {
	width: 70%;
}

.re_btn_td {
	width: 10%;
}
</style>

<title>Insert title here</title>
<script>

	$(document).ready(function() {


		
		$('.boardDelBtn').on('click', function() {
			var data_id = $(this).attr('data-id');
			if (confirm('글을 삭제하시겠습니까? \n삭제 후 복구할 수 없습니다!')) {
			    // Save it!
				window.location.href = 'deleteBoard.do?bId='+data_id;
			} else {
			    // Do nothing!
			}
			
		});

		
		

		$('.commentDelBtn').on('click', function() {
			var data_id = $(this).attr('data-id');
			var bId =$(this).attr('b-id');
			if (confirm('댓글을 삭제하시겠습니까? \n삭제 후 복구할 수 없습니다!')) {
			    // Save it!
				window.location.href = 'deleteComment.do?cId='+data_id+'&bId='+bId;
			} else {
			    // Do nothing!
			}
			
		});

		
		

		$('.re_commentBtn').on('click', function() {
			var data_id = $(this).attr('data-id');
			$("tr[data-id='" + data_id + "']").css('display', 'table-row');
		});

		$('.re_cancelBtn').on('click', function() {
			var data_id = $(this).attr('data-id');
			$("tr[data-id='" + data_id + "']").css('display', 'none');
		});


		$('.re_submitBtn').on('click', function() {
			var data_id = $(this).attr('data-id');
			var writer = $(".re_writer[data-id='" + data_id + "']").val();
			var pwd = $(".re_pwd[data-id='" + data_id + "']").val();
			var mId = $(".re_mId[data-id='" + data_id + "']").val();
			var parentId = $(".re_parentId[data-id='" + data_id + "']").val();
			var bId = $(".re_bId[data-id='" + data_id + "']").val();
			var comment = $(".re_comment[data-id='" + data_id + "']").val();
			window.location.href = 'writeComment.do?writer=' + writer + '&parentId=' +
					parentId+ '&bId=' + bId + '&comment=' + comment+ '&pwd='+pwd+'&mId='+mId;

		});


		$('.upBtn').on('click', function() {
			var data_id = $(this).attr('data-id');
			var params = "mode=0&bId="+data_id;
			$.ajax({
				url : 'boardUpDown.do',
				data : params,
				dataType : 'json',
				success : function(data) {
					if (data) {
						$(".upCount").html(data);
					}
				}
			});
		});

		$('.downBtn').on('click', function() {
			var data_id = $(this).attr('data-id');
			var params = "mode=1&bId="+data_id;
			$.ajax({
				url : 'boardUpDown.do',
				data : params,
				dataType : 'json',
				success : function(data) {
					if (data) {
						$(".downCount").html(data);
					}
				}
			});
		});


		/*$('.commentWbtn').on('click', function() {
			
			$.ajax({
				url : 'writeComment.do',
				type : 'post',
				data :$('#writeCommentFrm').serialize(),
				dataType : 'json',
				success : function(data) {
					if (data) {

					    
					}
				}
			});

		});*/

	});
</script>
</head>
<body>

	<div id="header">
		<jsp:include page="navigationbar.jsp" />
	</div>



	<div id="wrap" align="center">
		<h1></h1>
		<table id="boardViewTable">
			<tr>
				<th width="15%">번호</th>
				<td width="35%">${board.bId }</td>
				<th width="15%">작성자</th>
				<td width="35%">${board.writer }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.regDate }" type="date" /></td>
				<th>조회수</th>
				<td>${board.hit }</td>

			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${board.title }</td>
			</tr>

			<tr>
				<th><i class="save icon"></i>다운로드</th>
				<td colspan="3"><a href="download.do?id=${boardFile.id }">
						${boardFile.originFileName } </a> (크기:${boardFile.size })</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><pre>${board.content }
				</pre></td>
			</tr>
		</table>
		<br> <br>


		<div class="ui labeled button" tabindex="0" style="background-color: #f7f7f7">
			<button class="ui basic button upBtn" data-id=${board.bId }>
				<i class="thumbs red outline up icon"></i>
			</button>
			<a class="ui basic  left pointing label upCount">${board.up}</a>

		</div>
		<div class="ui labeled button" tabindex="0"  style="background-color: #f7f7f7">
			<button class="ui basic button downBtn" data-id=${board.bId }>
				<i class="thumbs blue outline down icon"></i>
			</button>
			<a class="ui basic left pointing  label downCount">${board.down}</a>
		</div>

		<br> <br> <br> <br>

		<button class="ui basic button" onclick="location.href='boardList.do'">게시글
			목록</button>
		<c:if test="${board.mId== loginUser.memId}">
			<button class="ui basic button"
				onclick="location.href='updateBoardForm.do?bId=${board.bId }'">게시글
				수정</button>
			<button class="ui basic button boardDelBtn" data-id=${board.bId }>게시글
				삭제</button>
		</c:if>
		<button class="ui basic button"
			onclick="location.href='writeBoardForm.do?parentId=${board.bId }'">답글
			쓰기</button>
		<br> <br> <br>

		<div id="commentDiv">

			<div class="commentList">


				<table id="commentListTable">
					<c:forEach items="${commentList }" var="comment">

						<tr>
							<td class="w_td">${comment.writer }</td>
							<td class="comm_td"><c:forEach begin="1"
									end="${comment.groupLv }" step="1">
					        RE:</c:forEach> ${comment.comment }</td>
							<td class="date_td"><fmt:formatDate
									value="${comment.regDate }" type="date" /></td>
							<td class="del_td"><c:choose>
									<c:when test="${comment.mId== loginUser.memId}">
										<button class="mini ui basic button commentDelBtn"
											data-id="${comment.cId}" b-id="${board.bId}">삭제</button></td>
							</c:when>
							<c:otherwise>
								<button class="mini ui basic button" style="visibility: hidden;">삭제</button>
								</td>
							</c:otherwise>
							</c:choose>
							<td class="re_td">
								<button class="mini ui basic button re_commentBtn"
									data-id="${comment.cId}">답글</button>
							</td>

						</tr>


						<tr class="re_comment_tr" data-id="${comment.cId}">
							<td>${loginUser.name } <input type="hidden"
								class="re_writer" data-id="${comment.cId}"
								value="${loginUser.name }"> <input type="hidden"
								class="re_pwd" data-id="${comment.cId}"
								value="${loginUser.pwd }"> <input type="hidden"
								class="re_mId" data-id="${comment.cId}"
								value="${loginUser.memId }"> <input type="hidden"
								class="re_parentId" data-id="${comment.cId}"
								value="${comment.cId}"> <input type="hidden"
								class="re_bId" data-id="${comment.cId}" value="${board.bId }"></td>
							<td colspan="2"><input class="commendInput re_comment"
								data-id="${comment.cId}" type="text" size="100"></td>
							<td class="re_td">
								<button class="mini ui basic button re_submitBtn"
									data-id="${comment.cId}">등록</button>
							</td>
							<td class="del_td">
								<button class="mini ui basic button re_cancelBtn"
									data-id="${comment.cId}">
									취소</i>
								</button>
							</td>
						</tr>

					</c:forEach>

				</table>

			</div>

			<div class="writeComment">

				<form id="writeCommentFrm" method="post" action="writeComment.do">

					<div class="writeCommentS1 block">
						<span class="loginUser"> ${loginUser.name } </span> <input
							type="hidden" name="writer" value=${loginUser.name }> <input
							type="hidden" name="pwd" value=${loginUser.pwd }> <input
							type="hidden" name="mId" value=${loginUser.memId }> <input
							type="hidden" name="parentId" value="0"> <input
							type="hidden" name="bId" value="${board.bId }">
					</div>
					<div class="writeCommentS2 block">
						<textarea class="commendInput" rows="3" cols="103" name="comment"></textarea>
					</div>
					<div class="writeCommentS3 block">
						<button class="ui blue basic button commentWbtn" type="submit">등록</button>
					</div>
				</form>

			</div>

		</div>
	</div>
</body>
</html>
