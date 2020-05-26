<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Klat</title>
</head>
<body>
	<h4>Sign Up Page</h4>
	<form action="signUpMember" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		ID : <input type="text" name="mId"><br>
		Name : <input type="text" name="mName"> <br>
		Password : <input type="password" name="mPw"><br>
		<input type="submit" value="SIGN UP">
	</form>
	

</body>
</html>