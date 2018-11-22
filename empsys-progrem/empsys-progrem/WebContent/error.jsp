<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="base.css">
<title>エラー</title>
</head>
<%
	String errorMessage = (String)session.getAttribute("errorMessage");
	String user_id = (String)session.getAttribute("user_id");
%>
<body>
	<div id="container">
		<div id="header">
			<h1>従業員情報管理システム</h1>
			<p> &nbsp;</p>
		</div>
		<div id ="hello">
		<% if(user_id == null){ %>
			ようこそ
		<% } else {%>
			ようこそ、<%=user_id %>さん
		<% } %>
		</div>
		<div class ="select">
			<%if(session.getAttribute("login_session") == null){ %>
				<a class="btn"  href="./login_form.html">ログイン</a>
			<%}else{ %>
				<a class="btn"  onClick="history.back()">前の画面へ戻る</a>
			<%} %>
		</div>

		<div id ="content">
			<h2>エラー</h2>
			<p><%=errorMessage %></p>
		</div>
		
		<div id="footer">
			Webアプリケーション応用講座
		</div>
	</div>
</body>
</html>
