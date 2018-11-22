<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="base.css">
	<title>登録完了</title>
</head>
<%String user_id =(String)session.getAttribute("user_id"); %>
	<body>
		<div id="container">
			<div id="header">
					<h1>従業員情報管理システム</h1>
					<p> &nbsp;</p>
			</div>
			
			<div id ="hello">
				ようこそ、<%=user_id %>さん
			</div>
			
			<div class ="select">
				<a class="btn" href="./employee_menu.jsp">従業員情報管理メニュー</a>
			</div>

			<div id ="content">
					<h2>登録完了</h2>
					<p>登録が完了しました。</p>
			 </div>
			 
			 <div id="footer">
				Webアプリケーション応用講座
			</div>
		</div>
 	</body>
</html>