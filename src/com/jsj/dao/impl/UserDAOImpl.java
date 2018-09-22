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

public class UserDAOImpl implements IUserDAO {

	public UserDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jsj?characterEncoding=UTF-8", "root", "root");
	}

	public int getTotal() {
		int total = 0;
		try (Connection c = getConnection(); Statement s = c.createStatement();) {
			String sql = "select count(*) from t_user";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public User findUser(String username) {
		User user = null;
		String sql = "select * from t_user where username like ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, "%" + username + "%");
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

}
