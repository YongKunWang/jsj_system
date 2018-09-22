package com.jsj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsj.pojo.User;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取session
		HttpSession session = request.getSession();
		// 获取用户对象
		User user = (User) session.getAttribute("user");
		// 判断用户是否有效
		if (user != null) {
			// 将用户对象逐出session
			session.removeAttribute("user");
		}
		response.sendRedirect("/jsj_system/login.jsp");
	}

}
