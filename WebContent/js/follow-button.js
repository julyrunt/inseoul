$(function() {
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("mouseenter", "#follow-button .follow-remove", function() {
		$(this).children(".material-symbols-outlined").html("person_remove");
		$(this).children(".symbol-label").html("언팔로우");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("mouseleave", "#follow-button .follow-remove", function() {
		$(this).children(".material-symbols-outlined").html("person");
		$(this).children(".symbol-label").html("팔로잉");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 팔로우
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#follow-button .follow-add", function() {
		follow();
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔로우
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#follow-button .follow-remove", function() {
		var result = confirm("팔로잉를 취소하시겠습니까?");
		if (result) {
			follow();
		};
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 프로필 페이지로 이동
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#follow-button .timeline", function() {
		var pid = $("#follow-button").attr("pid");
		$(location).attr('href', "../profile/timeline.pr?pid=" + pid);
	})
})
function follow() {
	var pid = $("#follow-button").attr("pid");
	$.ajax({
		type: "post",
		url: "../followbutton.do",
		data: {"pid":pid},
		dataType: 'html',
		success:function(data) {
			$("#follow-button").html(data);
		},
		error:function() {
			alert("fail");
		}
	});
}