<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
</head>
<body>
	<h4>Sign In Page</h4>
	<form action="signinmember" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		ID : <input type="text" name="userid"><br>
		PW : <input type="password" name="userpassword"><br>
		<input type="submit" value="SIGN IN">
	</form>

</body>
</html>