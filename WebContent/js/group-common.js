/**
 * 
 */
$(function() {
	$(document).on("click", ".paging-buttons button", function() {
		var url = $(this).attr("href");
		$(location).attr("href", url);
	})
	$(document).on("click", "#group-page .group", function(e) {
		var wid = $(this).attr("wid");
		var url = "../board/withDetail.with?wid=" + wid;
		if(!$(e.target).is("#group-page .group > .buttons") && !$(e.target).is("#group-page .group > .buttons *")) {
			$(location).attr("href", url);
		}
	})
})