/*
 * Webアプリケーション応用講座 UserBean.java
 *
 * Copyright(C) metro Inc. All Rights Reserved, 2018
 */
package emp.entity;

import java.io.Serializable;

/**
 * ユーザ情報を保持するJavaBeansクラス
 */
public class UserBean implements Serializable{

	private String user_id;
	private String password;
	private String update_date;


	/** デフォルトコンストラクタ */
	public UserBean(){};

	/** コンストラクタ */
	public UserBean(String user_id, String password, String update_date) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.update_date = update_date;
	}

	/** Getter and Setter */
	public String getUserId() {
		return user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUpdateDate() {
		return update_date;
	}

	public void setUpdateDate(String update_date) {
		this.update_date = update_date;
	}

}


