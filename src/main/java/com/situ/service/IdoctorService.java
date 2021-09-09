package com.situ.service;

import java.sql.SQLException;
import java.util.List;

import com.situ.entry.Doctor;



public interface IdoctorService {
public List<Doctor> selectDocs(String pnum,String psize) throws SQLException;
public List<Doctor> serchDocs(String ope,String data) throws SQLException;
	public Integer countDocs() throws SQLException;
	
	public Integer insertdoc(Doctor doctor) throws SQLException;
	
	public Integer updatedoc(Doctor doctor) throws SQLException;
	
	public Integer deldoc(String id);
}
