<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/signUpForm.css"/>
</head>
<body>

	<div class="container">
		<div class="sign_up_container">

			<a href="${contextPath }/main">
				<img src="${contextPath}/img/logo_black.png" alt="클랏 로고 이미지 입니다."/>
			</a>
			
			
			<form action="signupmember" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<p class="title">ID</p>
				<input type="text" name="userid" autocomplete="off">
				<p class="title">User name</p>
				<input type="text" name="username" autocomplete="off">
				<p class="title">Password</p>
				<input type="password" name="userpassword" autocomplete="off">
				<p class="title">Password check</p>
				<input type="password" name="userpassword" autocomplete="off"><br>
				<input type="submit" value="Sign up">
			</form>	
		</div>
	</div>
	
</body>
</html>