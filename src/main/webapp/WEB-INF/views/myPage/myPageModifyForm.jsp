<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/myPage.css"/>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.5/cropper.css"/>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.5/cropper.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.5/cropper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.5/cropper.min.js"></script>

<script>

var profileImgType = null;
var cropper = null;
var croppedCanvas = null;
var roundedCanvas = null;
var roundImg = null;
var profileImgType = null;
var cropImg = null;

$(function() {
	//이미지 크롭 모달 보여주기
	 $("#attachImgBtn").on('change', function() { //첨부파일이 변경되면
		 if (this.files && this.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$(".cropImgModal").fadeIn(300); // 이미지 크롭 모달창을 띄운다
					$('#cropImg').attr("src", e.target.result); // 모달의 이미지영역에 선택한 이미지를 노출하고
					cropImg = document.getElementById('cropImg');
					croppable = false;
					cropper = new Cropper(cropImg,{ //모달의 이미지 영역에 크로퍼 객체를 생성 하고, 옵션을 설정한다 (round 모양은 css로 처리)
						 viewMode: 1,
						 aspectRatio : 1
					});
				}
				reader.readAsDataURL(this.files[0]);
				if(cropper!= null){
					cropper.destroy();//크롭이미지 선택 영역 초기화						
				}
			}// end if	
	 	});//end change
			
	//크롭 모달창에서 [확인] 선택시 
	$(".cropImgBtn").on('click',function(){
		$("#profileImgType").attr("value","profileImgType");			
		croppedCanvas = cropper.getCroppedCanvas(); //해당 이미지를 저장하기 위한 객체를 생성한다
		roundedCanvas = getRoundedCanvas(croppedCanvas); //선택한 영역의 좌표값을  roundedCanvas에 넣어준다
		$('.my_page_profile_img img').attr('src',roundedCanvas.toDataURL()); // 가져온 이미지 데이터를 썸네일 이미지에 뿌려준다
		$("#attachImgBtn").val("");//첨부파일 초기화
		$(".cropImgModal").fadeOut(300);//해당 모달 창을 닫는다
		return false;
	});
	 	
	//이미지 크롭 모달 닫기
	$(".closeCropImgModal").on("click",function(){
		$("#attachImgBtn").val("");//첨부파일 초기화
		$(".cropImgModal").fadeOut(300);
		return false;
	});
	 		
	//모달 바깥쪽이 클릭되거나 다른 모달이 클릭될때 현재 모달 숨기기
	$(".container").mousedown(function(e){
		if(!$(".cropImgModal").is(e.target) && $(".cropImgModal").has(e.target).length===0)
			$(".cropImgModal").fadeOut(300);
		//return false;
	});

	//기본이미지로 변경
	$("#defalutImgBtn").on("click", function() {  
		$('.my_page_profile_img img').attr("src","${contextPath}/img/profileImage.png");
		$("#profileImgType").attr("value","defaultImg");
	});
	
	//[수정] 클릭
	$(".modify_member_form").on("submit", function(){
		var upload_fiile_form = $(".modify_member_form")[0];
		var formData = new FormData(upload_fiile_form);		  
		profileImgType = $("#profileImgType").val();
		if(profileImgType == "profileImgType"){
			cropper.getCroppedCanvas({
				width : 180,
				height: 180
			}).toBlob(function(blob) {
				  formData.set('croppedImage', blob);
				  $.ajax({
						url : "${contextPath}/mypage/modifymember",
						data : formData,
						processData : false,
						contentType : false,
						enctype: "multipart/form-data",
						type : "post",
						success : function(result){
							if(result){
								location.href="${contextPath}/mypage/mypagemain";
							}else{
								alert("수정 실패");
							}
						},
						error:function(request,status,error){
							alert("수정 실패");
						}
					}); //end ajax
			});
		} else{
			$.ajax({
				url : "${contextPath}/mypage/modifymember",
				data : formData,
				processData : false,
				contentType : false,
				enctype: "multipart/form-data",
				type : "post",
				success : function(result){
					if(result){
						location.href="${contextPath}/mypage/mypagemain";
					}else{
						alert("수정 실패");
					}
				},
				error:function(request,status,error){
					alert("수정 실패");
				}
			}); //end ajax
		}
		return false;
	});
	
});//end onload

//crop한 영역 canvas로 만들기
function getRoundedCanvas(sourceCanvas){
	var canvas = document.createElement('canvas');
	var context = canvas.getContext('2d');
	var width = sourceCanvas.width;
	var height = sourceCanvas.height;
	canvas.width = width;
	canvas.height = height;
	context.imageSmoothingEnabled = false;
	context.imageSmoothingQuality = 'high';
 	context.drawImage(sourceCanvas, 0, 0, width, height);
    context.globalCompositeOperation = 'destination-in';
    context.beginPath();
    context.arc( width/2, height/2, Math.min(width, height)/2, 0, 2*Math.PI, true);
    context.fill();
    return canvas;
}

</script>
</head>

<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<%@ include file="/WEB-INF/views/inc/nav.jsp"%>
	
	<div class="container">
		<div class="my_page_box">
			<form class="modify_member_form" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<div class="my_page_profile_container">
					<a class="my_page_profile_img" href="${contextPath}/mypage/mypagemain">
						<img src="${contextPath}/member/showProfileImg?mnum=${sessionScope.member.mNum}" alt="프로필 이미지입니다">
					</a>
				</div>
				
				<div class="modify_profileImage_btn_area">		
					<input type="hidden" id="profileImgType" name="profileImgType"> 
					<input type="file" id="attachImgBtn" name="profileImg" value="사진 선택" accept="image/*" multiple>
					<label for="attachImgBtn">프로필 변경</label>
					<input type="button" id="defalutImgBtn">
					<label for="defalutImgBtn">기본이미지</label>
				</div>
				
				<div class="my_page_name_container">
					<p>닉네임</p>
					<input type="text" name="mname" value="${member.mName}" autocomplete="off">
				</div>
				<div class="my_page_pw_container">
					<p>비밀번호</p>
					<input type="password" name="mpw" value="${member.mPw}" autocomplete="off">
				</div>
				<div class="my_page_pw_container">
					<p>비밀번호 확인</p>
					<input type="password" name="mpwcheck" value="${member.mPw}" autocomplete="off">
				</div>
				
				<input class="my_page_modify_btn" type="submit" value="수정">
			</form>
		</div>
		
		<!-- 프로필 이미지 수정 모달 -->
		<div class="cropImgModal modal">
			<div id="addImgForm">
				<div id="cropImgArea">
					<img src="http://via.placeholder.com/600x600" id="cropImg">
				</div>
				<a href="#" class="cropImgBtn">확인</a>
				<a href="#" class="closeCropImgModal">닫기</a>
			</div>
		</div>
	</div>
	
</body>
</html>