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
			<div class="my_page_profile_container">
				<img src="${contextPath}/img/${member.mProfileImg}.png" alt="프로필 이미지입니다">
			</div>
			<div class="my_page_id_container">
				<p>아이디</p>
				<p>${member.mId}</p>
			</div>
			<div class="my_page_name_container">
				<p>닉네임</p>
				<p>${member.mName}</p>
			</div>
			<button class="my_page_modify_btn" onclick="location.href='${contextPath}/mypage/modifyform'">수정</button>
		</div>
		<div class="my_page_btn_box">
			<button class="my_page_remove_btn" onclick="removeMember(${member.mNum})">탈퇴하시겠습니까?</button>
		</div>
	</div>

<script>
	/* by 혜선, 탈퇴 버튼 클릭 시 탈퇴 */
	function removeMember(mNum) {
		if(confirm("탈퇴하시겠습니까?") == true) {
			location.href = "${contextPath}/mypage/removemember?mnum=" + mNum;
		}
	}
</script>
</body>
</html>