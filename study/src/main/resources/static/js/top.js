
 !function dispTime(){
	 	var date = new Date();
	 	var time = document.getElementById("clock");
 		time.innerHTML = "<b style='color:#fff;line-height:70px;'>"+date.toLocaleString()+"</b>";
 		setTimeout(dispTime,1000);
 	}();
 	
 	