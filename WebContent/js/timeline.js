$(function() {
	$(document).on("click", ".paging-buttons button", function() {
		var url = $(this).attr("href");
		$(location).attr("href", url);
	})
})