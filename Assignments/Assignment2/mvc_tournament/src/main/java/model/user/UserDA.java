package model.user;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

@SuppressWarnings("unchecked")
public class UserDA implements UserDAI{
	
	public int insert(User u) {
		
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
	
	public void update(int id, User u) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<User> query = session.createQuery("update User set email= :email, password= :password, isAdmin= :isAdmin where id= :id");
		query.setParameter("email", u.getEmail());
		query.setParameter("password", u.getPassword());
		query.setParameter("isAdmin", u.getIsAdmin());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}
	
	public List<User> findAll(){
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<User> query = session.createQuery("from User");
		List<User> userList = query.getResultList();
		
		session.close();
		
		return userList;
		
	}
	
	public User findAccountByEmail(String email) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		User user = null;
		
		TypedQuery<User> query = session.createQuery("from User where email= :email");
		query.setParameter("email", email);
		
		try {
			
			user = (User) query.getSingleResult();
			
		}catch(NoResultException e) {
			
			session.close();
			return null;
			
		}
		

		session.close();
		
		return user;
		
	}
	
	public User findById(int id) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<User> query = session.createQuery("from User where id= :id");
		query.setParameter("id", id);
		User u = (User) query.getSingleResult();

		session.close();
		
		return u;
		
	}


	public void delete(int id) {
		
		
		
	}
	
	

}
