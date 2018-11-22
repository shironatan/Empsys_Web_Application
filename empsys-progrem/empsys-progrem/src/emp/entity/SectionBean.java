package emp.entity;

import java.io.Serializable;

public class SectionBean implements Serializable{



	private String section_code;
	private String section_name;
	private String update_date;


	/** デフォルトコンストラクタ */

	public SectionBean(){};



	/** コンストラクタ */

	public SectionBean(String section_code, String section_name, String update_date) {
		super();
		this.section_code = section_code;
		this.section_name = section_name;
		this.update_date = update_date;
	}

	/**
	 * 従業員コードをゲット
	 * @return section_code
	 */
	public String getSectionCode() {
		return section_code;
	}


	/**
	 * 従業員コードをセット
	 * @param section_code
	 */
	public void setSectionCode(String section_code) {
		this.section_code = section_code;
	}

	/**
	 * 従業員名をゲット
	 * @return section_name
	 */
	public String getSectionName() {
		return section_name;
	}



	/**
	 * 従業員名をセット
	 * @param section_name
	 */
	public void setSectionName(String section_name) {
		this.section_name = section_name;
	}

	/**
	 * 更新日をゲット
	 * @return update_Date
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



}