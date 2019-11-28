		var arr = [];
		var h = document.documentElement.clientHeight;
		$(".sub").each(function(i){
			$(this).css("top" ,i*27 + "px");
			$(".sub").css("height" ,h + "px");
			arr[i] = i*27;
		});
		$(".font").click(function(){
			var index = $(this).parent().index();
			$(".sub").each(function(i){
				if(i<=index){
					$(this).animate({top:arr[i] + "px"});
				}else{
					$(this).animate({top:arr[i] + h - (6*27+2) + "px"});
				}
			});
		});
		