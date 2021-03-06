<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
	
	<div class="container">
		<input type="hidden" class="pageType" value="chatmain">
		<div class="chat_room_list_title">
			채팅 목록
		</div>
		<button class="chat_add_btn" onclick="location.href='${contextPath}/chat/addform'"><i class="fas fa-comment-medical"></i></button>
		
		<c:forEach items="${chatInfoList}" var="chatInfo">
			<div class="chatInfo_container">
				<div class="chat_room_title" onclick="location.href='${contextPath}/chat/chatroom?crnum='+${chatInfo.chat.crNum}">
					${chatInfo.chat.crTitle}
				</div>
				<div class="chat_exit_btn" onclick="location.href='${contextPath}/chat/exitchatroom?crnum=${chatInfo.chat.crNum}'">
					<i class="fas fa-sign-out-alt"></i>
				</div>
				
				<ul class="member_list_container">
					<c:forEach items="${chatInfo.chatMemberList}" var="member">
						<li class="member_container">
							<div class="profile_img_container">
								<img src="${contextPath}/img/${member.mProfileImg}.png" alt="프로필 이미지입니다">
							</div>
							<p class="member_name">${member.mName}</p>	
						</li>
					</c:forEach>
					<li class="member_container">
						<div class="chat_invite_btn" onclick="location.href='${contextPath}/chat/inviteform?crnum='+${chatInfo.chat.crNum}">
							<i class="fas fa-plus"></i>
						</div>
						<p class="member_name">.</p>	
					</li>
				</ul>
			</div>
		</c:forEach>
	</div>
</body>
</html>