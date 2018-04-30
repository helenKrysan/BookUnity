package ua.bookUnity.dao;

import java.util.List;

import ua.bookUnity.model.Condition;




public interface ConditionDAO {
	Condition save(String conditionName);
	Condition update(Integer conditionID,String conditionName);
	List<Condition> getAll();
	Condition getOneByID(Integer conditionID);
	Condition getOneByName(String conditionName);
	void delete(Integer conditionID);
}
