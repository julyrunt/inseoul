/**
 * 
 */
$(function() {
	$(document).on("click", "#group-page .group .exit", function() {
		var item = $(this).closest(".group");
		var wid = item.attr("wid");
		var title = item.find(".title span:last-child").html();
		var result = confirm(title + "을 가입신청을 취소하시겠습니까?");
		if (result) {
			$.ajax({
				type: "post",
				url: "applyCancel.pr",
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