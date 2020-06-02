<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/myPage.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
	<div class="container">
		<div class="my_page_box">
			<form action="modifymember" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<a class="my_page_profile_img" href="${contextPath}/mypage/mypagemain">
					<img src="http://via.placeholder.com/600x600" alt="프로필 이미지입니다">
				</a>
				<div>
					<p>닉네임</p>
					<input type="text" name="mname" value="${member.mName}" autocomplete="off">
				</div>
				<div>
					<p>비밀번호</p>
					<input type="password" name="mpw" value="${member.mPw}" autocomplete="off">
				</div>
				<div>
					<p>비밀번호 확인</p>
					<input type="password" name="mpwcheck" value="${member.mPw}" autocomplete="off">
				</div>
				<input type="submit" value="수정">
			</form>
		</div>	
	</div>
</body>
</html>