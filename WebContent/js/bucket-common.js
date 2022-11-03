$(function() {
	/* --------------------------------------------------------------------------------------------------------------
	 * 관광지 선택 위젯
	 * -------------------------------------------------------------------------------------------------------------- */
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
				$(".location-name").html(this.input.val());
				$(".bucket-ticket > div:first-child").css("background", "linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url('../images/" + $("#location option:selected").attr("bg") + "'), url('../images/image_not_supported_black.svg')");
				$(".bucket-ticket > div:first-child").css("background-size", "100%");
				$(".bucket-ticket > div:first-child").css("background-position", "center");
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
				$(".location-name").html(this.input.val());
				$(".bucket-ticket > div:first-child").css("background", "linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url('../images/" + $("#location option:selected").attr("bg") + "'), url('../images/image_not_supported_black.svg')");
				$(".bucket-ticket > div:first-child").css("background-size", "100%");
				$(".bucket-ticket > div:first-child").css("background-position", "center");
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
	/* --------------------------------------------------------------------------------------------------------------
	 * 뒤로가기 버튼
	 * -------------------------------------------------------------------------------------------------------------- */
	$(document).on("click", "#cancel", function() {
		history.back();
	})
	/* --------------------------------------------------------------------------------------------------------------
	 * 버킷리스트 할것 입력 이벤트
	 * -------------------------------------------------------------------------------------------------------------- */
	$("#item").keyup(function() {
		$(".bucket-item").html($("#item").val());
	});
	/* --------------------------------------------------------------------------------------------------------------
	 * 버킷리스트 예정 상태 변경 이벤트
	 * -------------------------------------------------------------------------------------------------------------- */
	$(".progress").change(function() {
		if ($('input:radio[name=progress]:checked').val() == 0) {
			$("#icon-progress").html("pending");
			$("#icon-progress").attr("class", "material-symbols-outlined progress");
		} else {
			$("#icon-progress").html("task_alt");
			$("#icon-progress").attr("class", "material-symbols-outlined complete");
		}
	});
})
function check() {
	if ($("#location option:selected").val() == null || $("#location option:selected").val() == "") {
		return false;
	}
	if ($("#item").val() == null || $("#item").val() == "") {
		return false;
	}
	return true;
}