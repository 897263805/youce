<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>    
    <title>登陆成功加载页面</title>
    
	<link rel="stylesheet" type="text/css" href="css/loading.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script src="js/jquery-1.12.1.min.js"></script>
	<script type="text/javascript">
		//登陆成功加载界面
	!function time(){
		var login;
		login = setInterval(function(){
 			$("#login_hint").css("margin-top","0");
 		});		
		setTimeout(function(){
			window.location.href = 'teacher/center.jsp';
		},1500);
 	}();
	</script>
  </head>  
  <body>
    <div id="loading">
			<div id="login_hint">登录成功</div>
			<div id="loading_list">
				<div class="loading loading-left"></div>
				<div class="loading loading-center"></div>
				<div class="loading loading-right"></div>
			</div>
		</div>
  </body>
</html>
