package emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import emp.entity.EmployeeBean;

//commit
public class EmployeeDAO {

	private Connection con = null;

	/**
	 * 従業員リストを返す
	 * @return List<EmployeeBean>
	 */
	public List<EmployeeBean> list(){
		List<EmployeeBean> empList = new ArrayList<EmployeeBean>();
		ConnectionManager cm = new ConnectionManager();
		try {

			con = cm.getConnection();

			//select文
			String sql = "SELECT * FROM emp_sys_db.m_employee";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				EmployeeBean bean = new EmployeeBean();


				//従業員コード
				String code = rs.getString("emp_code");
				bean.setEmpCode(code);

				//従業員名
				String first_name = rs.getString("first_name");
				bean.setFirstName(first_name);

				//従業員名字
				String last_name = rs.getString("last_name");
				bean.setLastName(last_name);

				//従業員名カナ
				String first_name_kana = rs.getString("first_name_kana");
				bean.setFirstNameKana(first_name_kana);

				//従業員名字カナ
				String Last_name_kana = rs.getString("last_name_kana");
				bean.setLastNameKana(Last_name_kana);

				//性別
				String gender = rs.getString("gender");
				bean.setGender(gender);

				//誕生日
				String birthday = rs.getString("birthday");
				bean.setBirthday(birthday);


				String section = rs.getString("section_code");
				bean.setSectionCode(section);

				//入社日
				String emp_date = rs.getString("emp_date");
				bean.setEmp_date(emp_date);

				empList.add(bean);



			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLエラー");
		}finally {
			//DB切断
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println("DataBase切断エラー");
				}
			}
		}
		return empList;
	}


	/**
	 * 従業員コードをキーに削除する
	 * @param employee_code
	 * @return  boolean true:成功 false:未成功
	 */
	public boolean delete(String employee_code) {
		//Connect
		ConnectionManager cm = new ConnectionManager();
		try {

			con = cm.getConnection();

			//DELETE文
			String sql = "DELETE FROM emp_sys_db.m_employee WHERE emp_code = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//「？」に使用する値を設定しSQL文を完成
			int i = 1;
			pStmt.setString(i++, employee_code);

			//DELETE文を実行
			pStmt.executeUpdate();
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLエラー");
			return false;
		}finally {
			//DB切断
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println("DataBase切断エラー");
				}
			}
		}
	}

	/**
	 * 従業員コードをキーに重複チェック
	 * @param employee_code
	 * @return  boolean true:成功 false:未成功
	 */
	public EmployeeBean select(String employee_code) {
		//Connect
		ConnectionManager cm = new ConnectionManager();
		EmployeeBean bean = new EmployeeBean();
		try {
			con = cm.getConnection();

			//SELECT文
			String sql = "SELECT * FROM emp_sys_db.m_employee WHERE emp_code = ?;";
			PreparedStatement pStmt = con.prepareStatement(sql);
			int i = 1;
			pStmt.setString(i++, employee_code);

			//ResultSet
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				//従業員コードをセット
				String code = rs.getString("emp_code");
				bean.setEmpCode(code);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLエラー");
		}finally {
			//DB切断
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println("DataBase切断エラー");
				}
			}
		}
		return bean;
	}

	/**
	 * EmployeeBeanを引数に従業員を登録する
	 * @param bean
	 * @return  boolean true:成功 false:未成功
	 */
	public boolean entry(EmployeeBean bean) {
		//Connect
		ConnectionManager cm = new ConnectionManager();
		PreparedStatement pStmt = null;
		try {
			con = cm.getConnection();

			//INSERT文
			String sql = "INSERT INTO emp_sys_db.m_employee(emp_code,first_name,last_name,"
					+ "first_name_kana,last_name_kana,gender,birthday,section_code,emp_date,update_date)"
					+ " VALUES(?,?,?,?,?,?,"
					+ "?,?,?,?);";
			pStmt = con.prepareStatement(sql);

			//「？」に使用する値を設定しSQL文を完成
			int i = 1;
			pStmt.setString(i++, bean.getEmpCode());
			pStmt.setString(i++, bean.getFirstName());
			pStmt.setString(i++, bean.getLastName());
			pStmt.setString(i++, bean.getFirstNameKana());
			pStmt.setString(i++, bean.getLastNameKana());
			pStmt.setString(i++, bean.getGender());
			pStmt.setString(i++, bean.getBirthday());
			pStmt.setString(i++, bean.getSectionCode());
			pStmt.setString(i++, bean.getEmp_date());
			pStmt.setString(i++, bean.getUpdateDate());


			//INSERT文を実行
			pStmt.executeUpdate();

			return true;

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLエラー" + pStmt);
			return false;
		}finally {
			//DB切断
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println("DataBase切断エラー");
				}
			}
		}
	}

	/**
	 * EmployeeBeanを引数に従業員情報を更新する
	 * @param bean
	 * @return boolean true:成功 false:未成功
	 */
	public boolean update(EmployeeBean bean) {//Connect
		ConnectionManager cm = new ConnectionManager();
		PreparedStatement pStmt = null;
		try {
			con = cm.getConnection();

			//INSERT文
			String sql = "UPDATE emp_sys_db.m_employee"
					+ " set first_name = ?,last_name = ?,"
					+ "first_name_kana = ?,last_name_kana = ?,"
					+ "gender = ?,section_code = ?,update_date = ? "
					+ "where emp_code = ?";
			pStmt = con.prepareStatement(sql);

			//「？」に使用する値を設定しSQL文を完成
			int i = 1;
			pStmt.setString(i++, bean.getFirstName());
			pStmt.setString(i++, bean.getLastName());
			pStmt.setString(i++, bean.getFirstNameKana());
			pStmt.setString(i++, bean.getLastNameKana());
			pStmt.setString(i++, bean.getGender());
			pStmt.setString(i++, bean.getSectionCode());
			pStmt.setString(i++, bean.getUpdateDate());
			pStmt.setString(i++, bean.getEmpCode());

			//UPDATE文を実行
			pStmt.executeUpdate();


			return true;

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLエラー" + pStmt);
			return false;
		}finally {
			//DB切断
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println("DataBase切断エラー");
				}
			}
		}
	}
}

