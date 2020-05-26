<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Klat</title>
</head>
<body>
chatAddForm
	<div>
		<form action="addChatRoom" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" name="mNum" value="${mNum}">
			Title<input type="text" name="crTitle" autocomplete="off">
			<input type="submit">
		</form>
	</div>
</body>
</html>