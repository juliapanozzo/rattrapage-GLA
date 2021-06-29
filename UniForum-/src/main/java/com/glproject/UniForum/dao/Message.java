package com.glproject.UniForum.dao;


import java.time.LocalDate;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class represents the messages sent by users
 * 
 * 
 * @author julia
 *
 */
@PersistenceCapable
public class Message {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Long id = null;
	
	User writer; // the writer of the message
	String script;  // script of the message
	
	int votesNumber; // the votes number
	LocalDate date; // date of the message
	
	long discussionId;
	

	public long getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(long discussionId) {
		this.discussionId = discussionId;
	}

	public Message () {
		super();
		this.writer = new User();
		//this.date = new LocalDate<>();
	}
	
	public Message (User u, String s, int v) {
		this();
		this.writer = u;
		this.script = s;
		this.votesNumber = v;
	}
	

	public Long getID() {
		return id;
	}

	public User getWriter() {
		return this.writer;
	}
	
	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public int getVotesNumber() {
		return votesNumber;
	}

	public void setVotesNumber(int votesNumber) {
		this.votesNumber = votesNumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

		
	@Override
	public String toString() {
		return "Message [id=" +id+ ", writer=" +writer+ ", script=" +script+ 
				", votes number=" +votesNumber+ ", date=" +date+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		Message message = (Message) obj;
		return getID().equals(message.getID());
	}

}



