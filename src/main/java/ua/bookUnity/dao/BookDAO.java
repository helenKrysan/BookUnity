package ua.bookUnity.dao;

import java.util.List;

import ua.bookUnity.model.Book;

public interface BookDAO {

	Book save(String bookName,String author,String language,Integer yearIssue,String publishingHouse,String description,Integer numberOfPages,String price,String impression,String account_fk,Integer condition_fk,Integer category_fk);
	Book update(Integer bookID,String bookName,String author,String language,Integer yearIssue,String publishingHouse,String description,Integer numberOfPages,String price,String impression,String account_fk,Integer condition_fk,Integer category_fk);
	Book getOneByID(Integer bookID);
	List<Book> getAllByAccount(String account_fk);
	List<Book> getAllByCategory(Integer category_fk);
	List<Book> getAllByGenre(String genre);
	List<Book> getAllByAuthor(String author);
	List<Book> getAllByYear(Integer year);
	void delete(Integer bookID);
}
