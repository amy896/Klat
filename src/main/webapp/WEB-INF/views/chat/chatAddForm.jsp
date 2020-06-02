<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
	<div class="container">
	
		<div class="chat_add_form_container">
			<p>채팅방 제목을 입력해주세요.</p>
			<form action="addchatroom" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<input type="text" name="crtitle" autocomplete="off">
				<input type="submit" value="생성">
			</form>
		</div>	
	</div>
</body>
</html>