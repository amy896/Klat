<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>

$(function(){

	$("#send_chat_message_btn").on("click", function() {
		sendMessage();
	});
	
	function sendMessage() {
		var cmContent = $("#chat_message").val();
		stompClient.send("/client/sendChatMessage/"+$(".mNum").val()+"/"+$(".crNum").val(), {}, cmContent);
		$("#chat_message").val("");
	}
	
});

function addMessage(msgInfo) {
	var chatMsg = $("<div class='chat_message_box'>");
	var content = "<div>"+msgInfo.cmContent+"</div>";
	var writeDate = new Date(msgInfo.cmWriteDate);
	
	var processedWriteDate;
	var hour = writeDate.getHours();
	var minute = writeDate.getMinutes();
	
	if(hour > 12) {
		processedWriteDate = (Number(hour) - Number(12)) + ":" + minute + " PM";
	} else if (hour == 12) {
		processedWriteDate = hour + ":" + minute + " PM";
	} else {
		processedWriteDate = hour + ":" + minute + " AM"; 
	}
	
	chatMsg.append("<div>"+msgInfo.mName+"</div>"
				  +"<div>"+processedWriteDate+"</div>"
				  +content);

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
						<div>${chatMessage.mProfileImg}</div>
						<div>${chatMessage.mName}</div>
						<div><fmt:formatDate value="${chatMessage.cmWriteDate}" pattern="hh:mm"/></div>
						<div>${chatMessage.cmContent}</div>
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