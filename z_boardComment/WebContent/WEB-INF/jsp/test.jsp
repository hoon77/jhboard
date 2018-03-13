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
<script type="text/javascript">
$(document)
.ready(function() {
  $('.ui.menu .ui.dropdown').dropdown({
    on: 'hover'
  });
  $('.ui.menu a.item')
    .on('click', function() {
      $(this)
        .addClass('active')
        .siblings()
        .removeClass('active')
      ;
    })
  ;
})
;
</script>
</head>
<body>
	<div class="ui tabular menu">
		<a class="item"> Bio </a> <a class="item active"> Photos </a>
	</div>


</body>
</html>