/*
 * Webアプリケーション応用講座 UserDAOt.java
 *
 * Copyright(C) metro Inc. All Rights Reserved, 2018
 */
package emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import emp.entity.UserBean;

/**
 * ユーザ情報テーブルにアクセスするDAOクラス
 */
public class UserDAO {

	//コネクション情報を格納する
	private Connection con = null;

	/**
	 * ユーザIDをキーにユーザ情報を取得する
	 * @param user_id
	 * @return UserBean。取得できない場合は null を返却。
	 */
	public UserBean select(String user_id) {
		UserBean bean = null;

		// user_idをキーにDBの情報を取得
		ConnectionManager cm = new ConnectionManager();

		try {
			con = cm.getConnection();

			// SELECT文の準備
			String sql = "SELECT * FROM emp_sys_db.m_user where user_id = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);

			int i = 1;
			pStmt.setString(i++, user_id);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			if(rs.next()) { // 万が一複数件取得できても、最初の一件を返却する
				bean = new UserBean();
				bean.setUserId(rs.getString("user_id"));
				bean.setPassword(rs.getString("password"));
				bean.setUpdateDate(rs.getString("update_date"));
			}
			
		}catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return bean;

	}

	/**
	 * DBからuser_idとpasswordが一致してたらtrueを返すログインメソッド
	 * @param user_id, password
	 * @return true or false : boolean
	 * @throws SQLException
	 */
	public boolean login(String user_id, String password) throws SQLException {

		UserBean bean = new UserBean();

		// user_idをキーにDBの情報を取得
		ConnectionManager cm = new ConnectionManager();

		try {
			con = cm.getConnection();

			// SELECT文の準備
			String sql = "SELECT * FROM emp_sys_db.m_user where user_id = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setString(1, user_id);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			if(rs.next()) { // 万が一複数件取得できても、最初の一件を返却する
				bean.setUserId(rs.getString("user_id"));
				bean.setPassword(rs.getString("password"));
				
			}else {
				return false;
			}

			if (bean.getUserId().equals(user_id) && bean.getPassword().equals(password)) {
				return true;
			}
			return false;
		}
		catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		}
	}
}
