package interfaces;

import java.util.ArrayList;
import datatypes.MovieData;
import dbadapter.ShowMovieOverview;

public interface IMovie {

	public ArrayList<ShowMovieOverview> getAllMovies();

	

}
