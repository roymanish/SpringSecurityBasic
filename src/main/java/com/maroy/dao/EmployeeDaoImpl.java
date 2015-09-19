/**
 * 
 */
package com.maroy.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maroy.entity.EmployeeEntity;

/**
 * @author MaRoy
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
    private SessionFactory sessionFactory;
    
    
	
	@Override
	public void addEmployee(EmployeeEntity employee) {
		
		getSessionFactory().getCurrentSession().save(employee);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> getAllEmployees() {
		
		return getSessionFactory().getCurrentSession().createQuery("from EmployeeEntity").list();
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		
		EmployeeEntity employee = (EmployeeEntity) getSessionFactory().getCurrentSession().load(
				EmployeeEntity.class, employeeId);
        if (null != employee) {
        	getSessionFactory().getCurrentSession().delete(employee);
        }
	}

	public SessionFactory getSessionFactory() {
		
		if(sessionFactory == null){
			
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
