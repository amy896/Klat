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
chatInviteForm
	<div>
		<form action="inviteMember" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="text" placeholder="invite someone">
			<input type="submit">
		</form>
	</div>
</body>
</html>