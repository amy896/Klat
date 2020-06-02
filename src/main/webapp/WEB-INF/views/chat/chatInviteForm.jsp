<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/chat.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
	<div class="container">
	
		<div class="chat_invite_form_container">
			<p>초대하고 싶은 회원님의 아이디를 입력해주세요.</p>
			<form action="invitemember" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<input class="search_member_id" type="text" placeholder="invite someone">
				<input class="invite_member_btn" type="submit" value="초대">
				
				<ul class="search_member_list_container"></ul>
				
				<script type="text/javascript">
					$(function() {
						$(".search_member_id").on("keyup", function() {
							var keyword = $(".search_member_id").val();
							console.log(keyword);
							
							if(keyword != "") {
								$.ajax({
									url : "${contextPath}/chat/searchmemberlist",
									data : {"keyword" : keyword},
									dataType : "json",
									
									success : function(memberList) {
										$(".search_member_list_container li").remove();
										$(".search_member_list_container").show();
										if(memberList.length > 0) {
											for(var i in memberList) {
												var li = $("<li class='search_member_list'>");
												li.text(memberList[i]);
												(function(member) {
													li.on("click", function() {
														$(".search_member_id").val(memberList[member]);
														$(".search_member_list_container").hide();
														$(".invite_member_btn").attr("disabled", false);
													})
												})(i)
												$(".search_member_list_container").append(li);
											}
										} else {
											var li = $("<li class='no_search_member_list'>");
											li.text("그런 회원 없음");
											$(".search_member_list_container").append(li);
										}
									},
									
									error : function(request, status, error) {
										alert("request:" + request + " status:" + status + " error:" + error);
									}
								});
							}
						})
					})
				</script>

			</form>
		</div>
	</div>
</body>