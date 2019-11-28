			// 学生教师3D登录按钮
			$(".login_student_btn").click(function(){
				$("#login_main_right").css("transform","rotateX(-90deg)");
				$("#login_main_right").css("top","15px");
			});
			$(".login_teacher_btn").click(function(){
				$("#login_main_right").css("transform","rotateX(0deg)");
				$("#login_main_right").css("top","8px");
			});
			// 点击登录发生的事件
			function login_hint_error(){
				var login;
				var teacher;
				var teacher_password = document.getElementById("teacher_password").value;
				var teacher_id = document.getElementById("teacher_id").value;
				if(teacher_password == "admin" && teacher_id == "admin"){
					
					return true;
				}else{
					$(".login_hint_error").css("margin-top","0");
					var login = setTimeout(function(){
						$(".login_hint_error").css("margin-top","-25px");
					},1500);
					return false;
				}
			}
			