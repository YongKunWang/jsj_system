package com.jsj.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsj.dao.IUserDAO;
import com.jsj.dao.impl.UserDAOImpl;
import com.jsj.pojo.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");

		if ("add".equals(operation)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			boolean sex = Boolean.getBoolean(request.getParameter("sex"));// 将前端获取到的字符转化成boolean类型
			String address = request.getParameter("address");

			IUserDAO userDao = new UserDAOImpl();
			User user = new User();
			java.util.Date date = new java.util.Date(); // 获取当前时间

			user.setGmt_create(new java.sql.Date(date.getTime()));
			user.setGmt_modified(null);
			user.setUsername(username);
			user.setPassword(password);
			user.setRealname(realname);
			user.setSex(sex);
			user.setAddress(address);

			userDao.add(user);

			// 获取用户列表
			List<User> userList = userDao.list();
			// 将用户列表写入session里面
			request.getSession().setAttribute("userList", userList);
			// 重定向到index.jsp页面：地址栏上的地址变为/index.jsp，弄清楚重定向和转发的区别
			response.sendRedirect("/jsj_system/index.jsp");
		} else if ("delete".equals(operation)) {
			long id = Long.parseLong(request.getParameter("id"));
			IUserDAO userDao = new UserDAOImpl();

			userDao.delete(id);

			// 获取用户列表
			List<User> userList = userDao.list();
			// 将用户列表写入session里面
			request.getSession().setAttribute("userList", userList);
			// 重定向到index.jsp页面：地址栏上的地址变为/index.jsp，弄清楚重定向和转发的区别
			response.sendRedirect("/jsj_system/index.jsp");

		} else if ("edit".equals(operation)) {
			long id = Long.parseLong(request.getParameter("id"));
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date gmt_create = null;
			try {
				gmt_create = sf.parse(request.getParameter("gmt_create"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			boolean sex = Boolean.getBoolean(request.getParameter("sex"));// 将前端获取到的字符转化成boolean类型
			String address = request.getParameter("address");

			IUserDAO userDao = new UserDAOImpl();
			User user = new User();
			java.util.Date date = new java.util.Date(); // 获取当前时间

			user.setId(id);
			user.setGmt_create(new java.sql.Date(gmt_create.getTime()));
			user.setGmt_modified(new java.sql.Date(date.getTime()));
			user.setUsername(username);
			user.setPassword(password);
			user.setRealname(realname);
			user.setSex(sex);
			user.setAddress(address);

			userDao.update(user);

			// 获取用户列表
			List<User> userList = userDao.list();
			// 将用户列表写入session里面
			request.getSession().setAttribute("userList", userList);
			// 重定向到index.jsp页面：地址栏上的地址变为/index.jsp，弄清楚重定向和转发的区别
			response.sendRedirect("/jsj_system/index.jsp");
		} else if ("search".equals(operation)) {
			String username = request.getParameter("username");
			IUserDAO userDao = new UserDAOImpl();
			User user = userDao.findUser(username);
			// 获取用户列表
			List<User> userList = new ArrayList<User>();
			userList.add(user);
			// 将用户列表写入session里面
			request.getSession().setAttribute("userList", userList);
			// 重定向到index.jsp页面：地址栏上的地址变为/index.jsp，弄清楚重定向和转发的区别
			response.sendRedirect("/jsj_system/index.jsp");

		} else {
			IUserDAO userDao = new UserDAOImpl();
			// 获取用户列表
			List<User> userList = userDao.list();
			// 将用户列表写入session里面
			request.getSession().setAttribute("userList", userList);
			// 重定向到index.jsp页面：地址栏上的地址变为/index.jsp，弄清楚重定向和转发的区别
			response.sendRedirect("/jsj_system/index.jsp");
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
