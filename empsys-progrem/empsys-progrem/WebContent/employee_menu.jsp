
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="/error.jsp"%>
<!DOCTYPE html>
<html>

<head>
<!-- メニューを表示する -->
<!-- ログインしている、していない状態を判定する -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="base.css">
<title>従業員情報管理メニュー</title>
	<script type="text/javascript">
	function list(){
		document.menu.elements['operation'].value = "list";
		document.menu.submit();
		return true;
	}

	function entry(){
		document.menu.elements['operation'].value = "entry";
		document.menu.submit();
		return true;
	}
	</script>
</head>

<body>
<div id="container">
<div id="header">
	<h1>従業員情報管理システム</h1>
	<p>従業員情報管理サブ</p>
</div>

<% String name = (String)session.getAttribute("user_id"); %>
<div id="hello">
	ようこそ、<%=name %>さん
</div>

<div id="content">

	<h2>従業員情報管理メニュー</h2>

	<table id="table-menu" summary="">

		<tr>
			<td><a class="btn" href="#" onclick="list();">従業員情報一覧</a></td>
		</tr>
		<tr>
			<td><a class="btn" href="#" onclick="entry();">従業員情報新規登録</a></td>
		</tr>

		<tr>
			<td><a class="btn" href="logout.jsp">ログアウト</a><br /></td>
		</tr>
	</table>
	<form name="menu" action="./FrontControllerServlet" method="POST">
		<input type="hidden" name="screen_transition" value="employee_menu" />
		<input type="hidden" name="operation" value="" />
	</form>
</div>
<div id="footer">Webアプリケーション応用講座</div>
</div>
</body>
</html>