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

	/* 엔터 누르면 채팅 메시지 보내기 */
	$("#chat_message").on("keydown", function(e){
		if(e.keyCode == 13 && !e.shiftKey){
			e.preventDefault();
			sendMessage();
		}
	});
	
	/* by 혜선, 채팅 메시지 전송 및 입력창 비우기 */
	function sendMessage() {
		var cmContent = $("#chat_message").val();
		stompClient.send("/client/sendChatMessage/"+$(".mNum").val()+"/"+$(".crNum").val(), {}, cmContent);
		$("#chat_message").val("");
	}
});

/* by 혜선, 기존의 모든 채팅 메시지 불러오기 */
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
			alert("채팅 메시지를 불러오는데 실패했습니다.");
		}
	})
}

/* 날짜 */
var todayDate;

/* by 혜선, 날짜 메시지 띄우기 */
function showDateMessage(year, month, date){
	var dateMessageDiv = $("<div class='date_msg' align='center'><div>"+year+" - "+month+" - "+date+"</div></div>");
	$(".chat_message_list_container").append(dateMessageDiv);
	todayDate = date;
}

/* by 혜선, 채팅 메시지 종류 구분하여 그린 후 채팅창에 띄우기 */
function addMessage(msgInfo) {
	/* 채팅 메시지 작성자 구분하기 */
	var msgType;
	var mNum = $(".mNum").val();
	
	if(msgInfo.mNum == mNum) {
		msgType = "my_msg";
	} else {
		msgType = "their_msg";
	}
	
	var chatMsg = $("<div class='chat_message_box "+msgType+"'>");
	
	/* 채팅 메시지 시간 */
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
	
	/* 오늘 날짜(todayDate)와 만드는 chatMessage의 날짜가 같지 않은 경우  */
	if(todayDate != writeDate.getDate() && todayDate != 0){
		showDateMessage(writeDate.getFullYear(), Number(writeDate.getMonth())+Number(1), writeDate.getDate());
	}
	
	/* 채팅 메시지 & 입장퇴장 메시지 그리는 부분 */
	if(msgInfo.cmType == 'message') {
		chatMsg.append("<div class='chat_message_box_img'><img src='${contextPath}/img/"+msgInfo.member.mProfileImg+".png'></div>"
				  +"<div class='chat_message_box_text'>"
				  +"<span class='chat_message_box_name'>"+msgInfo.member.mName+"</span>"
				  +"<span class='chat_message_box_date'>"+processedWriteDate+"</span>"
				  +"<p class='chat_message_box_content'>"+msgInfo.cmContent+"</p>"
				  +"</div>");		
	} else if(msgInfo.cmType == 'system') {
		chatMsg = $("<div class='chat_system_message_box'>");
		chatMsg.append("<div class='system_message'>"+msgInfo.cmContent+"</div>");
	}
	$(".chat_message_list_container").append(chatMsg);
	$(".chat_message_list_container").stop();
	$(".chat_message_list_container").animate({scrollTop: $(".chat_message_list_container")[0].scrollHeight});
	
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
			</div>
			
			<div class="chat_message_input_box">
				<textarea id="chat_message" placeholder="write a message!"></textarea>
				<button id="send_chat_message_btn">전송</button>
			</div>
		</div>
	</div>
</body>
</html>