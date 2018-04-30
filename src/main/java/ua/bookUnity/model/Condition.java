package ua.bookUnity.model;

public class Condition {
	
	private Integer conditionID;
	private String conditionName;
	
	public Condition() {}
	
	public Condition(Integer conditionID,String conditionName) {
		this.conditionID=conditionID;
		this.conditionName=conditionName;
	}
	
	public String toString() {
		return "ID "+this.conditionID+"\nName "+this.conditionName;
	}
	
	
	public Integer getConditionID() {
		return conditionID;
	}

	public void setConditionID(Integer conditionID) {
		this.conditionID = conditionID;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	
}
