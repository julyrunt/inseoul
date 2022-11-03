$(function() {
	/*패스워드 입력값 보이기*/
	$(".pw-visibility").click(function() {
		var element = $(this).parent().children(".pw");
		if (element.attr("type") == "password") {
			$(this).html("visibility");
			element.attr("type", "text");
		} else {
			$(this).html("visibility_off");
			element.attr("type", "password");
		}
	})
})
/**
 *  폼의 데이터를 전송하기전 유효성 검사를 실시합니다.
 */
function checkUpdatable() {
	if (pwOldCheck() && pwNewCheck() && pwChkCheck()) {
		return true;
	}
	return false;
}
/**
 *  비밀번호 입력값의 공백 체크 및 유효성 검사를 실시합니다.
 */
function pwOldCheck() {
	var pwOld = $("#pwOldInput").val();
	if (pwOld == "") {
		$(".input-alert.pwOld").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (pwOld.length > 20) {
		$(".input-alert.pwOld").html("<font color='red'>내용은 16자 이하로 입력해주세요.</font>");
		return false;
	}
	$(".input-alert.pwOld").html("");
	return true;
}
/**
 *  새 비밀번호의 입력값의 공백 체크 및 유효성 검사를 실시합니다.
 */
function pwNewCheck() {
	var pwNew = $('#pwNewInput').val();
	var symbol = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\s]/g.test(pwNew);
	var lower = /[a-z]/g.test(pwNew);
	var upper = /[A-Z]/g.test(pwNew);
	var number = /[0-9]/g.test(pwNew);
	if (pwNew == "") {
		$(".input-alert.pwNew").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (pwNew.length < 8 || pwNew.length > 16 || !symbol || !lower || !upper || !number) {
		$(".input-alert.pwNew").html("<font color='red'>8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</font>");
		return false;
	}
	if (pwNew != "") {
		pwChkCheck();
	}
	$(".input-alert.pwNew").html("");
	return true;
}
/**
 *  새 비밀번호 확인의 입력값의 공백 체크 및 유효성 검사를 실시합니다.
 */
function pwChkCheck() {
	var pwNew = $("#pwNewInput").val();
	var pwChk = $("#pwChkInput").val();
	if (pwChk == "") {
		$(".input-alert.pwChk").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (pwNew != pwChk) {
		$(".input-alert.pwChk").html("<font color='red'>새 비밀번호와 일치하지 않습니다.</font>");
	}
	$(".input-alert.pwChk").html("");
	return true;
}