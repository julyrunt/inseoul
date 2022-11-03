/**
 * 
 */
$(function() {
	var wid = $("#manage-table").attr("wid");
	$.ajax({
		type: "post",
		url: "../board/groupManage.with",
		data: {"wid":wid},
		dataType: 'html',
		success:function(data) {
			$("#manage-table").html($(data).find("#manage-table"));
		},
		error:function() {
			alert("fail");
		}
	});
	$(document).on("click", "#group-info", function(e) {
		var wid = $(this).attr("wid");
		var url = "../board/withDetail.with?wid=" + wid;
		$(location).attr("href", url);
	})
	$(document).on("click", "#group-list", function() {
		var page = $(this).attr("page");
		var url = "list.pr?page=" + page;
		$(location).attr("href", url);
	})
})