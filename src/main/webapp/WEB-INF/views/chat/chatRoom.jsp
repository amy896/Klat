<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>

$(function(){

	loadAllMessage();
	
	$("#send_chat_message_btn").on("click", function() {
		sendMessage();
	});
	
	function sendMessage() {
		var cmContent = $("#chat_message").val();
		stompClient.send("/client/sendChatMessage/"+$(".mNum").val()+"/"+$(".crNum").val(), {}, cmContent);
		$("#chat_message").val("");
	}
	
});

function loadAllMessage() {
	var crNum = $(".crNum").val();
	
	$.ajax({
		url : "${contextPath}/chat/loadallmessage",
		data : {"crNum" : crNum},
		dataType : "json",
		success : function(data) {
			$.each(data, function(index, item) {
				addMessage(item);
			})
		},
		error : function(request, status, error) {
			alert("request:"+request+" status:"+status+" error:"+error);
		}
	})
}

function addMessage(msgInfo) {
	/* 이 채팅메시지는 누가 썼을까? */
	var msgType;
	var mNum = $(".mNum").val();
	if(msgInfo.mNum == mNum) {
		msgType = "my_msg";
	} else {
		msgType = "their_msg";
	}
	
	var chatMsg = $("<div class='chat_message_box "+msgType+"'>");
	
	/* 채팅메시지 시간 */
	var writeDate = new Date(msgInfo.cmWriteDate);
	var hour = writeDate.getHours();
	var minute = writeDate.getMinutes();
	
	var processedWriteDate;
	if(hour > 12) {
		processedWriteDate = (Number(hour) - Number(12)) + ":" + minute + " PM";
	} else if (hour == 12) {
		processedWriteDate = hour + ":" + minute + " PM";
	} else {
		processedWriteDate = hour + ":" + minute + " AM"; 
	}
	
	/* 채팅메시지 그리는 부분 */
	chatMsg.append("<div class='chat_message_box_img'></div>"
				  +"<div class='chat_message_box_text'>"
				  +"<span class='chat_message_box_name'>"+msgInfo.mName+"</span>"
				  +"<span class='chat_message_box_date'>"+processedWriteDate+"</span>"
				  +"<p class='chat_message_box_content'>"+msgInfo.cmContent+"</p>"
				  +"</div>");

	$(".chat_message_list_container").append(chatMsg);
}
</script>

</head>
<body>	
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>

	<div class="container">
		<div class="chat_container">
			<input type="hidden" class="pageType" value="chatroom">
			<input type="hidden" class="crNum" value="${chatroom.crNum }">
			<input type="hidden" class="mNum" value="${member.mNum }">
			
			<div class="chat_message_list_container">
				<c:forEach items="${chatMessageList}" var="chatMessage" varStatus="status">
					<div class="chat_message_box">
						<div class="chat_message_box_img"></div>
						<div class="chat_message_box_text">
							<span class="chat_message_box_name">${chatMessage.mName}</span>
							<span class="chat_message_box_date"><fmt:formatDate value="${chatMessage.cmWriteDate}" pattern="hh:mm"/></span>
							<p class="chat_message_box_content">${chatMessage.cmContent}</p>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="chat_message_input_box">
				<textarea id="chat_message" placeholder="write a message!"></textarea>
				<button id="send_chat_message_btn">전송</button>
			</div>
			
		</div>
	</div>
</body>
</html>