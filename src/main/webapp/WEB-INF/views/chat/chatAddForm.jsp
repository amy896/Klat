<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Klat</title>
</head>
<body>
<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
chatAddForm
	<div>
		<form action="addchatroom" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			Title<input type="text" name="crtitle" autocomplete="off">
			<input type="submit">
		</form>
	</div>
</body>
</html>