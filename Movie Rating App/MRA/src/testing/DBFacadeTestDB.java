package testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import datatypes.MovieData;
import datatypes.UserData;
import dbadapter.Configuration;
import dbadapter.DBFacade;
import dbadapter.Rating;
import dbadapter.ShowMovieOverview;
import dbadapter.User;
import junit.framework.TestCase;


public class DBFacadeTestDB extends TestCase {

	private Rating testR;
	private Rating testR1;
	private User testU;
	private ShowMovieOverview testSMO;
	private ShowMovieOverview testSMO1;
	private ShowMovieOverview testSMO2;
	

	/**
	 * Preparing classes with static methods
	 */
	@Before
	public void setUp() {

		// HolidayOffer object to be tested
		testR = new Rating( 5, "gut", 1 , 1);
		testR1 = new Rating( 5, "", 1 , 2);
		testU = new User(new UserData("Peter", "peter@peter.de", 21));		
		testSMO = new ShowMovieOverview(1,new MovieData("Malefecent", "Robert", "Angela", 2014));
		testSMO1 = new ShowMovieOverview(2,new MovieData("test1", "Rob", "Ange", 2012));
		testSMO2 = new ShowMovieOverview(3,new MovieData("Malef", "Rober", "Angel", 2010));
		
		ArrayList<ShowMovieOverview>testShowMovies = new ArrayList<ShowMovieOverview>();
//		
		testShowMovies.add(testSMO);
		testShowMovies.add(testSMO1);
		testShowMovies.add(testSMO2);
		//testShowMovies.contains(testSMO);
		//testShowMovies.contains(testR);

		
		
		

		// SQL statements
		String sqlCleanDB = "DROP TABLE IF EXISTS movie,user,userMovieList";
		String sqlCreateTableMovie = "CREATE TABLE movie (id int(11) NOT NULL  AUTO_INCREMENT, "
				+ "title VARCHAR(100) NOT NULL,"
				+ "  director text NOT NULL,"
				+ "  actorsName mediumtext NOT NULL,"
				+ "  origPubDate smallint(6) NOT NULL,"
				+ "  movieAvgRating float DEFAULT NULL, PRIMARY KEY (`id`))";
		
		String sqlCreateTableUser = "CREATE TABLE user ("
				+ "  id int(11) NOT NULL AUTO_INCREMENT,"
				+ "  email text NOT NULL,"
				+ "  username text NOT NULL,"
				+ "  age tinyint(4) NOT NULL, PRIMARY KEY (`id`))";
		
		String sqlCreateTableUserMovieList = "CREATE TABLE usermovielist ("
				+ "  id int(11) NOT NULL AUTO_INCREMENT,"
				+ "  movieRating float NOT NULL,"
				+ "  comment text DEFAULT NULL,"
				+ "  uid int(11) NOT NULL,"
				+ "  mid int(11) NOT NULL, PRIMARY KEY (`id`))"; 
		
		String sqlInsertMovie = "INSERT INTO movie (title,director,actorsName,origPubDate) "
				+ "VALUES (?,?,?,?)";
		String sqlInsertUser = "INSERT INTO user (username,email,age) VALUES (?,?,?)";
		String sqlInsertUserMovieList = "INSERT INTO userMovieList (movieRating,comment,uid,mid) VALUES (?,?,?,?)";

		// Perform database update
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
			try (PreparedStatement psCreateTableMovie = connection.prepareStatement(sqlCreateTableMovie)) {
				psCreateTableMovie.executeUpdate();
			}
			try (PreparedStatement psCreateTableUser = connection.prepareStatement(sqlCreateTableUser)) {
				psCreateTableUser.executeUpdate();
			}
			try (PreparedStatement psCreateTableUserMovieList = connection.prepareStatement(sqlCreateTableUserMovieList)) {
				psCreateTableUserMovieList.executeUpdate();
			}
			
			try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovie)) {
				
				psInsertMovie.setString(1, testSMO.getMovieData().getTitle());
				psInsertMovie.setString(2, testSMO.getMovieData().getDirector());
				psInsertMovie.setString(3, testSMO.getMovieData().getActorsName());
				psInsertMovie.setInt(4, testSMO.getMovieData().getOrigPubDate());
				
				psInsertMovie.executeUpdate();
			}
			try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovie)) {
				
				psInsertMovie.setString(1, testSMO1.getMovieData().getTitle());
				psInsertMovie.setString(2, testSMO1.getMovieData().getDirector());
				psInsertMovie.setString(3, testSMO1.getMovieData().getActorsName());
				psInsertMovie.setInt(4, testSMO1.getMovieData().getOrigPubDate());
				
				psInsertMovie.executeUpdate();
			}
			
			try (PreparedStatement psInsertUser = connection.prepareStatement(sqlInsertUser)) {
							
				psInsertUser.setString(1, testU.getUserData().getUserName());
				psInsertUser.setString(2, testU.getUserData().getEmail());
				psInsertUser.setInt(3, testU.getUserData().getAge());	
				
				psInsertUser.executeUpdate();
			}
			
			try (PreparedStatement psInsertUserMovieList = connection.prepareStatement(sqlInsertUserMovieList)) {
				
				psInsertUserMovieList.setInt(1, testR.getMovieRating());
				psInsertUserMovieList.setString(2, testR.getComment());
				psInsertUserMovieList.setInt(3, testR.getUid());
				psInsertUserMovieList.setInt(4, testR.getMid());
				
				psInsertUserMovieList.executeUpdate();
			}
			try (PreparedStatement psInsertUserMovieList = connection.prepareStatement(sqlInsertUserMovieList)) {
				
				psInsertUserMovieList.setInt(1, testR1.getMovieRating());
				psInsertUserMovieList.setString(2, testR1.getComment());
				psInsertUserMovieList.setInt(3, testR1.getUid());
				psInsertUserMovieList.setInt(4, testR1.getMid());
				
				psInsertUserMovieList.executeUpdate();
			}
			
		} catch (Exception e) {
			System.out.println("there is an errorrr");
			e.printStackTrace();
		}
	}

	/**
	 * Testing getAllMovies() with non-empty results.
	 */
	
	
	@Test 
	public void testGetAllMovies() {



		ArrayList<ShowMovieOverview> smo = DBFacade.getInstance().getAllMovies();
		// Verify return values
		assertTrue(smo.size() > 0);
		assertTrue(smo.get(0).getId() > 0);
		assertTrue(smo.get(0).getMovieData().getTitle().equals(testSMO.getMovieData().getTitle()));
		assertTrue(smo.get(0).getMovieData().getDirector().equals(testSMO.getMovieData().getDirector()));
		assertTrue(smo.get(0).getMovieData().getActorsName().equals(testSMO.getMovieData().getActorsName()));
		assertTrue(smo.get(0).getMovieData().getOrigPubDate() == testSMO.getMovieData().getOrigPubDate());
		assertTrue(smo.get(0).getMovieAvgRating() == testSMO.getMovieAvgRating());

	}
	
	@Test
	public void testAddMovie() {
		boolean result = false;
		MovieData movieData = new MovieData("Malefecent", "Mahmoud", "Angela", 2014);
        try {
             result = DBFacade.getInstance().addMovie(movieData);
        }
        catch (Exception e) 
        {
        e.printStackTrace();
        } 
        
         assertTrue(result==true);
	}
	
	@Test
	public void testcheckMovie() {
		boolean result1 = false;
		boolean result2 = false;

        
        result1 = DBFacade.getInstance().checkMovie("Malefecent",2014);
        result2 = DBFacade.getInstance().checkMovie("Malef",2012);
             
       
        assertFalse(result1==true);
        assertTrue(result2==true);
	}
	
	//////////////////////
	@Test
	public void testAddUser() {
		boolean result = false;
		UserData userData = new UserData("ali", "ali.com", 25);
        try {
             result = DBFacade.getInstance().addUser(userData);
        }
        catch (Exception e) 
        {
        e.printStackTrace();
        } 
        
        assertFalse(result==true);
	}
	
	@Test
	public void testcheckUser() {
		String result1 ;
		String result2 ;
		String result3 ;
		
		UserData userData1 = new UserData("ali", "ali.com", 25);
		UserData userData2 = new UserData("ali", "ali.com", 15);
		UserData userData3 =new UserData("Peter", "peter@peter.de", 21);
        
        result1 = DBFacade.getInstance().checkUser(userData1);
        result2 = DBFacade.getInstance().checkUser(userData2);
        result3 = DBFacade.getInstance().checkUser(userData3);
             
       
        assertTrue(result1.equals("ok"));
        assertTrue(result2.equals("age"));
        assertTrue(result3.equals("username"));
        
	}
	
	///////////////////////////
	@Test
	public void testAddRating() {
		boolean result = false;

        try {
             result = DBFacade.getInstance().addRating(8, "good", 3, 1);
        }
        catch (Exception e) 
        {
        e.printStackTrace();
        } 

         assertTrue(result==true);
        }
	@Test
	public void testCheckRating() {
		String result1 ;
		String result2 ;
		String result3 ;
		
		result1 = DBFacade.getInstance().checkRating(1,1);
		result2 = DBFacade.getInstance().checkRating(1,2);
        result3 = DBFacade.getInstance().checkRating(1,3);

        assertTrue(result1.equals("commentAndRating"));
        assertTrue(result2.equals("noComment"));
        assertTrue(result3.equals("noCommentNoRating"));
	}

	@Test
	public void testAddComment() {
		boolean result = false;

        try {
             result = DBFacade.getInstance().addComment("good",1,2);
        }
        catch (Exception e) 
        {
        e.printStackTrace();
        } 

         assertTrue(result==true);
        }
	
	@Test
	public void testavgRating() {
		boolean result = false;

        try {
             result = DBFacade.getInstance().avgRating();
        }
        catch (Exception e) 
        {
        e.printStackTrace();
        } 

         assertTrue(result==true);
        }
	
	/**
	 * Rest database
	 */

	@After
	public void tearDown() {

		// SQL statements
		String sqlCleanDB =  "DROP TABLE IF EXISTS movie,user,userMovieList";
		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
