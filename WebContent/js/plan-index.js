$(function(){
	$("#plantitle").focus();
})

window.onload = function () {
	var start_date = document.getElementById('start_date');
	var end_date = document.getElementById('end_date');
	
	var today = new Date().toISOString().substring(0, 10);
	var maxDate = new Date();
	var end_maxDate = new Date();
	var start = new Date();
	
	start.setDate(start.getDate());
	var end_day = getFormatDate(start);

	start_date.value = today;
	end_date.value = end_day;
	
	maxDate.setDate(maxDate.getDate() + 999);
	var limit = getFormatDate(maxDate);
	
	end_maxDate.setDate(end_maxDate.getDate() + 6);
	var end_limit = getFormatDate(end_maxDate);
	

	start_date.setAttribute("min", today);
	start_date.setAttribute("max", limit);
	
	end_date.setAttribute("min", end_day);
	end_date.setAttribute("max", end_limit);
	
	
	if ($(".plan-box").length < 14) {
		var size = ($(".plan-box").length) + "";
//		var number = size.padStart(2, "1");
		$("#plan-big-box").append($("<div class='plan-box bid"+0+"'>"+
				"<div class='plan-title'><p class='plandivp'>Day1</p></div>"+
		"</div>"));
	}
	//$("form").on("submit", function(event) {
//	$("#okbtn").on("click", function(event){
//		alert("시작");
//	    event.preventDefault();
//	    $(".plan-box").each(function (i, e) {
//	        let string = "";
//	        let string_arr = [];
//	        let day_string = $(".plandivp").text();
//	        $(".addtitle", $(e)).each(function (i2, e2) {
//	           string_arr.push($(e2).text());
//	        });
//	        newForm.append($('<input/>', {
//	           type: 'hidden',
//	           name: day_string,
//	           value: string_arr.join(",")
//	        }));
//	     });
//	    console.log(string_arr);
//	    $("form").attr("action", "planinsert.mk?string_arr=");
//	 });
	$("#okbtn").click(function () {
		 var title = document.getElementById("plantitle").value;
//		 var lid = document.getElementById("planlid").value;
		 var start_date = document.getElementById('start_date').value;
	     var end_date = document.getElementById('end_date').value;
	     var dat1 = new Date(end_date).toISOString(0, 10);
	     var dat2 = new Date(start_date).toISOString(0, 10);
//	     var end = getFormatDate(dat1);
//	     var start = getFormatDate(dat2);
	     
	     
	     if(title == ""){
	    	 alert("여행제목을 지어주세요.")
	    	 $("#plantitle").focus();
	    	 return false;
	     }
	     

	     var result = "";
		   $(".plan-box").each(function (i, e) {
//		      let string = "";
		      let string_arr = [];
		      let day_string = $(".plandivp", $(e)).text().substring(3);
		      
		      $(".addtitle", $(e)).each(function (i2, e2) {
		    	  
		         string_arr.push($(e2).text());
		         result += "&" + day_string + "-" + i2 + "=" + $(e2).attr('lid');
//		         result += "&" + day_string + "-" + i2 + "=" + ;
		      });
//		         console.log(string_arr + " " + day_string);
		   });
		   console.log(result);
		   location.href = "planinsert.mk?title="+title + result +"&plan_end="+ dat1 + "&plan_start="+ dat2 ;
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
/* 체크인 날짜 변경 시 체크아웃 날짜도  변경 */
function start_date_change(){
	var start_selectdate = document.getElementById("start_date").value; 

	var start = new Date(start_selectdate);
	start.setDate(start.getDate());
	document.getElementById("end_date").value = getFormatDate(start);
	var end_maxDate = start;
	end_maxDate.setDate(start.getDate() + 6);
	var end_limit = getFormatDate(end_maxDate);
	end_date.setAttribute("max", end_limit);
	
	
	
}

/* 체크아웃 날짜 변경 시 체크인 날짜 이후로 선택하게 */
function end_date_check(){
	var start_selectdate = document.getElementById("start_date").value; 
	var end_selectdate = document.getElementById("end_date").value; 
	var start = new Date(start_selectdate);
	
	start.setDate(start.getDate());
	
	
	if(end_selectdate < start_selectdate){
		alert("여행종료일은 여행시작일 이후 날짜로 가능합니다");
		document.getElementById("end_date").value = getFormatDate(start);
		return false;
	}
	var start2 = $('#start_date').val();
	var end2 = $('#end_date').val();
//	var plancss = doucumnet.getElementsByClassName("plan-box");
	
	var strDate1 = start2;
	var strDate2 = end2;
	var arr1 = strDate1.split('-');
	var arr2 = strDate2.split('-');
	var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
	var dat2 = new Date(arr2[0], arr2[1], arr2[2]);

	var zz = dat2 - dat1;
	
	
	var currDay = 24 * 60 * 60 * 1000;
	
	var tt = parseInt(zz/currDay)+1;

//  달력 일수 차이
//	alert(parseInt(zz/currDay)+1);
	
	
	if ($("plan-box").length < 7) {
		$("div[class^='plan-box']").remove()
		for(var x = 0 ; x<tt ; x++){
		var size = ($(".plan-box").length) + "";
//		var number = size.padStart(2, "1");
		$("#plan-big-box").append($("<div class='plan-box bid"+x+"'>"+
				"<div class='plan-title'><p class='plandivp'>Day"+(x+1)+"</p></div>"+
		"</div>"));
		}
}
}
//function timeclac(){
//	var start = $('#start_date').val();
//	var end = $('#end_date').val();
//	
//	var url = 'markerList.mk?start_date=' + start + '&end_date='+ end;
//	
//	$(location).attr("href", url);
//}

//$(document).on("click", "#add", function() {
//	}
//})

$(document).on("click", ".plan-box", function() {
	if (!$(this).hasClass("slt")){
		$(".plan-box").removeClass("slt");
		$(this).addClass("slt");
	}
});
$(document).on("click", ".addinfo input[type='image']", function() {
	$(this).closest(".addinfo").remove();
});





	