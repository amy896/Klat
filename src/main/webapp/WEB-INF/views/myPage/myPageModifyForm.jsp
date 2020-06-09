<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/myPage.css"/>
<script>
$(function(){
	
	$(".my_page_profile_container").on("click",function(){
		$(".profile_img_modal").fadeIn(300);
		var currentClassName = $(".mProfileImg").val();
		$("." + currentClassName).children("div").addClass("checked");
	});
	
	$(".close_btn").on("click",function(){
		$(".profile_img_modal").fadeOut(300);
	});

	$(".profile_img_list li").on("click", function(){
		$(".profile_img_list li div").removeClass("checked");
		//$(this).children("div").addClass("checked");
		var className = $(this).attr("class");
		$(".mProfileImg").val(className);
		$(".my_page_profile_container img").attr("src","${contextPath}/img/"+className+".png");
		$(".profile_img_modal").fadeOut(300);
	});
	
	$(".container").mousedown(function(e){
	if(!$(".profile_img_modal").is(e.target) && $(".profile_img_modal").has(e.target).length===0)
		$(".profile_img_modal").fadeOut(300);
	});
});
</script>

</head>

<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
		
	<div class="container">
		<div class="my_page_modify_box">
			<form action="${contextPath}/mypage/modifymember"class="modify_member_form" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<input class="mProfileImg" type="hidden" name="mProfileImg" value="${member.mProfileImg}">
				<div class="my_page_profile_container">
					<img src="${contextPath}/img/${member.mProfileImg}.png" alt="프로필 이미지입니다">
				</div>
				<div class="my_page_name_container">
					<p>닉네임</p>
					<input type="text" name="mname" value="${member.mName}" autocomplete="off">
				</div>
				<div class="my_page_pw_container">
					<p>비밀번호</p>
					<input type="password" name="mpw" value="${member.mPw}" autocomplete="off">
				</div>
				<div class="my_page_pw_check_container">
					<p>비밀번호 확인</p>
					<input type="password" name="mpwcheck" value="${member.mPw}" autocomplete="off">
				</div>
				<input class="my_page_modify_btn" type="submit" value="확인">
			</form>
		</div>
		
		<div class="profile_img_modal">
			<h3>프로필 이미지를 선택해주세요!</h3>
			<ul class="profile_img_list">
				<li class="type0">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type1">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type2">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type3">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type4">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type5">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type6">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type7">
					<div><i class="fas fa-check"></i></div>			
				</li>
				<li class="type8">
					<div><i class="fas fa-check"></i></div>			
				</li>
			</ul>
			<div class="close_btn">
				<i class="fas fa-times-circle"></i>
			</div>
		</div>
		
		
	
	</div>
		
</body>
</html>