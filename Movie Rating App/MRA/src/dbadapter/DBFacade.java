package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import datatypes.MovieData;
import datatypes.UserData;
import interfaces.IMovie;
import interfaces.UCmds;



public class DBFacade implements UCmds {
	private static DBFacade instance;

	
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	public static void setInstance(DBFacade dbfacade) {
		instance = dbfacade;
	}
	
	public boolean avgRating () {
		String sqlSelect = "SELECT AVG(movieRating) , mid FROM userMovieList GROUP BY mid";
		String sqlUpdate = "UPDATE movie SET movieAvgRating=? WHERE id=?";
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			
			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				
				
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						
						ShowMovieOverview temp = new ShowMovieOverview(rs.getFloat(1), rs.getInt(2));
						
						
						try (PreparedStatement ps1 = connection.prepareStatement(sqlUpdate)) {
							ps1.setFloat(1, temp.getMovieAvgRating());
							ps1.setInt(2, temp.getId());
							ps1.executeUpdate();
							} catch (SQLException e) {
							e.printStackTrace();
							}
//						return true;
							
					}
					;
				}
				
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean addUser(UserData user) {

        // Declare SQL query to insert offer.
        String sqlInsert = "INSERT INTO user (username,email,age) VALUES (?,?,?)";

        // Insert offer into database.
        try (Connection connection = DriverManager
                .getConnection(
                        "jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
                                + Configuration.getPort() + "/" + Configuration.getDatabase(),
                        Configuration.getUser(), Configuration.getPassword())) {

            try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getEmail());
                ps.setInt(3, user.getAge());


                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public String checkUser(UserData user) {
		
		if(user.getAge()<18) {
			return "age";
		}
		
		String sqlSelect = "SELECT * FROM user where username = ?";

		// Declare SQL query to insert offer.
		

		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			
			try (PreparedStatement ps1 = connection.prepareStatement(sqlSelect)) {
				
				ps1.setString(1, user.getUserName());
				try (ResultSet rs = ps1.executeQuery()) {
					User temp=null;
					if (rs.next()) {
						 temp = new User(new UserData(rs.getString(2), rs.getString(3), rs.getInt(4)));	
					};
					if(temp==null) {
						
						return "ok";
					}
					else {
						
						return "username";
					}
					
				}
				
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "db";
	}


	public ArrayList<ShowMovieOverview> getAllMovies() {
		
		ArrayList<ShowMovieOverview> result = new ArrayList<ShowMovieOverview>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM Movie";

		// Query all offers that fits to the given criteria.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
				

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						ShowMovieOverview temp = new ShowMovieOverview(rs.getInt(1), 
								new MovieData(rs.getString(2), rs.getString(3),  rs.getString(4),rs.getInt(5)), rs.getFloat(6));

						// Query all bookings for the offer to check if its
						// available.
							result.add(temp);
					}
					;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
		
	
	
	public boolean addRating(int movieRating, String comment, int uid, int mid) {
	
		

		// Declare SQL query to insert offer.
		String sqlInsert = "INSERT INTO userMovieList (movieRating,comment,uid,mid) VALUES (?,?,?,?)";

		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			
					try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
					ps.setInt(1, movieRating);
					ps.setString(2, comment);
					ps.setInt(3, 1);
					ps.setInt(4, mid);
					ps.executeUpdate();
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
public boolean addComment(String comment, int uid, int mid) {
	
		

		// Declare SQL query to insert offer.
		String sqlUpdate = "UPDATE userMovieList SET comment=? WHERE uid=? AND mid=?";

		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			
					try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
					ps.setString(1, comment);
					ps.setInt(2, 1);
					ps.setInt(3, mid);
					ps.executeUpdate();
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public String checkRating(int uid, int mid) {
		
		String sqlSelect = "SELECT * FROM userMovieList where uid = ? AND mid = ?";

		// Declare SQL query to insert offer.
		

		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			
			try (PreparedStatement ps1 = connection.prepareStatement(sqlSelect)) {
				
				ps1.setInt(1, 1);
				ps1.setInt(2, mid);
				try (ResultSet rs = ps1.executeQuery()) {
					Rating temp=null;
					if (rs.next()) {
						 temp = new Rating(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));	
					};
					if(temp==null) {
						
						return "noCommentNoRating";
					}
					else if(temp.getComment()=="") {
						return "noComment";
					}
					
				}
				
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "commentAndRating";
	}
	
	
public boolean addMovie(MovieData movieData) {
	
		

		// Declare SQL query to insert offer.
		String sqlInsert = "INSERT INTO movie (title,director,actorsName,origPubDate) VALUES (?,?,?,?)";

		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			
					try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
					ps.setString(1, movieData.getTitle());
					ps.setString(2, movieData.getDirector());
					ps.setString(3, movieData.getActorsName());
					ps.setInt(4, movieData.getOrigPubDate());
					ps.executeUpdate();
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

public boolean checkMovie(String title, int origPubDate) {
	
	String sqlSelect = "SELECT * FROM movie where title = ? AND origPubDate = ?";

	// Declare SQL query to insert offer.
	

	
	try (Connection connection = DriverManager
			.getConnection(
					"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
							+ Configuration.getPort() + "/" + Configuration.getDatabase(),
					Configuration.getUser(), Configuration.getPassword())) {
		
		try (PreparedStatement ps1 = connection.prepareStatement(sqlSelect)) {
			
			ps1.setString(1, title);
			ps1.setInt(2, origPubDate);
			try (ResultSet rs = ps1.executeQuery()) {
				ShowMovieOverview temp=null;
				if (rs.next()) {
					 temp = new ShowMovieOverview(rs.getInt(1), 
								new MovieData(rs.getString(2), rs.getString(3),  rs.getString(4),rs.getInt(5)), rs.getFloat(6));	
				};
				if(temp==null) {
					
					return true;
				}
				else {
					
					return false;
				}
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}

	

	
	

	
	
}
