package com.situ.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.situ.dao.PatientDao;
import com.situ.entry.Patient;
import com.situ.service.IpatientService;

public class PatientServiceImpl implements IpatientService {
PatientDao dao=new PatientDao();
	@Override
	public List<Patient> selectpatients(String pnum, String psize) throws SQLException {
		// TODO Auto-generated method stub
		Integer c=(Integer.parseInt(pnum)-1)*Integer.parseInt(psize);
		return dao.selectPatients(c.toString(), psize);
	}

	@Override
	public Integer countpatients() throws SQLException {
		// TODO Auto-generated method stub
		return dao.countPatients();
	}

	@Override
	public Integer insertpatient(Object... parems) throws SQLException {
		// TODO Auto-generated method stub
		return dao.insertPatient(parems);
	}

	@Override
	public Integer updatepatient(Patient patient) throws SQLException {
		// TODO Auto-generated method stub
		return dao.updatePatient(patient);
	}

	@Override
	public Integer delpatient(String id) {
		// TODO Auto-generated method stub
		try {
			return dao.delPatient(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Patient> findpatients(String ope, String data) throws SQLException {
		// TODO Auto-generated method stub
		return dao.findPatients(ope, data);
	}

}
