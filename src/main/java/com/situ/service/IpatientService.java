package com.situ.service;

import java.sql.SQLException;
import java.util.List;


import com.situ.entry.Patient;

public interface IpatientService {

	public List<Patient> findpatients(String ope, String data) throws SQLException;
	
	public List<Patient> selectpatients(String pnum, String psize) throws SQLException;

	public Integer countpatients() throws SQLException;

	public Integer insertpatient(Object... parems) throws SQLException;

	public Integer updatepatient(Patient patient) throws SQLException;

	public Integer delpatient(String id);
}
