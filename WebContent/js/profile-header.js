$(function() {
	/* --------------------------------------------------------------------------------------------------------------
	 * 자기소개 수정 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#main-header .introduction #introduction-edit", function() {
		var text = $("#main-header .introduction span:first-child").html(); 
		$("#main-header .introduction").html("<input id='introduction-input' type='text' name='introduction' maxlength='40' value='" + text + "' placeholder='자기소개를 입력해주세요.' />" +
				"<span id='introduction-submit' class='material-symbols-outlined'>edit_square</span>");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 자기소개 수정 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#main-header .introduction #introduction-submit", function() {
		var introduction = $("#main-header #introduction-input").val();
		if (introduction.length <= 200) {
			$.ajax({
				type: "post",
				url: "../introduction.jsp",
				data: {"introduction":introduction},
				dataType: 'html',
				success:function(data) {
					$("#main-header .introduction").html("<span>" + data + "</span>" +
					"<span id='introduction-edit' class='material-symbols-outlined'>settings</span>");
				},
				error:function() {
					alert("fail");
				}
			});
		}
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("mouseenter", "#main-header .follow-remove", function() {
		$(this).children(".material-symbols-outlined").html("person_remove");
		$(this).children(".symbol-label").html("언팔로우");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("mouseleave", "#main-header .follow-remove", function() {
		$(this).children(".material-symbols-outlined").html("person");
		$(this).children(".symbol-label").html("팔로잉");
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 팔로우
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#main-header .photo .follow-add", function() {
		follow();
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 언팔로우
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#main-header .photo .follow-remove", function() {
		follow();
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 프로필 페이지로 이동
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#main-header .photo .timeline", function() {
		var pid = $("#main-header .profile-header").attr("pid");
		$(location).attr('href', "../profile/timeline.pr?pid=" + pid);
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 배경 이미지 변경 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#main-header .profile-header-manage #background-image-change", function() {
	    var width = '620';
	    var height = '380';
	    var top = Math.ceil(( window.screen.height - height )/2);
	    var left = Math.ceil(( window.screen.width - width )/2);
	    var popup = window.open('../profile-background-edit.jsp',
	    		'프로필 헤더 배경 이미지 변경',
	    		'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left + ', scrollbars=no');
	})
})
/* --------------------------------------------------------------------------------------------------------------
 * 프로필 헤더 팔로우 버튼
 * -------------------------------------------------------------------------------------------------------------- */
function follow() {
	var pid = $("#main-header .profile-header").attr("pid");
	$.ajax({
		type: "post",
		url: "../followbutton.do",
		data: {"pid":pid},
		dataType: 'html',
		success:function(data) {
			$("#main-header .photo div:last-child").html(data);
		},
		error:function() {
			alert("fail");
		}
	});
}