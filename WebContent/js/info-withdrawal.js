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
	var result = confirm("회원을 탈퇴하시겠습니까?");
	if (!result) {
		return false;
	}
	if (pwCheck()) {
		return true;
	}
	return false;
}
/**
 *  새 비밀번호 확인의 입력값의 공백 체크 및 유효성 검사를 실시합니다.
 */
function pwCheck() {
	var pw = $(".pw").val();
	if (pw == "") {
		$(".input-alert").html("※ 회원 탈퇴시 모든 활동내역이 삭제됩니다.<br /><font color='red'>필수 정보입니다.</font>");
		return false;
	}
	$(".input-alert").html("※ 회원 탈퇴시 모든 활동내역이 삭제됩니다.");
	return true;
}