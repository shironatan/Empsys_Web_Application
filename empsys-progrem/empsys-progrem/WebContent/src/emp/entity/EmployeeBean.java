package emp.entity;

import java.io.Serializable;

public class EmployeeBean implements Serializable{

	private String emp_code;
	private String first_name;
	private String first_name_kana;
	private String last_name;
	private String last_name_kana;
	private String gender;
	private String birthday;
	private String section_code;
	private String section_name;
	private String update_date;
	private String emp_date;

	/** デフォルトコンストラクタ */
	public EmployeeBean(){};

	/** コンストラクタ */
	public EmployeeBean(String emp_code, String first_name, String first_name_kana,
			String last_name, String last_name_kana, String gender, String birthday,
			String section_code, String section_name, String update_date,String emp_date) {

		super();
		this.emp_code = emp_code;
		this.first_name = first_name;
		this.first_name_kana = first_name_kana;
		this.last_name = last_name;
		this.last_name_kana = last_name_kana;
		this.gender = gender;
		this.birthday = birthday;
		this.section_code = section_code;
		this.section_name = section_name;
		this.update_date = update_date;
		this.emp_date = emp_date;
	}

	/**
	 * 従業員コードをゲット
	 * @return emp_code
	 */
	public String getEmpCode() {
		return emp_code;
	}

	/**
	 * 従業員コードをセット
	 * @param emp_code
	 */
	public void setEmpCode(String emp_code) {
		this.emp_code = emp_code;
	}


	/**
	 * 名前をゲット
	 * @return first_name
	 */
	public String getFirstName() {
		return first_name;
	}

	/**
	 * 名前をセット
	 * @param first_name
	 */
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * 名前カナをゲット
	 * @return first_name_kana
	 */
	public String getFirstNameKana() {
		return first_name_kana;
	}

	/**
	 * 名前カナをセット
	 * @param first_name_kana
	 */
	public void setFirstNameKana(String first_name_kana) {
		this.first_name_kana = first_name_kana;
	}

	/**
	 * 名字をゲット
	 * @return last_name
	 */
	public String getLastName() {
		return last_name;
	}

	/**
	 * 名字をセット
	 * @param last_name
	 */
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * 名字カナをゲット
	 * @return last_name_kana
	 */
	public String getLastNameKana() {
		return last_name_kana;
	}

	/**
	 * 名字カナをセット
	 * @param last_name_kana
	 */
	public void setLastNameKana(String last_name_kana) {
		this.last_name_kana = last_name_kana;
	}

	/**
	 * 性別をゲット
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * 性別をセット
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 誕生日をゲット
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 誕生日をセット
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * 所属コードをゲット
	 * @return section_code
	 */
	public String getSectionCode() {
		return section_code;
	}

	/**
	 * 所属コードをセット
	 * @param section_code
	 */
	public void setSectionCode(String section_code) {
		this.section_code = section_code;
	}

	/**
	 * 所属名をゲット
	 * @return setion_name
	 */
	public String getSectionName() {
		return section_name;
	}

	/**
	 * 所属名をセット
	 * @param section_name
	 */
	public void setSectionName(String section_name) {
		this.section_name = section_name;
	}

	/**
	 * 更新日をゲット
	 * @return update_date
	 */
	public String getUpdateDate() {
		return update_date;
	}

	/**
	 * 更新日をセット
	 * @param update_date
	 */
	public void setUpdateDate(String update_date) {
		this.update_date = update_date;
	}

	/**
	 * 入社日をゲット
	 * @return emp_Date
	 */
	public String getEmp_date() {
		return emp_date;
	}

	/**
	 * 入社日をセット
	 * @param emp_Date
	 */
	public void setEmp_date(String emp_Date) {
		this.emp_date = emp_Date;
	}





}