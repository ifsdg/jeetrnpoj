package com.situ.entry;

public class Patient {
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String inpatientno;
	private Integer doctor_id;
	private Integer ward_id;
	
	private String doctor;
	private String dep;

	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String ward) {
		this.dep = ward;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getInpatientno() {
		return inpatientno;
	}
	public void setInpatientno(String inpatientno) {
		this.inpatientno = inpatientno;
	}
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	public Integer getWard_id() {
		return ward_id;
	}
	public void setWard_id(Integer ward_id) {
		this.ward_id = ward_id;
	}

	
	
}
