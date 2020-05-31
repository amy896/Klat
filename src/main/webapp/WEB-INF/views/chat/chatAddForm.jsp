<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/header.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/nav.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
	<div class="container">
		chatAddForm
		<div>
			<form action="addchatroom" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				Title<input type="text" name="crtitle" autocomplete="off">
				<input type="submit">
			</form>
		</div>	
	</div>
</body>
</html>