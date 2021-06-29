package com.glproject.UniForum.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * this class represents the different subjects of discussion threads
 * 
 * @author julia
 */
@PersistenceCapable
public class Thematic {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Long Id = null;
	
	String title; // the title of the thematic
	List<DiscussionThread> discussions; // the discussions in this subject
	
	public Thematic() {
		super();
	}
	
	public Thematic (String title) {
		super();
		this.title = title;
		this.discussions = new ArrayList<>();
		
	}
	
	public Thematic (String title, List<DiscussionThread> discussions) {
		this(title);
		this.discussions = new ArrayList<DiscussionThread>();
		this.discussions = discussions;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DiscussionThread> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<DiscussionThread> discussions) {
		this.discussions = discussions;
	}
	
	/*
	 * this method removes a discussion from a thematic
	 */
	public void removeDiscussion (DiscussionThread discussion) {
		this.discussions.remove(discussion);
	}
	
	/*
	 * this method adds a discussion to a thematic
	 */
	public void addDiscussion (DiscussionThread discu) {
		this.discussions.add(discu);
	}
	
	@Override
	public String toString() {
		return "Thematic [ID=" + Id + ", title=" + title + ", discussions=" + discussions +"]";

	}

	@Override
	public boolean equals(Object obj) {
		Thematic subject = (Thematic) obj;
		return getId().equals(subject.getId());
	}


}
