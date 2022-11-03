/**
 * 다음 주소 API
 */
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			$("#addressInput").val(data.address);
		}
	}).open();
}
/**
 *  폼의 데이터를 전송하기전 유효성 검사를 실시합니다.
 */
function checkUpdatable() {
	if (phoneCheck() && addrCheck() && detailCheck()) {
		return true;
	}
	return false;
}
function phoneCheck() {
	var phone = $("#phoneInput").val();
	if (phone == "") {
		$(".input-alert.phone").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (phone.length < 11 || phone.length > 16) {
		$(".input-alert.phone").html("<font color='red'>내용은 11~16자 사이로 입력해주세요.</font>");
		return false;
	}
	$(".input-alert.phone").html("");
	return true;
}
function addrCheck() {
	var address = $("#addressInput").val();
	if (address == "") {
		$(".input-alert.address").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (address.length > 100) {
		$(".input-alert.address").html("<font color='red'>내용은 100자 이하로 입력해주세요.</font>");
		return false;
	}
	$(".input-alert.address").html("");
	return true;
}
function detailCheck() {
	var detail = $("#detailInput").val();
	if (detail == "") {
		$(".input-alert.detail").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (detail.length > 100) {
		$(".input-alert.detail").html("<font color='red'>내용은 100자 이하로 입력해주세요.</font>");
		return false;
	}
	$(".input-alert.detail").html("");
	return true;
}