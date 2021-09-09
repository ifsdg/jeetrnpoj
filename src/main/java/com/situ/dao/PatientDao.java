package com.situ.dao;

import java.sql.SQLException;
import java.util.List;

import com.situ.entry.Patient;
import com.situ.util.DataSouseutil;
/**
 * 
 * @author Mrkua
 *
 */
public class PatientDao {

	public List<Patient> selectallPatients() throws SQLException {
		String sql="select * from patient";
		return DataSouseutil.executeQuery(sql, Patient.class);
		
	}
	/**
	 * 
	 * @param pnum
	 * @param psize
	 * @return
	 * @throws SQLException
	 */
	public List<Patient> selectPatients(String pnum,String psize) throws SQLException {
		String sql="select patient.*,doctor.`name` AS doctor ,department.`name` AS dep  "
				+ "from patient LEFT JOIN doctor ON doctor.id=patient.doctor_id LEFT JOIN department ON "
				+ "department.id=doctor.dept_id "
				+ "limit "+pnum+","+psize;
		return DataSouseutil.executeQuery(sql, Patient.class);
		
	}
	
	public List<Patient> findPatients(String ope,String data) throws SQLException {
		String sql="select patient.*,doctor.`name` AS doctor ,department.`name` AS dep  "
				+ "from patient LEFT JOIN doctor ON doctor.id=patient.doctor_id LEFT JOIN department ON department.id=doctor.dept_id "
				+" where instr(patient."+ope+",'" + data + "')>0";
		return DataSouseutil.executeQuery(sql, Patient.class);
		
	}
	/**
	 * 计算总数
	 * @return
	 * @throws SQLException
	 */
	public Integer countPatients() throws SQLException {
		String sql="select count(*) from patient";
		return DataSouseutil.executeQuery(sql);
		
	}
	/**
	 * 
	 * @param parems
	 * @return
	 * @throws SQLException
	 */
	public Integer insertPatient(Object...parems) throws SQLException {
		String sqlString="insert into patient(name,gender,age,inpatientno,doctor_id,ward_id)  values(?,?,?,?,?,?)";
		return DataSouseutil.executeUpdate(sqlString, parems);
		
	}
	/**
	 * 
	 * @param patient
	 * @return
	 * @throws SQLException
	 */
	public Integer updatePatient(Patient patient) throws SQLException {
		String sql="update patient set name=?,age=?,inpatientno=?,doctor_id=?,ward_id=?  where id=?";
		return DataSouseutil.executeUpdate(sql,patient.getName(),patient.getAge(),patient.getInpatientno(),patient.getDoctor_id(),patient.getWard_id()
				,patient.getId());
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer delPatient(String id) throws SQLException {
		String sql="DELETE FROM patient WHERE id=?";
		return DataSouseutil.executeUpdate(sql, id);
		
	}
}
