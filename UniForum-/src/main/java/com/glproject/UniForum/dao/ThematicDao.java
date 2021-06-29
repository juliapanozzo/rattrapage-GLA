package com.glproject.UniForum.dao;

import java.util.List;


/**
 * 
 * 
 * @author julia
 *
 */
public interface ThematicDao {
	
	/**
	 * 
	 * @return all the thematics of the database
	 */
	List<Thematic> getThematics();
	
	/**
	 * 
	 * @param ID
	 * @return the only thematic who has this specific key
	 */
	Thematic getThematic(Long ID);
	
	/**
	 *  
	 * @param discu
	 * @return the thematic that contains the discussion
	 */
	List<Thematic> getThematic(DiscussionThread discu); 
	
	/**
	 * add a thematic to the database
	 * @param subject
	 * @return
	 */
	/*Thematic addThematic(Thematic subject); */
	
	// update the thematic
	Thematic updateThematic(Thematic subject);
	
	// delete a thematic from the database
	/*void deleteThematic(Thematic subject);*/


}
