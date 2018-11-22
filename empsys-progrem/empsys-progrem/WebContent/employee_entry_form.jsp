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
<%
	String errorMessage = (String)session.getAttribute("errorMessge");
	String user_id = (String)session.getAttribute("user_id");
%>
<body>
	<div id="container">
		<div id="header">
			<h1>従業員情報管理システム</h1>
			<p>従業員登録</p>
			<p>&nbsp;</p>
		</div>

		<div id ="hello">
			ようこそ、<%=user_id %>さん
		</div>

		<div id="content">
			<form name="calendar"  action="./FrontControllerServlet" method="POST">
				<input type="hidden" name="screen_transition" value="employee_entry_form" />
				<!-- ----------------------------------------------------------------------------- -->
				<table id="table-form">
					<tr>
						<!-- 「従業員番号」入力欄  -->
						<td align="center">従業員番号</td>
						<td align="left">：<input type="text" name="emp_code"
							maxlength="4" size="1"></td>
					</tr>
					<tr>
						<!-- 「氏」入力欄  -->
						<td align="center">氏</td>
						<td align="left">：<input type="text" name="last_name"
							maxlength="5" size="5"></td>
					</tr>
					<tr>
						<!-- 「名」入力欄  -->
						<td align="center">名</td>
						<td align="left">：<input type="text" name="first_name"
							maxlength="5" size="5"></td>
					</tr>
					<tr>
						<!-- 「氏（かな）」入力欄  -->
						<td align="center">氏(ふりがな)</td>
						<td align="left">：<input type="text" name="last_name_kana"
							maxlength="10" size="17"></td>
					</tr>
					<tr>
						<!-- 「名（かな）」入力欄  -->
						<td align="center">名(ふりがな)</td>
						<td align="left">：<input type="text" name="first_name_kana"
							maxlength="10" size="17"></td>
					</tr>
					<tr>
						<!-- 「性別」入力欄  -->
						<td align="center">性別</td>
						<td align="left">：<input type="radio" name="gender" value="0">男<br>：<input
							type="radio" name="gender" value="1">女
						</td>
					</tr>
					<tr>
						<!-- 「所属部署」入力欄  -->
						<td align="center">所属部署</td>
						<td align="left">：<select name="section_code">
							<%
							for(SectionBean bean : sectionList){
								String section_code = bean.getSectionCode();
								String section_name = bean.getSectionName();
							%>
							<option value="<%= section_code%>">
								<%= section_name%></option>
							<%} %>
						</select>
						</td>
					</tr>
					<tr>
						<!-- 「生年月日」入力欄  -->
						<td align="center">生年月日</td>
						<td align="left">： <input title="ダブルクリックでカレンダーを表示"
							name="birthday" id="birth_day" maxlength="10" size="7"
							ondblClick="wrtCalendar(event,this.form.birth_day,'yyyy-mm-dd')">
						</td>
					</tr>
					<tr>
						<!-- 入社日  -->
						<td align="center">入社日</td>
						<td align="left">： <input title="ダブルクリックでカレンダーを表示"
							name="emp_date" id="emp_date" maxlength="10" size="7"
							ondblClick="wrtCalendar(event,this.form.emp_date,'yyyy-mm-dd')" />
						</td>
					</tr>
					<!-- 「登録」ボタン  -->
					<tr align="center" height="60px">
						<td colspan="2"><input type="submit" id="button" value="登録" /></td>
					</tr>
				</table>
				<p align="right">
					<a href="./employee_menu.jsp">メニュー画面に戻る</a><br>
				</p>
			</form>
			<div id="footer">Webアプリケーション応用講座</div>
		</div>
	</div>
</body>
</html>