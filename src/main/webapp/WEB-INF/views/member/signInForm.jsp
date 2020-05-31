<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/member.css"/>
</head>
<body>

	<div class="container">
		<div class = "sign_in_container">
			<a class="img_container" href="${contextPath }/main">
				<img src="${contextPath}/img/logo_black.png" alt="클랏 로고 이미지 입니다."/>
			</a>
			<form action="signinmember" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<p class="title">아이디</p> 
				<input type="text" name="userid" autocomplete="off">
				<p class="title">비밀번호</p> 
				<input type="password" name="userpassword" autocomplete="auto">
				<input type="submit" value="로그인">
			</form>
			<a href="${contextPath}/member/signupform">회원 가입</a>
		</div>
	</div>

</body>
</html>