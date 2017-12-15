package com.acebook.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acebook.entities.WallPost;

@Repository
public class WallPostDaoHibernate implements WallPostDao {

	private final Logger log = Logger.getRootLogger();
	
	@Autowired
	private SessionFactory sf;
	
	@Override
	@Transactional
	public WallPost save(WallPost wp) {
		 sf.getCurrentSession().save(wp);
		 return wp;
	}

	@Override
	@Transactional
	public WallPost get(int id) {
		return (WallPost) sf.getCurrentSession().get(WallPost.class, id);
	}

}
