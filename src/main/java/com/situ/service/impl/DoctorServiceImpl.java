package com.situ.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.situ.dao.DocterDao;
import com.situ.entry.Doctor;
import com.situ.service.IdoctorService;

public class DoctorServiceImpl implements IdoctorService {
DocterDao dao=new DocterDao();

@Override
public List<Doctor> selectDocs(String pnum, String psize) throws SQLException {
	// TODO Auto-generated method stub
	Integer c=(Integer.parseInt(pnum)-1)*Integer.parseInt(psize);
	return dao.selectdocs(c.toString(), psize);
}

@Override
public Integer countDocs() throws SQLException {
	// TODO Auto-generated method stub
	return dao.countDocs();
}

@Override
public Integer insertdoc(Doctor doctor) throws SQLException {
	// TODO Auto-generated method stub
	
	return dao.insertdocs(doctor.getName(),doctor.getCardno(),doctor.getTitle(),doctor.getAge()
			,doctor.getGender(),doctor.getDept_id());
}

@Override
public Integer updatedoc(Doctor doctor) throws SQLException {
	// TODO Auto-generated method stub
	return dao.updatedept(doctor);
}

@Override
public Integer deldoc(String id) {
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
public List<Doctor> serchDocs(String ope,String data) throws SQLException {
	// TODO Auto-generated method stub
	return dao.findDocs(ope, data);
}


}
