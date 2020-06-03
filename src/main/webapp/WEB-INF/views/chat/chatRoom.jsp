<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/lib/codemirror/lib/codemirror.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/lib/codemirror/theme/gruvbox-dark.css" />
<script type="text/javascript" src="${contextPath}/lib/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/addon/edit/closetag.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/addon/hint/show-hint.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/addon/hint/css-hint.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/mode/javascript/javascript.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/mode/css/css.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/mode/clike/clike.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/mode/xml/xml.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/mode/sql/sql.js"></script>
<script type="text/javascript" src="${contextPath}/lib/codemirror/mode/php/php.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>

var type;
var codeEditor;

$(function(){
	
	type = $(".codeType option:selected").val();
	codeEditor = CodeMirror.fromTextArea($('#codeEditor')[0],{
		mode : type,
		theme : "gruvbox-dark",
		lineNumbers : true,
		autoCloseTags : true
	});
	codeEditor.setSize("446", "300");

	$(".upload_code_btn").on("click",function(){
		sendCode();
	});
  
	$(".upload_img_btn").on("click",function(){
		var upload_fiile_form = $("#upload_img_form")[0];
		var formData = new FormData(upload_fiile_form);
		$.ajax({
			url : "${contextPath}/chat/uploadImageFile",
			data : formData,
			processData : false,
			contentType : false,
			enctype: "multipart/form-data",
			type : "post",
			dataType : "json",
			success : function(saveFileInfo){
				var fileName = saveFileInfo.fileName;
				var originFileName = saveFileInfo.originFileName;
				sendImageFile(fileName,originFileName);
			},
			error : function(){
				alert("파일전송 에러발생");
			}
		});
	});
	
	function sendCode(){
		var code = codeEditor.getValue();
		if(type.includes('/')){
			type = 'java';
		}
		stompClient.send("/client/sendCode/"+$(".mNum").val()+"/"+$(".crNum").val()+"/"+type, {}, code);
	};
	
	function sendImageFile(fileName,originFileName){
		stompClient.send("/client/sendImageFile/"+$(".mNum").val()+"/"+$(".crNum").val(), {}, originFileName);
	};

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
	
	var content;
	if(msgInfo.cmType == 'message') {
		content = msgInfo.cmContent;
	} else if(msgInfo.cmType == 'code') {
		
	} else if(msgInfo.cmType == 'img') {
		
	} 
	
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
	
	chatMsg.append("<div>"+msgInfo.mNum+"</div>"
				  +"<div>"+processedWriteDate+"</div>"
				  +"<div>"+content+"</div>");

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
						<div>${chatMessage.mNum}</div>
						<div><fmt:formatDate value="${chatMessage.cmWriteDate}" pattern="hh:mm"/></div>
						<div>${chatMessage.cmContent}</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="chat_message_input_box">
				<button>코드</button>
				<button>이미지</button>
				<textarea id="chat_message" placeholder="write a message!"></textarea>
				<button id="send_chat_message_btn">전송</button>
			</div>
			
		</div>
				
		<div class="uploadCode modal" style="border : 1px solid black">
			<select name="codeType" class="codeType">
				<option value="text/x-java">java</option>
				<option value="javascript">javascript</option>
				<option value="css">css</option>
				<option value="xml">xml</option>
				<option value="sql">sql</option>
				<option value="php">php</option>
			</select>
			<textarea id="codeEditor"></textarea>
			<a href="#" class="upload_code_btn">업로드</a>
		</div>

		<div class="uploadImage modal" style="border : 1px solid black">
			<form id="upload_img_form" method="post" enctype="multipart/form-data">
				<input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
				<input type="file" name="uploadimg" value="파일 선택" accept="image/*">
				<a href="#" class="upload_img_btn">업로드</a>
			</form>
		</div>
	</div>
</body>
</html>