package application;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




import interfaces.IMovie;
import interfaces.IUserMovieList;


import interfaces.UCmds;
import dbadapter.DBFacade;
import datatypes.MovieData;
import datatypes.UserData;
import dbadapter.ShowMovieOverview;



public class VRApplication implements  IMovie, IUserMovieList {

	private static VRApplication instance;


	public static VRApplication getInstance() {
		if (instance == null) {
			instance = new VRApplication();
		}

		return instance;
	}


	public boolean avgRating () {
		boolean result = false;
		try {
			 result=  DBFacade.getInstance().avgRating();
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  }
		return result;
	}
	public String checkUser(UserData user) {
		String result = "";
		  
		  try {
			   result = DBFacade.getInstance().checkUser(user);
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  } 
		  
		   return result;
	}
	
	public boolean addUser(UserData user) {
		boolean result = false;
		  
		  try {
			   result = DBFacade.getInstance().addUser(user);
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  } 
		  
		   return result;
	}
	
	public ArrayList<ShowMovieOverview> getAllMovies() {
		ArrayList<ShowMovieOverview> result = null;

		
		try {
			
			result = DBFacade.getInstance().getAllMovies();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	 public boolean addRating(int movieRating, String comment, int uid, int mid) {
		  boolean result = false;
		  
		  try {
			   result = DBFacade.getInstance().addRating(movieRating, comment, uid, mid);
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  } 
		  
		   return result;
		  }
	 
	 public boolean addComment(String comment, int uid, int mid) {
		  boolean result = false;
		  
		  try {
			   result = DBFacade.getInstance().addComment(comment, uid, mid);
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  } 
		  
		   return result;
		  }
	 
	 public String checkRating(int uid, int mid) {
		  String result = "";
		  
		  try {
			   result = DBFacade.getInstance().checkRating(uid, mid);
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  } 
		  
		   return result;
		  }
	 
	 public boolean addMovie(MovieData movieData) {
		 boolean result = false;
		  
		  try {
			   result = DBFacade.getInstance().addMovie(movieData);
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  } 
		  
		   return result;
	 }
	 public boolean checkMovie(String title, int origPubDate) {
		 boolean result = false;
		  
		  try {
			   result = DBFacade.getInstance().checkMovie(title,origPubDate);
		  }
		  catch (Exception e) 
		  {
		  e.printStackTrace();
		  } 
		  
		   return result;
	 }
	
	
}
	
