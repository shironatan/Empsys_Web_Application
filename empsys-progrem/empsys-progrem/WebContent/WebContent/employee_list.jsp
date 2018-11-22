<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="emp.entity.EmployeeBean" %>
<%@ page import="emp.entity.SectionBean" %>
<%@ page import="emp.dao.EmployeeDAO" %>
<%@ page import="emp.dao.SectionDAO" %>
<%@ page import="util.AutoCast" %>
<%@ page import="java.util.*" %>
<%@ page errorPage="/error.jsp" %>
<%
	if(session.getAttribute("login_session") == null){
		String errorMessage = "ログインしてください。";
		session.setAttribute("errorMessage", errorMessage);
		throw new JspException();
	}
%>
<%
	List<EmployeeBean> empList = AutoCast.automaticCast(request.getAttribute("empListForDisplay"));
%>
<!DOCTYPE html>
<jsp:useBean id="sdao" scope="session" class="emp.dao.SectionDAO" />
<html>
<head>
	<!-- 従業員情報一覧を表示する -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="base.css">
	<title>従業員情報一覧</title>
	<script type="text/javascript">
	function delete_employee(){
		// 「OK」時の処理 ＋ 確認ダイアログの表示
		if(window.confirm('削除しますか？')){
			document.emp_list.elements['operation'].value = "delete";
			return true;
		}else{
			// 「キャンセル」時の処理
			window.alert('削除がキャンセルされました'); // 警告ダイアログを表示
			return false;
		}
	}

	function update_employee(){
		document.emp_list.elements['operation'].value = "update";
		document.getEmementsByName("operation").value = "update";
		return true;
	}
	</script>

</head>
<%String user_id =(String)session.getAttribute("user_id"); %>
<%!
	String first_name_kana = null;
	String last_name_kana = null;
	String gender = null;
	String birth_day = null;
	String section_code = null;
	String emp_date = null;
%>
<body>
	<div id="container">
		<div id="header">
			<h1>従業員情報管理システム</h1>
			<p> 従業員情報管理サブ&nbsp;</p>
		</div>
		<div id ="hello">
			ようこそ、<%=user_id %>さん
		</div>
		<div class ="select">
			<a class="btn" href="./employee_menu.jsp">従業員情報管理メニュー</a>
		</div>

		<div id ="content">
		<h2>従業員情報一覧</h2>
		<form name="emp_list" action="./FrontControllerServlet" method="POST">
			<input type="hidden" name="screen_transition" value="employee_list" />
			 <table id="table-01" summary="" border="1">
			 	<tr>
					<th class="b">
						<input id="submit" type="submit" value="削除" onClick="return delete_employee();" />
					</th>
					<th class="b">
						<input id="submit" type="submit" value="更新" onclick="return update_employee();" />
					</th>
					<th class="b">従業員番号</th>
					<th class="c">氏名</th>
					<th class="c">ふりがな</th>
					<th class="a">性別</th>
					<th class="b">生年月日</th>
					<th class="e">所属名</th>
					<th class="b">入社日</th>
				</tr>
				<%
				for(EmployeeBean bean : empList)
				{
				%>

				<tr>
					<td align="center"><input type="radio" name="delete" value="<%=bean.getEmpCode() %>" /></td>
					<td align="center"><input type="radio" name="update" value="<%=bean.getEmpCode() %>" /></td>
					<td><%=bean.getEmpCode() %></td>
					<td><%=bean.getLastName() %><%=bean.getFirstName() %></td>
					<td>
					<%if ((bean.getLastNameKana().length()+bean.getFirstNameKana().length()) > 10) { %>
						<%=bean.getLastNameKana() %><br><%=bean.getFirstNameKana() %>
					<%}
					else {%>
						<%=bean.getLastNameKana() %><%=bean.getFirstNameKana() %>
					<%} %>
					</td>
					<td>
					<% String gender = "";
					String bean_gender = bean.getGender();
					if(bean_gender.equals("0")){
						gender = "男";
					}else{
						gender = "女";
					}
					%>
					<%=gender %></td>
					<td><%=bean.getBirthday() %></td>
					<td><%=bean.getSectionName() %></td>
					<td><%=bean.getEmp_date() %></td>

				</tr>
				<%} %>
			 </table>
			 <input type="hidden" name="operation" value="" />
		</form>
		</div>
		<div id="footer">
			Webアプリケーション応用講座
		</div>
	</div>
</body>
</html>
