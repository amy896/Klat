<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
<script>
$(function(){
	
	$(".img_upload_btn").on("click",function(){
		var upload_fiile_form = $("#upload_fiile_form")[0];
		var formData = new FormData(upload_fiile_form);
		$.ajax({
			url : "${contextPath}/chat/uploadFile",
			data : formData,
			processData : false,
			contentType : false,
			enctype: "multipart/form-data",
			type : "post",
			dataType : "json",
			success : function(jsonStr){
				var cmNum = jsonStr.cmNum;
				var fileName = jsonStr.fileName;
				var originName = jsonStr.originName;
				sendFile(fileName,originName,cmNum);
			},
			error : function(){
				alert("파일전송 에러발생");
			}
		});
	});
	
	
	function sendFile(fileName,originName,cmNum){
		var result = $("input [name='mNum']").val();
		stompClient.send("/client/sendFile/"+${sessionScope.member.mNum}+"/"+$(".crNum").val()+"/"+cmNum+"/"+originName);
	}

})

</script>

</head>
<body>	
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>

	<div class="container">
		<div class="chat_container">
			<input type="hidden" class="pageType" value="chatroom">
			<input type="hidden" class="crNum" value="${chatroom.crNum }">
			
			<div class="chat_message_list_container">
				<c:forEach items="${chatMessageList}" var="chatMessage" varStatus="status">
					<div class="chat_message_box">
						<div>${chatMessage.mNum}</div>
						<div>${chatMessage.cmWriteDate}</div>
						<div>${chatMessage.cmContent}</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="chat_message_input_box">
				<button>코드</button>
				<button>이미지</button>
				<textarea></textarea>
				<button>전송</button>
			</div>
		</div>
		
		<div class="codeModal">
			<form>
			
			
			</form>
		
		</div>

		<div class="uploadImage modal" style="border : 1px solid black">
			<form id="upload_fiile_form" method="post" enctype="multipart/form-data">
				<input type="hidden" value="${wNum}" name="wNum"> 
				<input type="text" value="${sessionScope.member.mNum}" name="mNum"> 
				<input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
				<input type="file" name="uploadimg" value="파일 선택" accept="image/*">
				<a href="#" class="img_upload_btn">업로드</a>
			</form>
		</div>
	</div>
</body>
</html>