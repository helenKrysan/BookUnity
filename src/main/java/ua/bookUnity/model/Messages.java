package ua.bookUnity.model;

import java.time.LocalDateTime;



public class Messages {
	
	

	private Integer messageID;
	private String content;
	private LocalDateTime createAt;
	private String loginFrom_fk;
	private String loginTo_fk;
	
	public Messages() {}
	
	public Messages(Integer messageID,String content,LocalDateTime createAt,String loginFrom_fk,String loginTo_fk) {
		this.messageID=messageID;
		this.content=content;
		this.createAt=createAt;
		this.loginFrom_fk=loginFrom_fk;
		this.loginTo_fk=loginTo_fk;
	}
	
	public String toString() {
		return "Create at: "+this.createAt+"\nFrom: "+this.loginFrom_fk+"\nTo: "+this.loginTo_fk;
	}
	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getLoginFrom_fk() {
		return loginFrom_fk;
	}

	public void setLoginFrom_fk(String loginFrom_fk) {
		this.loginFrom_fk = loginFrom_fk;
	}

	public String getLoginTo_fk() {
		return loginTo_fk;
	}

	public void setLoginTo_fk(String loginTo_fk) {
		this.loginTo_fk = loginTo_fk;
	}
}
