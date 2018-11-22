package emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import emp.entity.SectionBean;


//commit

public class SectionDAO {

	private Connection con = null;

	/**
	 * 所属マップを返す
	 * @return Map<String,SectionBean> keyはsection_code
	 */
	public Map<String,SectionBean> list(){
		Map<String,SectionBean> sectionMap = new TreeMap<String,SectionBean>();
		ConnectionManager cm = new ConnectionManager();

		try {
			con = cm.getConnection();

			//String sql = "SELECT * FROM emp_sys_db.m_seciton";
			String sql = "SELECT * FROM emp_sys_db.m_section";
			PreparedStatement pStmt = con.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				SectionBean bean = new SectionBean();
				String section_code = rs.getString("section_code");
				bean.setSectionCode(section_code);

				String section_name = rs.getString("section_name");
				bean.setSectionName(section_name);

				sectionMap.put(section_code, bean);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sectionMap;
	}
}
