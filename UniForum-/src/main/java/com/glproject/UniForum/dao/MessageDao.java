package com.glproject.UniForum.dao;

import java.util.List;

public interface MessageDao {

	
	/**
	 * Get all messages of the database
	 * 
	 * @return
	 */
	List<Message> getMessages();
	
	//List<Message> getMessages(User u, String s, int v);
	
	List<Message> getMessages(User u);
	
	/**
	 * get the message by its unique id
	 * @param ID
	 * @return
	 */
	Message getMessage(Long ID);
	
	Message addMessageTo(Long discussionId, Message message);
	
	Message updateMessage(Message message);

	Message addVote(Long messageId);
	
	DiscussionThread deleteMessageTo(Long discussionId, Message message);
	
}
