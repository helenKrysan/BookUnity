package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.bookUnity.dao.AccountDAO;
import ua.bookUnity.model.Account;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class AccountDAOImpl implements AccountDAO{

	public Account save(String login,String password,String gender,String firstName,String secondName,String thirdName,Integer age,String phoneNumber,String city,String region,String country) {
		
		Account account = null;	
		AccountDAO accountImpl= new AccountDAOImpl();
		
		if(accountImpl.getOneByLogin(login, password)==null) {
			
			String sql = "INSERT INTO account (login,password,gender,first_name,second_name,third_name,age,phone_number,city,region,country) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
			try (Connection connection = ConnectionToDB.getConnectionToDB();
					 PreparedStatement statement = connection.prepareStatement(sql);){
					
					statement.setString(1, login);
					statement.setString(2, password);
					statement.setString(3, gender);
					statement.setString(4, firstName);
					statement.setString(5, secondName);
					statement.setString(6, thirdName);
					statement.setInt(7, age);
					statement.setString(8, phoneNumber);
					statement.setString(9, city);
					statement.setString(10, region);
					statement.setString(11, country);
					
					if(statement.executeUpdate()==1) {
						account = new Account(login,password,gender,firstName,secondName,thirdName,age,phoneNumber,city,region,country);
					}
						
				} catch (DAOException e) {	
					e.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
		}
		
		return account;
	}

	public Account update(String login,String password,String gender,String firstName,String secondName,String thirdName,Integer age,String phoneNumber,String city,String region,String country) {
		
		Account account = null;	
		AccountDAO accountImpl= new AccountDAOImpl();
		
		if(accountImpl.getOneByLogin(login, password)!=null) {
			
			String sql = "UPDATE account SET  password=?, gender=?, first_name=?, second_name=?, third_name=?, age=?, phone_number=?, city=?, region=?, country=? WHERE login=?";
			
			try (Connection connection = ConnectionToDB.getConnectionToDB();
					 PreparedStatement statement = connection.prepareStatement(sql);){
					
					statement.setString(1, password);
					statement.setString(2, gender);
					statement.setString(3, firstName);
					statement.setString(4, secondName);
					statement.setString(5, thirdName);
					statement.setInt(6, age);
					statement.setString(7, phoneNumber);
					statement.setString(8, city);
					statement.setString(9, region);
					statement.setString(10, country);
					statement.setString(11, login);
					
					if(statement.executeUpdate()==1) {
						account = new Account(login,password,gender,firstName,secondName,thirdName,age,phoneNumber,city,region,country);
					}
						
				} catch (DAOException e) {	
					e.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
		}
		
		return account;
		
	}

	public Account getOneByLogin(String login, String password) {
		
		Account account = null;
		String sql ="SELECT * FROM account WHERE login = ? AND password = ?";
		
		try (Connection connection = ConnectionToDB.getConnectionToDB();
			 PreparedStatement statement = connection.prepareStatement(sql);){
				
			statement.setString(1, login);
			statement.setString(2, password);
				
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				account = new Account();
				account.setLogin(result.getString("login"));
				account.setPassword(result.getString("password"));
				account.setGender(result.getString("gender"));
				account.setFirstName(result.getString("first_name"));
				account.setSecondName(result.getString("second_name"));
				account.setThirdName(result.getString("third_name"));
				account.setAge(result.getInt("age"));
				account.setPhoneNumber(result.getString("phone_number"));
				account.setCity(result.getString("city"));
				account.setRegion(result.getString("region"));
				account.setCountry(result.getString("country"));
			}
			
					
		} catch (DAOException e) {	
				e.printStackTrace();
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
		
		return account;
		
	}

	public void delete(String login) {
		
			String sql = "DELETE FROM account WHERE login=?";
			
			try (Connection connection = ConnectionToDB.getConnectionToDB();
					 PreparedStatement statement = connection.prepareStatement(sql);){
					
				statement.setString(1, login);
				
				
				statement.executeUpdate();
				
				} catch (DAOException e) {	
					e.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
	}

}
