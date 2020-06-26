<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/member.css"/>
</head>
<script type="text/javascript">

	var idPwreg = /^[a-z0-9]{8,20}$/;
	var nameReg  = /^[a-z0-9가-힣]{2,20}$/;

	$(function() {
		/* submit 이벤트 발생시 <input> 의 값의 유효성 체크하여 회원가입을 진행 */
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
		
		/* blur 이벤트 발생시 <input> 의 값의 유효성 체크 */
		$(".inputVal").on("blur", function() {
			input_validation_check($(this));
		});
		
	});
	
	/* <input> 값 유효성 체크 */
	function input_validation_check(currentInput){
		var inputValue = currentInput.val();
		if (inputValue == "") {
			currentInput.next().text("필수 정보입니다.");
			return false;
		} else {
			var nameVal = currentInput.attr('name');
			var validation_check = currentInput.next();
			validation_check.text("");
			
			/* 중복 아이디 체크 */
			if (nameVal == "userid") {
				if(inputValue.match(idPwreg) != null) {
					$.ajax({
						url : "${contextPath}/member/checkUserId",
						data : {"userId" : inputValue},
						dataType : "json",
						success : function(result) {
							if(result) {
								validation_check.text("이미 사용 중인 아이디입니다.");
								return false;
							} else {
								validation_check.text("멋진 아이디네요!");
							}
						}
					});
				} else {
					validation_check.text("8~20자의 영문 소문자 숫자만 사용 가능합니다.");
					return false;
				}
			} else if (nameVal == "userpassword" && inputValue.match(idPwreg) == null) {
				validation_check.text("8~20자의 영문 소문자 숫자만 사용 가능합니다.");
				return false;
			} else if (nameVal == "userpasswordcheck") {
				var password1 = $("input[name='userpassword']").val();
				var password2 = $("input[name='userpasswordcheck']").val();
				if(password1 != password2){
					validation_check.text("비밀번호가 일치하지 않습니다.");
					return false;
				}
			} else if (nameVal == "username" && inputValue.match(nameReg) == null) {
				validation_check.text("2~20자의 영문 소문자, 숫자와 한글만 사용 가능합니다.");
				return false;
			}
		}

	}
	
</script>
<body>

	<div class="container">
		<div class="sign_up_container">
			<a class="img_container" href="${contextPath }/main">
				<img src="${contextPath}/img/logo_white.png" alt="클랏 로고 이미지 입니다."/>
			</a>
			<form action="signupmember" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<p class="title">아이디</p>
				<input class="inputVal" type="text" name="userid" autocomplete="off">
				<p class="validation_check"></p>
				
				<p class="title">비밀번호</p>
				<input class="inputVal" type="password" name="userpassword" autocomplete="off">
				<p class="validation_check"></p>
				
				<p class="title">비밀번호 재확인</p>
				<input class="inputVal" type="password" name="userpasswordcheck" autocomplete="off">
				<p class="validation_check"></p>
				
				<p class="title">이름</p>
				<input class="inputVal" type="text" name="username" autocomplete="off">
				<p class="validation_check"></p>
				
				<input type="submit" value="가입하기">
			</form>
			<a href="${contextPath}/member/signinform">로그인</a>
		</div>
	</div>
	
</body>
</html>