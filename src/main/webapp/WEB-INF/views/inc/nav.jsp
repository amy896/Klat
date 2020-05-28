<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<script>

$(function() {
	loadChatRoomList();
});

function loadChatRoomList() {
	var chatRoomList = $(".chatRoomList");
	$.ajax({
		url : "${contextPath}/chat/getchatroomlist",
		dataType : "json",
		success : function(data) {
			chatRoomList.empty();
			$.each(data, function (index, item) {
				var str = "<div onclick='enterChatRoom("+item.crNum+")'>"+item.crTitle+"</div>";
				chatRoomList.append(str);
			})
		}
	})
}

function enterChatRoom(crNum) {
	location.href="${contextPath}/chat/chatroom?crnum="+crNum;
}

</script>
navigation
<div onclick="location.href='${contextPath}/chat/addform'">채팅 추가</div>
<div class="chatRoomList">
</div>
<hr>
