
window.onload = function (){
/* 모집기한, 여행날짜 오늘 날짜로 적용 */
var mojib_limit = document.getElementById('mojib_limit');
var trip_start = document.getElementById('trip_start');
var trip_end = document.getElementById('trip_end');
var today = new Date().toISOString().substring(0, 10);
var maxDate = new Date();
var start = new Date();
start.setDate(start.getDate()+1);
var start_day = getFormatDate(start);

mojib_limit.value = today;
trip_start.value = start_day;
trip_end.value = start_day;
maxDate.setDate(maxDate.getDate() + 30);
var limit = getFormatDate(maxDate);

mojib_limit.setAttribute("min", today);
mojib_limit.setAttribute("max", limit);
trip_start.setAttribute("min", start_day);
trip_end.setAttribute("min", start_day);


}

/* 사진 첨부 하면 맨 아래 사진 띄우기 */
$(document).on("change", "#file_btn", function() {
	var file = $(this).prop('files')[0];
    var reader = new FileReader();
    reader.onloadend = function (e) {
    	$(".photozone-one").css('background-image', 'url("' + e.target.result + '")');
    }
    reader.readAsDataURL(file);
})

/* 모집기한 날짜 선택 제한 */
function mojib_limit_check(){
var selectdate = document.getElementById("mojib_limit").value; // input date 값 가져오기
var start_selectdate = document.getElementById("trip_start").value; 
var end_selectdate = document.getElementById("trip_end").value; 

var date = new Date(); 									//현재시간
var minDate = getFormatDate(date); 

date.setDate(date.getDate() + 30);
var maxDate = getFormatDate(date);

if (selectdate < minDate) {
	alert("모집기한을 오늘 이후의 날짜를 선택해주세요");
	document.getElementById("mojib_limit").value = minDate;

	return false;
}
if (selectdate > maxDate) {
	alert("+30일까지 설정 가능합니다.");
	document.getElementById("mojib_limit").value = minDate;
	
	return false;
	}
if (selectdate > start_selectdate){
	document.getElementById("trip_start").value = selectdate;
	document.getElementById("trip_end").value = selectdate;
}
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

/* 여행기간 제한설정 */
function trip_day_check(){
	var start_selectdate = document.getElementById("trip_start").value; 
	var end_selectdate = document.getElementById("trip_end").value; 
	var mojib_limit = document.getElementById("mojib_limit").value;
	
	var date = new Date(); 
	date.setDate(date.getDate()+1);
	var minDate = getFormatDate(date);
	var end = new Date(start_selectdate);	
	
	date.setDate(date.getDate() + 60);
	var maxDate = getFormatDate(date);
	
	end.setDate(end.getDate() + 30);
	var end_limit = getFormatDate(end);
	
	if (start_selectdate < minDate) {
		alert("여행시작일을 오늘 이후의 날짜를 선택해주세요");
		document.getElementById("trip_start").value = minDate;
		
		return false;
	}
	if (start_selectdate > maxDate) {
		alert("+60일까지 설정 가능합니다.");
		document.getElementById("trip_start").value = minDate;
		
		return false;
	}
	if (end_selectdate < start_selectdate){
		document.getElementById("trip_end").value = start_selectdate;
		
		return false;
	}
	if (end_selectdate > end_limit){
		alert("여행 끝 날짜는 여행시작 이후 +30일까지만 가능합니다");
		document.getElementById("trip_end").value = start_selectdate;
		
		return false;
	}
	if (start_selectdate < mojib_limit){
		alert("여행 시작 날짜는 모집기한 이후만 가능합니다");
		document.getElementById("trip_start").value = mojib_limit;
		
		return false;
	}
}
function trip_endday_check(){
	var start_selectdate = document.getElementById("trip_start").value; 
	var end_selectdate = document.getElementById("trip_end").value; 
	
	if(end_selectdate < start_selectdate){
		alert("여행 종료 날짜는 여행시작 이후 가능합니다");
		document.getElementById("trip_end").value = start_selectdate;
		return false;
	}
}
function dues_check() {
	var duesInput = $('#dues').val();
	var number = /[0-9]/g.test(duesInput);
	var symbol = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\s]/g.test(duesInput);
	var lower = /[a-z]/g.test(duesInput);
	var upper = /[A-Z]/g.test(duesInput);
	var korean = /[ㄱ-ㅎ]/g.test(duesInput);
	if (!number){
		alert("회비는 숫자만 작성 가능합니다");
		$('#dues').val("0");
	}else if(symbol || lower || upper || korean){
		alert("회비는 숫자만 작성 가능합니다");
		$('#dues').val("0");
	}
}





