package com.glproject.UniForum.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.glproject.UniForum.dao.impl.DiscussionThreadDaoImpl;
import com.glproject.UniForum.dao.impl.MessageDaoImpl;
import com.glproject.UniForum.dao.impl.ThematicDaoImpl;
import com.glproject.UniForum.dao.impl.UserDaoImpl;

public class DAO {
	
	
	static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Glproject");
	
	public static PersistenceManagerFactory getPmf() {
		return pmf;
	}
	
	
	public static UserDao getUserDao() {
		return new UserDaoImpl(pmf);
	}

	public static ThematicDao getThematicDao() {
		return new ThematicDaoImpl(pmf);
	}

	public static DiscussionThreadDao getDiscussionThreadDao() {
		return new DiscussionThreadDaoImpl(pmf);
	}
	
	public static MessageDao getMessageDao() {
		return new MessageDaoImpl(pmf);
	}
}
