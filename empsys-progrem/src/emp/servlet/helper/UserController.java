/*
 * Webアプリケーション応用講座 EmployeeEntryController.java
 *
 * Copyright(C) metro Inc. All Rights Reserved, 2018
 */
package emp.servlet.helper;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.dao.UserDAO;


/**
 * ユーザに関する処理を行うコントロールクラス
 */
public class UserController {

	/**
	 * ログイン処理を行う。
	 * ログイン成功時、セッションスコープにユーザIDを格納する。
	 * ログイン失敗時、セッションスコープからユーザIDを削除する。
	 * @param request
	 * @param response
	 * @return true:ログイン成功、false:ログイン失敗
	 * @throws IOException
	 */
	public boolean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//エラー画面に表示するメッセージ変数
		String errorMessage = "";

		//セッション情報
		HttpSession session = request.getSession();

		//ログインしたかどうかを保持する変数
		boolean login_session = false;

		//ログイン入力情報をパラメータから変数に保持
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		if (user_id == null ||user_id.length() == 0 ||
			password == null || password.length() == 0 ) {
			errorMessage = "ユーザIDまたはパスワードが未入力です。";

		/*
		}
		else if (user_id.length() <= 6) {
			errorMessage = "ユーザIDの入力桁数が違います。";

		*/
		}
		else {
			try{
				UserDAO udao = new UserDAO();

				//UserDAOに情報が一致したら true , その他 falseを返す変数
				boolean isLogin = udao.login(user_id, password);
				if (isLogin) {
					//ログイン成功時
					login_session = true;

					//ユーザIDをセッションに持たせる
					session.setAttribute("user_id", user_id);
				}
				else {
					errorMessage = "認証に失敗しました。ユーザID、パスワードを確認してください。";
				}

			}
			catch (SQLException e) {
				//SQLエラーは拾わない
				errorMessage = "エラーが発生しました。";
				e.printStackTrace();
			}
		}

		//セッションでuser_idを保持していた場合、書き換える（エラー画面でnull）
		if (!login_session) {
			user_id = null;
			session.setAttribute("user_id", user_id);
		}

		//セッションにlogin_session変数を保持する
		session.setAttribute("login_session",login_session);

		//セッションにerrorMessage変数を保持する
		session.setAttribute("errorMessage", errorMessage);

		return login_session;
	}
}
