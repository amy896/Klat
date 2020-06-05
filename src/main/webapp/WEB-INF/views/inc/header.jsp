<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<c:set var="member" value="<%=request.getSession().getAttribute(\"member\")%>" />
<script>
	var sock;
	var stompClient;
	
	$(function() {
		socketConnect();
		var pageType = $(".pageType").val();
		if(pageType == "chatroom"){
			var chatTitle = $(".crTitle").val();
			$(".chat_room_title > p").text(chatTitle);
		}else{
			$(".header_container > div").hide();
		}
	});

	function change() {
		var crNum = 1;
		var crTitle = $("#new_chat_room_title").val();
		$.ajax({
			url : "${contextPath}/chat/modifychatroom",
			data : {
				"crnum" : crNum,
				"crtitle" : crTitle
			},
			dataType : "json",
			success : function(result) {
				$("#new_chat_room_title").val('Default Value');
				$(".chat_room_title > p").empty();
				$(".chat_room_title > p").text(crTitle);
			},
			error : function(request, status, error) {
				alert("request:" + request + " status:" + status + " error:" + error);
			}
		})
	}// end change()
	
	function socketConnect(){
		sock = new SockJS("${contextPath}/chat");
		stompClient = Stomp.over(sock);
		stompClient.connect({}, function() {
			var crNum = $(".crNum").val();
			stompClient.subscribe("/category/msg/"+crNum, function(cm) {
				msgInfo = JSON.parse(cm.body);
				addMessage(msgInfo);
			});
		});
	}//end socketConnect()
	
</script>
<div class="header_container">
	<a class="img_container" href="${contextPath }/chat/chatmain">
		<img src="${contextPath}/img/logo_white.png" alt="클랏 로고입니다"/>
	</a>

	<div class="chat_room_title">
		<p></p>
		<input type="text" id="new_chat_room_title" placeholder="new title please">
		<button onclick="change()">수정</button>
	</div>
	
	<div class="invite_member_btn" onclick="location.href='${contextPath}/chat/inviteform'">
		<i class="fas fa-user-friends"></i>
	</div>
	<div class="exit_chatroom_btn" onclick="location.href='${contextPath}/chat/exitchatroom?crnum=${chatInfo.chat.crNum}'">
		<i class="fas fa-sign-out-alt"></i>
	</div>
</div>