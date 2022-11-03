window.onload = function (){
/* 체크인 체크아웃 날짜 초기값 오늘로 적용 */
var start_date = document.getElementById('start_date');
var end_date = document.getElementById('end_date');

var today = new Date().toISOString().substring(0, 10);
var maxDate = new Date();
var end_maxDate = new Date();
var start = new Date();

start.setDate(start.getDate()+1);
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

/* 숙박 리뷰 아코디언? 효과 */
var article = (".tb .show");
$(".tb .item td").click(function() {

	var myArticle = $(this).parents().next("tr");
	if ($(myArticle).hasClass('hide')) {
		$(article).removeClass('show').addClass('hide');
		$(myArticle).removeClass('hide').addClass('show');
	} else {
		$(myArticle).addClass('hide').removeClass('show');
	}
});

}
/* 현재 시간을 yyyy-mm-dd 로 변환해서 리턴 */
function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M 달은 0~11로 계산되기 때문에 +1을 해줘야 됨
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}
/* 체크인 날짜 변경 시 체크아웃 날짜도 +1 변경 */
function start_date_change(){
	var start_selectdate = document.getElementById("start_date").value; 

	var start = new Date(start_selectdate);
	start.setDate(start.getDate()+1);
	document.getElementById("end_date").value = getFormatDate(start);

}
/* 체크아웃 날짜 변경 시 체크인 날짜 이후로 선택하게 */
function end_date_check(){
	var start_selectdate = document.getElementById("start_date").value; 
	var end_selectdate = document.getElementById("end_date").value; 
	var start = new Date(start_selectdate);
	start.setDate(start.getDate()+1);
	
	if(end_selectdate < start_selectdate){
		alert("체크아웃은 체크인 이후 날짜로 가능합니다");
		document.getElementById("end_date").value = getFormatDate(start);
		return false;
	}
}

function reserv(roomid, price, capacity){
	
	var start = $('#start_date').val();
	var end = $('#end_date').val();
	var hid = $('.hid').val();
	
	var url = 'RoomReservation.lm?hid=' + hid + '&roomid=' + roomid + '&price=' + price + '&start_date=' + start + '&end_date='+ end + '&capacity='+capacity;
	
	$(location).attr("href", url);
}



function write_review(){
	var modal = document.getElementById('modal');
	modal.style.display = 'flex';
}

function modaloff(){
	var modal = document.getElementById('modal');
	modal.style.display = 'none';
}

function reviewWrite(uid){
	var title = document.getElementById('re_title').value;
	var contents = document.getElementById('re_contents').value;
	var warning = document.getElementById('warning');
	
	if(title == ''){
		warning.innerText = "제목을 작성해주세요";
		return false;
	}
	if(contents == ''){
		warning.innerText = "내용을 작성해주세요";
		return false;
	}
	
	$.ajax({
		url : 'review_write.lm',
		data : {'uid' : uid, 'title' : title, 'contents' : contents},
		dataType : 'html',
		success : function(data) {
			$(".reviewbox").html(data);
		},
		error : function() {
			alert("fail");
		}
	});
}
