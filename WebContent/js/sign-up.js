$(function() {
	/**
	 * 슬라이더 설정
	 */
	$('.bxslider').bxSlider({
		mode: 'fade',
		controls: true,
		captions: true,
		slideWidth: 600
	});
	/**
	 * 패스워드 입력값 보이기
	 */
	$("#pw-visibility").click(function() {
		if ($("#pw-input").attr("type") == "password") {
			$("#pw-visibility").html("visibility");
			$("#pw-input").attr("type", "text");
		} else {
			$("#pw-visibility").html("visibility_off");
			$("#pw-input").attr("type", "password");
		}
	})
	$("#pw-chk-visibility").click(function() {
		if ($("#pw-chk").attr("type") == "password") {
			$("#pw-chk-visibility").html("visibility");
			$("#pw-chk").attr("type", "text");
		} else {
			$("#pw-chk-visibility").html("visibility_off");
			$("#pw-chk").attr("type", "password");
		}
	})
	/*이메일 체크*/
	$('#bt-email-check').click(function() {
		var email = document.getElementById("email").value;
		var domain = document.getElementById("domain").value;
		if (email.length == 0 || domain.length == 0) {
			return;
		}
		if (!alertEmail()){
			$.ajax({
				url: 'email-check.jsp',
				dataType: 'html',
				type: "post",
				data: {"email": getEmail()},
				success:function(data) {
					if (data == "Y"){
						$("#emailAlert").html("<font color='red' size='2'>이미 존재하는 이메일 입니다.</font>");
					} else {
						$('#bt-email-check').attr("disabled", true);
						$("#emailAlert").html("<font color='green' size='2'>사용 가능한 이메일 입니다.</font>");
					}
				},
				error:function() {
					alert("fail");
				}
			});
		}
	});
	/*이메일 입력폼에 키보드가 눌리면 중복확인 버튼을 재활성화 시킨다.*/
	$('#email').keyup(function() {
		refreshEmailCheck();
	})
	/*이메일 입력폼의 내용이 비어있는 경우 알림을 표시한다.*/
	$('#email').blur(function() {
		if ($('#email').val().length == 0){
			refreshEmailCheck();
		}
	})
	/*도메인 입력폼에 키보드가 눌리면 중복확인 버튼을 재활성화 시킨다.*/
	$('#domain').keyup(function() {
		refreshEmailCheck();
	})
	/*도메인 입력폼의 내용이 비어있는 경우 알림을 표시한다.*/
	$('#domain').blur(function() {
		if ($('#domain').val().length == 0){
			refreshEmailCheck();
		}
	})
	/*선택한 도메인 값을 입력하고 중복확인 버튼을 활성화 시킨다.*/
	$('#sel-domain').click(function() {
		$('#domain').val($('#sel-domain option:selected').val());
		refreshEmailCheck();
	})
});
/**
 * 회원 가입 버튼을 클릭했을 시 양식에 이상이 없는지 체크합니다.
 */
function signUp() {
	if (emptyCheck() || emailCheck() || passCheck() || phoneCheck()) {
		return false;
	}
	return true;
}
/**
 * 비어있는 양식이 있는지 확인하고, 알림을 띄웁니다.
 */
function emptyCheck() {
	var taget, result = "";
	var elements = document.getElementsByName("sign-up")[0].elements;
	for (var element of elements) {
		if (element.value == "" && element.id != "detailAddress"
				(element.type == "file" || 
						element.type == "text" || 
						element.type == "password")) {
			if (taget == undefined) {
				taget = element;
			}
			result += "- " + element.alt + "\n";
		} else if (element.type == "checkbox" && !element.checked){
			if (taget == undefined) {
				taget = element;
			}
			result += "- " + element.alt + "\n";
		}
	}
	if (result != "") {
		alert("아래의 양식들이 비어있습니다.\n" + result);
		taget.focus();
		return true;
	}
	return false;
}
/**
 * 이메일 정보를 취득한다.
 */
function getEmail() {
	return $('#email').val() + "@" + $('#domain').val();
}
function refreshEmailCheck() {
	$('#bt-email-check').attr("disabled", false);
	alertEmail();
}
/**
 * 이메일 중복확인 여부를 확인합니다.
 */
function emailCheck() {
	if (!$('#bt-email-check').attr("disabled")){
		$('#emailAlert').html("<font color='red' size='2'>중복확인을 해주세요.</font>");
		return true;
	}
	return false;
}
/**
 * 입력된 이메일의 길이와 구성을 확인합니다.
 */
