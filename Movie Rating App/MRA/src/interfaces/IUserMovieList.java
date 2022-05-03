package interfaces;

import java.util.ArrayList;
import datatypes.MovieData;
import dbadapter.ShowMovieOverview;

public interface IUserMovieList {

	public boolean addRating(int movieRating, String comment, int uid, int mid);

	

}
