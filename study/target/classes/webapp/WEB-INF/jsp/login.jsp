<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta name="Author" content="许超&吴志斌">
		<meta name="Keywords" content="用例系统">
		<meta name="Description" content="本系统纯属个人编制，如有雷同纯属巧合">
		<title>友测</title>
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		
	</head>
	<body>		
		<div class="login_hint_error" th:text="${error}" ></div>
		<div id="login_main">
		<span th:text="${error}"></span>
			<div id="login_main_left">
				<div class="login_student_btn">测试人员登录</div>
				<div class="login_teacher_btn">管理员登录</div>
			</div>
			<div id="login_main_right">
			<!-- 学生登录 -->
				<div id="login_student">
					<form action="\userLogin" method="post">
						<table>
							<tr><td></td></tr>
							<tr>
								<td height="30" colspan="2" align="center">
									<b class="typeface_two">学&nbsp;生&nbsp;登&nbsp;录</b>
								</td>
							</tr>
							<tr><td></td></tr>
							<tr>
								<td class="typeface_one">&nbsp;用户名：</td>
								<td>
			 						<input type="text" id="student_id" name="name" class="loginbar"/>
								</td>
							</tr>
							<tr>
								<td class="typeface_one">&nbsp;密码：</td>
								<td height="40px">
									<input type="password" id="password" name="password" class="loginbar"/>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" id="login_btn" value="登录"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<!-- 教师登录 -->
				<div id="login_teacher">
					<form action="loading.jsp" method="post">
						<table>
							<tr><td></td></tr>
							<tr>
								<td height="30" colspan="2" align="center">
									<b class="typeface_two">教&nbsp;师&nbsp;登&nbsp;录</b>
								</td>
							</tr>
							<tr><td></td></tr>
							<tr>
								<td class="typeface_one">&nbsp;工号：</td>
								<td>
									<input type="text" id="teacher_id" name="teacher_id" class="loginbar"/>
								</td>
							</tr>
							<tr>
								<td class="typeface_one">&nbsp;密码：</td>
								<td height="40px">
									<input type="password" id="teacher_password" name="teacher_password" class="loginbar"/>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" id="login_btn" onclick="return login_hint_error()" value="登录"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<script src="js/jquery-1.12.1.min.js"></script>
		<script src="js/login.js"></script>
		<script src="js/script.js"></script>
		
	</body>
</html>
