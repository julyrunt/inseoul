/**
 * 
 */
$(function() {
	$(document).on("click", ".paging-buttons button", function() {
		var url = $(this).attr("href");
		$(location).attr("href", url);
	})
	$(document).on("click", "#receipt-page .receipt", function() {
		var rid = $(this).attr("rid");
		var category = $("#receipt-page").attr("category");
		var url = "view.pr?rid=" + rid + "&category=" + category;
		$(location).attr("href", url);
	})
})