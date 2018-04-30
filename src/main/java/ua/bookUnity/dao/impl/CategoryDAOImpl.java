package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ua.bookUnity.dao.CategoryDAO;
import ua.bookUnity.model.Category;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class CategoryDAOImpl implements CategoryDAO {


	public Category save(String categoryName) {
		Category category = null;
		CategoryDAO categoryDAO =new CategoryDAOImpl();
		
		if(categoryDAO.getOneByName(categoryName)==null) {
			
			String sql ="INSERT INTO category (category_name) VALUES (?) RETURNING category_id ";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setString(1, categoryName);
				statement.execute();
				ResultSet result = statement.getResultSet();
				if(result.next()) {
					category = new Category(result.getInt("category_id"),categoryName);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return category;
	}


	public Category update(Integer categoryID, String categoryName) {
		Category category = null;
		CategoryDAO categoryDAO =new CategoryDAOImpl();
		if(categoryDAO.getOneByID(categoryID)!=null) {
			
			String sql ="UPDATE category SET category_name=? WHERE category_id=?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
				statement.setString(1, categoryName);
				statement.setInt(2, categoryID);
				
				if(statement.executeUpdate()==1) {
					category = new Category(categoryID,categoryName);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return category;
	}


	public List<Category> getAll() {

		List<Category> categoryList = new LinkedList<Category>();
		
		String sql = "SELECT * FROM category";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Category category = new Category(result.getInt("category_id"), result.getString("category_name"));
				categoryList.add(category);				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return categoryList;
	}


	public Category getOneByID(Integer categoryID) {
		Category category = null;
			
		String sql ="SELECT * FROM category WHERE category_id=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, categoryID);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				category = new Category(categoryID, result.getString("category_name"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return category;
	}


	public Category getOneByName(String categoryName) {
		Category category = null;
		
		String sql ="SELECT * FROM category WHERE category_name=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, categoryName);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				category = new Category(result.getInt("category_id"), categoryName);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return category;
		
	}


	public void delete(Integer categoryID) {
		CategoryDAO categoryDAO =new CategoryDAOImpl();
		if(categoryDAO.getOneByID(categoryID)!=null) {
			
			String sql ="DELETE FROM category WHERE category_id =?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setInt(1, categoryID);
				statement.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
