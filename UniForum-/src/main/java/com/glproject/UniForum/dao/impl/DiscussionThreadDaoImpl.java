package com.glproject.UniForum.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.glproject.UniForum.dao.DiscussionThread;
import com.glproject.UniForum.dao.DiscussionThreadDao;
import com.glproject.UniForum.dao.Message;
import com.glproject.UniForum.dao.Thematic;


public class DiscussionThreadDaoImpl implements DiscussionThreadDao {

	private PersistenceManagerFactory pmf;

	public DiscussionThreadDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}
	
	@Override
	public DiscussionThread addDiscussionTo(long thematicId, DiscussionThread discussion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		DiscussionThread detached = null;
		
		try {
			tx.begin();
			Thematic thematicPersistent = pm.getObjectById(Thematic.class, thematicId);
			
			discussion.setThematicId(thematicId);
			
			thematicPersistent.addDiscussion(discussion);
			
			detached = pm.detachCopy(discussion);
			
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	
	@Override
	public DiscussionThread updateDiscussion(DiscussionThread discussion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		DiscussionThread detached = new DiscussionThread();
		
		try {
			tx.begin();
			DiscussionThread discussionPersistent = pm.getObjectById(DiscussionThread.class, discussion.getID());
			discussionPersistent.setSubject(discussion.getSubject());
			discussionPersistent.setQuestion(discussion.getQuestion());
			discussionPersistent.setAnswerMessage(discussion.getAnswerMessage());
			discussionPersistent.setCreator(discussion.getCreator());

			
			discussionPersistent.toString();
			
			detached = pm.detachCopy(discussionPersistent);
								
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return detached;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DiscussionThread> getDiscussions() {
		List<DiscussionThread> discussion = null;
		List<DiscussionThread> detached = new ArrayList<DiscussionThread>();

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query q = pm.newQuery(DiscussionThread.class);
			discussion = (List<DiscussionThread>) q.execute();
			detached = (List<DiscussionThread>) pm.detachCopyAll(discussion);

			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}


	@Override
	public DiscussionThread getDiscussion(Long ID) {
		DiscussionThread discussion = null;
		DiscussionThread detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			discussion = pm.getObjectById(DiscussionThread.class, ID);
			discussion.toString();
			detached = pm.detachCopy(discussion);
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DiscussionThread> getDiscussion(Message question) {
		List<DiscussionThread> discussions = null;
		List<DiscussionThread> detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query q = pm.newQuery(DiscussionThread.class);
			q.declareParameters("String placeName");
			q.setFilter("name == placeName");
			
			discussions = (List<DiscussionThread>) q.execute(question);
			discussions.toString();

			detached = (List<DiscussionThread>) pm.detachCopyAll(discussions);
			
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}


	@Override
	public Thematic deleteDiscussionTo(Long thematicId, DiscussionThread discussion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Thematic detached = null;

		try {
			tx.begin();
			
			Thematic thematic = pm.getObjectById(Thematic.class, thematicId);
			thematic.removeDiscussion(discussion);
			
			detached = pm.detachCopy(thematic);
			
			discussion = pm.getObjectById(DiscussionThread.class, discussion.getID());
			
			pm.deletePersistent(discussion);
			
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return detached; 

	}


}
