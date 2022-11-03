/**
 *  폼의 데이터를 전송하기전 유효성 검사를 실시합니다.
 */
function checkUpdatable() {
	if (questionCheck() && answerCheck()) {
		return true;
	}
	return false;
}
/**
 *  본인 확인 질문의 입력값에 유효성 검사를 하고 그 결과를 반환합니다.
 */
function questionCheck() {
	var question = $("#questionInput").val();
	if (question == "") {
		$(".input-alert.question").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (question.length > 40) {
		$(".input-alert.question").html("<font color='red'>내용은 40자 이하로 입력해주세요.</font>");
		return false;
	}
	$(".input-alert.question").html("");
	return true;
}
/**
 *  본인 확인 답변의 입력값에 유효성 검사를 하고 그 결과를 반환합니다.
 */
function answerCheck() {
	var answer = $("#answerInput").val();
	if (answer == "") {
		$(".input-alert.answer").html("<font color='red'>필수 정보입니다.</font>");
		return false;
	} else if (answer.length > 40) {
		$(".input-alert.answer").html("<font color='red'>내용은 40자 이하로 입력해주세요.</font>");
		return false;
	}
	$(".input-alert.answer").html("");
	return true;
}