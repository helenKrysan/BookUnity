package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ua.bookUnity.dao.BookDAO;
import ua.bookUnity.dao.BookGenreDAO;
import ua.bookUnity.dao.GenreDAO;
import ua.bookUnity.dao.SubcategoryDAO;
import ua.bookUnity.model.Book;
import ua.bookUnity.model.BookGenre;
import ua.bookUnity.model.Genre;
import ua.bookUnity.model.Subcategory;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class BookGenreDAOImpl implements BookGenreDAO{

	
	public BookGenre getByAllFields(Integer book_fk, Integer genre_fk, Integer subcategory_fk) {
		BookGenre bookGenre = null;
		String sql = "SELECT * FROM book_genre WHERE genre_fk=? AND book_fk=? AND subcategory_fk=?";
		SubcategoryDAO sbDAO = new SubcategoryDAOImpl();
		GenreDAO genreDAO = new GenreDAOImpl();
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, genre_fk);
			statement.setInt(2, book_fk);
			if(subcategory_fk!=null) {
				statement.setInt(3, subcategory_fk);
			}else {
				statement.setInt(3,sbDAO.getOneByName(genreDAO.getOneByID(genre_fk).getGenreName()).getSubcategoryID());
			}
			
			
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				bookGenre = new BookGenre(book_fk,genre_fk,subcategory_fk);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return bookGenre;
	}

	
	public BookGenre save(Integer book_fk, Integer genre_fk, Integer subcategory_fk) {
		
		BookGenre bookGenre = null;
		BookGenreDAO bgDAO = new BookGenreDAOImpl();
		SubcategoryDAO sbDAO = new SubcategoryDAOImpl();
		GenreDAO genreDAO = new GenreDAOImpl();
		if((bgDAO.getByAllFields(book_fk, genre_fk, subcategory_fk)==null)&&((subcategory_fk==null)||((subcategory_fk!=null )&& sbDAO.getAllNameSubcOfGenre(genre_fk).contains(sbDAO.getOneById(subcategory_fk).getSubcategoryName())))) {
			String sql = "INSERT INTO book_genre (genre_fk,book_fk, subcategory_fk) VALUES (?,?,?)";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setInt(1, genre_fk);
				statement.setInt(2, book_fk);
				Integer subcategory ;
				if(subcategory_fk!=null) {
					subcategory =  subcategory_fk;
					statement.setInt(3,subcategory);
				}else {
					subcategory = sbDAO.getOneByName(genreDAO.getOneByID(genre_fk).getGenreName()).getSubcategoryID();
					statement.setInt(3,subcategory);
				}
				
				
				
				if(statement.executeUpdate()==1) {
					bookGenre = new BookGenre(book_fk,genre_fk,subcategory);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
		return bookGenre;
	}

	
	public List<Book> getBookByGenre(Integer genre_fk) {
		
		String sql = "SELECT book_fk FROM book_genre WHERE genre_fk=?";
		
		List<Book> bookList = new LinkedList<Book>();
		BookDAO bkDAO = new BookDAOImpl();
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, genre_fk);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Book book = bkDAO.getOneByID(result.getInt("book_fk"));
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return bookList;

	}


	public List<Book> getBookBySubcategory(Integer subcategory_fk) {
		String sql = "SELECT book_fk FROM book_genre WHERE subcategory_fk=?";
		
		List<Book> bookList = new LinkedList<Book>();
		BookDAO bkDAO = new BookDAOImpl();
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, subcategory_fk);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Book book = bkDAO.getOneByID(result.getInt("book_fk"));
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	
	public Map<Subcategory, Genre> getBookGenres(Integer book_fk) {
		String sql = "SELECT * FROM book_genre WHERE book_fk=?";
		
		Map<Subcategory, Genre> bookGenreMap = new HashMap<Subcategory, Genre>();
		GenreDAO genreDAO = new GenreDAOImpl();
		SubcategoryDAO sbDAO = new SubcategoryDAOImpl();
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, book_fk);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Subcategory subcategory = sbDAO.getOneById(result.getInt("subcategory_fk"));
				Genre genre = genreDAO.getOneByID(result.getInt("genre_fk"));
				
				bookGenreMap.put(subcategory,genre);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return bookGenreMap;
	}

	
	public void delete(Integer book_fk, Integer genre_fk, Integer subcategory_fk) {
		
		
		BookGenreDAO bgDAO = new BookGenreDAOImpl();
		
		if(bgDAO.getByAllFields(book_fk, genre_fk, subcategory_fk)!=null){
			String sql = "DELETE FROM book_genre WHERE genre_fk=? AND book_fk=? AND subcategory_fk=?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setInt(1, genre_fk);
				statement.setInt(2, book_fk);
				statement.setInt(3, subcategory_fk);
				
				statement.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
}
