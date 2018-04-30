package ua.bookUnity.dao;

import java.util.List;

import ua.bookUnity.model.WishList;

public interface WishListDAO {

	WishList save(String login_fk,Integer book_fk);
	List<WishList> getBookWS(Integer book_fk);
	List<WishList> getAccountWS(String login_fk);
	List<WishList> getAll();
	WishList getOne(String login_fk,Integer book_fk);
	void delete(String login_fk,Integer book_fk);
}
