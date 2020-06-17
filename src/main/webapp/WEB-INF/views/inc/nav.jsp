<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

$(function() {
	loadChatRoomList();
	var pageType = $(".pageType").val();
	if(pageType == "chatmain"){
		$(".chat_list_container").hide();
	}
	
});

function loadChatRoomList() {
	var crNum = $(".crNum").val();
	var chatRoomList = $(".chatRoomList");
	$.ajax({
		url : "${contextPath}/chat/getchatroomlist",
		dataType : "json",
		success : function(data) {
			chatRoomList.empty();
			$.each(data, function (index, item) {
				var str = "<div "+(crNum ==item.crNum?'class="currentChat"':"")+" onclick='enterChatRoom("+item.crNum+")'>"+item.crTitle+"</div>";
				chatRoomList.append(str);
			})
			
		}
	})
}

function enterChatRoom(crNum) {
	location.href="${contextPath}/chat/chatroom?crnum="+crNum;
}

</script>
<div class="navigation_container">
	<div class="profile_container">
		<a class="profile_img_container" href="${contextPath}/mypage/mypagemain">
			<img src="${contextPath}/img/${sessionScope.member.mProfileImg}.png" alt="프로필 이미지입니다">
		</a>
		<a href="${contextPath}/mypage/mypagemain"><span class="member_name">${sessionScope.member.mName}</span> 님</a>	
	</div>
	<form action="${contextPath}/member/signoutmember" method="post">
		<input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
		<button>로그아웃</button>
	</form>
	<div class="chat_list_container">
		<p class="title">채팅 목록</p>
		<button class="nav_chat_add_btn" onclick="location.href='${contextPath}/chat/addform'"><i class="fas fa-comment-medical"></i></button>
		<div class="chatRoomList"></div>
	</div>
</div>
