<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<script>
	$(function() {
		$(".chat_room_title > p").text("채팅방 이름 들어갈 자리");
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
	}
</script>

header
<div class="chat_room_title">
	<p></p>
	<input type="text" id="new_chat_room_title" placeholder="new title please">
	<button onclick="change()">수정</button>
</div>
<hr>