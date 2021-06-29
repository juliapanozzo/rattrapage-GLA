package com.glproject.UniForum.dao;

import java.util.List;

public interface DiscussionThreadDao {

	
	/**
	 * 
	 * @return all discussions in the database
	 */
	List<DiscussionThread> getDiscussions();

	DiscussionThread getDiscussion(Long ID);
	
	// recover the discussion that begins with the question
	List<DiscussionThread> getDiscussion(Message Question); 
	
	// add a discussion in a thematic
	DiscussionThread addDiscussionTo(long thematicId, DiscussionThread discussion); 
	
	// update the discussion
	DiscussionThread updateDiscussion(DiscussionThread discussion);
	
	// to delete a discussion from a thematic
	Thematic deleteDiscussionTo(Long thematicId, DiscussionThread discussion);


}
