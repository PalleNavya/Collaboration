package com.niit.collaboration.model;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="c_chat")
@Component
public class Chat {

	@Id
	private String chatid;
	private String userid;
	private String Friend_id;
	private String forum_id;
	private Date chatdate;
	private String status;
	
	public String getChatid() {
		return chatid;
	}
	public void setChatid(String chatid) {
		this.chatid = chatid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFriend_id() {
		return Friend_id;
	}
	public void setFriend_id(String friend_id) {
		Friend_id = friend_id;
	}
	public String getForum_id() {
		return forum_id;
	}
	public void setForum_id(String forum_id) {
		this.forum_id = forum_id;
	}
	
	public Date getChatdate() {
		return chatdate;
	}
	public void setChatdate(Date chatdate) {
		this.chatdate = chatdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	


}
