package com.situ.service;

import java.sql.SQLException;
import java.util.List;

import com.situ.entry.Dept;

public interface IdpetService {
	
	public List<Dept> selectDepts() throws SQLException;
	
	public List<Dept> selectDepts(String pnum,String psize) throws SQLException;
	
	public Integer countDepts() throws SQLException;
	
	public Integer insertdept(Object...parems) throws SQLException;
	
	public Integer updatedept(Dept dept) throws SQLException;
	
	public Integer deldept(String id);
}
