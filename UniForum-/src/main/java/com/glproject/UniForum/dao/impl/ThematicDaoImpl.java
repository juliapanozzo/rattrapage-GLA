package com.glproject.UniForum.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.glproject.UniForum.dao.DiscussionThread;
import com.glproject.UniForum.dao.Thematic;
import com.glproject.UniForum.dao.ThematicDao;


public class ThematicDaoImpl implements ThematicDao {
	
	private PersistenceManagerFactory pmf;

	public ThematicDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Thematic> getThematics() {
		List<Thematic> subjects = null;
		List<Thematic> detached = new ArrayList<Thematic>();

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query q = pm.newQuery(Thematic.class);
			subjects = (List<Thematic>) q.execute();
			subjects.toString();
			detached = (List<Thematic>) pm.detachCopyAll(subjects);

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
	public Thematic getThematic(Long ID) {
		Thematic subject = null;
		Thematic detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			subject = pm.getObjectById(Thematic.class, ID);
			
			subject.toString();
			
			
			detached = pm.detachCopy(subject);
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
	public List<Thematic> getThematic(DiscussionThread discu) {
		List<Thematic> subjects = null;
		List<Thematic> detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query q = pm.newQuery(Thematic.class);
			q.declareParameters("DiscussionThread discussion");
			
			q.setFilter("discu == discussion");
			
			subjects = (List<Thematic>) q.execute(discu);
			detached = (List<Thematic>) pm.detachCopyAll(subjects);
				
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}
/*
	@Override
	public Thematic addThematic(Thematic subject) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Thematic detached = new Thematic();
		
		try {
			tx.begin();
			subject = pm.makePersistent(subject);
			
			detached = pm.detachCopy(subject);
			
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}*/

	@Override
	public Thematic updateThematic(Thematic subject) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Thematic detached = null;
		
		try {
			tx.begin();
			Thematic thematicPersistent = pm.getObjectById(Thematic.class, subject.getId());
			thematicPersistent.setTitle(subject.getTitle());
			thematicPersistent.setDiscussions(null);
			subject.getDiscussions().forEach(discussion->thematicPersistent.addDiscussion(pm.getObjectById(DiscussionThread.class, discussion.getID())));
			
			thematicPersistent.toString();
			
			detached = pm.detachCopy(thematicPersistent);
								
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return detached;
	}
	/*
	@Override
	public void deleteThematic(Thematic subject) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			
			pm.deletePersistent(pm.getObjectById(Thematic.class, subject.getId()));
			
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}*/

}
