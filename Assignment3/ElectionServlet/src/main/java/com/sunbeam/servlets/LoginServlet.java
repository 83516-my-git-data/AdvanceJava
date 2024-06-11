package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		try(UserDao userDao = new UserDaoImpl()) {
			User user = userDao.findByEmail(email);
			System.out.println(email);
			if(user != null && user.getPassword().equals(passwd)) {
				//Login
				
				Cookie c = new Cookie("uname", user.getFirstName());
				c.setMaxAge(3600); // 3600 seconds = 1hr.
				resp.addCookie(c);
				
				HttpSession session = req.getSession();
				session.setAttribute("curuser", user);
				
				//// System.out.println("Login Successful: "+user);
				
				if(user.getRole().equals("voter")) {
					// Voter login
					resp.sendRedirect("candlist");
					
//					String reUrl = resp.encodeRedirectURL("candlist");
//					resp.sendRedirect(reUrl);
					
				}else {
					//Admin login
					resp.sendRedirect("result");
					
//					String reUrl = resp.encodeRedirectURL("result");
//					resp.sendRedirect(reUrl);
				}
				
			}else {
				// login failed
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login Failed</title>");
				out.println("</head>");
				out.println("<body align='center'>");
				
				ServletContext app = this.getServletContext();
				String appTitle = app.getInitParameter("AppTitle");
				out.printf("<h3>%s</h3>", appTitle);
				
				out.println("Invalid Email or Password. <br/><br/>");
				out.println("<a href='index.html' >Login Again</a>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
}
