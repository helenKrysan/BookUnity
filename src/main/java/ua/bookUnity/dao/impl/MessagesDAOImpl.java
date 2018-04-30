package ua.bookUnity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import ua.bookUnity.dao.MessagesDAO;
import ua.bookUnity.model.Messages;
import ua.bookUnity.service.ConnectionToDB;
import ua.bookUnity.service.DAOException;

public class MessagesDAOImpl implements MessagesDAO {
	
	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // String formatDateTime = time.format(formatter);
	
	public Messages save(String content,String loginFrom_fk, String loginTo_fk) {
		
		Messages message = null;
		String sql ="INSERT INTO messages (content,create_at,from_id,to_id) VALUES (?,?,?,?) RETURNING message_id";
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
			
			LocalDateTime localDateTime = LocalDateTime.now();
			statement.setString(1, content);
			statement.setTimestamp(2, Timestamp.valueOf(localDateTime));
			statement.setString(3, loginFrom_fk);
			statement.setString(4, loginTo_fk);
			
			statement.execute();
			ResultSet result = statement.getResultSet();
			if(result.next()) {
				message = new Messages(result.getInt("message_id"),content,localDateTime,loginFrom_fk,loginTo_fk);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return message;
	}


	public List<Messages> getAllMesagesOfTwoPeople(String loginFrom_fk, String loginTo_fk) {
		List<Messages> messagesList = new LinkedList<Messages>();
		String sql ="SELECT * FROM messages WHERE from_id=? AND to_id=?";
		try(Connection connection = ConnectionToDB.getConnectionToDB();
			PreparedStatement statement = connection.prepareStatement(sql);){
		
			statement.setString(1, loginFrom_fk);
			statement.setString(2, loginTo_fk);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Messages message = new Messages(result.getInt("message_id"),result.getString("content"),result.getTimestamp("create_at").toLocalDateTime(),loginFrom_fk,loginTo_fk);
				messagesList.add(message);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return messagesList;
	}

	
	
}