function alertEmail() {
	if ($('#bt-email-check').attr("disabled")){
		return true;
	}
	var email = $('#email').val();
	var domain = $('#domain').val();
	if (email == "") {
		$('#emailAlert').html("<font color='red' size='2'>필수 정보입니다.</font>");
		return true;
	} else if (!/[a-z0-9_-]/g.test(email) || email.length < 5 || email.length > 20) {
		$('#emailAlert').html("<font color='red' size='2'>5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</font>");
		return true;
	} else if (domain == "") {
		$('#emailAlert').html("<font color='red' size='2'>도메인 정보가 비어있습니다.</font>");
		return true;
	} else if (!/(.+)\.[a-z]{2,3}/g.test(domain)) {
		$('#emailAlert').html("<font color='red' size='2'>도메인 양식이 잘 못되었습니다.</font>");
		return true;
	}
	$('#emailAlert').html("");
	return false;
}
/**
 * 생년월일 정보를 취득한다.
 */
function getBirth() {
	var birth = "";
	var year = document.getElementsByName("num-year")[0].value
	var mOptions = document.getElementsByName("sel-month")[0].options;
	var day = document.getElementsByName("num-day")[0].value
	for (var month of mOptions) {
		if (month.selected) {
			birth = year + "-" + month.value + "-";
			if (day < 10){
				birth += "0";
			}
			birth += day;
			return  birth;
		}
	}
}
/**
 * 전화번호 정보를 취득한다.
 */
function getPhone() {
	var phoneNo = document.getElementsByName("num-phone")[0].value;
	var nOptions = document.getElementsByName("nationNo")[0].options;
	for (var nation of nOptions) {
		if (nation.selected) {
			return "+" + nation.value + "-" + phoneNo;
		}
	}
}
/**
 * 프로필 사진으로 png 파일인지 검사하고 변경합니다.
 */
function photoCheck() {
	var files = $("#photo")[0].files;
	if(files.length < 1){
		$('#preview').attr('src', "../images/man 1.png");
		$('#fileAlert').html("<font color='red' size='2'>파일을 등록해주세요.</font>");
		return false;
	}
	var file = files[0];
	if (file.size > 5 * 1024 * 1024) {
		$('#preview').attr('src', "../images/man 1.png");
		$('.file-size').html("<font size='2'>· 파일 크기 : </font><font color='red' size='2'>" + (file.size/(1024*1024)).toFixed(1) + "MB / 5MB</font>");
		$('#fileAlert').html("<font color='red' size='2'>5MB 이하의 파일을 등록해주세요.</font>");
		$("#photo").val("");
		return false;
	} else {
		$('.file-size').html("<font size='2'>· 파일 크기 : " + (file.size/(1024*1024)).toFixed(1) + "MB / 5MB</font>");
	}
	var filePath = $("#photo").val();
	var fReader = new FileReader();
	if (filePath.substring(filePath.length - 4) == ".png"){
		fReader.readAsDataURL(file);
		fReader.onloadend = function(event){
			$('#preview').attr('src', event.target.result);
		}
		return true;
	} else {
		$('#preview').attr('src', "../images/man 1.png");
		$('#fileAlert').html("<font color='red' size='2'>.png 파일을 등록해주세요.</font>");
	}
	$("#photo").val("");
	return false;
}
/**
 * 비밀번호 양식이 제대로 작성되어있는지 유무를 확인하고, 알림을 띄웁니다.
 */
function passCheck() {
	var chk01 = alertPwChk();
	var chk02 = alertPwInput();
	if (chk01) {
		alert("비밀번호가 일치하지 않습니다.");
	} else if (chk02){
		alert("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
	}
	return (chk01 || chk02);
}
/**
 * 입력된 패스워드의 길이와 구성을 확인합니다.
 */
function alertPwInput() {
	var pwInput = $('#pw-input').val();
	var symbol = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\s]/g.test(pwInput);
	var lower = /[a-z]/g.test(pwInput);
	var upper = /[A-Z]/g.test(pwInput);
	var number = /[0-9]/g.test(pwInput);
	if (pwInput == "") {
		$('#pwAlert').html("<font color='red' size='2'>필수 정보입니다.</font>");
		return true;
	} else if (pwInput.length < 8 || pwInput.length > 16 || !symbol || !lower || !upper || !number) {
		$('#pwAlert').html("<font color='red' size='2'>8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</font>");
		return true;
	}
	if (pwInput != "") {
		alertPwChk();
	}
	$('#pwAlert').html("");
	return false;
}
/**
 * 입력된 패스워드가 동일한지 확인합니다.
 */
