$(document).ready(function(){
	/* --------------------------------------------------------------------------------------------------------------
	 * 글로벌 네비게이션 슬라이드 업 다운
	 * -------------------------------------------------------------------------------------------------------------- */
	$(".gnb").hide();
	$(".top-nav").mouseenter(function() {
		if ($(".gnb").css('display') == 'none') {
			$(".gnb").slideDown();
		}
	});
	$("header").mouseleave(function() {
		if ($(".gnb").css('display') != 'none') {
			$(".gnb").slideUp();
		}
	});
	/* --------------------------------------------------------------------------------------------------------------
	 * 버튼 클릭
	 * -------------------------------------------------------------------------------------------------------------- */
	$("#btIndex").on("click", function() {
		$(location).attr("href", "./");
	});
	$("#btBack").on("click", function() {
		$(location).attr("href", "../");
	});
	$("#btSignUp").on("click", function() {
		$(location).attr("href", "../sign-up");
	});
	$("#btLogIn").on("click", function() {
		$(location).attr("href", "../log-in");
	});
	$("#btToLogIn").on("click", function() {
		$(location).attr("href", "../log-in");
	});
	$("#btToSignUp").on("click", function() {
		$(location).attr("href", "../sign-up");
	});
	$("#btLogOut").on("click", function() {
		$(location).attr("href", "../log-out.jsp");
	});
});
