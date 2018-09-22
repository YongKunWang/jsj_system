package com.jsj.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsj.dao.IUserDAO;
import com.jsj.dao.impl.UserDAOImpl;
import com.jsj.pojo.User;

//@WebServlet("/LoginServlet")是干什么的，还有没有其他的配置方法？哪一种比较好？为什么？
@WebServlet("/LoginServlet")
// 为什么要extends HttpServlet？
public class LoginServlet extends HttpServlet {
	// 这里为什么要写serialVersionUID？
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取用户名
		String username = request.getParameter("username");
		// 获取密码
		String password = request.getParameter("password");
		System.out.println("username=" + username);
		System.out.println("password=" + password);
		// 实例化UserDao对象，不应该是UserDAOImpl userDao = new UserDAOImpl();吗？为什么要这么写？
		IUserDAO userDao = new UserDAOImpl();
		// 根据用户密码查询用户
		User user = userDao.login(username, password);
		// 判断user是否为空
		if (user != null) {
			// 将用户对象放入session中，可以好好思考session是个什么东西？
			request.getSession().setAttribute("user", user);
			// 获取用户列表
			List<User> userList = userDao.list();
			// 将用户列表写入session里面
			request.getSession().setAttribute("userList", userList);
			// 既然登陆成功就应该把session里面的login_error属性去掉
			request.getSession().removeAttribute("login_error");
			// 重定向到index.jsp页面：地址栏上的地址变为/index.jsp，弄清楚重定向和转发的区别
			response.sendRedirect("/jsj_system/index.jsp");
		} else {
			// 登录失败，往session里面写入login_error，这样用户就可以在登录页面看到提示登录错误的信息
			request.getSession().setAttribute("login_error", "错误：用户名或密码错误！");
			// 下面这个方法是转发到login.jsp页面：地址栏上的地址不变。还是/login.jsp
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet方法是干嘛的？doPost方法又是干嘛的？为什么在这里又要嵌套一个doPost方法？
		doPost(request, response);
	}
}
