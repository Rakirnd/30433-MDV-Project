package dataAccess.hibernate;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dataAccess.dao.UserDataDAI;
import model.user.User;
import model.userData.UserData;
import util.HibernateUtil;

@SuppressWarnings("unchecked")
public class UserDataDA implements UserDataDAI{
	
	public int insert(UserData u) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		//start transaction
		session.beginTransaction();
		
		//Save the Model object
		session.save(u);
		
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session factory, otherwise program won't end
		session.close();
		
		return u.getId();
		
	}
	
	public void update(int id, UserData u) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<User> query = session.createQuery("update UserData set userID= :uid, firstName= :fn, lastName= :ln, balance= :balance where id= :id");
		query.setParameter("uid", u.getUserID());
		query.setParameter("fn", u.getFirstName());
		query.setParameter("ln", u.getLastName());
		query.setParameter("balance", u.getBalance());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}
	
	
	public List<UserData> findAll(){
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<UserData> query = session.createQuery("from UserData");
		List<UserData> userList = query.getResultList();
		
		session.close();
		
		return userList;
		
	}
	
	public UserData findById(int id) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<UserData> query = session.createQuery("from UserData where id= :id");
		query.setParameter("id", id);
		UserData ud = (UserData) query.getSingleResult();

		session.close();
		
		return ud;
		
	}


	public UserData findByUserId(int uid) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		UserData user = null;
		
		TypedQuery<UserData> query = session.createQuery("from UserData where userID= :uid");
		query.setParameter("uid", uid);
		
		try {
			
			user = (UserData) query.getSingleResult();
			
		}catch(NoResultException e) {
			
			session.close();
			return null;
			
		}
	
		session.close();
		return user;
		
	}

	public void updateBalance(int id, int balance) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		
		TypedQuery<User> query = session.createQuery("update UserData set balance= :balance where id= :id");
		query.setParameter("balance", balance);
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
