<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/myPage.css"/>
</head>

<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
	
	<div class="container">
		<div class="my_page_modify_box">
			<form action="${contextPath}/mypage/modifymember"class="modify_member_form" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div class="my_page_profile_container">
					<img src="${contextPath}/img/${member.mProfileImg}.png" alt="프로필 이미지입니다">
				</div>
				<div class="my_page_name_container">
					<p>닉네임</p>
					<input type="text" name="mname" value="${member.mName}" autocomplete="off">
				</div>
				<div class="my_page_pw_container">
					<p>비밀번호</p>
					<input type="password" name="mpw" value="${member.mPw}" autocomplete="off">
				</div>
				<div class="my_page_pw_check_container">
					<p>비밀번호 확인</p>
					<input type="password" name="mpwcheck" value="${member.mPw}" autocomplete="off">
				</div>
				<input class="my_page_modify_btn" type="submit" value="확인">
			</form>
		</div>
	</div>
	
</body>
</html>