package com.situ.dao;

import java.sql.SQLException;
import java.util.List;

import com.situ.entry.Doctor;
import com.situ.util.DataSouseutil;

public class DocterDao {
/**
 * 
 * @param pnum
 * @param psize
 * @return
 * @throws SQLException
 */
	public List<Doctor> selectdocs(String pnum,String psize) throws SQLException {
		String sql="select doctor.*,department.`name`AS dept from doctor LEFT JOIN department ON dept_id=department.id limit "+pnum+","+psize;
		return DataSouseutil.executeQuery(sql,Doctor.class);
		
	}
	
	public List<Doctor> selectdocs(String deptid) throws SQLException {
		String sql="select * from doctor where dept_id=? ";
		return DataSouseutil.executeQuery(sql,Doctor.class,deptid);
		
	}
	/**
	 * 计算总数
	 * @return
	 * @throws SQLException
	 */
	public Integer countDocs() throws SQLException {
		String sql="select count(*) from doctor";
		return DataSouseutil.executeQuery(sql);
		
	}
	/**
	 * 
	 * @param parems
	 * @return
	 * @throws SQLException
	 */
	public Integer insertdocs(Object...parems) throws SQLException {
		String sqlString="insert into doctor(name,cardno,title,age,gender,dept_id) values(?,?,?,?,?,?)";
		return DataSouseutil.executeUpdate(sqlString, parems);
		
	}
	/**
	 * 
	 * @param patient
	 * @return
	 * @throws SQLException
	 */
	public Integer updatedept(Doctor doc) throws SQLException {
		String sql="update doctor set name=?,cardno=?,title=?,age=?,gender=?,dept_id=?  where id=?";
		return DataSouseutil.executeUpdate(sql,doc.getName(),doc.getCardno(),doc.getTitle(),doc.getAge(),doc.getGender(),doc.getDept_id()
				,doc.getId());
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer deldept(String id) throws SQLException {
		String sql="DELETE FROM doctor WHERE id=?";
		return DataSouseutil.executeUpdate(sql, id);
		
	}
	public List<Doctor> findDocs(String ope,String data) throws SQLException {
		String sql="select doctor.*,department.`name`AS dept from doctor LEFT JOIN department ON dept_id=department.id where doctor."+ope+"=?";
		return DataSouseutil.executeQuery(sql,Doctor.class,data);
		
	}
}
