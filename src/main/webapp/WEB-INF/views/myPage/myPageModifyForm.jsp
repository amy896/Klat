<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<body>
<%@ include file="/WEB-INF/views/inc/nav.jsp"%>

<div>
	<form action="modifymember" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div>프로필 이미지
		</div>
		<div>닉네임 :
			<input type="text" name="mname" value="${member.mName}" autocomplete="off">
		</div>
		<div>비밀번호 :
			<input type="password" name="mpw" value="${member.mPw}" autocomplete="off">
		</div>
		<div>비밀번호 확인 :
			<input type="password" name="mpwcheck" value="${member.mPw}" autocomplete="off">
		</div>
		<input type="submit" value="수정">
		
	</form>
</div>

</body>