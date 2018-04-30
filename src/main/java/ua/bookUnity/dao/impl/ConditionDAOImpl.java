package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.bookUnity.dao.ConditionDAO;
import ua.bookUnity.model.Condition;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class ConditionDAOImpl implements ConditionDAO{
	
	public Condition save(String conditionName){
		Condition condition = null;
		ConditionDAO conditionDAO =new ConditionDAOImpl();
		
		if(conditionDAO.getOneByName(conditionName)==null) {
			
			String sql ="INSERT INTO condition (condition_name) VALUES (?) RETURNING condition_id ";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setString(1, conditionName);
				statement.execute();
				ResultSet result = statement.getResultSet();
				if(result.next()) {
					condition = new Condition(result.getInt("condition_id"),conditionName);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return condition;
	}


	public Condition update(Integer conditionID,String conditionName){
		Condition condition = null;
		ConditionDAO conditionDAO =new ConditionDAOImpl();
		
		if(conditionDAO.getOneByID(conditionID)!=null) {
			
			String sql ="UPDATE condition SET condition_name=? WHERE condition_id=?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
			
				statement.setString(1, conditionName);
				statement.setInt(2, conditionID);
				
				if(statement.executeUpdate()==1) {
					condition = new Condition(conditionID,conditionName);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return condition;
	}


	public List<Condition> getAll() {

		List<Condition> conditionList = new LinkedList<Condition>();
		
		String sql = "SELECT * FROM condition";
		
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Condition condition = new Condition(result.getInt("condition_id"), result.getString("condition_name"));
				conditionList.add(condition);				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return conditionList;
	}


	public Condition getOneByID(Integer conditionID) {
		Condition condition = null;
			
		String sql ="SELECT * FROM condition WHERE condition_id=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, conditionID);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				condition = new Condition(conditionID, result.getString("condition_name"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return condition;
	}


	public Condition getOneByName(String conditionName) {
		Condition condition = null;
		
		String sql ="SELECT * FROM condition WHERE condition_name=?";
			
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, conditionName);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				condition = new Condition(result.getInt("condition_id"), conditionName);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return condition;
		
	}


	public void delete (Integer conditionID){
		
		ConditionDAO conditionDAO =new ConditionDAOImpl();
		
		if(conditionDAO.getOneByID(conditionID)!=null) {
			
			String sql ="DELETE FROM condition WHERE condition_id =?";
			
			try(Connection connection = ConnectionToDB.getConnectionToDB();
				PreparedStatement statement = connection.prepareStatement(sql);){
				
				statement.setInt(1, conditionID);
				statement.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
