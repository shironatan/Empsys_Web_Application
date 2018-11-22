<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
	<link rel="stylesheet" type="text/css" href="base.css">
	<script type="text/javascript">
		<!--
			history.forward();
		//-->
	</script>
	<title>ログアウト</title>
</head>
<%
   // セッションの削除
   session.invalidate();
%>
<body>
<div id="container">
	<div id="header">
		<h1>従業員情報管理システム</h1>
		<p> &nbsp;</p>
	</div>
	<div class ="select">
		<a class="btn" href="./index.html">ログイン</a>
	</div>
	<div id ="content">
		<h2>ログアウト</h2>
		<p>ログアウトが完了しました。</p>
	 </div>
	 <div id="footer">Webアプリケーション応用講座</div>
</div>
</body>
</html>