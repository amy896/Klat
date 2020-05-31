<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/member.css"/>
<script type="text/javascript">

	var idPwreg = /^[a-z0-9]{8,20}$/;
	var nameReg  = /^[a-z0-9가-힣]{2,20}$/;

	$(function() {
		$("form").on("submit", function(){
			var resultArr = new Array;
			$(".inputVal").each(function(){
				var result = input_validation_check($(this));
				resultArr.push(result);			
			});
			for(var i=0;i<resultArr.length;i++){
				if(resultArr[i] == false){
					return false;
				}
			}
		});

		$(".inputVal").on("blur", function() {
			input_validation_check($(this));
		});
		
	});
	
	function input_validation_check(currentInput){
		var inputValue = currentInput.val();
		if (inputValue == "") {
			currentInput.next().text("필수 정보입니다.");
			return false;
		} else {
			var validation_check = currentInput.next();
			validation_check.text("");
		}
	}
	
</script>
</head>
<body>

	<div class="container">
		<div class = "sign_in_container">
			<a class="img_container" href="${contextPath }/main">
				<img src="${contextPath}/img/logo_black.png" alt="클랏 로고 이미지 입니다."/>
			</a>
			<form action="signinmember" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<p class="title">아이디</p> 
				<input class="inputVal" type="text" name="userid" autocomplete="off">
				<p class="validation_check"></p>
				
				<p class="title">비밀번호</p> 
				<input class="inputVal" type="password" name="userpassword" autocomplete="auto">
				<p class="validation_check"></p>
				
				<input type="submit" value="로그인">
			</form>
			<a href="${contextPath}/member/signupform">회원 가입</a>
		</div>
	</div>

</body>
</html>