package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.VRApplication;
import datatypes.UserData;
import dbadapter.User;


public class UnregisteredUserGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("navtype", "unregistereduser");

		try {
			request.getRequestDispatcher("/templates/register.ftl").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			
			
			String username = (String) request.getParameter("username");
			String email = (String) request.getParameter("email");
			String age1 = (String) request.getParameter("age");
			int age= Integer.valueOf(age1);
			
			String result=VRApplication.getInstance().checkUser(new UserData(username,email,age));
			if(result=="ok") {
				VRApplication.getInstance().addUser(new UserData(username,email,age));
				request.setAttribute("navtype", "user");
				request.getRequestDispatcher("/templates/index.ftl").forward(request, response);
			}
			
			else if(result=="age"){
				try {
					request.setAttribute("navtype", "unregistereduser");
					request.setAttribute("errormessage", "Database error: Age is less than 18");
					request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("navtype", "unregistereduser");
					request.setAttribute("errormessage", "System error: please contact the administrator");
					e.printStackTrace();
				}
			}
			else if(result=="username")
			{
				try {
					request.setAttribute("navtype", "unregistereduser");
					request.setAttribute("errormessage", "Database error: Username already exists in the database");
					request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("navtype", "unregistereduser");
					request.setAttribute("errormessage", "System error: please contact the administrator");
					e.printStackTrace();
				}
			}
			else {
				try {
					request.setAttribute("navtype", "unregistereduser");
					request.setAttribute("errormessage", "Database error: please contact the administrator");
					request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("navtype", "unregistereduser");
					request.setAttribute("errormessage", "System error: please contact the administrator");
					e.printStackTrace();
				}
			}
			
			
		}
		catch (Exception e1) {
			try {
				request.setAttribute("navtype", "unregistereduser");
				request.setAttribute("errormessage", "Database error: One or more fields were wrong");
				request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("navtype", "unregistereduser");
				request.setAttribute("errormessage", "System error: please contact the administrator");
				e.printStackTrace();
			}
			e1.printStackTrace();
		}

	}
}