$(function() {
	$('.with-detail-replydeletebox').hide();

	$('.with-detail-replybox-one').mouseenter(function() {
		$('.with-detail-replydeletebox').show();
	})
	$('.with-detail-replybox-one').mouseleave(function() {
		$('.with-detail-replydeletebox').hide();
	})
	/* 댓글작성 */
	$('.reply-submit').click(function() {

		var wid = $("#rewid").val();
		var ref = $("#reref").val();
		var contents = encodeURIComponent($("#replybox").val());
		if (contents == "") {
			alert('글을 작성해야 합니다');
			return false;
		} else {
			$.ajax({
				url : 'replyWrite.with?contents=' + contents + '&ref=' + ref,
				dataType : 'html',
				success : function(data) {
					$(".with-detail-replybox").html(data);
					$("#replybox").val(null);
				},
				error : function() {
					alert("댓글 fail");
				}
			});
		}
	})
	/* 댓글 삭제 */
	$(document).on("click", ".with-detail-replydeletebox .clear-box ",
			function() {
				var wid = $(this).attr("rewid");
				var ref = $("#reref").val();

				var result = confirm('댓글을 삭제하시겠습니까?');
				if (result == true) {
					$.ajax({
						url : 'replyDelete.with?wid=' + wid + '&ref=' + ref,
						dataType : 'html',
						success : function(data) {
							$(".with-detail-replybox").html(data);
						},
						error : function() {
							alert("fail");
						}
					});
				}
			})
	/* 그룹 신청 버튼 클릭 */
	$('#join-btn').on('click', function() {
		var result = confirm('그룹신청을 하시겠습니까?');
		var wid = $('#wid').val();
		if (result == true) {
			$.ajax({
				url : 'withGroup.with',
				data : {
					'wid' : wid
				},
				dataType : 'html',
				success : function(data) {
					alert('그룹신청 완료!');
					$("#group-box").html(data);
				},
				error : function() {
					alert('fail');
				}
			});
		}
	})
	/* 그룹 취소 */
	$('#wait-btn, #con-btn').on('click', function() {
		var result = confirm('그룹신청을 취소하시겠습니까?');
		var wid = $('#wid').val();
		if (result == true) {

			$.ajax({
				url : 'withGroup.with',
				data : {
					'wid' : wid
				},
				dataType : 'html',
				success : function(data) {
					alert('그룹신청 취소!');
					$("#group-box").html(data);
				},
				error : function() {
					alert('fail');
				}
			});

		}
	})
	/* 그룹참여 버튼 효과 */
	$('#join-btn, #unjoin-btn').mouseenter(
			function() {
				$('#join-btn img, #unjoin-btn img').attr('src',
						'../images/group_join_r.png');
			})
	$('#join-btn, #unjoin-btn').mouseleave(
			function() {
				$('#join-btn img, #unjoin-btn img').attr('src',
						'../images/group_join.png');
			})
	$('#wait-btn').mouseenter(function() {
		$('#wait-btn img').attr('src', '../images/group_wait_r.png');
	})
	$('#wait-btn').mouseleave(function() {
		$('#wait-btn img').attr('src', '../images/group_wait.png');
	})

	/* 그룹관리버튼 */
	$('#manage-btn').on('click', function() {
		var wid = document.getElementById('wid').value;
		var url = "groupManage.with?wid=" + wid;

		$(location).attr("href", url);
		// $.ajax({
		//			
		// url : 'groupManage.with',
		// data : {'wid' : wid},
		// dataType : 'html',
		// success : function(data) {
		// console.log('관리자 실행!');
		// $("#manage-box").html(data);
		// },
		// error : function() {
		// alert('fail');
		// }
		// });
	})

	$('#group-appro').on('click', function() {
		var wid = document.getElementById('wid').value;

	})

	/* 비로그인 참여클릭 시 */
	$('#unjoin-btn').on('click', function() {
		alert('로그인해야 참여가능합니다');
		var url = "../log-in/";

		$(location).attr("href", url);
	})

})
/* 그룹 관리 수락 */
function appro(uid) {
//	var url = "groupManageAppro.with";
	var wid = document.getElementById('wid').value;
	
	$.ajax({
		url : '../board/groupManageAppro.with',
		data : {'wid' : wid, 'uid' : uid},
		dataType : 'html',
		success : function(data) {
			$("#manage-table").html(data);
		},
		error : function() {
			alert('fail');
		}
	});
}
/* 그룹 관리 추방 */
function banned(uid) {
	var url = "../board/groupManagebanned.with";
	var wid = document.getElementById('wid').value;

	$.ajax({
		url : url,
		data : {'wid' : wid, 'uid' : uid},
		dataType : 'html',
		success : function(data) {
			$("#manage-table").html(data);
		},
		error : function() {
			alert('fail');
		}
	});
}
/* 댓글 3줄 이상 작성 못하게 제한 */
function rowcheck(){
	var str = $("#replybox").val();
	var str_arr = str.split("\n");  // 줄바꿈 기준으로 나눔 
	var row = str_arr.length;  // row = 줄 수 
	if(row >3){
	//마지막 입력문자 삭제
	alert("3줄 이상 입력할 수 없습니다.")
	var lastChar = str.slice(0,-1); //열 
	$("#replybox").val(lastChar)
	}
}

