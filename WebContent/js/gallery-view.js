$(function() {
	/* --------------------------------------------------------------------------------------------------------------
	 * 앨범
	 * -------------------------------------------------------------------------------------------------------------- */
	$("#album-picture-thumbs img").on("click", function() {
		$("#album-picture-main").attr("src", $(this).attr("src"));
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 목록
	 * -------------------------------------------------------------------------------------------------------------- */
	$("#album-list").on("click", function() {
		var pid = $("#pid").val();
		var category = $("#category").val();
		var page = $("#page").val();
		var search = $("#search").val();
		var keywords = $("#keywords").val();
		var url = "list.do?pid=" + pid + "&category=" + category;
		if (page != null && page != undefined) {
			url += "&page=" + page;
		} else {
			url += "&page=" + "1";
		}
		if (search != null && search != undefined && keywords != null && keywords != undefined) {
			url += "&search=" + search + "&keywords=" + keywords;
		}
		$(location).attr("href", url);
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 수정
	 * -------------------------------------------------------------------------------------------------------------- */
	$("#album-modify").on("click", function() {
		var pid = $("#pid").val();
		var category = $("#category").val();
		var aid = $("#aid").val();
		var page = $("#page").val();
		var search = $("#search").val();
		var keywords = $("#keywords").val();
		var url = "modify.do?pid=" + pid + "&category=" + category + "&aid=" + aid;
		if (page != null && page != undefined) {
			url += "&page=" + page;
		} else {
			url += "&page=" + "1";
		}
		if (search != null && search != undefined && keywords != null && keywords != undefined) {
			url += "&search=" + search + "&keywords=" + keywords;
		}
		$(location).attr("href", url);
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 삭제
	 * -------------------------------------------------------------------------------------------------------------- */
	$("#album-delete").on("click", function() {
		var pid = $("#pid").val();
		var category = $("#category").val();
		var aid = $("#aid").val();
		var page = $("#page").val();
		var search = $("#search").val();
		var keywords = $("#keywords").val();
		var url = "delete.do?pid=" + pid + "&category=" + category + "&aid=" + aid;
		if (page != null && page != undefined) {
			url += "&page=" + page;
		} else {
			url += "&page=" + "1";
		}
		if (search != null && search != undefined && keywords != null && keywords != undefined) {
			url += "&search=" + search + "&keywords=" + keywords;
		}
		var result = confirm("앨범을 삭제하시겠습니까?");
		if (result) {
			$(location).attr("href", url);
		}
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 추천
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#recommend span", function() {
		var aid = $("#aid").val();
		$.ajax({
			type: "post",
			url: "recommend.do",
			data: {"aid":aid},
			dataType: 'html',
			success:function(data) {
				$('#recommend').html(data);
			},
			error:function() {
				alert("fail");
			}
		});
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 댓글 작성
	 * -------------------------------------------------------------------------------------------------------------- */
	$('#reply-write .reply-submit').click(function() {
		var aid = $("#aid").val();
		var contents = $("#reply-write textarea").val();
		if (contents.trim().length > 0) {
			$.ajax({
				type: "post",
				url: "replyWrite.do",
				data: {"aid":aid, "contents":contents},
				dataType: 'html',
				success:function(data) {
					$("#reply-page").html(data);
					$("#reply-write textarea").val(null);
				},
				error:function() {
					alert("fail");
				}
			});
		}
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 댓글 목록 - 수정 폼 생성
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#reply-page .reply-modify", function() {
		var rid = $(this).attr("rid");
		var contents = $(this).closest(".reply-contents");
		var nick = contents.children(".reply-writer-nick").html();
		var date = contents.children(".reply-write-date").html().replace("&nbsp;(수정됨)", "");
		var text = contents.children(".reply-text").html();
		contents.html("<span class='reply-writer-nick'>"+nick+"</span>\n" +
				"<span class='reply-write-date'>"+date+"\n&nbsp;(수정중)</span><br />" +
				"<div class='reply-modify-form'>" +
				"<textarea>"+text+"</textarea>" +
				"<input rid='" + rid + "' class='reply-modify-submit' type='button' value='수정'>" +
				"</div>");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 댓글 목록 - 수정
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#reply-page .reply-modify-submit", function() {
		var aid = $("#aid").val();
		var rid = $(this).attr("rid");
		var rp = $(".replies").attr("rp");
		var contents = $(this).parent().children("textarea").val();
		if (contents.trim().length > 0) {
			$.ajax({
				type: "post",
				url: "replyModify.do",
				data: {"aid":aid, "rid":rid, "contents":contents, "rp":rp},
				dataType: 'html',
				success:function(data) {
					$("#reply-page").html(data);
				},
				error:function() {
					alert("fail");
				}
			});
		}
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 댓글 목록 - 삭제
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#reply-page .reply-delete", function() {
		var aid = $("#aid").val();
		var rid = $(this).attr("rid");
		var rp = $(".replies").attr("rp");
		var result = confirm("댓글을 삭제하시겠습니까?");
		if (result) {
			$.ajax({
				type: "post",
				url: "replyDelete.do?",
				data: {"aid":aid, "rid":rid, "rp":rp},
				dataType: 'html',
				success:function(data) {
					$("#reply-page").html(data);
				},
				error:function() {
					alert("fail");
				}
			});
		};
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 댓글 목록 - 페이지 이동
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#reply-page .paging-buttons button", function() {
		var aid = $("#aid").val();
		var rp = $(this).val();
		$.ajax({
			type: "post",
			url: "reply.do?",
			data: {"aid":aid, "rp":rp},
			dataType: 'html',
			success:function(data) {
				$("#reply-page").html(data);
			},
			error:function() {
				alert("fail");
			}
		});
	})
})