	
	//当鼠标移动到文本框与密码框时触发事件
	window.onload = function(){
		var elements = document.getElementsByTagName("input");
		for (var i = 0; i<elements.length; i++ ){
			var type = elements[i].type;
			if(type == "text" || type == "password"){
				elements[i].onmouseover = function(){
					this.style.background = "#ccccff";
				};
				elements[i].onmouseout = function(){
					this.style.background = "#fff";
				};
			}
		}
	}
	/*!function(global){
		var error = function(data){
			if(data.error1 != ""){
				setTimeout(function(){
					data.hint.style.margin="0";
				},0);
				setTimeout(function(){
					data.hint.style.margin="-25px 0 0 0";
				},1500);
			}
		};
		global.error=error;
	}(window);*/
	
	