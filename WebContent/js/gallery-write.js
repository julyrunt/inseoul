$( function() {
	$.widget( "custom.combobox", {
		_create: function() {
			this.wrapper = $( "<span>" )
			.addClass( "custom-combobox" )
			.insertAfter( this.element );

			this.element.hide();
			this._createAutocomplete();
			this._createShowAllButton();
		},

		_createAutocomplete: function() {
			var selected = this.element.children( ":selected" ),
			value = selected.val() ? selected.text() : "";

			this.input = $( "<input>" )
			.appendTo( this.wrapper )
			.val( value )
			.attr( "title", "" )
			.addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
			.autocomplete({
				delay: 0,
				minLength: 0,
				source: this._source.bind( this )
			})
			.tooltip({
				classes: {
					"ui-tooltip": "ui-state-highlight"
				}
			});

			this._on( this.input, {
				autocompleteselect: function( event, ui ) {
					ui.item.option.selected = true;
					this._trigger( "select", event, {
						item: ui.item.option
					});
				},

				autocompletechange: "_removeIfInvalid"
			});
		},

		_createShowAllButton: function() {
			var input = this.input,
			wasOpen = false;

			$( "<a>" )
			.attr( "tabIndex", -1 )
			.attr( "title", "Show All Items" )
			.tooltip()
			.appendTo( this.wrapper )
			.button({
				icons: {
					primary: "ui-icon-triangle-1-s"
				},
				text: false
			})
			.removeClass( "ui-corner-all" )
			.addClass( "custom-combobox-toggle ui-corner-right" )
			.on( "mousedown", function() {
				wasOpen = input.autocomplete( "widget" ).is( ":visible" );
			})
			.on( "click", function() {
				input.trigger( "focus" );

				// Close if already visible
				if ( wasOpen ) {
					return;
				}

				// Pass empty string as value to search for, displaying all results
				input.autocomplete( "search", "" );
			});
		},

		_source: function( request, response ) {
			var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
			response( this.element.children( "option" ).map(function() {
				var text = $( this ).text();
				if ( this.value && ( !request.term || matcher.test(text) ) )
					return {
					label: text,
					value: text,
					option: this
				};
			}) );
		},

		_removeIfInvalid: function( event, ui ) {

			// Selected an item, nothing to do
			if ( ui.item ) {
				return;
			}

			// Search for a match (case-insensitive)
			var value = this.input.val(),
			valueLowerCase = value.toLowerCase(),
			valid = false;
			this.element.children( "option" ).each(function() {
				if ( $( this ).text().toLowerCase() === valueLowerCase ) {
					this.selected = valid = true;
					return false;
				}
			});

			// Found a match, nothing to do
			if ( valid ) {
				return;
			}

			// Remove invalid value
			this.input
			.val( "" )
			.attr( "title", value + "는 현재 제공하지 않는 관광지입니다." )
			.tooltip( "open" );
			this.element.val( "" );
			this._delay(function() {
				this.input.tooltip( "close" ).attr( "title", "" );
			}, 2500 );
			this.input.autocomplete( "instance" ).term = "";
		},

		_destroy: function() {
			this.wrapper.remove();
			this.element.show();
		}
	});
	$("#location").combobox();
	$(document).on("click", "#cancel", function() {
		history.back();
	})
	$(document).on("click", "#add", function() {
		if ($(".file-input").length < 10) {
			var size = ($(".file-input").length + 1) + "";
			var number = size.padStart(2, "0");
			$("#file-input-list").append($("<li class='file-input'>"+
					"<div id='preview" + number + "'></div>"+
					"<input name='img" + number + "' type='file' id='img" + number + "' accept='image/png' required='required' />"+
			"</li>"));
		}
		refreshButtons();
	})
	$(document).on("click", "#remove", function() {
		if ($(".file-input").length > 1) {
			$(".file-input:last-child").remove();
		}
		refreshButtons();
	})
	$(document).on("change", "#img01", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview01").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img02", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview02").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img03", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview03").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img04", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview04").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img05", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview05").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img06", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview06").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img07", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview07").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img08", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview08").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img09", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview09").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
	$(document).on("change", "#img10", function() {
		var file = $(this).prop('files')[0];
		var reader = new FileReader();
		reader.onloadend = function (e) {
			$("#preview10").css('background-image', 'url("' + e.target.result + '"), url("../images/transparent.svg")');
		}
		reader.readAsDataURL(file);
		checkFileSize();
	})
})

function refreshButtons() {
	if ($(".file-input").length == 10) {
		$("#add").attr("disabled", "disabled");
	} else {
		$("#add").removeAttr("disabled");
	}
	if ($(".file-input").length == 1) {
		$("#remove").attr("disabled", "disabled");
	} else {
		$("#remove").removeAttr("disabled");
	}
	$("#photo-count").html($(".file-input").length);
	checkFileSize();
}

function checkFileSize() {
	var mb = 1024 * 1024;
	var max = 5 * mb * 10;
	var sum = 0;
	var inputFiles = $("input[type='file' i]");
	if (inputFiles.length > 0) {
		for (var i = 0; i < inputFiles.length; i++) {
			if (inputFiles[i].files.length > 0) {
				sum += inputFiles[i].files[0].size;
			}
		}
	}
	var result = (max >= sum); 
	if (result) {
		$("#file-size").html((sum / mb).toFixed(1)+"MB / 50MB · 파일형식: png · 파일명: 공백문자 제외");
	} else {
		$("#file-size").html("<font color='red'>" + (sum / mb).toFixed(1)+"MB</font> / 50MB · 파일형식: png · 파일명: 공백문자 제외");
	}
	return result;
}
