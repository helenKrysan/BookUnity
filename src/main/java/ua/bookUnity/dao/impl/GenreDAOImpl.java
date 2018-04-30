package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.bookUnity.dao.GenreDAO;
import ua.bookUnity.dao.SubcategoryDAO;
import ua.bookUnity.model.Genre;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class GenreDAOImpl implements GenreDAO {

	
	public Genre save(String genreName) {
		GenreDAO genreDAO =new GenreDAOImpl();
		Genre genre = null;
		
		if(genreDAO.getOneByName(genreName)==null) {
			
			String sql ="INSERT INTO genre (genre_name) VALUES (?) RETURNING genre_id";
			SubcategoryDAO sbDAO = new SubcategoryDAOImpl();
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setString(1, genreName);
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				
				if(resultSet.next()) {
					genre =  new Genre(resultSet.getInt("genre_id"),genreName);
					sbDAO.save(genreName, "Базова підкатегорія", resultSet.getInt("genre_id"));
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}	
		return genre;
	}

	
	public Genre update(Integer genreID, String oldGenreName,String newGenreName) {
		GenreDAO genreDAO =new GenreDAOImpl();
		Genre genre = null;
		
		if(genreDAO.getOneByID(genreID)!=null) {
			SubcategoryDAO sbDAO = new SubcategoryDAOImpl();
			String sql ="UPDATE genre SET genre_name =? WHERE genre_id=?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setString(1, newGenreName);
				statement.setInt(2, genreID);
				
				if(statement.executeUpdate()==1) {
					genre =  new Genre(genreID,newGenreName);
					sbDAO.update(sbDAO.getOneByName(oldGenreName).getSubcategoryID(), newGenreName, "Базова підкатегорія", genreID);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}	
		return genre;
	}

	
	public Genre getOneByName(String genreName) {
		Genre genre = null;
		
		String sql ="SELECT * FROM genre WHERE genre_name=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, genreName);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				genre = new Genre(result.getInt("genre_id"),genreName);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return genre;
	}

	
	public Genre getOneByID(Integer genreID) {
		Genre genre = null;
		
		String sql ="SELECT * FROM genre WHERE genre_id=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, genreID);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				genre = new Genre(genreID, result.getString("genre_name"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return genre;
	}

	
	public List<Genre> getAll() {
		List<Genre> genreList = new LinkedList<Genre>();
		
		String sql = "SELECT * FROM genre";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Genre genre = new Genre(result.getInt("genre_id"), result.getString("genre_name"));
				genreList.add(genre);				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return genreList;
	}

	
	public void delete(Integer genreID) {
		
		GenreDAO genreDAO =new GenreDAOImpl();
		
		if(genreDAO.getOneByID(genreID)!=null) {
			
			String sql ="DELETE FROM genre WHERE genre_id =?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setInt(1, genreID);
				statement.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}	
	}

}
