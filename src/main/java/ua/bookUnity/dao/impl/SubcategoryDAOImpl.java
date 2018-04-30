package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.bookUnity.dao.SubcategoryDAO;
import ua.bookUnity.model.Subcategory;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class SubcategoryDAOImpl implements SubcategoryDAO {

	
	public Subcategory save(String subcategoryName, String description, Integer genre_fk) {
		
		Subcategory subcategory = null;
		SubcategoryDAO subcategoryDAO =new SubcategoryDAOImpl();
		
		if(subcategoryDAO.getOneByName(subcategoryName)==null) {
			
			String sql ="INSERT INTO subcategory (subcategory_name,description,genre_fk) VALUES (?,?,?) RETURNING subcategory_id";
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				statement.setString(1, subcategoryName);
				statement.setString(2, description);
				statement.setInt(3, genre_fk);
				statement.execute();
				ResultSet result = statement.getResultSet();
				if(result.next()) {
					subcategory = new Subcategory(result.getInt("subcategory_id"),subcategoryName,description,genre_fk);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
		
		return subcategory;
	}

	
	public Subcategory update(Integer subcategoryID, String subcategoryName, String description, Integer genre_fk) {
		Subcategory subcategory = null;
		SubcategoryDAO subcategoryDAO =new SubcategoryDAOImpl();
		
		if(subcategoryDAO.getOneById(subcategoryID)!=null) {
			
			String sql ="UPDATE subcategory SET subcategory_name=?,description=?,genre_fk=? WHERE subcategory_id=?";
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				statement.setString(1, subcategoryName);
				statement.setString(2, description);
				statement.setInt(3, genre_fk);
				statement.setInt(4, subcategoryID);

				if(statement.executeUpdate()==1) {
					subcategory = new Subcategory(subcategoryID,subcategoryName,description,genre_fk);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
		
		return subcategory;
		
	}

	
	public List<Subcategory> getAll() {
	
		List<Subcategory> subcategoryList = new LinkedList<Subcategory>();
		String sql ="SELECT * FROM subcategory";
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Subcategory subcategory = new Subcategory(result.getInt("subcategory_id"),result.getString("subcategory_name"),result.getString("description"),result.getInt("genre_fk"));
				subcategoryList.add(subcategory);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return subcategoryList;
	}


	public List<Subcategory> getAllSubcOfGenre(Integer genre_fk) {
		
		List<Subcategory> subcategoryList = new LinkedList<Subcategory>();
		String sql ="SELECT * FROM subcategory WHERE genre_fk=?";
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, genre_fk);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Subcategory subcategory = new Subcategory(result.getInt("subcategory_id"),result.getString("subcategory_name"),result.getString("description"),genre_fk);
				subcategoryList.add(subcategory);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return subcategoryList;
	}


	public Subcategory getOneById(Integer subcategoryID) {
		Subcategory subcategory = null;
		String sql ="SELECT * FROM subcategory WHERE subcategory_id=?";
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, subcategoryID);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				 subcategory = new Subcategory(result.getInt("subcategory_id"),result.getString("subcategory_name"),result.getString("description"),result.getInt("genre_fk"));	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return subcategory;
	}

	
	public Subcategory getOneByName(String subcategoryName) {
		Subcategory subcategory = null;
		String sql ="SELECT * FROM subcategory WHERE subcategory_name=?";
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, subcategoryName);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				 subcategory = new Subcategory(result.getInt("subcategory_id"),result.getString("subcategory_name"),result.getString("description"),result.getInt("genre_fk"));	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return subcategory;
	}


	public void delete(Integer subcategoryID) {
		SubcategoryDAO subcategoryDAO =new SubcategoryDAOImpl();
		
		if(subcategoryDAO.getOneById(subcategoryID)!=null) {
			
			String sql ="DELETE FROM subcategory  WHERE subcategory_id=?";
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setInt(1, subcategoryID);
				statement.executeUpdate();
			
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public List<String> getAllNameSubcOfGenre(Integer genre_fk) {
		List<String> subcategoryList = new LinkedList<String>();
		String sql ="SELECT * FROM subcategory WHERE genre_fk=?";
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, genre_fk);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String subcategory = result.getString("subcategory_name");
				subcategoryList.add(subcategory);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return subcategoryList;
	}

}
