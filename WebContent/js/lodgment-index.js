$(function() {
	history.replaceState({},null,location.pathname);
//	window.location.reload();
	/* 숙박인덱스 숙박등급, 필터 */
	$('#searchbtn').click(
			function() {
				var rating = $('input[name=rating]:input').val();
				var selects_vals = new Array();
				$("input[name='selects']:checked").each(function() {
					selects_vals.push(this.value);
				});
				var capacity = $('#cnt-per').text();

				var start = $('#start_date').val();
				var end = $('#end_date').val();
				var bedtype = $('input[name=bed-type]:checked').val();
				

				$.ajax({
					url : 'lodgment-filter.lm?rating=' + rating + '&selects='
							+ selects_vals + '&capacity=' + capacity
							+ '&checkin=' + start + '&checkout=' + end
							+ '&bedtype=' + bedtype,
					dataType : 'html',
					traditional : true,
					success : function(data) {
						$("#right-bigbox").html(data);
					},
					error : function() {
						alert("fail");
					}
				});
			})

	// $('#searchbtn').click(function() {
	// var rating = $('input[name=rating]:input').val();
	// var selects_vals = [];
	// var selects_val = $('.check');
	// $(selects_val).each(function() {
	// if ($(this).is(":checked")){
	// selects_val = 1;
	// }else{
	// selects_val = 0;
	// }
	// selects_vals.push(selects_val);
	// })
	// for(var i =0 ; i<selects_vals.length; i++){
	// console.log(i+":" + selects_vals[i]);
	// }
	// $.ajax({
	// url : 'lodgment-filter.lm?rating=' + rating + '&selects=' + selects_vals,
	// dataType : 'html',
	// traditional: true,
	// success : function(data) {
	// $("#right-bigbox").html(data);
	// },
	// error : function() {
	// alert("fail");
	// }
	// });
	// })

	/* 숙박인덱스 필터 라디오 */
	// $('#searchbtn').click(function() {
	// var rating = $('input[name=rating]:checked').val();
	// $.ajax({
	// url : 'lodgment-filter.lm?rating=' + rating,
	// dataType : 'html',
	// success : function(data) {
	// $("#right-bigbox").html(data);
	// },
	// error : function() {
	// alert("fail");
	// }
	// });
	// })
	/* 초기화 버튼 */
	// $('#resetbtn').click(function() {
	// $.ajax({
	// url : 'lodgment.lm',
	// dataType : 'html',
	// success : function(data) {
	// $("body").html(data);
	//				
	// },
	// error : function() {
	// alert("fail");
	// }
	// });
	// })
			
	/* 검색 */
	$('#search').keydown(function(key) {
		if (key.keyCode == 13) {
			var search = $('#search').val();

			$.ajax({
				url : 'lodgment-search.lm?search=' + search,
				dataType : 'html',
				success : function(data) {
					
					$("#right-bigbox").html(data);
				},
				error : function() {
					alert('fail');
				}
			});
		}
	})
	$(document).on('click', '.lodgment-contents', function() {
		
		var capacity = $('#cnt-per').text();
		var hid = $(this).attr("hid");
		
		location.href = "lodgment-detail.lm?hid="+hid+"&capacity="+capacity;
		
	})
	/* 베드타입 선택 css */
	$('input[name=bed-type]').on('change', function() {
		switch ($('input[name=bed-type]:checked').val()) {
		case "s":
			$('#bed-type_s img').attr('src','images/icon-bed-s_r.png');
			$('#bed-type_d img').attr('src','images/icon-double-bed.png');
			$('#bed-type_t img').attr('src','images/icon-twin-beds.png');
			$('#bed-type_o img').attr('src','images/icon-bed-o.png');
			$('#bed-type_s').css('color', 'red');
			$('#bed-type_d').css('color', 'black');
			$('#bed-type_t').css('color', 'black');
			$('#bed-type_o').css('color', 'black');
			break;
		case "d":
			$('#bed-type_d img').attr('src','images/icon-double-bed_r.png');
			$('#bed-type_s img').attr('src','images/icon-bed-s.png');
			$('#bed-type_t img').attr('src','images/icon-twin-beds.png');
			$('#bed-type_o img').attr('src','images/icon-bed-o.png');
			$('#bed-type_s').css('color', 'black');
			$('#bed-type_d').css('color', 'red');
			$('#bed-type_t').css('color', 'black');
			$('#bed-type_o').css('color', 'black');
			break;
		case "t":
			$('#bed-type_t img').attr('src','images/icon-twin-beds_r.png');
			$('#bed-type_s img').attr('src','images/icon-bed-s.png');
			$('#bed-type_d img').attr('src','images/icon-double-bed.png');
			$('#bed-type_o img').attr('src','images/icon-bed-o.png');
			$('#bed-type_s').css('color', 'black');
			$('#bed-type_d').css('color', 'black');
			$('#bed-type_t').css('color', 'red');
			$('#bed-type_o').css('color', 'black');
			break;
		case "o":
			$('#bed-type_o img').attr('src','images/icon-bed-o_r.png');
			$('#bed-type_s img').attr('src','images/icon-bed-s.png');
			$('#bed-type_d img').attr('src','images/icon-double-bed.png');
			$('#bed-type_t img').attr('src','images/icon-twin-beds.png');
			$('#bed-type_s').css('color', 'black');
			$('#bed-type_d').css('color', 'black');
			$('#bed-type_t').css('color', 'black');
			$('#bed-type_o').css('color', 'red');
			break;
		default:
			break;
		}
	})
})
window.onload = function() {
	/* 체크인 체크아웃 날짜 초기값 오늘로 적용 */
	var start_date = document.getElementById('start_date');
	var end_date = document.getElementById('end_date');

	var today = new Date().toISOString().substring(0, 10);
	var maxDate = new Date();
	var end_maxDate = new Date();
	var start = new Date();

	start.setDate(start.getDate() + 1);
	var end_day = getFormatDate(start);

	start_date.value = today;
	end_date.value = end_day;

	maxDate.setDate(maxDate.getDate() + 60);
	var limit = getFormatDate(maxDate);
	end_maxDate.setDate(end_maxDate.getDate() + 70);
	var end_limit = getFormatDate(end_maxDate);

	start_date.setAttribute("min", today);
	start_date.setAttribute("max", limit);

	end_date.setAttribute("min", end_day);
	end_date.setAttribute("max", end_limit);

}
/* 현재 시간을 yyyy-mm-dd 로 변환해서 리턴 */
function getFormatDate(date) {
	var year = date.getFullYear(); // yyyy
	var month = (1 + date.getMonth()); // M 달은 0~11로 계산되기 때문에 +1을 해줘야 됨
	month = month >= 10 ? month : '0' + month; // month 두자리로 저장
	var day = date.getDate(); // d
	day = day >= 10 ? day : '0' + day; // day 두자리로 저장
	return year + '-' + month + '-' + day; // '-' 추가하여 yyyy-mm-dd 형태 생성 가능
}
/* 체크인 날짜 변경 시 체크아웃 날짜도 +1 변경 */
function start_date_change() {
	var start_selectdate = document.getElementById("start_date").value;

	var start = new Date(start_selectdate);
	start.setDate(start.getDate() + 1);
	document.getElementById("end_date").value = getFormatDate(start);

}
/* 체크아웃 날짜 변경 시 체크인 날짜 이후로 선택하게 */
function end_date_check() {
	var start_selectdate = document.getElementById("start_date").value;
	var end_selectdate = document.getElementById("end_date").value;
	var start = new Date(start_selectdate);
	start.setDate(start.getDate() + 1);

	if (end_selectdate < start_selectdate) {
		alert("체크아웃은 체크인 이후 날짜로 가능합니다");
		document.getElementById("end_date").value = getFormatDate(start);
		return false;
	}
}

/* 인원 설정 */
function per_cnt(type) {
	var resultcnt = document.getElementById('cnt-per');
	var cnt_person = resultcnt.innerText;

	if (type === 'minus') {
		if (cnt_person > 2)
			cnt_person = parseInt(cnt_person) - 1;
	} else if (type === 'plus') {
		if (cnt_person < 10)
			cnt_person = parseInt(cnt_person) + 1;
	}
	resultcnt.innerText = cnt_person;
}

