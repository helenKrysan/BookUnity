package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.bookUnity.dao.WishListDAO;
import ua.bookUnity.model.WishList;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class WishListDAOImpl implements WishListDAO{

	
	public WishList save(String login_fk, Integer book_fk) {
		WishList wishList = null;
		WishListDAO wsDAO = new WishListDAOImpl();
		if(wsDAO.getOne(login_fk, book_fk)==null) {
			
			String sql = "INSERT INTO wish_list (account_fk,book_fk) VALUES (?,?)";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setString(1, login_fk);
				statement.setInt(2, book_fk);
				
				if(statement.executeUpdate()==1) {
					wishList = new WishList(login_fk, book_fk);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
		return wishList;
	}


	public List<WishList> getBookWS(Integer book_fk) {
		List<WishList> wishList = new LinkedList<WishList>();
		String sql = "SELECT * FROM wish_list WHERE book_fk=?";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, book_fk);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				WishList wish = new WishList(result.getString("account_fk"), result.getInt("book_fk"));
				wishList.add(wish);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return wishList;
	}

	
	public List<WishList> getAccountWS(String login_fk) {
		List<WishList> wishList = new LinkedList<WishList>();
		String sql = "SELECT * FROM wish_list WHERE account_fk=?";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, login_fk);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				WishList wish = new WishList(result.getString("account_fk"), result.getInt("book_fk"));
				wishList.add(wish);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return wishList;
	}

	
	public List<WishList> getAll() {
		List<WishList> wishList = new LinkedList<WishList>();
		String sql = "SELECT * FROM wish_list";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				WishList wish = new WishList(result.getString("account_fk"), result.getInt("book_fk"));
				wishList.add(wish);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return wishList;
		
	}

	
	public void delete(String login_fk, Integer book_fk) {
		
		
		WishListDAO wsDAO = new WishListDAOImpl();
		if(wsDAO.getOne(login_fk, book_fk)!=null) {
			
			String sql = "DELETE FROM wish_list WHERE account_fk=? AND book_fk=?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setString(1, login_fk);
				statement.setInt(2, book_fk);
				
				statement.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}


	
	public WishList getOne(String login_fk, Integer book_fk) {
		WishList wishList = null;
		String sql = "SELECT * FROM wish_list WHERE account_fk=? AND book_fk=?";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, login_fk);
			statement.setInt(2, book_fk);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				wishList = new WishList(login_fk, book_fk);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return wishList;
	}

	
}
