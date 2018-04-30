package ua.bookUnity.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.bookUnity.dao.BookDAO;
import ua.bookUnity.model.Book;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class BookDAOImpl implements BookDAO{

	
	public Book save(String bookName, String author, String language, Integer yearIssue,
			String publishingHouse, String description, Integer numberOfPages, String price, String impression,
			String account_fk, Integer condition_fk, Integer category_fk) {
		
		Book book = null;
		
		String sql ="INSERT INTO book (name, author, language, year_of_issue,publishing_house, description, number_of_pages, price, own_impression, account_fk, category_fk, condition_fk) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) RETURNING book_id";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, bookName);
			statement.setString(2, author);
			statement.setString(3, language);
			statement.setInt(4, yearIssue);
			statement.setString(5, publishingHouse);
			statement.setString(6, description);
			statement.setInt(7, numberOfPages);
			statement.setBigDecimal(8, new BigDecimal(price));
			statement.setString(9, impression);
			statement.setString(10, account_fk);
			statement.setInt(11, condition_fk);
			statement.setInt(12, category_fk);
			statement.execute();
			ResultSet result = statement.getResultSet();
			
			if(result.next()) {
				Integer bookID = result.getInt("book_id");
				book = new Book(bookID, bookName, author, language, yearIssue,publishingHouse,description,numberOfPages, new BigDecimal(price), impression,account_fk,condition_fk, category_fk);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return book;
	}

	
	public Book update(Integer bookID, String bookName, String author, String language, Integer yearIssue,
			String publishingHouse, String description, Integer numberOfPages, String price, String impression,
			String account_fk, Integer condition_fk, Integer category_fk) {
		
		Book book = null;
		BookDAO bookDAO = new BookDAOImpl();
		
		if(bookDAO.getOneByID(bookID)!=null) {
			
			String sql ="UPDATE book SET name=?, author=?, language=?, year_of_issue=?,publishing_house=?, description=?, number_of_pages=?, price=?, own_impression=?, account_fk=?, category_fk=?, condition_fk=? WHERE book_id =? ";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setString(1, bookName);
				statement.setString(2, author);
				statement.setString(3, language);
				statement.setInt(4, yearIssue);
				statement.setString(5, publishingHouse);
				statement.setString(6, description);
				statement.setInt(7, numberOfPages);
				statement.setBigDecimal(8, new BigDecimal(price));
				statement.setString(9, impression);
				statement.setString(10, account_fk);
				statement.setInt(11, condition_fk);
				statement.setInt(12, category_fk);
				statement.setInt(13, bookID);

				if(statement.executeUpdate()==1) {
					book = new Book(bookID, bookName, author, language, yearIssue,publishingHouse,description,numberOfPages, new BigDecimal(price), impression,account_fk,condition_fk, category_fk);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return book;
	
	}

	
	public List<Book> getAllByAccount(String account_fk) {
 
		List<Book> bookList = new LinkedList<Book>();
		
		String sql ="SELECT * FROM book WHERE account_fk=? ";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, account_fk);
			ResultSet result =  statement.executeQuery();

			while(result.next()) {
				Book book = new Book();
				book.setBookID(result.getInt("book_id"));
				book.setBookName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setLanguage(result.getString("language"));
				book.setYearIssue(result.getInt("year_of_issue"));
				book.setPublishingHouse(result.getString("publishing_house"));
				book.setDescription(result.getString("description"));
				book.setNumberOfPages(result.getInt("number_of_pages"));
				book.setPrice(result.getBigDecimal("price"));
				book.setImpression(result.getString("own_impression"));
				book.setAccount_fk(result.getString("account_fk"));
				book.setCategory_fk(result.getInt("category_fk"));
				book.setCondition_fk(result.getInt("condition_fk"));
				bookList.add(book);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}

	
	public void delete(Integer bookID) {
		
		
		BookDAO bookDAO = new BookDAOImpl();
		
		if(bookDAO.getOneByID(bookID)!=null) {
			
			String sql ="DELETE FROM book WHERE book_id=? ";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setInt(1, bookID);
			
				statement.executeUpdate();	
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		
		}
		
	}



	public Book getOneByID(Integer bookID) {
		
		Book book = null;
		
		String sql ="SELECT * FROM book WHERE book_id=? ";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, bookID);
			ResultSet result =  statement.executeQuery();

			if(result.next()) {
				book = new Book();
				book.setBookID(result.getInt("book_id"));
				book.setBookName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setLanguage(result.getString("language"));
				book.setYearIssue(result.getInt("year_of_issue"));
				book.setPublishingHouse(result.getString("publishing_house"));
				book.setDescription(result.getString("description"));
				book.setNumberOfPages(result.getInt("number_of_pages"));
				book.setPrice(result.getBigDecimal("price"));
				book.setImpression(result.getString("own_impression"));
				book.setAccount_fk(result.getString("account_fk"));
				book.setCategory_fk(result.getInt("category_fk"));
				book.setCondition_fk(result.getInt("condition_fk"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return book;
		
	}


	
	public List<Book> getAllByCategory(Integer category_fk) {
		
		List<Book> bookList = new LinkedList<Book>();
		
		String sql ="SELECT * FROM book WHERE category_fk=? ";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, category_fk);
			ResultSet result =  statement.executeQuery();

			while(result.next()) {
				Book book = new Book();
				book.setBookID(result.getInt("book_id"));
				book.setBookName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setLanguage(result.getString("language"));
				book.setYearIssue(result.getInt("year_of_issue"));
				book.setPublishingHouse(result.getString("publishing_house"));
				book.setDescription(result.getString("description"));
				book.setNumberOfPages(result.getInt("number_of_pages"));
				book.setPrice(result.getBigDecimal("price"));
				book.setImpression(result.getString("own_impression"));
				book.setAccount_fk(result.getString("account_fk"));
				book.setCategory_fk(result.getInt("category_fk"));
				book.setCondition_fk(result.getInt("condition_fk"));
				bookList.add(book);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return bookList;
		
	}


	
	public List<Book> getAllByGenre(String genre) {
		List<Book> bookList = new LinkedList<Book>();
		
		String sql ="SELECT * FROM book WHERE book_id IN (SELECT book_id FROM book_genre WHERE genre_fk IN (SELECT genre_fk FROM subcategory WHERE subcategory_id=?)) ";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, genre);
			ResultSet result =  statement.executeQuery();

			while(result.next()) {
				Book book = new Book();
				book.setBookID(result.getInt("book_id"));
				book.setBookName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setLanguage(result.getString("language"));
				book.setYearIssue(result.getInt("year_of_issue"));
				book.setPublishingHouse(result.getString("publishing_house"));
				book.setDescription(result.getString("description"));
				book.setNumberOfPages(result.getInt("number_of_pages"));
				book.setPrice(result.getBigDecimal("price"));
				book.setImpression(result.getString("own_impression"));
				book.setAccount_fk(result.getString("account_fk"));
				book.setCategory_fk(result.getInt("category_fk"));
				book.setCondition_fk(result.getInt("condition_fk"));
				bookList.add(book);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}


	
	public List<Book> getAllByAuthor(String author) {
		List<Book> bookList = new LinkedList<Book>();
		
		String sql ="SELECT * FROM book WHERE author=? ";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, author);
			ResultSet result =  statement.executeQuery();
			while(result.next()) {
				System.out.println(result.getInt("book_id"));
				Book book = new Book();
				book.setBookID(result.getInt("book_id"));
				book.setBookName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setLanguage(result.getString("language"));
				book.setYearIssue(result.getInt("year_of_issue"));
				book.setPublishingHouse(result.getString("publishing_house"));
				book.setDescription(result.getString("description"));
				book.setNumberOfPages(result.getInt("number_of_pages"));
				book.setPrice(result.getBigDecimal("price"));
				book.setImpression(result.getString("own_impression"));
				book.setAccount_fk(result.getString("account_fk"));
				book.setCategory_fk(result.getInt("category_fk"));
				book.setCondition_fk(result.getInt("condition_fk"));
				bookList.add(book);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}


	
	public List<Book> getAllByYear(Integer year) {
List<Book> bookList = new LinkedList<Book>();
		
		String sql ="SELECT * FROM book WHERE year_of_issue=? ";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, year);
			ResultSet result =  statement.executeQuery();

			while(result.next()) {
				Book book = new Book();
				book.setBookID(result.getInt("book_id"));
				book.setBookName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setLanguage(result.getString("language"));
				book.setYearIssue(result.getInt("year_of_issue"));
				book.setPublishingHouse(result.getString("publishing_house"));
				book.setDescription(result.getString("description"));
				book.setNumberOfPages(result.getInt("number_of_pages"));
				book.setPrice(result.getBigDecimal("price"));
				book.setImpression(result.getString("own_impression"));
				book.setAccount_fk(result.getString("account_fk"));
				book.setCategory_fk(result.getInt("category_fk"));
				book.setCondition_fk(result.getInt("condition_fk"));
				bookList.add(book);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}

}
