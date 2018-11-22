/*
 * Webアプリケーション応用講座 FrontControllerServlet.java
 *
 * Copyright(C) metro Inc. All Rights Reserved, 2018
 */
package emp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.servlet.helper.EmployeeController;
import emp.servlet.helper.UserController;

/**
 * Servlet implementation class FrontControllerServlet
 * 処理の振り分けを行うコントロールクラス
 */
public class FrontControllerServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 * デフォルトコンストラクタ
	 * */
	public FrontControllerServlet(){
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGetは今回は使用しない
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング指定
		request.setCharacterEncoding("utf-8");

		//画面遷移する場所を入れる (デフォルト : エラー画面)
		String rdstr = "/error.jsp";

		/* form から渡されたものを代入 (value値はファイル名と同じ) */
		String screen_transition = request.getParameter("screen_transition");

		if (screen_transition.equals("login_form")) {	// index.htmlから
			UserController uc = new UserController();
			/** ログイン処理 */
			if(uc.login(request,response)){
				/** 従業員一覧管理メニュー表示 */
				rdstr = "/employee_menu.jsp";
			}

		}
		else if (screen_transition.equals("employee_menu")) { 	// employee_manu.jspから
			String operation = request.getParameter("operation");	// 操作種別の取得
			if (operation != null) {
				if (operation.equals("list")) {	// 一覧表示
					EmployeeController ec = new EmployeeController();
					/** 従業員一覧の取得 */
					if (ec.list(request, response)) {
						rdstr = "/employee_list.jsp";
					}

				}else if (operation.equals("entry")) {	// 登録

					EmployeeController ec = new EmployeeController();
					/** 所属マップの取得 */
					if (ec.sectionMap(request, response)) {
						/** 登録画面表示 */
						rdstr = "/employee_entry_form.jsp";
					}
				}
			}

		}else if (screen_transition.equals("employee_list")) {	// employee_list.jspから

			String operation = request.getParameter("operation");	// 操作種別の取得
			if (operation != null) {
				if (operation.equals("update")) {	// 更新
					EmployeeController ec = new EmployeeController();
					/** 従事者情報の取得 */
					if (ec.sectionMap(request, response)) {
						/** 更新画面表示 */
						rdstr = "/employee_update_form.jsp";
						System.out.println("画面遷移①");
					}

				}else if (operation.equals("delete")) {	// 削除
					/** 削除処理 */
					EmployeeController ec = new EmployeeController();

					if(ec.delete(request, response)){
						/** 削除結果画面表示 */
						rdstr = "/employee_delete.jsp";
					}
				}
			}

		}else if (screen_transition.equals("employee_entry_form")) {	// employee_entry_form.jspから
			EmployeeController ec = new EmployeeController();
			/** 登録処理 */
			if(ec.entry(request,response)){
				/** 登録結果画面表示 */
				rdstr = "/employee_entry.jsp";
			}

		}else if (screen_transition.equals("employee_update_form")) { 	// employee_update_form.jspから
			EmployeeController ec = new EmployeeController();
			/** 更新処理 */
			if (ec.update(request, response)) {
				/** 更新結果画面 */
				rdstr = "/employee_update.jsp";
			}

		}else if (screen_transition.equals("employee_update")) {	// employee_update.jspから
			EmployeeController ec = new EmployeeController();
			/** 従業員一覧の取得 */
			if (ec.list(request, response)) {
				rdstr = "/employee_list.jsp";
			}

		}else if (screen_transition.equals("employee_delete")) {	// employee_delete.jspから
			EmployeeController ec = new EmployeeController();
			/** 従業員一覧の取得 */
			if (ec.list(request, response)) {
				rdstr = "/employee_list.jsp";
			}
		}

		//画面遷移する
		request.getRequestDispatcher(rdstr).forward(request, response);
	}
}
