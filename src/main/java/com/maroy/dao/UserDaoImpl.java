/**
 * 
 */
package com.maroy.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maroy.entity.UserEntity;

/**
 * @author MaRoy
 *
 */
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addUser(UserEntity user){
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public UserEntity loadUserByUserName(String userName){
		UserEntity user = (UserEntity)this.sessionFactory.getCurrentSession().createQuery("from UserEntity where userName='"+userName+"'").list().get(0);
		return user;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
