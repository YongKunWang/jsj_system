package com.jsj.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jsj.dao.IUserDAO;
import com.jsj.pojo.User;

public class UserDAOImplTest {

	@Test
	public void testGetTotal() {
		IUserDAO userDao = new UserDAOImpl();
		int total = userDao.getTotal();
		System.out.println("total==" + total);
	}

	// @Test
	// public void testAdd() {
	// IUserDAO userDao = new UserDAOImpl();
	// User user = new User();
	// java.util.Date date = new java.util.Date(); // 获取当前时间
	// user.setGmt_create(new java.sql.Date(date.getTime()));
	// user.setGmt_modified(null);
	// user.setUsername("user003");
	// user.setPassword("111111");
	// user.setRealname("用户003");
	// user.setSex(true);
	// user.setAddress("成都市武侯区");
	// userDao.add(user);
	// }

	@Test
	public void testUpdate() {
		IUserDAO userDao = new UserDAOImpl();
		User user = new User();
		java.util.Date date = new java.util.Date(); // 获取当前时间
		user.setId(1);
		user.setGmt_create(new java.sql.Date(date.getTime()));
		user.setGmt_modified(null);
		user.setUsername("admin");
		user.setPassword("111111");
		user.setRealname("超级管理员");
		user.setSex(true);
		user.setAddress("成都市武侯区西南民族大学");
		userDao.update(user);
	}

	@Test
	public void testDelete() {
		IUserDAO userDao = new UserDAOImpl();
		long delete_id = 5;
		if (userDao.delete(delete_id)) {
			System.out.println("delete成功！");
		} else {
			System.out.println("delete失败！");
		}

	}

	@Test
	public void testGet() {
		IUserDAO userDao = new UserDAOImpl();
		System.out.println("userList:" + userDao.get(1).toString());
	}

	@Test
	public void testList() {
		List<User> userList = new ArrayList<User>();
		IUserDAO userDao = new UserDAOImpl();
		userList = userDao.list();
		for (User user : userList) {
			System.out.println("userList:" + user.toString());
		}
	}

}
