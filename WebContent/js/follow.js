$(function() {
	/* --------------------------------------------------------------------------------------------------------------
	 * 프로필 페이지로 이동
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#follow-page .follow", function(e) {
		var pid = $(this).attr("pid");
		var url = "../profile/timeline.pr?pid=" + pid;
		if(!$(e.target).is("#follow-page .follow > span:last-child") && !$(e.target).is("#follow-page .follow > span:last-child *")) {
			$(location).attr("href", url);
		}
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("mouseenter", "#follow-page .follow-remove", function() {
		$(this).children(".material-symbols-outlined").html("person_remove");
		$(this).children(".symbol-label").html("언팔로우");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("mouseleave", "#follow-page .follow-remove", function() {
		$(this).children(".material-symbols-outlined").html("person");
		$(this).children(".symbol-label").html("팔로잉");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 팔로우
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#follow-page .follow > span:last-child", function() {
		var pid = $(this).closest(".follow").attr("pid");
		$.ajax({
			type: "post",
			url: "follow.do",
			data: {"pid":pid},
			dataType: 'html',
			success:function(data) {
				$(".follow[pid="+pid+"]").html(data);
			},
			error:function() {
				alert("fail");
			}
		});
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 페이지 이동
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", ".paging-buttons button", function() {
		var url = $(this).attr("href");
		$(location).attr("href", url);
	})
})