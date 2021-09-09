package com.situ.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.situ.dao.WardDao;
import com.situ.entry.Ward;
import com.situ.service.IwardService;

public class WardServiceImpl implements IwardService {
WardDao dao=new WardDao();
	@Override
	public List<Ward> selectwards(String pnum, String psize) throws SQLException {
		// TODO Auto-generated method stub
		Integer c=(Integer.parseInt(pnum)-1)*Integer.parseInt(psize);
		return dao.selectWards(c.toString(), psize);
	}

	@Override
	public Integer countwards() throws SQLException {
		// TODO Auto-generated method stub
		return dao.countWards();
	}

	@Override
	public Integer insertward(Object... parems) throws SQLException {
		// TODO Auto-generated method stub
		return dao.insertWards(parems);
	}

	@Override
	public Integer updateward(Ward ward) throws SQLException {
		// TODO Auto-generated method stub
		return dao.updateWard(ward);
	}

	@Override
	public Integer delward(String id) {
		// TODO Auto-generated method stub
		try {
			return dao.delWard(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Ward> selectwards(String id) throws SQLException {
		// TODO Auto-generated method stub
		return dao.selectWard(id);
	}

}