function alertPwChk() {
	var pwInput = $('#pw-input').val();
	var pwChk = $('#pw-chk').val();
	if (pwInput != pwChk) {
		$('#pwChkAlert').html("<font color='red' size='2'>비밀번호가 일치하지 않습니다.</font>");
		return true;
	} else if (pwChk == "") {
		$('#pwChkAlert').html("<font color='red' size='2'>필수 정보입니다.</font>");
		return true;
	}
	$('#pwChkAlert').html("");
	return false;
}
/**
 * 입력된 이름의 길이와 구성을 확인합니다.
 */
function alertName() {
	var name = document.getElementsByName("txt-name")[0].value;
	var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\s]/g;
	if (name == "") {
		document.getElementById("nameAlert").innerHTML = "<font color='red' size='2'>필수 정보입니다.</font>";
		return true;
	} else if (regExp.test(name)) {
		document.getElementById("nameAlert").innerHTML = "<font color='red' size='2'>한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)</font>";
		return true;
	}
	document.getElementById("nameAlert").innerHTML = "";
	return false;
}
function alertNick() {
	var nick = document.getElementsByName("txt-nick")[0].value;
	if (nick == "") {
		document.getElementById("nickAlert").innerHTML = "<font color='red' size='2'>필수 정보입니다.</font>";
		return true;
	}
	document.getElementById("nickAlert").innerHTML = "";
	return false;
}
function alertPhone() {
	var phone = document.getElementsByName("num-phone")[0].value;
	if (phone == "") {
		document.getElementById("phoneAlert").innerHTML = "<font color='red' size='2'>필수 정보입니다.</font>";
		return true;
	}
	document.getElementById("phoneAlert").innerHTML = "";
	return false;
}
function alertAddr() {
	var addr = document.getElementsByName("txt-addr")[0].value;
	if (addr == "") {
		document.getElementById("addrAlert").innerHTML = "<font color='red' size='2'>필수 정보입니다.</font>";
		return true;
	}
	document.getElementById("addrAlert").innerHTML = "";
	return false;
}
function alertQuestion() {
	var question = document.getElementsByName("txt-question")[0].value;
	if (question == "") {
		document.getElementById("questionAlert").innerHTML = "<font color='red' size='2'>필수 정보입니다.</font>";
		return true;
	}
	document.getElementById("questionAlert").innerHTML = "";
	return false;
}
function alertAnswer() {
	var answer = document.getElementsByName("txt-answer")[0].value;
	if (answer == "") {
		document.getElementById("answerAlert").innerHTML = "<font color='red' size='2'>필수 정보입니다.</font>";
		return true;
	}
	document.getElementById("answerAlert").innerHTML = "";
	return false;
}
/**
 * 전화번호 양식내 입력된 길이를 확인하고, 알림을 띄웁니다.
 */
function phoneCheck() {
	var phone = document.getElementsByName("num-phone")[0].value;
	if (phone.length < 11) {
		alert("전화번호는 국번을 포함한 11자리 이상이어야만 합니다.");
		return true;
	}
	return false;
}
/**
 * 해당 월의 마지막일에 맞게 입력폼의 최대 수치를 재설정합니다.
 */
function birthCheck() {
	var options = document.getElementsByName("sel-month")[0].options;
	var day = document.getElementsByName("num-day")[0];
	for (var month of options) {
		if (month.selected) {
			switch (month.value) {
			case "02":
				day.max = 28;
				break;
			case "01": case "03": case "05": case "07": case "08":
			case "10": case "12":
				day.max = 31;
				break;
			default:
				day.max = 30;
			}
			if (day.value > day.max){
				day.value = day.max;
			}
			break;
		}
	}
}
/**
 * 전화번호에 숫자만 입력되도록 처리합니다.(현재 한글 입력을 막지 못함)
 */
function numberCheck() {
	switch (event.key) {
	case "1": case "2": case "3": case "4": case "5": 
	case "6": case "7": case "8": case "9": case "0": 
		return true;
	default: 
		return false;
	}
}
/**
 * 다음 주소 API
 */
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById("address").value = data.address;
			alertAddr();
		}
	}).open();
}