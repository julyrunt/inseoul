$(function() {

	/* 탯글 작성 */
	$('.reply-submit').click(function() {

		var tid = $("#tid").val();
		var contents = $("#replybox").val();
		if (contents == "") {
			alert('글을 작성해야 합니다');
			return false;
		} else {
			$.ajax({
				url : 'travelsReplyWrite.tv',
				data : {
					'tid' : tid,
					'contents' : contents
				},
				dataType : 'html',
				success : function(data) {
					$(".travels-detail-replybox").html(data);
					$("#replybox").val(null);
				},
				error : function() {
					alert("fail");
				}
			});
		}
	})
	/* 댓글 삭제 */
	$(document).on("click", ".travels-detail-replydeletebox .clear-box ",
			function() {

				var rid = $(this).attr("rerid");
				var tid = $("#tid").val();

				var result = confirm('댓글을 삭제하시겠습니까?');
				if (result == true) {
					$.ajax({
						url : 'travelsReplyDelete.tv',
						data : {'rid' : rid, 'tid':tid},
						dataType : 'html',
						success : function(data) {
							$(".travels-detail-replybox").html(data);
						},
						error : function() {
							alert("fail");
						}
					});
				}
			})
	/* 좋아요 */
	$('.likes').on('click', function(){
		var tid = $('#tid').val();
		
		$.ajax({
			url : 'travelsLike.tv',
			data : {'tid' : tid} ,
			dataType : 'html',
			success : function(data) {
				$("#like_box").html(data);
			},
			error : function() {
				alert('fail');
			}
		})
	})		
	
	$('#file_btn2_label').hide();
	$('#file_btn3_label').hide();
})
/* 댓글 3줄 이상 작성 못하게 제한 */
function rowcheck() {
	var str = $("#replybox").val();
	var str_arr = str.split("\n"); // 줄바꿈 기준으로 나눔
	var row = str_arr.length; // row = 줄 수
	if (row > 3) {
		// 마지막 입력문자 삭제
		alert("3줄 이상 입력할 수 없습니다.")
		var lastChar = str.slice(0, -1); // 열
		$("#replybox").val(lastChar)
	}
}
/* 여행기 수정 */
function travels_up(){
	var tid = document.getElementById('tid').value;
	var lid = document.getElementById('lid').value;
	
	var url = "travelsUpdate.tv?tid="+tid+"&lid="+lid;
	
	location.href = url;
}
/* 여행기 삭제 */
function travels_del(){
	var tid = document.getElementById('tid').value;
	var url = "travelsDelete.tv?tid="+tid;
	var result = confirm('글을 삭제 하시겠습니까?');
	if(result == true)
	location.href = url;
}

$(document).on("change", "#file_btn1", function() {
	var file = $(this).prop('files')[0];
    var reader = new FileReader();
    reader.onloadend = function (e) {
    	$("#img01").css('background-image', 'url("' + e.target.result + '")');
    }
    reader.readAsDataURL(file);
    $('#file_btn2_label').show();
})
$(document).on("change", "#file_btn2", function() {
	var file = $(this).prop('files')[0];
    var reader = new FileReader();
    reader.onloadend = function (e) {
    	$("#img02").css('background-image', 'url("' + e.target.result + '")');
    }
    reader.readAsDataURL(file);
    $('#file_btn3_label').show();
})
$(document).on("change", "#file_btn3", function() {
	var file = $(this).prop('files')[0];
    var reader = new FileReader();
    reader.onloadend = function (e) {
    	$("#img03").css('background-image', 'url("' + e.target.result + '")');
    }
    reader.readAsDataURL(file);
})

function login(){
	var result = confirm('로그인 해야 가능합니다. 로그인 하시겠습니까?');
	
	if(result == true)
		location.href = "../log-in/";
}
