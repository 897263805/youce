//试题类型选择触发时间
		window.onload = function(){
			questionType();
			var select = document.getElementById("questionType");
			select.addEventListener("click",questionType,false);
			}
		function questionType(){
			var typeDiv = document.getElementsByClassName("answer");
			for(var i=0; i<typeDiv.length;i++){
				typeDiv[i].style.display = "none";
			}
				var typeValue = document.getElementById("questionType").value;
				if(typeValue === "1"){
					typeDiv[0].style.display = "block";
				}else if(typeValue === "2"){
					typeDiv[1].style.display = "block";
				}else if(typeValue === "3"){
					typeDiv[2].style.display = "block";
				}
				else if(typeValue === "4"){
					typeDiv[3].style.display = "block";
				}
				else{
					typeDiv[4].style.display = "block";
				}			
		}