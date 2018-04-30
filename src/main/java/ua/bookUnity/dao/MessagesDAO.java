package ua.bookUnity.dao;

import java.util.List;

import ua.bookUnity.model.Messages;

public interface MessagesDAO {

	Messages save(String content,String loginFrom_fk,String loginTo_fk);
	List<Messages> getAllMesagesOfTwoPeople(String loginFrom_fk,String loginTo_fk);
}
