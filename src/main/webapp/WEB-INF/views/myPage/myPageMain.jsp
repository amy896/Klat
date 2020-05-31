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
		<div>
			<div>프로필 이미지 ${member.mProfileImg}</div>
			<div>아이디 : ${member.mId}</div>
			<div>닉네임 : ${member.mName}</div>
			<div>비밀번호 : ${member.mPw}</div>
		</div>
		
		<button onclick="location.href='${contextPath}/mypage/modifyform'">수정</button>
		<button onclick="removeMember(${member.mNum})">탈퇴</button>
			
	</div>

<script>
	function removeMember(mNum) {
		if(confirm("탈퇴하시겠습니까?") == true) {
			location.href = "${contextPath}/mypage/removemember?mnum=" + mNum;
		}
	}
</script>
</body>
</html>