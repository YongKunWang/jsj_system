package com.jsj.pojo;

import java.util.Date;

public class User {
	public long id;
	public Date gmt_create;
	public Date gmt_modified;
	public String username;
	public String password;
	public String realname;
	public Boolean sex;
	public String address;

	public User(long id, Date gmt_create, Date gmt_modified, String username, String password, String realname,
			Boolean sex, String address) {
		super();
		this.id = id;
		this.gmt_create = gmt_create;
		this.gmt_modified = gmt_modified;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.sex = sex;
		this.address = address;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_modified() {
		return gmt_modified;
	}

	public void setGmt_modified(Date gmt_modified) {
		this.gmt_modified = gmt_modified;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", gmt_create=" + gmt_create + ", gmt_modified=" + gmt_modified + ", username="
				+ username + ", password=" + password + ", realname=" + realname + ", sex=" + sex + ", address="
				+ address + "]";
	}
}
