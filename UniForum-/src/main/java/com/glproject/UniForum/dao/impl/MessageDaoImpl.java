package com.glproject.UniForum.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.glproject.UniForum.dao.DiscussionThread;
import com.glproject.UniForum.dao.Message;
import com.glproject.UniForum.dao.MessageDao;
import com.glproject.UniForum.dao.User;

public class MessageDaoImpl implements MessageDao {

	private PersistenceManagerFactory pmf;
	

	public MessageDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessages() {
		List<Message> messages = null;
		List<Message> detached = new ArrayList<Message>();

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query q = pm.newQuery(Message.class);
			messages = (List<Message>) q.execute();
			detached = (List<Message>) pm.detachCopyAll(messages);

			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessages(User u, String s, int v) {
		List<Message> messages = null;
		List<Message> detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query q = pm.newQuery(Message.class);
			q.declareParameters("String mapName");
			q.setFilter("name == mapName");
			
			//messages = (List<Message>) q.execute(u);
			//messages = (List<Message>) q.execute(s);
			messages = (List<Message>) q.execute(u, s, v);
			messages.toString();

			detached = (List<Message>) pm.detachCopyAll(messages);
			
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessages(User u) {
		List<Message> messages = null;
		List<Message> detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query q = pm.newQuery(Message.class);
			q.declareParameters("String mapName");
			q.setFilter("name == mapName");
			
			messages = (List<Message>) q.execute(u);
			messages.toString();

			detached = (List<Message>) pm.detachCopyAll(messages);
			
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
	public Message getMessage(Long ID) {
		Message message = null;
		Message detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			message = pm.getObjectById(Message.class, ID);
			message.toString();
			detached = pm.detachCopy(message);
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
	public Message addMessageTo(Long discussionId, Message message) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		//Message detached = new Message();
		Message detached = new Message();
		
		try {
			tx.begin();
			//message = pm.makePersistent(message);
			
			DiscussionThread discussionPersistent = pm.getObjectById(DiscussionThread.class, discussionId);
			
			discussionPersistent.addAnswer(message);
				
			detached = pm.detachCopy(message);
			
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
	public Message updateMessage(Message message) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Message detached = null;
		
		try {
			tx.begin();
			Message messagePersistent = pm.getObjectById(Message.class, message.getID());
			messagePersistent.setScript(message.getScript());
			messagePersistent.setWriter(message.getWriter());
			messagePersistent.setVotesNumber(message.getVotesNumber());
			messagePersistent.setDate(message.getDate());
			
			messagePersistent.toString();
			
			detached = pm.detachCopy(messagePersistent);
								
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
	public DiscussionThread deleteMessageTo(Long discussionId, Message message) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		DiscussionThread detached = null;

		try {
			tx.begin();
			
			DiscussionThread discussion = pm.getObjectById(DiscussionThread.class, discussionId);
			discussion.removeAnswer(message);
			detached = pm.detachCopy(discussion);
			
			/*for (Place place : map.getPlaces()) {
				place = pm.getObjectById(Place.class,place.getID());
				pm.deletePersistent(place);
			}*/
			
			message = pm.getObjectById(Message.class, message.getID());
			
			pm.deletePersistent(message);
			
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
	public Message addVote(Long messageId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Message detached = null;

		try {
			tx.begin();
			
			Message message= pm.getObjectById(Message.class, messageId);
			message.setVotesNumber(message.getVotesNumber() + 1);
			
			detached = pm.detachCopy(message);

			message = pm.getObjectById(Message.class, message.getID());

			
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
