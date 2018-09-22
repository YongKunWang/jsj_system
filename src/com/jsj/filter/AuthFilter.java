package com.jsj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsj.pojo.User;

//没有拦截器的话，不法分子都不用登录，通过地址栏就可以删除用户，甚至清空你的数据库表里面的数据，想想他是什么做到的？
public class AuthFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String uri = request.getRequestURI();
		// 放行对静态资源的访问，以及登录页面的访问：想想为什么要怎么做？不放行会怎么样？
		if (uri.endsWith("login.jsp") || uri.endsWith("LoginServlet") || uri.endsWith(".css") || uri.endsWith(".js")
				|| uri.endsWith(".png")) {
			chain.doFilter(request, response);
			return;
		}
		// 看看用户是否登录，搞清楚这段代码是如何确认用户是否登录
		User user = (User) request.getSession().getAttribute("user");
		if (null == user) {
			response.sendRedirect("/jsj_system/login.jsp");
			return;
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
