<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css"/>
</head>
<body>
	<div class="container">
	
		<div class="main_title_container">
			<img src="${contextPath}/img/logo_white.png">
			<p>Share Your Thinking</p>
			<button onclick="location.href='${contextPath}/member/signupform'">Sign up</button>
			<button onclick="location.href='${contextPath}/member/signinform'">Sign in</button>
		</div>
		
	</div>
</body>
</html>