package com.jsj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsj.dao.IUserDAO;
import com.jsj.dao.impl.UserDAOImpl;
import com.jsj.pojo.User;

@WebServlet("/GetEditUserServlet")
public class GetEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("请求参数" + request.getQueryString());
		String id = request.getParameter("edit_id");
		long edit_id = Long.parseLong(id);
		System.out.println("edit_id==" + edit_id);

		IUserDAO userDao = new UserDAOImpl();

		User editUser = userDao.get(edit_id);

		request.getSession().setAttribute("editUser", editUser);
		response.sendRedirect("/jsj_system/user-edit.jsp");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
