package com.jsj.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jsj.dao.IUserDAO;
import com.jsj.pojo.User;

//实现接口类IUserDAO的所有函数，实现类命名一般后面要加上Impl，如UserDAOImpl。
//链接数据库的步骤：
//(1)加载mysql驱动
//(2)连接mysql数据库
//(3)创建Statement对象或者PreparedStatement对象
//(4)执行sql语句并获取返回的结果集
public class UserDAOImpl implements IUserDAO {
	// (1)加载mysql驱动：Class.forName("com.mysql.jdbc.Driver")
	public UserDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// (2)连接mysql数据库，如下：我的mysql项目名称jsj，用户密码都是root
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jsj?characterEncoding=UTF-8", "root", "root");
	}

	// @Override注解表示我们要重写接口IUserDAO里面定义的getTotal()函数
	@Override
	public int getTotal() {
		int total = 0;
		// 这里的getConnection()方法就是我们步骤2上面已经写好的连接数据库的方法，直接调用即可。
		// (3)创建Statement对象:c.createStatement();
		try (Connection c = getConnection(); Statement s = c.createStatement();) {
			// 编写sql语句
			String sql = "select count(*) from t_user";
			// (4)执行sql语句并获取返回的结果集
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				// 获取结果集里面的的第一个参数，其实这里总共也就一个参数
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回拿到的total用户总数
		return total;
	}

	@Override
	public void add(User user) {
		String sql = "insert into t_user values(null,?,?,?,?,?,?,?)";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDate(1, (java.sql.Date) user.gmt_create);
			ps.setDate(2, (java.sql.Date) user.gmt_modified);
			ps.setString(3, user.username);
			ps.setString(4, user.password);
			ps.setString(5, user.realname);
			ps.setBoolean(6, user.sex);
			ps.setString(7, user.address);
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				user.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		String sql = "update t_user set gmt_create= ?, gmt_modified = ? , username = ?, password = ? , realname = ? , sex = ? , address = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDate(1, (java.sql.Date) user.gmt_create);
			ps.setDate(2, (java.sql.Date) user.gmt_modified);
			ps.setString(3, user.username);
			ps.setString(4, user.password);
			ps.setString(5, user.realname);
			ps.setBoolean(6, user.sex);
			ps.setString(7, user.address);
			ps.setLong(8, user.id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(long id) {
		try (Connection c = getConnection(); Statement s = c.createStatement();) {
			String sql = "delete from t_user where id = " + id;
			s.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User get(long id) {
		User user = null;
		try (Connection c = getConnection(); Statement s = c.createStatement();) {
			String sql = "select * from t_user where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setGmt_create(rs.getDate("gmt_create"));
				user.setGmt_modified(rs.getDate("gmt_modified"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setSex(rs.getBoolean("sex"));
				user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> list() {
		List<User> userList = new ArrayList<User>();
		String sql = "select * from t_user order by id desc";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setGmt_create(rs.getDate("gmt_create"));
				user.setGmt_modified(rs.getDate("gmt_modified"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setSex(rs.getBoolean("sex"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User login(String username, String password) {
		User user = null;
		String sql = "select * from t_user where username = ? and password = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setGmt_create(rs.getDate("gmt_create"));
				user.setGmt_modified(rs.getDate("gmt_modified"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setSex(rs.getBoolean("sex"));
				user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> findUser(String username) {
		List<User> userList = new ArrayList<User>();
		String sql = "select * from t_user where username like ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, "%" + username + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setGmt_create(rs.getDate("gmt_create"));
				user.setGmt_modified(rs.getDate("gmt_modified"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setSex(rs.getBoolean("sex"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

}
