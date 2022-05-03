package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.VRApplication;
import datatypes.MovieData;
import dbadapter.Rating;
import dbadapter.ShowMovieOverview;


public class UserGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("navtype", "user");
		boolean res=VRApplication.getInstance().avgRating();
		List<ShowMovieOverview> allMovies = null;
		try {
			allMovies = VRApplication.getInstance().getAllMovies();
			request.setAttribute("allMovies", allMovies);
			request.getRequestDispatcher("/templates/defaultWebpageG.ftl").forward(request, response);
		}catch(Exception eq) {
			
		}
		
		try {
			
		}catch(Exception eq) {
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String checkRating ;
		if (request.getParameter("action").equals("addRating")) {
			try {	
				request.setAttribute("navtype", "user");
				String val= request.getParameter("id");
				int mid= Integer.valueOf(val);
				checkRating=VRApplication.getInstance().checkRating(1, mid);
				request.setAttribute("mid",val);
				if(checkRating=="noComment") {
					request.setAttribute("condition", "noComment");
				}
				else if(checkRating=="noCommentNoRating"){
					request.setAttribute("condition", "noCommentNoRating");
				}
				else {
					request.setAttribute("condition", "commentAndRating");
				}
				request.getRequestDispatcher("/templates/ratingPage.ftl").forward(request, response);
				
				
				
			}catch(Exception e) {
			
			}
	
		}
		
		else if(request.getParameter("action").contains("addingMovie")) {
			try {
				request.setAttribute("navtype", "user");
				
				request.getRequestDispatcher("/templates/addMovie.ftl").forward(request, response);
			}
			catch(Exception e){}
			
		}
		
		else if(request.getParameter("action").contains("insertRating")) {
			request.setAttribute("navtype", "user");
			try {
				
				String val= request.getParameter("id");
				int mid= Integer.valueOf(val);
				request.setAttribute("mid",val);
				String rating1 = (String) request.getParameter("rating");
				int rating= Integer.valueOf(rating1);
				String comment = (String) request.getParameter("comment");
				int uid = 1;
				VRApplication.getInstance().addRating(rating,comment,uid,mid);
				request.getRequestDispatcher("/templates/index.ftl").forward(request, response);
			}
			catch(Exception e){}
			
		}
		else if(request.getParameter("action").contains("insertComment")) {
			request.setAttribute("navtype", "user");
			try {
				String val= request.getParameter("id");
				int mid= Integer.valueOf(val);
				request.setAttribute("mid",val);
				String comment = (String) request.getParameter("comment");
				int uid = 1;
				VRApplication.getInstance().addComment(comment, uid, mid);
				request.getRequestDispatcher("/templates/index.ftl").forward(request, response);
			}
			catch(Exception e) {}
		}

		else if(request.getParameter("action").contains("insertMovie")) {
			request.setAttribute("navtype", "user");
			try {
				List<ShowMovieOverview> allMovies = null;
				String title = (String) request.getParameter("title");
				String director = (String) request.getParameter("director");
				String actorsName = (String) request.getParameter("actorsName");
				String origPubDate1 = (String) request.getParameter("origPubDate");
				int origPubDate= Integer.valueOf(origPubDate1);
				boolean result=VRApplication.getInstance().checkMovie(title, origPubDate);
				if(result==true) {
					VRApplication.getInstance().addMovie(new MovieData(title,director,actorsName,origPubDate));
					allMovies = VRApplication.getInstance().getAllMovies();
					request.setAttribute("allMovies", allMovies);
					request.getRequestDispatcher("/templates/defaultWebpageG.ftl").forward(request, response);
				}
				else {
					try {
						request.setAttribute("errormessage", "Database error: Movie already exists in Database");
						request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
					} catch (Exception e) {
						request.setAttribute("errormessage", "System error: please contact the administrator");
						e.printStackTrace();
					}
				}
				
			}
			catch (Exception e1) {
				request.setAttribute("navtype", "user");
				try {
					request.setAttribute("errormessage", "Database error: One or more fields were wrong");
					request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("errormessage", "System error: please contact the administrator");
					e.printStackTrace();
				}
				e1.printStackTrace();
			}
		}
		
		
		
		
	}
}






