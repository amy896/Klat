<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/member.css"/>
</head>
<script type="text/javascript">

	var idRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var pwRegExp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;

	$(function() {
		$("input").on("blur", function() {
			var inputValue = $(this).val();

			if (inputValue == "") {
				$(this).next().text("필수 정보입니다.");
			} else {
				$(this).next().text("");
				var nameVal = $(this).attr('name');
				
				if (nameVal == "userid") {
					if(inputValue.match(regExp) != null) {
						$.ajax({
							url : "${contextPath}/member/checkUserId",
							data : {"userId" : inputValue},
							dataType : "json",
							success : function(result) {
								if (result) {
									$(this).next().text("이미 사용 중인 아이디입니다.");
								} else {
									$(this).next().text("멋진 아이디네요!");
								}
							}
						});
					} else {
						$(this).next().text("이메일 형식을 입력해주세요.");
					}

				} else if (nameVal == "") {

				} else if (nameVal == "") {

				} else if (nameVal == "") {

				}

			}
		});
	})
</script>
<body>

	<div class="container">
		<div class="sign_up_container">
			<a class="img_container" href="${contextPath }/main">
				<img src="${contextPath}/img/logo_black.png" alt="클랏 로고 이미지 입니다."/>
			</a>
			<form action="signupmember" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<p class="title">ID</p>
				<input type="text" name="userid" autocomplete="off">
				<p class="validation_check"></p>
				
				<p class="title">User name</p>
				<input type="text" name="username" autocomplete="off">
				<p class="validation_check"></p>
				
				<p class="title">Password</p>
				<input type="password" name="userpassword" autocomplete="off">
				<p class="validation_check"></p>
				
				<p class="title">Password check</p>
				<input type="password" name="userpasswordCheck" autocomplete="off">
				<p class="validation_check"></p>
				
				<input type="submit" value="Sign up">
			</form>
			<a href="${contextPath}/member/signinform">Sign in to Klat</a>
		</div>
	</div>
	
</body>
</html>