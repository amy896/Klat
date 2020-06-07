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
	
	function exitChatRoom(crnum){
		$.ajax({
			url : "${contextPath}/chat/exitchatroom",
			method : "get",
			data : {"crnum":crnum},
			dataType : "json",
			success: function(data){
				alert(data.exitChatroomResult);
				alert(data.crnum);
				alert(data.receiverName);
				if(data.exitChatroomResult){
					stompClient.send("/client/sendChatMessage/-1/"+data.crNum, {}, data.receiverName+"님이 퇴장하셨습니다.");
					window.location.href="${contextPath}/chat/chatmain";
				}else{
					alert("채팅방 나가기 실패");
				}
			},
			error: function(){
				alert("채팅방 나가기 에러 발생");
			}
		});	
	}
	
</script>
<div class="header_container">
	<a class="img_container" href="${contextPath }/chat/chatmain">
		<img src="${contextPath}/img/logo_white.png" alt="클랏 로고입니다"/>
	</a>

	<div class="chat_room_title">
		<input type="text" id="new_chat_room_title" value="${chatroom.crTitle}" readonly="readonly">
		<button onclick="change()">수정</button>
	</div>
	
	<script type="text/javascript">
		$("#new_chat_room_title").dblclick(function() {
			$("#new_chat_room_title").attr("readonly", false);
			$("#new_chat_room_title").css({backgroundColor : "white", color : "black"});
			$(".chat_room_title button").css("display", "inline-block");
		})
		
		function change() {
			var crNum = $(".crNum").val();
			var crTitle = $("#new_chat_room_title").val();
			$.ajax({
				url : "${contextPath}/chat/modifychatroom",
				data : {
					"crnum" : crNum,
					"crtitle" : crTitle
				},
				dataType : "json",
				success : function(result) {
					$("#new_chat_room_title").attr("readonly", true);
					$(".chat_room_title button").css("display", "none");
					location.reload();
				},
				error : function(request, status, error) {
					alert("request:" + request + " status:" + status + " error:" + error);
				}
			})
		}
	</script>
	
	<div class="invite_member_btn" onclick="location.href='${contextPath}/chat/inviteform'">
		<i class="fas fa-user-friends"></i>
	</div>
	<div class="exit_chatroom_btn" onclick="exitChatRoom(${chatroom.crNum})">
		<i class="fas fa-sign-out-alt"></i>
	</div>
</div>