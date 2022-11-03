/**
 * 
 */
$(function() {
	$(document).on("click", "#group-page .group .manage", function() {
		var page = $("#group-page").attr("page");
		var wid = $(this).closest(".group").attr("wid");
		var url = "manage.pr?wid=" + wid + "&page=" + page;
		$(location).attr("href", url);
	})
	$(document).on("click", "#group-page .group .exit", function() {
		var item = $(this).closest(".group");
		var wid = item.attr("wid");
		var title = item.find(".title span:last-child").html();
		var result = confirm(title + "을 삭제하시겠습니까?");
		if (result) {
			$.ajax({
				type: "post",
				url: "manageCancel.pr",
				data: {"wid":wid},
				dataType: 'html',
				success:function(data) {
					if (data == '1') {
						item.remove();
					} else {
						alert("fail");
					}
				},
				error:function() {
					alert("fail");
				}
			});
		}
	})
})