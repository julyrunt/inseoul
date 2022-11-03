$( function() {
	/* --------------------------------------------------------------------------------------------------------------
	 * 팝업 닫기
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", ".cancel", function() {
		window.close();
	});
	/* --------------------------------------------------------------------------------------------------------------
	 * 이미시 파일 선택시 미리보기 및 유효성 검사
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("change", "#image", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview").css('background-image', 'url("' + e.target.result + '"), url("images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	});
})
function photoCheck() {
	/* --------------------------------------------------------------------------------------------------------------
	 * 업로드 이미지 유효성 검사
	 * -------------------------------------------------------------------------------------------------------------- */
	var files = $("#image")[0].files;
	if(files.length < 1){
		return false;
	}
	var file = files[0];
	if (file.size > 5 * 1024 * 1024) {
		return false;
	}
	var filePath = $("#image").val();
	var fReader = new FileReader();
	if (filePath.substring(filePath.length - 4) == ".png"){
		fReader.readAsDataURL(file);
		fReader.onloadend = function(event){
			$('#preview').css('background-image', 'url("' + event.target.result + '"), url("images/transparent.svg")');
		}
		return true;
	} else {
		$('#preview').css('background-image', 'url("images/transparent.svg")');
	}
	$("#image").val("");
	return false;
}