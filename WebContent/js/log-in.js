$(function() {
	/*패스워드 입력값 보이기*/
	$("#pw-visibility").click(function() {
		if ($("#pw").attr("type") == "password") {
			$("#pw-visibility").html("visibility");
			$("#pw").attr("type", "text");
		} else {
			$("#pw-visibility").html("visibility_off");
			$("#pw").attr("type", "password");
		}
	})
	$("#emailinquiry").click(function() {
		goUrl("emailinquiry");
	})
	$("#pwinquiry").click(function() {
		goUrl("pwinquiry?email=" + $("#email").val());
	})
})