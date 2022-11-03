/**
 *  폼의 데이터를 전송하기전 유효성 검사를 실시합니다.
 */
function checkUpdatable() {
	if (photoCheck() && nickCheck()) {
		return true;
	}
	return false;
}
/**
 * 업로드 이미지의 크기와 확장자를 검사하고 그 결과를 반환합니다.
 * 
 */
function photoCheck() {
	var files = $("#profileUpload")[0].files;
	if(files.length < 1){
		$(".input-preview").css('background-image', 'url("../images/transparent.svg")');
		$(".input-alert.file-size").html("0MB / 5MB · 파일형식: png · 파일명: 공백문자 제외");
		return false;
	}
	var file = files[0];
	var mb = 1024 * 1024;
	if (file.size > 5 * mb) {
		$(".input-preview").css('background-image', 'url("../images/transparent.svg")');
		$(".input-alert.file-size").html("<font color='red'>" + (file.size / mb).toFixed(1)+"MB</font> / 5MB · 파일형식: png · 파일명: 공백문자 제외");
		$("#profileUpload").val("");
		return false;
	} else {
		$(".input-alert.file-size").html((file.size / mb).toFixed(1)+"MB / 5MB · 파일형식: png · 파일명: 공백문자 제외");
	}
	var filePath = $("#profileUpload").val();
	var fReader = new FileReader();
	if (filePath.substring(filePath.length - 4) == ".png"){
		var pattern = /[\s]/g;
		if( pattern.test(filePath) == true){
			$(".input-preview").css('background-image', 'url("../images/transparent.svg")');
			$(".input-alert.file-size").html("0MB / 5MB · 파일형식: png · <font color='red'>파일명: 공백문자 제외</font>");
			$("#profileUpload").val("");
			return false;
		} else {
			fReader.readAsDataURL(file);
			fReader.onloadend = function(event){
				$(".input-preview").css('background-image', 'url("' + event.target.result + '"), url("../images/transparent.svg")');
			}
			return true;
		}
	} else {
		$(".input-preview").css('background-image', 'url("../images/transparent.svg")');
		$(".input-alert.file-size").html("0MB / 5MB · <font color='red'>파일형식: png</font> · 파일명: 공백문자 제외");
	}
	$("#profileUpload").val("");
	return false;
}
/**
 * 닉네임 입력값에 대한 유효성 검사를 실시합니다.
 */
function nickCheck() {
	var nick = $("#nickNameInput").val();
	if (nick == "") {
		$(".input-alert.nick").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (nick.length > 45) {
		$(".input-alert.nick").html("<font color='red'>닉네임은 45자 이하로 입력해주세요.</font>");
		return false;
	}
	$(".input-alert.nick").html("");
	return true;
}
