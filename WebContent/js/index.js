$(document).ready(function(){
	/*인덱스 페이지 슬라이더 설정*/
	$('.slider').bxSlider({
		mode: 'fade',
		auto: true,
		controls: false,
		pager: false
	});
	$('.responsive').slick({
		dots: true,
		infinite: false,
		speed: 300,
		slidesToShow: 4,
		slidesToScroll: 4,
		responsive: [
			{
				breakpoint: 1024,
				settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: true,
					dots: true
				}
			},
			{
				breakpoint: 600,
				settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				}
			},
			{
				breakpoint: 480,
				settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				}
			}
			]
	});
	/*글로벌 네비게이션 슬라이드 업다운*/
	$(".gnb").hide();
	$(".top-nav").mouseenter(function() {
		$(".gnb").slideDown();
	});
	$("header").mouseleave(function() {
		$(".gnb").slideUp();
	});
	/*버튼 클릭*/
	$("#btIndex").on("click", function() {
		$(location).attr("href", "./");
	});
	$("#btBack").on("click", function() {
		$(location).attr("href", "../");
	});
	$("#btSignUp").on("click", function() {
		$(location).attr("href", "sign-up");
	});
	$("#btLogIn").on("click", function() {
		$(location).attr("href", "log-in");
	});
	$("#btToLogIn").on("click", function() {
		$(location).attr("href", "log-in");
	});
	$("#btToSignUp").on("click", function() {
		$(location).attr("href", "sign-up");
	});
	$("#btLogOut").on("click", function() {
		$(location).attr("href", "log-out.jsp");
	});
});


