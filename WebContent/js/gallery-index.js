$(function() {
	$(document).on("click", ".paging-buttons button", function() {
		var url = $(this).attr("href");
		$(location).attr("href", url);
	})
	$(document).on("click", "#submit", function() {
		$("form").submit();
	})
	$(document).on("click", "#write", function() {
		var url = "write.do";
		$(location).attr("href", url);
	})
})