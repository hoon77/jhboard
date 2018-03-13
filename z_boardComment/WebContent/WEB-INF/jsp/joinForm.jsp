<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="semantic/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="semantic/semantic.js"></script>
<script type="text/javascript" src="js/member.js"></script>
<title>Insert title here</title>
<style type="text/css">
#registerFormDiv {
	width: 600px;
	margin: 50px auto;
}

#content {
	margin-top: 120px;
}
</style>
<script>
$(document).ready(function() {

	

		$('.memberId').on('blur', function() {
			var memberId = $('.memberId').val();
			var params = "memberId="+memberId;
			$.ajax({
				url : 'confirmMemberId.do',
				data : params,
				dataType : 'json',
				success : function(data) {

						if(data==false) {
							alert("이미 존재하는 아이디입니다!");
						}
						
						else if(data==true){
							
							alert("사용가능한 아이디입니다!");
						}
					
				},
				error : function(a,b,c){
					
					alert(a.responseText);
				}
			});
			
		
		});
			
		
	});
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="navigationbar.jsp" />
	</div>
	<div id="content">

		<div id="registerFormDiv">

			<h2 class="ui teal horizontal divider header">
				<span class="joinLabel">회 원 가 입</span>
			</h2>
			<form class="ui form" action="join.do" method="post" name="frm">
				<div class="field">
					<label>아이디</label> <input type="text" name="id" placeholder="아이디"
						class="memberId">
				</div>
				<div class="field">
					<label>이름</label> <input type="text" name="name" placeholder="이름">
				</div>
				<div class="field">
					<label>비밀번호</label> <input type="text" name="pwd"
						placeholder="비밀번호">
				</div>

				<div class="field">
					<label>비밀번호 확인</label> <input type="text" name="re_pwd"
						placeholder="비밀번호 확인">
				</div>
				<center>
					<br>
					<br>
					<button class="ui red basic button" type="reset">취소</button>
					<button class="ui blue basic button" type="submit"
						onclick="return joinCheck()">회원가입</button>
				</center>
			</form>
		</div>
	</div>
</body>
</html>