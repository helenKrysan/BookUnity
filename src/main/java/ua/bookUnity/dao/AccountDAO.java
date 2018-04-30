package ua.bookUnity.dao;

import ua.bookUnity.model.Account;

public interface AccountDAO {

	Account save(String login,String password,String gender,String firstName,String secondName,String thirdName,Integer age,String phoneNumber,String city,String region,String country);
	Account update(String login,String password,String gender,String firstName,String secondName,String thirdName,Integer age,String phoneNumber,String city,String region,String country);
	Account getOneByLogin(String login, String password);
	void delete(String login);
	
	
}
