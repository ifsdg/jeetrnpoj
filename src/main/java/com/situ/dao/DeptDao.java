package com.situ.dao;

import java.sql.SQLException;
import java.util.List;

import com.situ.entry.Dept;
import com.situ.util.DataSouseutil;

public class DeptDao {

	public List<Dept> selectallDepts() throws SQLException {
		String sql="select * from department";
		return DataSouseutil.executeQuery(sql, Dept.class);
		
	}
	
	public List<Dept> selectDepts(String pnum,String psize) throws SQLException {
		String sql="select * from department limit "+pnum+","+psize;
		return DataSouseutil.executeQuery(sql, Dept.class);
		
	}
	
	public Integer countDepts() throws SQLException {
		String sql="select count(*) from department";
		return DataSouseutil.executeQuery(sql);
		
	}
	
	public Integer insertdept(Object...parems) throws SQLException {
		String sqlString="insert into department(name,tel,address) values(?,?,?)";
		return DataSouseutil.executeUpdate(sqlString, parems);
		
	}
	
	public Integer updatedept(Dept dept) throws SQLException {
		String sql="update department set name=?,tel=?,address=? where id=?";
		return DataSouseutil.executeUpdate(sql, dept.getName(),dept.getTel(),dept.getAddress(),dept.getId());
		
	}
	public Integer deldept(String id) throws SQLException {
		String sql="DELETE FROM department WHERE id=?";
		return DataSouseutil.executeUpdate(sql, id);
		
	}
}
