package interfaces;

import java.util.ArrayList;

import datatypes.MovieData;
import datatypes.UserData;
import dbadapter.ShowMovieOverview;


public interface UCmds {

	public ArrayList<ShowMovieOverview> getAllMovies();
	public boolean avgRating ();
	public String checkUser(UserData user);
	public boolean addUser(UserData user);
	public boolean addRating(int movieRating, String comment, int uid, int mid);
	public boolean addComment(String comment, int uid, int mid);
	public String checkRating(int uid, int mid);
	public boolean addMovie(MovieData movieData);
	public boolean checkMovie(String title, int origPubDate);
	
	
}
