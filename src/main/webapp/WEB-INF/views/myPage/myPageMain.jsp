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
			<a class="my_page_profile_img" href="${contextPath}/mypage/mypagemain">
				<img src="${contextPath}/member/showProfileImg?mnum=${sessionScope.member.mNum}" alt="프로필 이미지입니다">
			</a>
			<div>
				<p>아이디</p>
				${member.mId}
			</div>
			<div>
				<p>닉네임</p>
				${member.mName}
			</div>
			<div>
				<p>비밀번호</p>
				${member.mPw}
			</div>
			<button onclick="location.href='${contextPath}/mypage/modifyform'">수정</button>
			<button onclick="removeMember(${member.mNum})">탈퇴</button>
		</div>
		
			
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