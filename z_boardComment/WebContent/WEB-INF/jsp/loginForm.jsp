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

<style type="text/css">
body {
	background-color: #deeaee;
}

body>.grid {
	height: 100%;
}

#content {
	margin-top: 120px;
}

.image {
	margin-top: -100px;
}

.column {
	max-width: 450px;
}

.logoDiv {
	color: #8d9db6;
}

.loginBtn {
	color: #8d9db6;
}

#message {
	margin-top: 40px;
}
</style>

</head>


<script>

	$(document).ready(function() {
		
		var message = '${message}';
		  if(message) {
		  alert(message);
		  }
		  

	});
	
	
	
	</script>
<body>


	<div id="header">
		<jsp:include page="navigationbar.jsp" />
	</div>

	<div id="content">
		<div class="ui middle aligned center aligned grid">
			<div class="column">
				<h2 class="ui teal image header">
					<div class="logoDiv">LOGiN GO!</div>
				</h2>
				<form class="ui large form loginForm" action="login.do"
					method="post">
					<div class="ui stacked segment">
						<div class="field">
							<div class="ui left icon input">
								<i class="user icon"></i> <input type="text" name="id"
									placeholder="ID">
							</div>
						</div>
						<div class="field">
							<div class="ui left icon input">
								<i class="lock icon"></i> <input type="password" name="pwd"
									placeholder="Password">
							</div>
						</div>
						<button type="submit" class="fluid ui teal button loginBtn">
							Login</button>
					</div>
				</form>



			</div>
		</div>
	</div>
</body>
</html>