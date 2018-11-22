<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="emp.entity.SectionBean"%>
<%@ page import="util.AutoCast" %>
<%@ page import="java.util.*"%>
<%@ page errorPage="/error.jsp"%>
<%
	if(session.getAttribute("login_session") == null){
		String errorMessage = "ログインしてください。";
		session.setAttribute("errorMessage", errorMessage);
		throw new JspException();
	}
String emp_code = request.getParameter("update");
System.out.println(emp_code);
%>
<%
List<SectionBean> sectionList = AutoCast.automaticCast(request.getAttribute("sectionListForDisplay"));
%>
<!DOCTYPE html>
<jsp:useBean id="sdao" scope="session" class="emp.dao.SectionDAO" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="base.css">
	<title>従業員情報新規登録</title>
	<script src="calendar.js"></script>
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
		<div id="content">

			<form name="calendar"  action="./FrontControllerServlet" method="POST">
				<input type="hidden" name="emp_code" value="<%=emp_code %>"/>
				<input type="hidden" name="screen_transition" value="employee_update_form" />
				<!-- ----------------------------------------------------------------------------- -->
				<table id="table-form">
					<tr>
						<td align="center">氏</td>
						<td align="left">：<input type="text" name="last_name"></td>
					</tr>
					<tr>
						<td align="center">名</td>
						<td align="left">：<input type="text" name="first_name"></td>
					</tr>
					<tr>
						<td align="center">氏(ふりがな)</td>
						<td align="left">：<input type="text" name="last_name_kana"></td>
					</tr>
					<tr>
						<td align="center">名(ふりがな)</td>
						<td align="left">：<input type="text" name="first_name_kana"></td>
					</tr>
					<tr>
						<td align="center">性別</td>
						<td align="left">：<input type="radio" name="gender" value="0">男<br>：<input type="radio" name="gender" value="1">女</td>
					</tr>
					<tr>
						<td align="center">所属コード</td>
						<td align="left">：<select name="section_code">
						<%
						for(SectionBean bean : sectionList){
							String section_code = bean.getSectionCode();
							String section_name = bean.getSectionName();
						%>
						<option value="<%= section_code%>">
						<%= section_name%></option>
						<%} %>
						</select></td>
					</tr>

					<!-- 「更新」ボタン  -->
					<tr align="center" height="60px">
					<td colspan="2"><input type="submit" id="button" value="更新" /></td>
					</tr>
				</table>
				<p align="right"><a href="./employee_menu.jsp">メニュー画面に戻る</a><br></p>
			</form>
			<div id="footer">Webアプリケーション応用講座</div>
		</div>
	</div>
</body>
</html>