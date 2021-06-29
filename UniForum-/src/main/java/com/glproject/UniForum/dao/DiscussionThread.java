package com.glproject.UniForum.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class represents the discussions threads that are composed by a question and answers
 *
 */
@PersistenceCapable
public class DiscussionThread {


	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Long id = null;

	Message question; // the question created by the user
	List<Message> answerMessage; // the answers from the other users
	Thematic subject;

	//long creatorId;
	User creator; // the user who asks the question
	
	long thematicId;
	
	public DiscussionThread() {
		super();
		this.question.votesNumber = 0;
	}
	
	
	public DiscussionThread(User creator) {
		super();
		this.question.votesNumber = 0;
		this.creator = creator;
		this.answerMessage = new ArrayList<Message>();
	}
	
	public DiscussionThread(User creator, Thematic s) {
		this(creator);
		this.question.votesNumber = 0;
		this.subject = s;
	}
	

	public Long getID() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}


	public Message getQuestion() {
		return question;
	}


	public void setQuestion(Message question) {
		this.question = question;
	}


	public List<Message> getAnswerMessage() {
		return answerMessage;
	}


	public void setAnswerMessage(List<Message> answerMessage) {
		this.answerMessage = answerMessage;
	}


	public Thematic getSubject() {
		return subject;
	}


	public void setSubject(Thematic subject) {
		this.subject = subject;
	}


	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
	public long getThematicId() {
		return thematicId;
	}


	public void setThematicId(long thematicId) {
		this.thematicId = thematicId;
	}


	/*
	 * add an answer to discussion
	 */
	public void addAnswer(Message m) {
		this.answerMessage.add(m);
	}
	
	/*
	 * remove an answer to discussion
	 */
	public void removeAnswer(Message m) {
		this.answerMessage.remove(m);
	}


	@Override
	public String toString() {
		return "Discussion [id=" + id + ", thematic=" + subject + ", question=" + question + 
				", answers=" + answerMessage + ", creator=" + creator + "]";
	}

	@Override
	public boolean equals(Object obj) {
		DiscussionThread discussion = (DiscussionThread) obj;
		return getID().equals(discussion.getID());
	}


}
