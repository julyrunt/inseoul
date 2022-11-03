window.onload = () => {
  // panel-faq-container
  const panelFaqContainer = document.querySelectorAll(".panel-faq-container"); // NodeList 객체
  
  // panel-faq-answer
  let panelFaqContents = document.querySelectorAll(".panel-faq-contents");

  // btn-all-close
  const btnAllClose = document.querySelector("#btn-all-close");
  
  // 반복문 순회하면서 해당 FAQ제목 클릭시 콜백 처리
  for( let i=0; i < panelFaqContainer.length; i++ ) {
    panelFaqContainer[i].addEventListener('click', function() { // 클릭시 처리할 일
      // FAQ 제목 클릭시 -> 본문이 보이게끔 -> active 클래스 추가
    	var ref = $(".ajaxref").val();  //클래스
		var uid = $(".ajaxuid").val();		 
		var sid = $(".ajaxsid").val();		 
		var page = $(".ajaxpage").val();		 
		(function(i) {
			$.ajax({ 
			// url에 가서 
			url : 'sc-reply-ajax.jsp?ref='+ref+'&uid='+uid+'&sid='+sid+'&page='+page,
			// dataType 형태의 파일을 가져와
			dataType : "html",
			//성공하면
			success : function(data){
				$(".replies").html(data);
			},
			// 실패하면
			error : function(){
				alert("fail");
			}
		});
      panelFaqContents[i].classList.toggle('active');
		})(i);
    });
  };
  
  btnAllClose.addEventListener('click', function() {
    // 버튼 클릭시 처리할 일  
    for(let i=0; i < panelFaqContents.length; i++) {
        panelFaqContents[i].classList.remove('active');
    };
  });
}
//$(document).ready(function(){
//	$(".panel-faq-container").click(function(){
//		// ajax 실행
//		var ref = $("#ajaxref").val();
//		var uid = $("#ajaxuid").val();		 
//		$.ajax({ 
//			// url에 가서 
//			url : 'sc-reply-ajax.jsp?ref='+ref+'&uid='+uid,
//			// dataType 형태의 파일을 가져와
//			dataType : "html",
//			//성공하면
//			success : function(data){
//				$("#replies").html(data);
//			},
//			// 실패하면
//			error : function(){
//				alert("fail");
//			}
//		});
//	});
//});