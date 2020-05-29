<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<body>
<%@ include file="/WEB-INF/views/inc/nav.jsp"%>

<div>
	<div>프로필 이미지 ${member.mProfileImg}</div>
	<div>아이디 : ${member.mId}</div>
	<div>닉네임 : ${member.mName}</div>
	<div>비밀번호 : ${member.mPw}</div>
</div>

<button onclick="location.href='${contextPath}/mypage/modifyform'">수정</button>
<button onclick="removeMember(${member.mNum})">탈퇴</button>
<script>
	function removeMember(mNum) {
		if(confirm("탈퇴하시겠습니까?") == true) {
			location.href = "${contextPath}/mypage/removemember?mnum=" + mNum;
		}
	}
</script>
</body>