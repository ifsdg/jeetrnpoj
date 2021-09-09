package com.situ.service;

import java.sql.SQLException;
import java.util.List;

import com.situ.entry.Ward;



public interface IwardService {
public List<Ward> selectwards(String pnum,String psize) throws SQLException;
public List<Ward> selectwards(String id) throws SQLException;
	public Integer countwards() throws SQLException;
	
	public Integer insertward(Object...parems) throws SQLException;
	
	public Integer updateward(Ward ward) throws SQLException;
	
	public Integer delward(String id);
	public List<Ward> selectward(String deptid) throws SQLException;
}
