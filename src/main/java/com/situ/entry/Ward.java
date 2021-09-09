package com.situ.entry;

public class Ward {

	private Integer id;
	private String wardno;
	private String address;
	private Integer dept_id;
	
	private String dep;
	private long count;
	
	
	
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWardno() {
		return wardno;
	}
	public void setWardno(String wardno) {
		this.wardno = wardno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	
}
