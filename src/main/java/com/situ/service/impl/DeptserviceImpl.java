package com.situ.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.situ.dao.DeptDao;
import com.situ.entry.Dept;
import com.situ.service.IdpetService;


public class DeptserviceImpl implements IdpetService {
	DeptDao dao=new DeptDao();

	@Override
	public List<Dept> selectDepts(String pnum, String psize) throws SQLException {
		// TODO Auto-generated method stub

		Integer c=(Integer.parseInt(pnum)-1)*Integer.parseInt(psize);
		return dao.selectDepts(c.toString(), psize);
	}

	
	@Override
	public Integer countDepts() throws SQLException {
		// TODO Auto-generated method stub
		return dao.countDepts();
	}

	@Override
	public Integer insertdept(Object... parems) throws SQLException {
		// TODO Auto-generated method stub
		return dao.insertdept(parems);
	}

	@Override
	public Integer updatedept(Dept dept) throws SQLException {
		// TODO Auto-generated method stub
		return dao.updatedept(dept);
	}

	@Override
	public Integer deldept(String id) {
		// TODO Auto-generated method stub
		try {
			return dao.deldept(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}


	@Override
	public List<Dept> selectDepts() throws SQLException {
		// TODO Auto-generated method stub
		return dao.selectallDepts();
	}
	
	
}
