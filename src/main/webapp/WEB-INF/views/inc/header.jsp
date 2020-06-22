<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<c:set var="member" value="<%=request.getSession().getAttribute(\"member\")%>" />
<script>
	var sock;
	var stompClient;
	var index;
	var offset;
	var regex;
	var search_chat_keyword;
	var chat_message_array = null;
	
	$(function() {
		socketConnect();
		var pageType = $(".pageType").val();
		if(pageType == "chatroom"){
			var chatTitle = $(".crTitle").val();
			$(".chat_room_title > p").text(chatTitle);
		}else{
			$(".header_container > div").hide();
		}
		
		/* 검색 영역 toggle */
		$(".search_chat_btn").on("click", function(){
			$(".search_chat_container").toggle();
			searchCofigReset();
		});
		
		/* 엔터를 누르면 검색하기 */
		$(".search_chat_input").on("keydown", function(e){
			if(e.keyCode == 13){
				chat_message_array = new Array();
				search_chat_keyword = $(".search_chat_input").val();
				regex = new RegExp(search_chat_keyword,"");

				$('.chat_message_box_content').each(function (index, item) { 
					var chat_message = $(item).text();
					if(chat_message.includes(search_chat_keyword)){
						chat_message_array.push($(item));}
				});

				if(chat_message_array.length==0){
					alert("검색결과가 없습니다.");
				}else{
					index = chat_message_array.length-1;
					searchResultFoucus(index);
				} 
			}
		});
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
	
	function searchResultUp(){
		index = index-1;
		searchResultFoucus(index);
	}
	
	function searchResultDown(){
		index = index+1;
		searchResultFoucus(index);
	}
	
	function searchResultFoucus(index){
		$("span").removeClass('search_highlighter');
		var current_message = chat_message_array[index];
		current_message.html(current_message.text().replace(regex, "<span class='search_highlighter'>"+search_chat_keyword+"</span>"));
		offset = chat_message_array[index].offset();
		console.log(offset.top);
		$(".chat_message_list_container").scrollTop(offset.top);
		$(".search_result_up").attr('disabled', false);
		$(".search_result_down").attr('disabled', false);
		
		if(index == 0){
			$(".search_result_up").attr('disabled', true);}
		if(index == chat_message_array.length-1){
			$(".search_result_down").attr('disabled', true);}
	}
	
	function searchCofigReset(){
		$(".search_chat_input").val("");
		$(".search_result_up").attr('disabled', true);
		$(".search_result_down").attr('disabled', true);
		$("span").removeClass('search_highlighter');
		chat_message_array = new Array();
	}
	
	function closeSearchContainer(){
		$(".search_chat_container").hide();
		searchCofigReset();
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
			$("#new_chat_room_title").css({backgroundColor : "rgba(255, 255, 225, 0.1)", border : "1px solid white"});
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
	<div class="search_chat_btn">
		<i class="fas fa-search"></i>
	</div>
	<div class="search_chat_container">
		<input class="search_chat_input" type="text" placeholder="대화내용 검색">
		<button class="search_result_up" onclick="searchResultUp()"><i class="fas fa-chevron-up"></i></button>
		<button class="search_result_down" onclick="searchResultDown()"><i class="fas fa-chevron-down"></i></button>
		<button onclick="closeSearchContainer()"><i class="fas fa-times"></i></button>
	</div>
	<div class="invite_member_btn" onclick="location.href='${contextPath}/chat/inviteform'">
		<i class="fas fa-user-friends"></i>
	</div>
	<div class="exit_chatroom_btn" onclick="location.href='${contextPath}/chat/exitchatroom?crnum=${chatroom.crNum}'">
		<i class="fas fa-sign-out-alt"></i>
	</div>
</div>