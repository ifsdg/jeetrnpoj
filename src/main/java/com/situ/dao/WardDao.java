package com.situ.dao;

import java.sql.SQLException;
import java.util.List;


import com.situ.entry.Ward;
import com.situ.util.DataSouseutil;

public class WardDao {
	/**
	 * 
	 * @param pnum
	 * @param psize
	 * @return
	 * @throws SQLException
	 */
		public List<Ward> selectWards(String pnum,String psize) throws SQLException {
			String sql="SELECT ward.*,department.`name` as dep,COUNT(patient.id)AS count FROM ward" + 
					" LEFT JOIN department ON dept_id=department.id" + 
					" LEFT JOIN patient ON patient.ward_id=ward.id" + 
					" GROUP BY ward.id limit "+pnum+","+psize;
			return DataSouseutil.executeQuery(sql,Ward.class);
			
		}
		
		public List<Ward> selectWard(String id) throws SQLException{
			String sql="SELECT ward.*,department.`name` as dep,COUNT(patient.id)AS count FROM ward" + 
					" LEFT JOIN department ON dept_id=department.id" + 
					" LEFT JOIN patient ON patient.ward_id=ward.id" + 
					"  where ward.id="+id+" GROUP BY ward.id";
			
			return DataSouseutil.executeQuery(sql, Ward.class);
		}
		/**
		 * 计算总数
		 * @return
		 * @throws SQLException
		 */
		public Integer countWards() throws SQLException {
			String sql="select count(*) from ward";
			return DataSouseutil.executeQuery(sql);
			
		}
		/**
		 * 
		 * @param parems
		 * @return
		 * @throws SQLException
		 */
		public Integer insertWards(Object...parems) throws SQLException {
			String sqlString="insert into ward(wardno,address,dept_id) values(?,?,?)";
			return DataSouseutil.executeUpdate(sqlString, parems);
			
		}
		/**
		 * 
		 * @param patient
		 * @return
		 * @throws SQLException
		 */
		public Integer updateWard(Ward ward) throws SQLException {
			String sql="update ward set wardno=?,address=?,dept_id=?   where id=?";
			return DataSouseutil.executeUpdate(sql,ward.getWardno(),ward.getAddress(),ward.getDept_id(),ward.getId());
			
		}
		/**
		 * 
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		public Integer delWard(String id) throws SQLException {
			String sql="DELETE FROM ward WHERE id=?";
			return DataSouseutil.executeUpdate(sql, id);
			
		}
		
}
