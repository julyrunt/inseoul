/**
 * 
 */
$(function() {
	$(document).on("click", "#receipt-list", function() {
		var category = $(this).attr("category");
		var page = $(this).attr("page");
		var url = "list.pr?category=" + category + "&page=" + page;
		$(location).attr("href", url);
	})
})