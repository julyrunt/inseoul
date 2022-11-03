$(function() {
	history.replaceState({},null,location.pathname);
	
	$('#reservation_btn').on('click', function() {

		if ($('#reser_name').val() == '') {
			alert('필수 입력창을 확인해주세요');
			return false;
		}
		if ($('#reser_phone').val() == '') {
			alert('필수 입력창을 확인해주세요');
			return false;
		}
		if (!$('#allcheck').is(':checked')){
			alert('약관에 동의하셔야 합니다');
			return false;
		}
		
		if (($('#certify').val() != $('#certify_').text()) || ($('#certify').val() == '')){
			alert('인증번호를 확인해주세요');
			$('#certify').show();
			$('#certify_').show();
			var random = rand(100000, 999999);
			$('#certify_').text(random);
			return false;
		}
//		popup(); 팝업창 봉인
	})
	/* 동의 체크박스 제어 */
	$('#allcheck').click(function(){
		if($('#allcheck').is(':checked')){
			$('input[name=checkT]').prop('checked', true);
		}else{
			$('input[name=checkT]').prop('checked', false);
		}
	})
	$('input[name=checkT]').click(function(){
		var total = $('input[name=checkT]').length;
		var allchecked = $('input[name=checkT]:checked').length;
		if(total == allchecked){
			$('#allcheck').prop('checked',true);
		}else{
			$('#allcheck').prop('checked',false);
		}
	})
	$('#certify').hide();
	$('#certify_').hide();
	$('#certify').attr('maxlength', 6);
	$('#phone_confirm').on('click', function(){
		$('#certify').show();
		$('#certify_').show();
		var random = rand(100000, 999999);
		$('#certify_').text(random);
	})
	
	
	
})
/* 최종예약 확인 팝업창 생성 */
//function popup() {
//	var popup = window
//			.open('reservation_confirm.jsp', '팝업',
//					'width=400, height=600, left=750px, top=200px, scrollbars=no, resizable=no');
//}
function rand(min, max) {
	  return Math.floor(Math.random() * (max - min + 1)) + min;
}
function gohome(){
	var url = ".././";
	$(location).attr("href", url);
}
function golist(){
	var url = "lodgment.lm";
	$(location).attr("href", url);
}

window.onload = function() {
	/* 예약 최종확인 팝업 */
//	var today = new Date().toISOString().substring(0, 10);
//
//	var h_name = opener.$("#h_name").val();
//	var r_id = opener.$("#r_id").val();
//	var r_name = opener.$("#r_name").val();
//	var t_price = opener.$("#t_price").val();
//	var s_date = opener.$("#s_date").val();
//	var e_date = opener.$("#e_date").val();
//	var name = opener.$("#reser_name").val();
//	var phone = opener.$("#reser_phone").val();
//	var pay_sel = opener.$("#pay_sel").val();
//	var capacity = sessionStorage.getItem("capacity");
//
//	var table = document.createElement('table');
//	var tbody = document.createElement('tbody');
//
//	var table_box = document.getElementById('table_box');
//
//	var row_0 = document.createElement('tr');
//	var today1 = document.createElement('td');
//	today1.innerHTML = "예약 날짜";
//	var today2 = document.createElement('td');
//	today2.innerHTML = today;
//
//	row_0.appendChild(today1);
//	row_0.appendChild(today2);
//	tbody.appendChild(row_0);
//
//	var row_1 = document.createElement('tr');
//	var name1 = document.createElement('td');
//	name1.innerHTML = "예약자 이름";
//	var name2 = document.createElement('td');
//	name2.innerHTML = name;
//
//	row_1.appendChild(name1);
//	row_1.appendChild(name2);
//	tbody.appendChild(row_1);
//
//	var row_2 = document.createElement('tr');
//	var phone1 = document.createElement('td');
//	phone1.innerHTML = "예약자 연락처";
//	var phone2 = document.createElement('td');
//	phone2.innerHTML = phone;
//
//	row_2.appendChild(phone1);
//	row_2.appendChild(phone2);
//	tbody.appendChild(row_2);
//
//	var row_3 = document.createElement('tr');
//	var h_name1 = document.createElement('td');
//	h_name1.innerHTML = "숙소명";
//	var h_name2 = document.createElement('td');
//	h_name2.innerHTML = h_name;
//
//	row_3.appendChild(h_name1);
//	row_3.appendChild(h_name2);
//	tbody.appendChild(row_3);
//
//	var row_4 = document.createElement('tr');
//	var r_name1 = document.createElement('td');
//	r_name1.innerHTML = "방이름";
//	var r_name2 = document.createElement('td');
//	r_name2.innerHTML = r_name;
//
//	row_4.appendChild(r_name1);
//	row_4.appendChild(r_name2);
//	tbody.appendChild(row_4);
//
//	var row_5 = document.createElement('tr');
//	var s_date1 = document.createElement('td');
//	s_date1.innerHTML = "체크인";
//	var s_date2 = document.createElement('td');
//	s_date2.innerHTML = s_date;
//
//	row_5.appendChild(s_date1);
//	row_5.appendChild(s_date2);
//	tbody.appendChild(row_5);
//
//	var row_6 = document.createElement('tr');
//	var e_date1 = document.createElement('td');
//	e_date1.innerHTML = "체크아웃";
//	var e_date2 = document.createElement('td');
//	e_date2.innerHTML = e_date;
//
//	row_6.appendChild(e_date1);
//	row_6.appendChild(e_date2);
//	tbody.appendChild(row_6);
//
//	var row_7 = document.createElement('tr');
//	var person1 = document.createElement('td');
//	person1.innerHTML = "예약인원";
//	var person2 = document.createElement('td');
//	person2.innerHTML = capacity+" 명";
//
//	row_7.appendChild(person1);
//	row_7.appendChild(person2);
//	tbody.appendChild(row_7);
//	
//	var row_8 = document.createElement('tr');
//	var pay_sel1 = document.createElement('td');
//	pay_sel1.innerHTML = "결제 방법";
//	var pay_sel2 = document.createElement('td');
//	pay_sel2.innerHTML = pay_sel;
//
//	row_8.appendChild(pay_sel1);
//	row_8.appendChild(pay_sel2);
//	tbody.appendChild(row_8);
//
//	var row_9 = document.createElement('tr');
//	var t_price1 = document.createElement('td');
//	t_price1.innerHTML = "총 가격";
//	var t_price2 = document.createElement('td');
//	t_price2.innerHTML = t_price + " 원";
//
//	row_9.appendChild(t_price1);
//	row_9.appendChild(t_price2);
//	tbody.appendChild(row_9);
//
//	table.appendChild(tbody);
//	table_box.appendChild(table);

//	$('#confirm_btn').on('click', function() {
//		
//		var url = 'reservation_success.lm?roomid='+r_id+'&check_in='+s_date+'&check_out='+e_date+'&price='+t_price+'&capacity='+capacity;
//		alert('예약이 완료되었습니다');
//		sessionStorage.removeItem("capacity");
		
//		window.opener.location.href = url;
//		self.close();
//		opener.location.href = url;
		
// $(location).attr("href", url);
//	})
}

