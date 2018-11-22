package emp.servlet.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.dao.SectionDAO;
import emp.entity.EmployeeBean;
import emp.entity.EmployeeDAO;
import emp.entity.SectionBean;

public class EmployeeControler {
	public boolean list(HttpServletRequest request, HttpServletResponse response){

		//所属マップを取得
		SectionDAO sdao = new SectionDAO();
		Map<String,SectionBean> sectionMap = sdao.list();

		//従業員一覧を取得
		EmployeeDAO edao = new EmployeeDAO();
		List<EmployeeBean> empList = edao.list();

		//表示用の従業員一覧を準備しておく
		List<EmployeeBean> empListforDisplay = new ArrayList<EmployeeBean>();

		//表示用に加工する
		for(EmployeeBean bean : empList) {
			EmployeeBean beanForDisplay = new EmployeeBean();

			String section_code = bean.getSectionCode();

			SectionBean secBean = sectionMap.get(section_code);

			//所属名
			String section_name = secBean.getSectionName();
			beanForDisplay.setSectionName(section_name);

			//従業員コード
			beanForDisplay.setEmpCode(bean.getEmpCode());

			//氏名（名）
			beanForDisplay.setFirstName(bean.getFirstName());

			//氏名（氏）
			beanForDisplay.setLastName(bean.getLastName());

			//ふりがな（名）
			beanForDisplay.setFirstNameKana(bean.getFirstNameKana());

			//ふりがな(氏)
			beanForDisplay.setLastNameKana(bean.getLastNameKana());

			//性別
			beanForDisplay.setGender(bean.getGender());

			//誕生日
			beanForDisplay.setBirthday(bean.getBirthday());

			//所属コード
			beanForDisplay.setSectionCode(bean.getSectionCode());


			//入社日
			beanForDisplay.setEmp_date(bean.getEmp_date());

			empListforDisplay.add(beanForDisplay);


		}

		//リクエストスコープに保存
		//request.setAttribute(jspで参照するときの名前, 加工済みの従業員一覧);
		request.setAttribute("empListForDisplay",empListforDisplay);
		return true;
	}

	/*
	 * DELETE
	 */


	public boolean delete(HttpServletRequest request, HttpServletResponse response) {
		//リクエストから従業員コードを取得
		String emp_code = request.getParameter("delete");

		//DELETE文実行
		EmployeeDAO edao = new EmployeeDAO();
		boolean delete_flag = edao.delete(emp_code);

		if(delete_flag) {
			return true;
		}else {
			return false;
		}
	}
	/*
	 * 所属テーブルを拾っていく
	 */
	public boolean sectionMap(HttpServletRequest request, HttpServletResponse response) {
		SectionDAO sdao = new SectionDAO();
		Map<String,SectionBean> sectionMap = sdao.list();

		//Listを宣言し、Valuesメソッドを使用して値を取得する
		List<SectionBean> list = new ArrayList<SectionBean>(sectionMap.values());

		//request.setAttribute(jspで参照するときの名前, 加工済みの従業員コード);
		request.setAttribute("sectionListForDisplay",list);


		return true;

	}

	public boolean entry(HttpServletRequest request, HttpServletResponse response) {
		//リクエストから入力情報を取得 && 入力チェック
		EmployeeBean bean = new EmployeeBean();

		//従業員コード
		String emp_code = request.getParameter("emp_code");
		if(emp_code.length() != 4) {
			return false;
		}
		bean.setEmpCode(emp_code);

		//ファーストネーム
		String first_name = request.getParameter("first_name");
		if(first_name.length() > 16 || first_name.length() == 0) {
			return false;
		}
		bean.setFirstName(first_name);

		//ラストネーム
		String last_name = request.getParameter("last_name");
		if(last_name.length() > 16 || last_name.length() == 0) {
			return false;
		}
		bean.setLastName(last_name);

		//ファーストネームカナ
		String first_name_kana = request.getParameter("first_name_kana");
		if(first_name_kana.length() > 24 || first_name_kana.length() == 0) {
			return false;
		}
		bean.setFirstNameKana(first_name_kana);

		//ラストネームカナ
		String last_name_kana = request.getParameter("last_name_kana");
		if(last_name_kana.length() > 24 || last_name_kana.length() == 0) {
			return false;
		}
		bean.setLastNameKana(last_name_kana);

		//性別
		String gender = request.getParameter("gender");
		if(gender == null) {
			return false;
		}

		//誕生日
		String birthday = request.getParameter("birthday");
		if(birthday.length() > 8 || birthday.length() == 0) {
			return false;
		}
		bean.setBirthday(birthday);

		//所属コード
		String section_code = request.getParameter("section_code");
		if(section_code == null) {
			return false;
		}
		bean.setSectionCode(section_code);

		//入社日
		String emp_date = request.getParameter("emp_date");
		if(emp_date.length() == 8 || emp_date.length() == 0) {
			return false;
		}
		bean.setEmp_date(emp_date);

		//更新日
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String update_date = sdf1.format(date);
		bean.setUpdateDate(update_date);

		//SELECT文を発行 あるかないかチェック
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeBean check = dao.select(bean.getEmpCode());


		//従業員コードがあるか否か判定
		boolean insert_flag = false;
		if(check.getEmpCode() != null) {
			//従業員コードがなかった場合INSERT文を実行
			insert_flag = dao.entry(bean);
		}
		if(insert_flag) {
			return true;
		}else {
			return false;
		}

	}


}


